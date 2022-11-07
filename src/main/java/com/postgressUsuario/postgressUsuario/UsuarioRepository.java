package com.postgressUsuario.postgressUsuario;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioEntity, String>, JpaRepository<UsuarioEntity, String>  {

    List<UsuarioEntity> findAllByNombre(String nombre);

}
