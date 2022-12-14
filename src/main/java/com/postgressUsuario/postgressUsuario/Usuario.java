package com.postgressUsuario.postgressUsuario;


import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Usuario implements Serializable {
    @Id
    private String nombre;

    private String apellido;

    private Integer edad;

    private String sexo;

    private List<String> lenguajesAprendidos;

}
