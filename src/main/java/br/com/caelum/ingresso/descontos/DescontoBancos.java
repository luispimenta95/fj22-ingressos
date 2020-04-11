package br.com.caelum.ingresso.descontos;

import java.math.BigDecimal;

public class DescontoBancos implements Desconto {

	public BigDecimal aplicarDesconto(BigDecimal preco) {
		
		return preco.multiply(new BigDecimal("0.3"));
	}

}
