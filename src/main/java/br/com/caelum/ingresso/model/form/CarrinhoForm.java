package br.com.caelum.ingresso.model.form;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.ingresso.dao.LugarDao;
import br.com.caelum.ingresso.dao.SessaoDao;
import br.com.caelum.ingresso.model.Ingresso;

public class CarrinhoForm {
	private List<Ingresso> ingressos = new ArrayList<>();
	
	public List<Ingresso> getIngressos() {
		return ingressos;
	}
	public void setIngressos(List<Ingresso> ingressos) {
		this.ingressos = ingressos;
	}
	
	public List<Ingresso> toIngresso(SessaoDao sd, LugarDao ld){
		
		List<Ingresso> lista = null;
		return lista;
		
	}
}
