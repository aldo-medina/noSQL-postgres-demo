package com.postgressUsuario.postgressUsuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioDB extends JpaRepository<Usuario, String> {

}
