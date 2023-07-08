package br.edu.barriga.infra;

import br.edu.barriga.domain.Usuario;
import br.edu.barriga.domain.builders.UsuarioBuilder;
import br.edu.barriga.service.repository.UsuarioRepository;

import java.util.Optional;

import static br.edu.barriga.domain.builders.UsuarioBuilder.umUsuario;

public class UsuarioDummyRepository implements UsuarioRepository {
    @Override
    public Usuario salvar(Usuario usuario) {
        return umUsuario()
                .comNome(usuario.nome())
                .comEmail(usuario.email())
                .comSenha(usuario.senha())
                .agora();
    }

    @Override
    public Optional<Usuario> getUserByEmail(String email) {
        if ("user@mail.com".equals(email)) {
            return Optional.of(umUsuario()
                    .comEmail(email)
                    .agora());
        }
        return Optional.empty();
    }
}
