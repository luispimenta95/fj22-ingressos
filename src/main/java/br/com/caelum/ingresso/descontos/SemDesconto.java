package br.com.caelum.ingresso.descontos;

import java.math.BigDecimal;

public class SemDesconto implements Desconto {

	public BigDecimal aplicarDesconto(BigDecimal preco) {
		
		return preco;
	}
	@Override
	public String getDescricao() {
		// TODO Auto-generated method stub
		return "Normal";
	}
}
