package br.edu.barriga.domain;

import br.edu.barriga.domain.exceptions.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static br.edu.barriga.domain.builders.ContaBuilder.umaConta;
import static br.edu.barriga.domain.builders.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.*;

public class ContaTest {

    private static Stream<Arguments> dataProvider() {
        return Stream.of(
                Arguments.of(1L, null, umUsuario().agora(), "Nome e obrigatorio"),
                Arguments.of(1L, "Conta valida", null, "Usuario e obrigatorio")
        );
    }

    @Test
    public void deveCriarContaValida() {
        Conta conta = umaConta().agora();

        assertAll("Conta",
                () -> assertEquals(1L, conta.id()),
                () -> assertEquals("Conta valida", conta.nome()),
                () -> assertEquals(umUsuario().agora(), conta.usuario())
        );
    }

    @ParameterizedTest
    @MethodSource("dataProvider")
    public void deveRejeitarContaInvalida(Long id, String nome, Usuario usuario, String mensagem) {
        String errorMessage = assertThrows(ValidationException.class, () ->
                umaConta().comId(id).comNome(nome).comUsuario(usuario).agora()).getMessage();
        assertEquals(mensagem, errorMessage);
    }
}
