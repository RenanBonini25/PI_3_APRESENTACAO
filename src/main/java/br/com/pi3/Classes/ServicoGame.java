package br.com.pi3.Classes;

import br.com.pi3.DAO.DAOGame;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServicoGame {

    public static void cadastrarGame(Game game, ArrayList<CategoriaGame> categorias) {
        try {
            int idGame = DAOGame.incluir(game);
            for (int i = 0; i < categorias.size(); i++) {
                categorias.get(i).setIdGame(idGame);
                DAOGame.incluirGameCat(categorias.get(i));
            }

        } catch (Exception ex) {
            
        }
    }
    
    public static void atualizarGame(Game game, ArrayList<CategoriaGame> categorias) {
        try {
            DAOGame.atualizarGame(game);
        } catch (Exception ex) {
            
        }
    }
    
    public static void atualizarCategoria(Game game) {
        ArrayList<CategoriaGame> categorias = game.getCategorias();
        for (int i = 0; i < categorias.size(); i++) {
            categorias.get(i).setIdGame(game.getId());
            try {
                DAOGame.incluirGameCat(categorias.get(i));
            } catch (ClassNotFoundException ex) {
            } catch (SQLException ex) {
            }
        }
    }
}
