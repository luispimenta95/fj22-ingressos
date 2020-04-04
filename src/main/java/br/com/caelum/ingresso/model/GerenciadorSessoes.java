package br.com.caelum.ingresso.model;

import java.time.LocalDateTime;
import java.util.List;

public class GerenciadorSessoes {
	private List<Sessao> sessoesExistentes;
	public GerenciadorSessoes(List<Sessao> sessoesExistentes) {
		this.sessoesExistentes= sessoesExistentes;
	}
	public boolean cabe(Sessao novaSessao) {
		if(terminaAmanha(novaSessao)) {
			return false;
		}
		return false;
	}
	private boolean terminaAmanha(Sessao novaSessao) {
		return false;
	}
	
	private boolean temConflito(Sessao novaSessao,Sessao sessaoExistente) {
		LocalDateTime termino = getTerminaHoje(novaSessao);
		return false;
	}
	private LocalDateTime getTerminaHoje(Sessao novaSessao) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
