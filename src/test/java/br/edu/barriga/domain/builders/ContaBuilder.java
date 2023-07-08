package br.edu.barriga.domain.builders;

import br.edu.barriga.domain.Usuario;
import br.edu.barriga.domain.Conta;

import static br.edu.barriga.domain.builders.UsuarioBuilder.umUsuario;

public class ContaBuilder {
    private Long id;
    private String nome;
    private Usuario usuario;

    private ContaBuilder(){}

    public static ContaBuilder umaConta() {
        ContaBuilder builder = new ContaBuilder();
        inicializarDadosPadroes(builder);
        return builder;
    }

    public static void inicializarDadosPadroes(ContaBuilder builder) {
        builder.id = 1L;
        builder.nome = "Conta valida";
        builder.usuario = umUsuario().agora();
    }

    public ContaBuilder comId(Long id) {
        this.id = id;
        return this;
    }

    public ContaBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ContaBuilder comUsuario(Usuario usuario) {
        this.usuario = usuario;
        return this;
    }

    public Conta agora() {
        return new Conta(id, nome, usuario);
    }
}

