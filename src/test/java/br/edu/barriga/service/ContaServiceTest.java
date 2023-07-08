package br.edu.barriga.service;

import br.edu.barriga.domain.Conta;
import br.edu.barriga.domain.builders.ContaBuilder;
import br.edu.barriga.domain.exceptions.ValidationException;
import br.edu.barriga.service.external.ContaEvent;
import br.edu.barriga.service.repository.ContaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;

import static br.edu.barriga.domain.builders.ContaBuilder.umaConta;
import static br.edu.barriga.service.external.ContaEvent.EventType.CREATED;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ContaServiceTest {

    @InjectMocks
    private ContaService service;
    @Mock
    private ContaRepository repository;
    @Mock
    private ContaEvent event;

    @Test
    public void deveSalvarPrimeiraContaComSucesso() {
        Conta contaToSave = umaConta().comId(null).agora();

        when(repository.salvar(contaToSave))
                .thenReturn(umaConta().agora());
        doNothing().when(event).dispatch(umaConta().agora(), CREATED);

        Conta savedConta = service.salvar(contaToSave);

        assertNotNull(savedConta.id());
    }

    @Test
    public void deveSalvarSegundaContaComSucesso() {
        Conta contaToSave = umaConta().comId(null).agora();

        when(repository.obterContasPorUsuario(contaToSave.usuario().id()))
                .thenReturn(Collections.singletonList(umaConta().comNome("Outra Conta").agora()));
        when(repository.salvar(contaToSave)).thenReturn(umaConta().agora());

        Conta savedConta = service.salvar(contaToSave);
        assertNotNull(savedConta.id());
    }

    @Test
    public void deveRejeitarContaRepetida() {
        Conta contaToSave = umaConta().comId(null).agora();

        when(repository.obterContasPorUsuario(contaToSave.usuario().id()))
                .thenReturn(Collections.singletonList(umaConta().agora()));

        String message = assertThrows(ValidationException.class, () ->
                service.salvar(contaToSave)).getMessage();

        assertEquals("Usuario ja possui uma conta com este nome", message);
    }
}
