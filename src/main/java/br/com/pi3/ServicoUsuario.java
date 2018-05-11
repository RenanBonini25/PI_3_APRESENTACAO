
package br.com.pi3;

import br.com.pi3.Classes.Usuario;
import br.com.pi3.DAO.DAOUsuario;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicoUsuario {
    
    public static Usuario buscarPorUsername(String username) {
        Usuario usuario = new Usuario();
        try {
            usuario = DAOUsuario.procurarUsuario(username);
        } catch (SQLException ex) {
            Logger.getLogger(ServicoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ServicoUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
    
}
