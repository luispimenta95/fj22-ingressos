package br.com.caelum.ingresso.dao;

import br.com.caelum.ingresso.model.Genero;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by nando on 03/03/17.
 */
@Repository
public class GeneroDao {

    @PersistenceContext
    private EntityManager manager;


    public Genero findOne(Integer id) {
        return manager.find(Genero.class, id);
    }

    public void save(Genero filme) {
        manager.persist(filme);
    }

    public List<Genero> findAll() {
        return manager.createQuery("select g from Genero g order by g.nome", Genero.class).getResultList();
    }

    public void delete(Integer id) {
        manager.remove(findOne(id));
    }
}
