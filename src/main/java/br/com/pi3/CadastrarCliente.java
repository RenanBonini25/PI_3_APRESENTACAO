/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.pi3;

import br.com.pi3.Classes.Cliente;
import br.com.pi3.DAO.DAOCliente;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(name = "CadastrarCliente", urlPatterns = {"/CadastrarCliente"})
public class CadastrarCliente extends HttpServlet {

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
            
            Cliente cliente = new Cliente (nome,cpf,sexo,data,estadoCivil,endereco,complemento,
            numero,bairro,cep,cidade,estado);
            
        try {
            DAOCliente.incluir(cliente);
        } catch (SQLException ex) {
            Logger.getLogger(CadastrarCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CadastrarCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            response.sendRedirect("/pi3-1.0-SNAPSHOT/ListagemClientes");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
