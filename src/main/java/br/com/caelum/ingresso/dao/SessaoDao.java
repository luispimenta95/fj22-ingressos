package br.com.caelum.ingresso.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

@Repository
public class SessaoDao {
	@PersistenceContext
	private EntityManager em;

	
	
	
	
	public void salva(Sessao s) {
		em.persist(s);
	}

	public List<Sessao> buscaSessoes(Sala sala) {

		return em.createQuery("select	s from	Sessao	s	where	s.sala	=	:sala order by s.horario", Sessao.class)
				.setParameter("sala", sala).getResultList();
	}
	
	public List<Sessao> buscaFilme(Filme filme) {

		return em.createQuery("select	s from	Sessao	s	where	s.filme	=	:filme order by s.horario", Sessao.class)
				.setParameter("filme", filme).getResultList();
	}
	public Sessao findOne(Integer id) {
		return em.find(Sessao.class, id);
	}
	
	public void delete (Integer id) {
		em.remove(findOne(id));
	}
}
