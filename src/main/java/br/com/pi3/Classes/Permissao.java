package br.com.pi3.Classes;

import java.io.Serializable;

public class Permissao implements Serializable {

    private String nome;

    public Permissao() {
    }

    public Permissao(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
