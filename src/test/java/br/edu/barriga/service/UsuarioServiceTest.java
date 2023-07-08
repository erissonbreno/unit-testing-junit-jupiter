package br.edu.barriga.service;

import br.edu.barriga.domain.Usuario;
import br.edu.barriga.domain.builders.UsuarioBuilder;
import br.edu.barriga.domain.exceptions.ValidationException;
import br.edu.barriga.infra.UsuarioDummyRepository;
import br.edu.barriga.service.repository.UsuarioRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static br.edu.barriga.domain.builders.UsuarioBuilder.umUsuario;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository repository;
    @InjectMocks
    UsuarioService service;

    @Test
    public void deveSalvarUsuarioComSucessoComDummyClass() {
        service = new UsuarioService(new UsuarioDummyRepository());

        Usuario user = umUsuario()
                .comId(null)
                .comEmail("outro@mail.com")
                .agora();
        Usuario saverUser = service.salvar(user);

        assertNotNull(saverUser.id());
    }

    @Test
    public void deveRetornarEmptyQuandoUsuarioInexistente() {

        Optional<Usuario> user = service.getUserByEmail("user@mail.com");
        assertTrue(user.isEmpty());
    }

    @Test
    public void deveRetornarUmUsuarioPorEmail() {
        when(repository.getUserByEmail("user@mail.com")).thenReturn(Optional.of(umUsuario().agora()));

        Optional<Usuario> user = service.getUserByEmail("user@mail.com");
        assertTrue(user.isPresent());

        verify(repository, times(1)).getUserByEmail("user@mail.com");
    }

    @Test
    public void deveSalvarUsuarioComSucesso() {
        Usuario userToSave = umUsuario().comId(null).agora();

//        when(repository.getUserByEmail(userToSave.email()))
//                .thenReturn(Optional.empty());
        when(repository.salvar(userToSave))
                .thenReturn(umUsuario().agora());

        Usuario savedUser = service.salvar(userToSave);
        assertNotNull(savedUser.id());

        verify(repository).getUserByEmail(userToSave.email());
        verify(repository).salvar(userToSave);
    }

    @Test
    public void deveRejeitarUsuarioExistente() {
        Usuario userToSave = umUsuario().comId(null).agora();
        when(repository.getUserByEmail(userToSave.email()))
                .thenReturn(Optional.of(umUsuario().agora()));

        ValidationException validationException = assertThrows(ValidationException.class, () ->
                service.salvar(userToSave));
        assertTrue(validationException.getMessage().endsWith("ja cadastrado!"));
    }
}
