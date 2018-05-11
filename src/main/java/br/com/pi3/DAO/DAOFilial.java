package br.com.pi3.DAO;

import br.com.pi3.Classes.Filial;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOFilial {

    private static Connection obterConexao() throws ClassNotFoundException, SQLException {
        //1A) Registrar drive JDBC
        Class.forName("com.mysql.jdbc.Driver");

        //1B) Abrir conexão com o BD
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/pi3", "root", "");
    }

    public static long incluir(Filial filial) throws SQLException, ClassNotFoundException {
        String query = "INSERT INTO filial (NOME, CNPJ, ENDERECO,"
                + "COMPLEMENTO, NUMERO, BAIRRO, CEP, CIDADE, ESTADO)"
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) ";
        long idFilial = 0;

        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setString(1, filial.getNome());
                stmt.setString(2, filial.getCnpj());
                stmt.setString(3, filial.getEndereco());
                stmt.setString(4, filial.getComplemento());
                stmt.setString(5, filial.getNumero());
                stmt.setString(6, filial.getBairro());
                stmt.setString(7, filial.getCep());
                stmt.setString(8, filial.getCidade());
                stmt.setString(9, filial.getEstado());
                stmt.executeUpdate();

                try (ResultSet chave = stmt.getGeneratedKeys()) {
                    if (chave.next()) {
                        idFilial = chave.getLong(1);
                    }
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
        return idFilial;
    }

    public static ArrayList<Filial> listar() {
        ArrayList<Filial> listaFiliais = new ArrayList<>();
        String query = "SELECT * FROM filial";
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                try (ResultSet resultados = stmt.executeQuery()) {
                    while (resultados.next()) {
                        Filial filial = new Filial();
                        filial.setId(resultados.getInt("ID"));
                        filial.setNome(resultados.getString("NOME"));
                        filial.setCnpj(resultados.getString("CNPJ"));
                        filial.setEndereco(resultados.getString("ENDERECO"));
                        filial.setComplemento(resultados.getString("COMPLEMENTO"));
                        filial.setNumero(resultados.getString("NUMERO"));
                        filial.setBairro(resultados.getString("BAIRRO"));
                        filial.setCep(resultados.getString("CEP"));
                        filial.setCidade(resultados.getString("CIDADE"));
                        filial.setEstado(resultados.getString("ESTADO"));
                        listaFiliais.add(filial);
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
        return listaFiliais;
    }

    public static ArrayList<Filial> obterFilial(int id) {
        ArrayList<Filial> filiais = new ArrayList<>();
        String query = "SELECT * FROM filial WHERE ID = ?";
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id);
                try (ResultSet resultados = stmt.executeQuery()) {
                    while (resultados.next()) {
                        Filial filial = new Filial();
                        filial.setId(resultados.getInt("ID"));
                        filial.setNome(resultados.getString("NOME"));
                        filial.setCnpj(resultados.getString("CNPJ"));
                        filial.setEndereco(resultados.getString("ENDERECO"));
                        filial.setComplemento(resultados.getString("COMPLEMENTO"));
                        filial.setNumero(resultados.getString("NUMERO"));
                        filial.setBairro(resultados.getString("BAIRRO"));
                        filial.setCep(resultados.getString("CEP"));
                        filial.setCidade(resultados.getString("CIDADE"));
                        filial.setEstado(resultados.getString("ESTADO"));
                        filiais.add(filial);
                        return filiais;
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

    public static void atualizarFilial(Filial filial) {
        String query = "UPDATE filial SET NOME=?, CNPJ=?, "
                + "ENDERECO=?, COMPLEMENTO=?, NUMERO=?, BAIRRO=?, CEP=?, CIDADE=?, ESTADO=? WHERE ID=?";
        try (Connection conn = obterConexao()) {
            conn.setAutoCommit(false);
            try (PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setString(1, filial.getNome());
                stmt.setString(2, filial.getCnpj());
                stmt.setString(3, filial.getEndereco());
                stmt.setString(4, filial.getComplemento());
                stmt.setString(5, filial.getNumero());
                stmt.setString(6, filial.getBairro());
                stmt.setString(7, filial.getCep());
                stmt.setString(8, filial.getCidade());
                stmt.setString(9, filial.getEstado());
                stmt.setInt(10, filial.getId());

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

    public static void excluirFilial(int id) {
        String query = "DELETE FROM filial WHERE ID = ?";
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
