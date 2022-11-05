package com.postgressUsuario.postgressUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioDB usuariodb;

    public Usuario addUsuario(Usuario usuario){

        return usuariodb.save(usuario);
    }

    public Usuario updateUser(Usuario usuario){

        return usuariodb.save(usuario);
    }

    public List<Usuario> getUsers(){

        return usuariodb.findAll();
    }

    public void deleteUser(Usuario usuario){

        usuariodb.delete(usuario);
    }
}
