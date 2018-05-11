
package br.com.pi3;

import br.com.pi3.Classes.CategoriaGame;
import br.com.pi3.Classes.Game;
import br.com.pi3.Classes.ServicoGame;
import br.com.pi3.DAO.DAOFilial;
import br.com.pi3.DAO.DAOGame;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EditarGame", urlPatterns = {"/EditarGame"})
public class EditarGame extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idTemp = request.getParameter("id");
        int id = Integer.parseInt(idTemp);
        request.setAttribute("obterGame", DAOGame.obterGame(id));
        request.setAttribute("categorias", DAOGame.listarCat(id));
        RequestDispatcher rd = request.getRequestDispatcher("EditarGame.jsp");
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
        
        Game game = new Game();
        ArrayList<CategoriaGame> categorias = new ArrayList<>();
        game.setCategorias(categorias);

        String nome = request.getParameter("txtNome");
        
        if (request.getParameter("catAcao") != null) {
            CategoriaGame cat = new CategoriaGame();
            cat.setNome("Ação");
            cat.setId(1);
            categorias.add(cat);
        }
        if (request.getParameter("catSimulacao") != null) {
            CategoriaGame cat = new CategoriaGame();
            cat.setNome("Simulação");
            cat.setId(2);
            categorias.add(cat);
        }
        if (request.getParameter("catRPG") != null) {
            CategoriaGame cat = new CategoriaGame();
            cat.setNome("RPG");
            cat.setId(3);
            categorias.add(cat);
        }
        if (request.getParameter("catEsportes") != null) {
            CategoriaGame cat = new CategoriaGame();
            cat.setNome("Esportes");
            cat.setId(4);
            categorias.add(cat);
        }
        if (request.getParameter("catAventura") != null) {
            CategoriaGame cat = new CategoriaGame();
            cat.setNome("Aventura");
            cat.setId(5);
            categorias.add(cat);
        }
        if (request.getParameter("catEstrategia") != null) {
            CategoriaGame cat = new CategoriaGame();
            cat.setNome("Estratégia");
            cat.setId(6);
            categorias.add(cat);
        }
        

        String desenv = request.getParameter("txtDesenvolvedora");
        String indicClass = request.getParameter("txtClassificacao");
        String plataforma = request.getParameter("Plataforma");
        String compra = request.getParameter("txtPrecoCompra");
        double precoCompra = Double.parseDouble(compra);
        String venda = request.getParameter("txtPrecoVenda");
        double precoVenda = Double.parseDouble(venda);
        String qtd = request.getParameter("txtQuantidade");
        int quantidade = Integer.parseInt(qtd);

        game.setNome(nome);
        game.setDesenvolvedora(desenv);
        game.setPlataforma(plataforma);
        game.setClassIndicativa(indicClass);
        game.setPrecoCompra(precoCompra);
        game.setPrecoVenda(precoVenda);
        game.setQuantidade(quantidade);
        game.setId(id);

        try {
            DAOGame.atualizarGame(game);
        } catch (Exception ex) {

        }

        response.sendRedirect("/pi3-1.0-SNAPSHOT/ListagemGames");
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
