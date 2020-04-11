package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.persistence.Entity;
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

	public Ingresso(Sessao sessao, Desconto tipoDeDesconto) {
		this.sessao = sessao;
		this.preco = tipoDeDesconto.aplicarDesconto(sessao.getPreco());
	}
}
