package br.com.pi3;

import br.com.pi3.Classes.Permissao;
import br.com.pi3.Classes.Usuario;
import br.com.pi3.DAO.DAOFilial;
import br.com.pi3.DAO.DAOUsuario;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CadastrarUsuario", urlPatterns = {"/CadastrarUsuario"})
public class CadastrarUsuario extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("listaFiliais", DAOFilial.listar());
        RequestDispatcher rd = request.getRequestDispatcher("CadastrarUsuario.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(EditarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String nome = request.getParameter("txtNome");
        String cpf = request.getParameter("txtCpf");
        String userName = request.getParameter("txtUsername");
        String senha = request.getParameter("txtSenha");
        String setor = request.getParameter("Setor");
        String filial = request.getParameter("Filial");
        String permissaoNome = request.getParameter("Permissao");
        Permissao permissao = new Permissao();
        permissao.setNome(permissaoNome);
        
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setCpf(cpf);
        usuario.setUserName(userName);
        usuario.setSenha(senha);
        usuario.setSetor(setor);
        usuario.setFilial(filial);
        List<Permissao> permissoes = new ArrayList<>();
        permissoes.add(permissao);
        usuario.setPermissoes(permissoes);
        
        try {
            DAOUsuario.incluir(usuario);
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastrarUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }

        
        response.sendRedirect("/pi3-1.0-SNAPSHOT/ListagemUsuarios");
        
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
