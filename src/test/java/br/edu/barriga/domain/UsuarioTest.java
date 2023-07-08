package br.edu.barriga.domain;

import br.edu.barriga.domain.exceptions.ValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static br.edu.barriga.domain.builders.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.*;

public class UsuarioTest {

    @Test
    public void deveCriarUsuarioValido() {
        Usuario usuario = umUsuario().agora();
        assertAll("Usuario",
                () -> assertEquals(1L, usuario.id()),
                () -> assertEquals("Usuario Valido", usuario.nome()),
                () -> assertEquals("user@mail.com", usuario.email()),
                () -> assertEquals("123456", usuario.senha())
        );
    }

    @Test
    public void deveRejeitarUsarioSemNome() {
        ValidationException validationException = assertThrows(ValidationException.class, () -> {
            umUsuario().comNome(null).agora();
        });
        assertEquals("Nome e obrigatorio", validationException.getMessage());
    }

    @Test
    public void deveRejeitarUsarioSemEmail() {
        ValidationException validationException = assertThrows(ValidationException.class, () -> {
            umUsuario().comEmail(null).agora();
        });
        assertEquals("Email e obrigatorio", validationException.getMessage());
    }

    @Test
    public void deveRejeitarUsarioSemSenha() {
        ValidationException validationException = assertThrows(ValidationException.class, () -> {
            umUsuario().comSenha(null).agora();
        });
        assertEquals("Senha e obrigatoria", validationException.getMessage());
    }

    private static Stream<Arguments> usuarioData() {
        return Stream.of(
                Arguments.of(null, "breno@email.com", "123456", "Nome e obrigatorio"),
                Arguments.of("Breno", null, "123456", "Email e obrigatorio"),
                Arguments.of("Breno", "breno@email.com", null, "Senha e obrigatoria")
        );
    }

    @ParameterizedTest()
    @MethodSource("usuarioData")
    public void deveRejeitarUsuario(String nome, String email, String senha, String message) {
        ValidationException validationException = assertThrows(ValidationException.class, () -> {
            umUsuario().comNome(nome).comEmail(email).comSenha(senha).agora();
        });
        assertEquals(message, validationException.getMessage());
    }

    @ParameterizedTest()
    @CsvFileSource(files = "src/test/resources/CamposObrigatoriosUsuario.csv", nullValues = "null", useHeadersInDisplayName = true)
    public void deveRejeitarUsuarioUsandoCsv(String nome, String email, String senha, String message) {
        ValidationException validationException = assertThrows(ValidationException.class, () -> {
            umUsuario().comNome(nome).comEmail(email).comSenha(senha).agora();
        });
        assertEquals(message, validationException.getMessage());
    }
}
