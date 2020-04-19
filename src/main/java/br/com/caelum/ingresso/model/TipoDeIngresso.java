package br.com.caelum.ingresso.model;

import java.math.BigDecimal;

import br.com.caelum.ingresso.descontos.*;

public enum TipoDeIngresso {
	INTEIRO(new SemDesconto()),
	ESTUDANTE(new DescontoEstudantes()),
	BANCO (new DescontoBancos());
	
	private final Desconto desconto;
	 
	private TipoDeIngresso(Desconto desconto) {
		this.desconto = desconto;
	}
	
	public BigDecimal aplicarDesconto(BigDecimal valor) {
		return desconto.aplicarDesconto(valor);
	}
	
	public String getDescricao() {
		return this.desconto.getDescricao();
	}
	 
}
