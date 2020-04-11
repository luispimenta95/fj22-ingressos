package br.com.caelum.ingresso.descontos;

import java.math.BigDecimal;

public class SemDesconto implements Desconto {

	public BigDecimal aplicarDesconto(BigDecimal preco) {
		
		return preco;
	}

}
