
package br.com.pi3;

import br.com.pi3.Classes.Permissao;
import br.com.pi3.Classes.Usuario;
import br.com.pi3.DAO.DAOFilial;
import br.com.pi3.DAO.DAOUsuario;
import java.io.IOException;
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


@WebServlet(name = "EditarUsuario", urlPatterns = {"/EditarUsuario"})
public class EditarUsuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idTemp = request.getParameter("id");
        int id = Integer.parseInt(idTemp);
        request.setAttribute("obterUsuario", DAOUsuario.obterUsuario(id));
        request.setAttribute("listaFiliais", DAOFilial.listar());
        RequestDispatcher rd = request.getRequestDispatcher("EditarUsuario.jsp");
        try {
            rd.forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(EditarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String idTemp = request.getParameter("id");
        int id = Integer.parseInt(idTemp);
        String nome = request.getParameter("txtNome");
        String cpf = request.getParameter("txtCpf");
        String username = request.getParameter("txtUsername");
        String senha = request.getParameter("txtSenha");
        String setor = request.getParameter("Setor");
        String filial = request.getParameter("Filial");
        String permissaoNome = request.getParameter("Permissao");
        
        Permissao permissao = new Permissao();
        permissao.setNome(permissaoNome);
        Usuario usuario = new Usuario(nome, cpf, username, senha, setor, filial);
        List<Permissao> permissoes = new ArrayList<>();
        permissoes.add(permissao);
        usuario.setPermissoes(permissoes);
        usuario.setId(id);
        
        try {
        DAOUsuario.atualizarUsuario(usuario);
        } catch (Exception ex) {
            
        }
        
        response.sendRedirect("/pi3-1.0-SNAPSHOT/ListagemUsuarios");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
