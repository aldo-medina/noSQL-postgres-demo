package com.postgressUsuario.postgressUsuario;

import java.io.Serializable;
import java.util.HashMap;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateRequest implements Serializable {

    private HashMap<String, Object> queryParams;

    private HashMap<String, Object> updateParams;

}
