package br.com.pi3.DAO;

import br.com.pi3.Classes.CategoriaGame;
import br.com.pi3.Classes.Game;
import br.com.pi3.Classes.ServicoGame;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOGame {

    private static Connection obterConexao() throws ClassNotFoundException, SQLException {
        //1A) Registrar drive JDBC
        Class.forName("com.mysql.jdbc.Driver");

        //1B) Abrir conexão com o BD
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/pi3", "root", "");
    }

    public static int incluir(Game game) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO game (NOME, QUANTIDADE, PRECOCOMPRA,"
                + "PRECOVENDA, PLATAFORMA, DESENVOLVEDORA, CLASSIFICACAO)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?) ";
        int idGame = 0;

        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, game.getNome());
                stmt.setInt(2, game.getQuantidade());
                stmt.setDouble(3, game.getPrecoCompra());
                stmt.setDouble(4, game.getPrecoVenda());
                stmt.setString(5, game.getPlataforma());
                stmt.setString(6, game.getDesenvolvedora());
                stmt.setString(7, game.getClassIndicativa());
                stmt.executeUpdate();

                try (ResultSet chave = stmt.getGeneratedKeys()) {
                    if (chave.next()) {
                        idGame = chave.getInt(1);
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
        return idGame;
    }

    public static void incluirGameCat(CategoriaGame categoria) throws ClassNotFoundException, SQLException {
        String queryCat = "INSERT INTO game_cat (ID_GAME, ID_CAT, NOME_CAT) VALUES (?, ?, ?)";

        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt2 = conn.prepareStatement(queryCat)) {
                stmt2.setLong(1, categoria.getIdGame());
                stmt2.setInt(2, categoria.getId());
                stmt2.setString(3, categoria.getNome());
                stmt2.executeUpdate();
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }

        }
    }

    public static ArrayList<Game> listar() {
        ArrayList<Game> listaGames = new ArrayList<>();
        String query = "SELECT * FROM game";
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                try (ResultSet resultados = stmt.executeQuery()) {
                    while (resultados.next()) {
                        Game game = new Game();
                        game.setId(resultados.getInt("ID"));
                        game.setNome(resultados.getString("NOME"));
                        game.setQuantidade(resultados.getInt("QUANTIDADE"));
                        game.setPrecoCompra(resultados.getDouble("PRECOCOMPRA"));
                        game.setPrecoVenda(resultados.getDouble("PRECOVENDA"));
                        game.setPlataforma(resultados.getString("PLATAFORMA"));
                        game.setDesenvolvedora(resultados.getString("DESENVOLVEDORA"));
                        listaGames.add(game);
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaGames;
    }

    public static ArrayList<CategoriaGame> listarCat(int id) {
        ArrayList<CategoriaGame> categorias = new ArrayList<>();
        String query = "SELECT * FROM game_cat WHERE ID_GAME = ?";
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                try (ResultSet resultados = stmt.executeQuery()) {
                    while (resultados.next()) {
                        CategoriaGame cat = new CategoriaGame();
                        cat.setId(resultados.getInt("ID_CAT"));
                        cat.setNome(resultados.getString("NOME_CAT"));
                        cat.setIdGame(resultados.getInt("ID_GAME"));
                        categorias.add(cat);
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categorias;
    }

    public static ArrayList<Game> obterGame(int id) {
        ArrayList<Game> listaGames = new ArrayList<>();
        String query = "SELECT * FROM game WHERE ID = ?";
        String query2 = "SELECT distinct GAME_CAT.ID_CAT FROM GAME_CAT\n"
                + "INNER JOIN GAME ON ? = GAME_CAT.ID_GAME";
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                try (ResultSet resultados = stmt.executeQuery()) {
                    while (resultados.next()) {
                        Game game = new Game();
                        game.setId(resultados.getInt("ID"));
                        game.setNome(resultados.getString("NOME"));
                        game.setQuantidade(resultados.getInt("QUANTIDADE"));
                        game.setPrecoCompra(resultados.getDouble("PRECOCOMPRA"));
                        game.setPrecoVenda(resultados.getDouble("PRECOVENDA"));
                        game.setClassIndicativa(resultados.getString("CLASSIFICACAO"));
                        game.setPlataforma(resultados.getString("PLATAFORMA"));
                        game.setDesenvolvedora(resultados.getString("DESENVOLVEDORA"));
                        ArrayList<CategoriaGame> categorias = new ArrayList<>();
                        try (PreparedStatement stmt2 = conn.prepareStatement(query2)) {
                            stmt2.setInt(1, id);
                            try (ResultSet resultados2 = stmt2.executeQuery()) {
                                while (resultados2.next()) {
                                    CategoriaGame categoria = new CategoriaGame();
                                    categoria.setId(resultados2.getInt("ID_CAT"));
                                    categoria.setIdGame(game.getId());
                                    categorias.add(categoria);
                                }
                                game.setCategorias(categorias);
                            }
                        }
                        listaGames.add(game);
                        return listaGames;
                    }
                }

                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static void atualizarGame(Game game) {
        String query = "UPDATE game SET NOME=?, QUANTIDADE=?, "
                + "PRECOCOMPRA=?, PRECOVENDA=?, PLATAFORMA=?, DESENVOLVEDORA=?, CLASSIFICACAO=? WHERE ID=?";
        String query2 = "DELETE FROM GAME_CAT WHERE ID_GAME = ?";
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, game.getNome());
                stmt.setInt(2, game.getQuantidade());
                stmt.setDouble(3, game.getPrecoCompra());
                stmt.setDouble(4, game.getPrecoVenda());
                stmt.setString(5, game.getPlataforma());
                stmt.setString(6, game.getDesenvolvedora());
                stmt.setString(7, game.getClassIndicativa());
                stmt.setInt(8, game.getId());
                stmt.executeUpdate();

                try (PreparedStatement stmt2 = conn.prepareStatement(query2)) {
                    stmt2.setInt(1, game.getId());
                    stmt2.executeUpdate();
                }

                conn.commit();
                ServicoGame.atualizarCategoria(game);
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void excluirGame(int id) {
        String query = "DELETE FROM game WHERE ID = ?";
        try (Connection conn = obterConexao()) {
            try (PreparedStatement stmtCategoria = conn.prepareStatement(query)) {
                stmtCategoria.setInt(1, id);
                stmtCategoria.executeUpdate();
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DAOCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
