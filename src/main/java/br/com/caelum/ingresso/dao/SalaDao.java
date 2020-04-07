package br.com.caelum.ingresso.dao;

import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sala;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import java.util.List;

/**
 * Created by nando on 03/03/17.
 */
@Repository
public class SalaDao {

    @PersistenceContext
    private EntityManager manager;

    public Sala findOne(Integer id) {

        return manager.find(Sala.class, id);
    }

    public void save(Sala sala) {
        manager.merge(sala);
    }

    public List<Sala> findAll() {
        return manager.createQuery("select s from Sala s", Sala.class).getResultList();
    }
    
    public List<Sala> BuscaNomes(String nome) {
        Query query = manager.createQuery("FROM Sala WHERE nome like concat('%',:nome_sala,'%')");
        query.setParameter("nome_sala", nome);
        List<Sala> list = query.getResultList();
        if(list.size()==0) {
            return manager.createQuery("select s from Sala s order by s.nome", Sala.class).getResultList();

        }
            
        return list;
    }


    public void delete(Integer id) {
        manager.remove(findOne(id));
    }
}
