package br.edu.barriga.infra;

import br.edu.barriga.domain.Usuario;
import br.edu.barriga.domain.builders.UsuarioBuilder;
import br.edu.barriga.domain.exceptions.ValidationException;
import br.edu.barriga.service.UsuarioService;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@Tag("Infra")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserServiceComUserMemoryRepositoryTest {

    private static final UsuarioService service = new UsuarioService(new UsuarioMemoryRepository());

    @Test
    @Order(1)
    public void deveSalvarUsuarioValido() {
        Usuario user = service.salvar(UsuarioBuilder.umUsuario().comId(null).agora());

        assertNotNull(user.id());
//        assertEquals(2L, user.id());
    }

    @Test
    @Order(2)
    public void deveRejeitarUsuarioExistente() {
        ValidationException exception = assertThrows(ValidationException.class, () ->
                service.salvar(UsuarioBuilder.umUsuario().comId(null).agora())
        );

        assertEquals("Usuario user@mail.com ja cadastrado!", exception.getMessage());

    }
}
