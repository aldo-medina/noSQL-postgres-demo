package com.postgressUsuario.postgressUsuario;


import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class UsuarioEntity implements Serializable {
    @Id
    private String nombre;

    private String apellido;

    private Integer edad;

    private String sexo;
    
    private String lenguajesAprendidos;

}
