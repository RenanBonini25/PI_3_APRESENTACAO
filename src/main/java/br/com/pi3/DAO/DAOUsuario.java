
package br.com.pi3.DAO;

import br.com.pi3.Classes.Permissao;
import br.com.pi3.Classes.Usuario;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOUsuario {
    
    private static Connection obterConexao() throws ClassNotFoundException, SQLException {
        //1A) Registrar drive JDBC
        Class.forName("com.mysql.jdbc.Driver");

        //1B) Abrir conexão com o BD
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/pi3", "root", "");
    }
    
    public static long incluir(Usuario usuario) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO usuario (NOME, CPF, USERNAME,"
                + "SENHA, SETOR, FILIAL, PERMISSAO)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?) ";
        long idUsuario = 0;

        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getCpf());
                stmt.setString(3, usuario.getUserName());
                stmt.setString(4, usuario.getSenha());
                stmt.setString(5, usuario.getSetor());
                stmt.setString(6, usuario.getFilial());
                stmt.setString(7, usuario.getPermissoes().get(0).getNome());
                stmt.executeUpdate();

                try (ResultSet chave = stmt.getGeneratedKeys()) {
                    if (chave.next()) {
                        idUsuario = chave.getLong(1);
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
        return idUsuario;
    }
    
    public static Usuario procurarUsuario(String username) throws SQLException, ClassNotFoundException {
        String query = "SELECT * FROM usuario WHERE username = ?";
        Usuario usuario = new Usuario();
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, username);
                try (ResultSet resultados = stmt.executeQuery()) {
                    while (resultados.next()) {
                        usuario.setId(resultados.getInt("ID"));
                        usuario.setNome(resultados.getString("NOME"));
                        usuario.setCpf(resultados.getString("CPF"));
                        usuario.setUserName(resultados.getString("USERNAME"));
                        usuario.setSenha(resultados.getString("SENHA"));
                        usuario.setSetor(resultados.getString("SETOR"));
                        usuario.setFilial(resultados.getString("FILIAL"));
                        Permissao permissao = new Permissao();
                        permissao.setNome(resultados.getString("PERMISSAO"));
                        List<Permissao> permissoes = new ArrayList<>();
                        permissoes.add(permissao);
                        usuario.setPermissoes(permissoes);
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
        return usuario;
    }
    
    public static ArrayList<Usuario> listar() {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        String query = "SELECT * FROM usuario";
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                try (ResultSet resultados = stmt.executeQuery()) {
                    while (resultados.next()) {
                        Usuario usuario = new Usuario();
                        usuario.setId(resultados.getInt("ID"));
                        usuario.setNome(resultados.getString("NOME"));
                        usuario.setCpf(resultados.getString("CPF"));
                        usuario.setUserName(resultados.getString("USERNAME"));
                        usuario.setSenha(resultados.getString("SENHA"));
                        usuario.setSetor(resultados.getString("SETOR"));
                        usuario.setFilial(resultados.getString("FILIAL"));
                        Permissao permissao = new Permissao();
                        permissao.setNome(resultados.getString("PERMISSAO"));
                        List<Permissao> permissoes = new ArrayList<>();
                        permissoes.add(permissao);
                        usuario.setPermissoes(permissoes);
                        listaUsuarios.add(usuario);
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
        return listaUsuarios;
    }
    
    public static ArrayList<Usuario> obterUsuario(int id) {
        ArrayList<Usuario> listaUsuarios = new ArrayList<>();
        String query = "SELECT * FROM usuario WHERE ID = ?";
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                try (ResultSet resultados = stmt.executeQuery()) {
                    while (resultados.next()) {
                        Usuario usuario = new Usuario();
                        usuario.setId(resultados.getInt("ID"));
                        usuario.setNome(resultados.getString("NOME"));
                        usuario.setCpf(resultados.getString("CPF"));
                        usuario.setUserName(resultados.getString("USERNAME"));
                        usuario.setSenha(resultados.getString("SENHA"));
                        usuario.setSetor(resultados.getString("SETOR"));
                        usuario.setFilial(resultados.getString("FILIAL"));
                        Permissao permissao = new Permissao();
                        permissao.setNome(resultados.getString("PERMISSAO"));
                        List<Permissao> permissoes = new ArrayList<>();
                        permissoes.add(permissao);
                        usuario.setPermissoes(permissoes);
                        listaUsuarios.add(usuario);
                        return listaUsuarios;
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
    
    public static void atualizarUsuario(Usuario usuario) {
        String query = "UPDATE usuario SET NOME=?, CPF=?, "
                + "USERNAME=?, SENHA=?, SETOR=?, FILIAL=?, PERMISSAO=? WHERE ID=?";
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, usuario.getNome());
                stmt.setString(2, usuario.getCpf());
                stmt.setString(3, usuario.getUserName());
                stmt.setString(4, usuario.getSenha());
                stmt.setString(5, usuario.getSetor());
                stmt.setString(6, usuario.getFilial());
                stmt.setString(7, usuario.getPermissoes().get(0).getNome());
                stmt.setInt(8, usuario.getId());

                stmt.executeUpdate();
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
    }
    
    public static void excluirUsuario(int id) {
        String query = "DELETE FROM usuario WHERE ID = ?";
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
