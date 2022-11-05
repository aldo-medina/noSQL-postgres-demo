package com.postgressUsuario.postgressUsuario;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
public class Usuario {
    @Id
    private String nombre;

    private String apellido;

    private Integer edad;

    private String sexo;

    @Lob
    private List<String> lenguajesAprendidos;

}
