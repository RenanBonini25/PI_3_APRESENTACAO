
package br.com.pi3;

import br.com.pi3.Classes.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession sessao = request.getSession();
        if (sessao.getAttribute("usuario") != null) {
            response.sendRedirect(request.getContextPath() + "/home.jsp");
            return;
        }
        request.getRequestDispatcher("login.jsp").forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        String senha = request.getParameter("senha");
        
        ServicoUsuario servico = new ServicoUsuario();
        Usuario usuario = servico.buscarPorUsername(username);
        
        if (senha.equals(usuario.getSenha())) {
            HttpSession sessao = request.getSession();
            sessao.setAttribute("usuario", usuario);
            response.sendRedirect(request.getContextPath() + "/home.jsp");
        } else {
            request.setAttribute("msgErro", "Usuário ou Senha inválido!");
            request.getRequestDispatcher("erroLogin.jsp").forward(request, response);
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
