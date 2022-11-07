package com.postgressUsuario.postgressUsuario;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final ObjectMapper objectMapper;

    @PersistenceContext
    private EntityManager em;


    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.objectMapper = new ObjectMapper();
    }

    public void addUsuario(UsuarioEntity usuarioEntity){
        usuarioRepository.save(usuarioEntity);
    }

    public List<UsuarioEntity> getUsers(String nombre){
        return usuarioRepository.findAllByNombre(nombre);
    }

    public void addUsuarios(List<UsuarioEntity> usuario){
        usuarioRepository.saveAll(usuario);
    }

    @Transactional
    public void deleteUsuarioByQuery(HashMap<String, Object> queryParams) throws JsonProcessingException {
        String basicQuery = "delete from UsuarioEntity u" + this.whereConstruct(queryParams);

        Query query = this.em.createQuery(basicQuery);

        for (var entry : queryParams.entrySet()) {
            Object value = "lenguajesAprendidos".equals(entry.getKey()) ? objectMapper
                    .writeValueAsString(entry.getValue()) : entry.getValue();
            query.setParameter(entry.getKey(), value);
        }
        query.executeUpdate();

    }

    @Transactional
    public void updateUsuario(HashMap<String, Object> queryParams, HashMap<String, Object> updateParams) throws JsonProcessingException {
        String basicQuery = "update UsuarioEntity u" + this.setConstruct(updateParams) + this.whereConstruct(queryParams);

        Query query = this.em.createQuery(basicQuery);
        for (var entry : updateParams.entrySet()) {
            Object value = "lenguajesAprendidos".equals(entry.getKey()) ? objectMapper
                    .writeValueAsString(entry.getValue()) : entry.getValue();
            query.setParameter("set_" + entry.getKey(), value);
        }

        for (var entry : queryParams.entrySet()) {
            Object value = "lenguajesAprendidos".equals(entry.getKey()) ? objectMapper
                    .writeValueAsString(entry.getValue()) : entry.getValue();
            query.setParameter(entry.getKey(), value);
        }
        query.executeUpdate();

    }

    private StringBuilder whereConstruct(HashMap<String, Object> params) {
        StringBuilder where = new StringBuilder(" where");
        boolean andRequired = false;
        for (var entry : params.entrySet()) {
            if (!andRequired) {
                andRequired = true;
            } else {
                where.append(" and");
            }
            String addToQuery = " u." + entry.getKey() + " = :" + entry.getKey();
            where.append(addToQuery);
        }
        return where;
    }

    private StringBuilder setConstruct(HashMap<String, Object> params) {
        StringBuilder set = new StringBuilder(" set");
        boolean andRequired = false;

        for (var entry : params.entrySet()) {
            if (!andRequired) {
                andRequired = true;
            } else {
                set.append(",");
            }
            String addToQuery = " u." + entry.getKey() + " = :set_" + entry.getKey();
            set.append(addToQuery);
        }
        return  set;
    }

}
