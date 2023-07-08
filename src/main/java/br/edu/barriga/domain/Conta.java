package br.edu.barriga.domain;

import br.edu.barriga.domain.exceptions.ValidationException;

import java.util.Objects;

public class Conta {

    private Long id;
    private String nome;
    private Usuario usuario;

    public Conta(Long id, String nome, Usuario usuario) {
        if(nome == null) throw new ValidationException("Nome e obrigatorio");
        if(usuario == null) throw new ValidationException("Usuario e obrigatorio");

        this.id = id;
        this.nome = nome;
        this.usuario = usuario;
    }

    public long id() {
        return id;
    }

    public String nome() {
        return nome;
    }

    public Usuario usuario() {
        return usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conta conta = (Conta) o;
        return Objects.equals(id, conta.id) && Objects.equals(nome, conta.nome) && Objects.equals(usuario, conta.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, usuario);
    }
}
