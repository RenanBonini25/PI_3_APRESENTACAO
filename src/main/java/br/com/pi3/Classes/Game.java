package br.com.pi3.Classes;

import java.util.ArrayList;

public class Game extends Produto {
    
    private String plataforma;
    private String desenvolvedora;
    private String classIndicativa;
    private ArrayList<CategoriaGame> categorias;

    public Game() {
    }

    public Game(String nome, int quantidade, double precoCompra, double precoVenda, 
            String plataforma, String desenvolvedora, String classIndicativa) {
        super(nome, quantidade, precoCompra, precoVenda);
        this.plataforma = plataforma;
        this.desenvolvedora = desenvolvedora;
        this.classIndicativa = classIndicativa;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getDesenvolvedora() {
        return desenvolvedora;
    }

    public void setDesenvolvedora(String desenvolvedora) {
        this.desenvolvedora = desenvolvedora;
    }

    public String getClassIndicativa() {
        return classIndicativa;
    }

    public void setClassIndicativa(String classIndicativa) {
        this.classIndicativa = classIndicativa;
    }

    public ArrayList<CategoriaGame> getCategorias() {
        return categorias;
    }

    public void setCategorias(ArrayList<CategoriaGame> categorias) {
        this.categorias = categorias;
    }
    
}
