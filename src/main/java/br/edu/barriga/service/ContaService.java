package br.edu.barriga.service;

import br.edu.barriga.domain.Conta;
import br.edu.barriga.domain.exceptions.ValidationException;
import br.edu.barriga.service.external.ContaEvent;
import br.edu.barriga.service.repository.ContaRepository;

import java.util.List;

import static br.edu.barriga.service.external.ContaEvent.EventType.CREATED;

public class ContaService {

    private ContaRepository repository;
    private ContaEvent event;

    public ContaService(ContaRepository repository, ContaEvent event) {
        this.repository = repository;
        this.event = event;
    }

    public Conta salvar(Conta conta) {
        List<Conta> contas = repository.obterContasPorUsuario(conta.usuario().id());
        contas.stream().forEach(contaExistente -> {
            if(conta.nome().equals(contaExistente.nome())) {
                throw new ValidationException("Usuario ja possui uma conta com este nome");
            }
        });
        Conta contaPersistida = repository.salvar(conta);
        event.dispatch(contaPersistida, CREATED);
        return contaPersistida;
    }

}