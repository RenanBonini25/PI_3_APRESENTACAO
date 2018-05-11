package br.com.pi3.Classes;

import java.util.List;

public class Usuario {

    private int id;
    private String nome;
    private String cpf;
    private String userName;
    private String senha;
    private String setor;
    private String filial;
    private List<Permissao> permissoes;

    public Usuario() {
    }

    public Usuario(String nome, String cpf, String username, String senha, String setor, String filial) {
        this.nome = nome;
        this.cpf = cpf;
        this.userName = username;
        this.senha = senha;
        this.setor = setor;
        this.filial = filial;
    }

    public boolean verificarPermissao(String nomePermissao) {
        for (Permissao p : getPermissoes()) {
            if (p.getNome().equals(nomePermissao)) {
                return true;
            }
        }
        return false;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getFilial() {
        return filial;
    }

    public void setFilial(String filial) {
        this.filial = filial;
    }

    public List<Permissao> getPermissoes() {
        return permissoes;
    }

    public void setPermissoes(List<Permissao> permissoes) {
        this.permissoes = permissoes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }


}
