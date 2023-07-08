package br.edu.barriga.service;

import br.edu.barriga.domain.Usuario;
import br.edu.barriga.domain.exceptions.ValidationException;
import br.edu.barriga.service.repository.UsuarioRepository;

import java.util.Optional;

public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario salvar(Usuario usuario) {
        repository.getUserByEmail(usuario.email()).ifPresent(user -> {
            throw new ValidationException(String.format("Usuario %s ja cadastrado!", usuario.email()));
        });
        return repository.salvar(usuario);
    }

    public Optional<Usuario> getUserByEmail(String email) {
        return repository.getUserByEmail(email);
    }
}
