package com.login.database;

import com.login.models.Usuario;
import java.util.ArrayList;
import java.util.List;

public class BaseDeDatos {
    private static List<Usuario> usuarios = new ArrayList<>();

    public static boolean agregarUsuario(Usuario usuario) {
        if (buscarUsuarioPorUsername(usuario.getUsername()) == null) {
            usuarios.add(usuario);
            return true;
        }
        return false;
    }

    public static Usuario buscarUsuarioPorUsername(String username) {
        return usuarios.stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    public static List<Usuario> obtenerUsuarios() {
        return usuarios;
    }

    public static boolean eliminarUsuario(String username) {
        return usuarios.removeIf(u -> u.getUsername().equals(username));
    }

    public static boolean actualizarUsuario(Usuario usuarioActualizado) {
        Usuario existente = buscarUsuarioPorUsername(usuarioActualizado.getUsername());
        if (existente != null) {
            existente.setNombre(usuarioActualizado.getNombre());
            existente.setApellido(usuarioActualizado.getApellido());
            existente.setTelefono(usuarioActualizado.getTelefono());
            existente.setEmail(usuarioActualizado.getEmail());
            existente.setPassword(usuarioActualizado.getPassword());
            return true;
        }
        return false;
    }
}
