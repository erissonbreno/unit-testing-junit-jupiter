package br.edu.barriga.service.repository;

import br.edu.barriga.domain.Conta;

import java.util.List;

public interface ContaRepository {
    Conta salvar(Conta conta);

    List<Conta> obterContasPorUsuario(Long usuarioId);
}
