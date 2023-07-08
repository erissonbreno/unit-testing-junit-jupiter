package br.edu.barriga.domain;

import br.edu.barriga.domain.exceptions.ValidationException;

import java.util.Objects;

public class Usuario {

    private final Long id;
    private final String nome;
    private final String email;
    private final String senha;

    public Usuario(Long id, String nome, String email, String senha) {
        if (nome == null) throw new ValidationException("Nome e obrigatorio");
        if (email == null) throw new ValidationException("Email e obrigatorio");
        if (senha == null) throw new ValidationException("Senha e obrigatoria");

        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public Long id() {
        return id;
    }

    public String nome() {
        return nome;
    }

    public String email() {
        return email;
    }

    public String senha() {
        return senha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return Objects.equals(id, usuario.id) && Objects.equals(nome, usuario.nome) && Objects.equals(email, usuario.email) && Objects.equals(senha, usuario.senha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome, email, senha);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}
