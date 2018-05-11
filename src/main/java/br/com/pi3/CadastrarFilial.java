package br.com.pi3;

import br.com.pi3.Classes.Filial;
import br.com.pi3.DAO.DAOFilial;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "CadastrarFilial", urlPatterns = {"/CadastrarFilial"})
public class CadastrarFilial extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("txtNome");
        String cnpj = request.getParameter("txtCnpj");
        String endereco = request.getParameter("txtEndereco");
        String complemento = request.getParameter("txtComplemento");
        String cep = request.getParameter("txtCep");
        String numero = request.getParameter("txtNumero");
        String bairro = request.getParameter("txtBairro");
        String cidade = request.getParameter("txtCidade");
        String estado = request.getParameter("estado");

        Filial filial = new Filial(nome, cnpj, endereco, numero, complemento,
                bairro, cep, cidade, estado);

        try {
            DAOFilial.incluir(filial);
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarFilial.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastrarFilial.class.getName()).log(Level.SEVERE, null, ex);
        }
        response.sendRedirect("ListagemFiliais");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
