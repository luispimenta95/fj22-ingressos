package br.com.caelum.ingresso.model;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Sessao {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Filme f;
	private LocalTime horario;
	@ManyToOne
	private Sala s;

	public Sessao() {

	}

	public Sessao(Filme f, LocalTime horario, Sala s) {

		this.f = f;
		this.horario = horario;
		this.s = s;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Filme getF() {
		return f;
	}

	public void setF(Filme f) {
		this.f = f;
	}

	public LocalTime getHorario() {
		return horario;
	}

	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public Sala getS() {
		return s;
	}

	public void setS(Sala s) {
		this.s = s;
	}

}
