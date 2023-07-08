package br.edu.barriga.service.repository;

import br.edu.barriga.domain.Usuario;

import java.util.Optional;

public interface UsuarioRepository {

    Usuario salvar(Usuario usuario);

    Optional<Usuario> getUserByEmail(String email);
}
