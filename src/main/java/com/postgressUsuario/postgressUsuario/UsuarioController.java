package com.postgressUsuario.postgressUsuario;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/postgres", produces="application/json")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/usuarios")
    public ResponseEntity<String> getUsuarios(@RequestParam(value = "nombre") String nombre){
        try{
            List<UsuarioEntity> usuarioEntities = this.usuarioService.getUsers(nombre);
            String jsonString = objectMapper.writeValueAsString(usuarioEntities);
            return new ResponseEntity<String>(jsonString, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/usuarios/addone")
    public ResponseEntity<String> addUsuario(@RequestBody Usuario usuario){
        try{
            this.usuarioService.addUsuario(this.usuarioToUsuarioEntity(usuario));
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/usuarios/addmany")
    public ResponseEntity<String> addUsuarios(@RequestBody List<Usuario> usuarios) {
        try {
            List<UsuarioEntity> usuarioEntities = new ArrayList<>();
            usuarios.forEach(usuario -> {
                try {
                    usuarioEntities.add(this.usuarioToUsuarioEntity(usuario));
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            });
            this.usuarioService.addUsuarios(usuarioEntities);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/usuarios/deletebyquery")
    public ResponseEntity<String> deleteUsuarioByQuery(@RequestBody HashMap<String, Object> queryParams) {
        try {
            this.usuarioService.deleteUsuarioByQuery(queryParams);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/usuarios/update")
    public ResponseEntity<String> updateUsuario(@RequestBody UpdateRequest updateRequest) {
        try {
            this.usuarioService.updateUsuario(updateRequest.getQueryParams(), updateRequest.getUpdateParams());
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    private UsuarioEntity usuarioToUsuarioEntity(Usuario usuario) throws JsonProcessingException {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNombre(usuario.getNombre());
        usuarioEntity.setApellido(usuario.getApellido());
        usuarioEntity.setEdad(usuario.getEdad());
        usuarioEntity.setSexo(usuario.getSexo());
        usuarioEntity.setLenguajesAprendidos(objectMapper.writeValueAsString(usuario.getLenguajesAprendidos()));
        return usuarioEntity;
    }

}
