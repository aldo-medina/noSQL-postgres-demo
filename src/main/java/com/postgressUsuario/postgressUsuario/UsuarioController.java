package com.postgressUsuario.postgressUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/postgres", produces="application/json")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios")
    public ResponseEntity<String> getUsuarios(){
        try{
            List<Usuario> usuarios = this.usuarioService.getUsers();
            return new ResponseEntity<String>((MultiValueMap<String, String>) usuarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/usuarios/add")
    public ResponseEntity<String> addUsuario(@RequestBody Usuario usuario){
        try{
            this.usuarioService.addUsuario(usuario);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("usuarios/update")
    public ResponseEntity<String> updateUsuario(@RequestBody Usuario usuario){
        try{
            this.usuarioService.updateUser(usuario);
            return new ResponseEntity<String>(HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("usuarios/delete")
    public void deleteUsuario(@RequestBody Usuario usuario){
        usuarioService.deleteUser(usuario);
    }
}
