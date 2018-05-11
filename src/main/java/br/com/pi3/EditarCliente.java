package br.com.pi3;

import br.com.pi3.Classes.Cliente;
import br.com.pi3.DAO.DAOCliente;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EditarCliente", urlPatterns = {"/EditarCliente"})
public class EditarCliente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) {
        String idTemp = request.getParameter("id");
        int id = Integer.parseInt(idTemp);
        request.setAttribute("obterCliente", DAOCliente.obterCliente(id));
        RequestDispatcher rd = request.getRequestDispatcher("EditarCliente.jsp");
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
        String sexo = request.getParameter("Sexo");
        String data = request.getParameter("dataNascimento");
        String estadoCivil = request.getParameter("EstadoCivil");
        String endereco = request.getParameter("txtEndereco");
        String complemento = request.getParameter("txtComplemento");
        String numero = request.getParameter("txtNumero");
        String bairro = request.getParameter("txtBairro");
        String cep = request.getParameter("txtCep");
        String cidade = request.getParameter("txtCidade");
        String estado = request.getParameter("estado");

        Cliente cliente = new Cliente(nome, cpf, sexo, data, estadoCivil, endereco, complemento, numero, bairro,
                cep, cidade, estado);
        cliente.setId(id);

        try {
        DAOCliente.atualizarCliente(cliente);
        } catch (Exception ex) {
            
        }
        
        response.sendRedirect("/pi3-1.0-SNAPSHOT/ListagemClientes");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
