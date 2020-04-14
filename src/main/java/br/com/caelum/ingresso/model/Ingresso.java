package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import br.com.caelum.ingresso.descontos.Desconto;

@Entity
public class Ingresso {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@ManyToOne
	private Sessao sessao;
	private BigDecimal preco;
	@ManyToOne
	private Lugar lugar;
	
	@Enumerated(EnumType.STRING)
	private TipoDeIngresso tipoDeIngresso;

	public TipoDeIngresso getTipoDeIngresso() {
		return tipoDeIngresso;
	}

	public void setTipoDeIngresso(TipoDeIngresso tipoDeIngresso) {
		this.tipoDeIngresso = tipoDeIngresso;
	}

	public Lugar getLugar() {
		return lugar;
	}

	public void setLugar(Lugar lugar) {
		this.lugar = lugar;
	}

	public Sessao getSessao() {
		return sessao;
	}

	public void setSessao(Sessao sessao) {
		this.sessao = sessao;
	}

	public BigDecimal getPreco() {
		return preco.setScale(2, RoundingMode.HALF_UP);
	}

	public Ingresso() {
		// TODO Auto-generated constructor stub
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Ingresso(Sessao sessao, TipoDeIngresso tipo,Lugar lugar) {
		this.sessao = sessao;
		this.preco = tipo.aplicarDesconto(sessao.getPreco());
		this.lugar = lugar;
	
	}
}
	