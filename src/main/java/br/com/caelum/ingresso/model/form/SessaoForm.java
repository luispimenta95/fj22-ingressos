package br.com.caelum.ingresso.model.form;

import java.time.LocalTime;

import javax.validation.constraints.NotNull;

import br.com.caelum.ingresso.dao.FilmeDao;
import br.com.caelum.ingresso.dao.SalaDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class SessaoForm {
	@NotNull
	private Integer salaId;
	@NotNull
	private Integer filmeId;
	@NotNull
	private LocalTime horario;
	public Integer getSalaId() {
		return salaId;
	}
	public void setSalaId(Integer salaId) {
		this.salaId = salaId;
	}
	public Integer getFilmeId() {
		return filmeId;
	}
	public void setFilmeId(Integer filmeId) {
		this.filmeId = filmeId;
	}
	public LocalTime getHorario() {
		return horario;
	}
	public void setHorario(LocalTime horario) {
		this.horario = horario;
	}

	public Sessao toSessao(SalaDao sd, FilmeDao fd) {
		Filme filme =fd.findOne(filmeId);
		Sala sala = sd.findOne(salaId);
		Sessao sessao = new Sessao(filme, this.horario, sala);
		return sessao;
	}

}
