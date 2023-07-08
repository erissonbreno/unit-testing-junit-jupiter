package br.edu.barriga.infra;

import br.edu.barriga.domain.Usuario;
import br.edu.barriga.service.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsuarioMemoryRepository implements UsuarioRepository {

    private final List<Usuario> users;
    private Long currentId;

    public UsuarioMemoryRepository() {
        currentId = 0L;
        users = new ArrayList<>();
        salvar(new Usuario(null, "User #1", "user1@mail.com", "123456"));
    }

    @Override
    public Usuario salvar(Usuario usuario) {
        Usuario newUser = new Usuario(nextId(), usuario.nome(), usuario.email(), usuario.senha());
        users.add(newUser);
        return newUser;
    }

    @Override
    public Optional<Usuario> getUserByEmail(String email) {
        return users.stream()
                .filter(user -> user.email().equalsIgnoreCase(email))
                .findFirst();
    }

    private Long nextId() {
        return ++currentId;
    }

    public void printUsers() {
        System.out.println(users);
    }

    public static void main(String[] args) {
        UsuarioMemoryRepository repo = new UsuarioMemoryRepository();
        repo.printUsers();
        repo.salvar(new Usuario(null, "Usuario 2", "a@a.com", "123"));
        repo.printUsers();
    }
}
