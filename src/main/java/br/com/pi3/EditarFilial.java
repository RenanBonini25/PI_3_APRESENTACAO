package br.com.pi3;

import br.com.pi3.Classes.Filial;
import br.com.pi3.DAO.DAOFilial;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EditarFilial", urlPatterns = {"/EditarFilial"})
public class EditarFilial extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idTemp = request.getParameter("id");
        int id = Integer.parseInt(idTemp);
        request.setAttribute("obterFilial", DAOFilial.obterFilial(id));
        RequestDispatcher rd = request.getRequestDispatcher("EditarFilial.jsp");
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
        String cnpj = request.getParameter("txtCnpj");
        String endereco = request.getParameter("txtEndereco");
        String complemento = request.getParameter("txtComplemento");
        String numero = request.getParameter("txtNumero");
        String bairro = request.getParameter("txtBairro");
        String cep = request.getParameter("txtCep");
        String cidade = request.getParameter("txtCidade");
        String estado = request.getParameter("estado");

        Filial filial = new Filial(nome, cnpj, endereco, numero, complemento,
                bairro, cep, cidade, estado);
        filial.setId(id);

        DAOFilial.atualizarFilial(filial);

        response.sendRedirect("/pi3-1.0-SNAPSHOT/ListagemFiliais");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
