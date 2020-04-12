package br.com.caelum.ingresso.descontos;

import java.math.BigDecimal;

public class DescontoBancos implements Desconto {

	public BigDecimal aplicarDesconto(BigDecimal preco) {
		
		return preco.multiply(new BigDecimal("0.3"));
	}

	@Override
	public String getDescricao() {
		// TODO Auto-generated method stub
		return "Desconto Banco";
	}
	

}
