package br.com.caelum.ingresso.descontos;

import java.math.BigDecimal;

public class DescontoEstudantes implements Desconto {

	public BigDecimal aplicarDesconto(BigDecimal preco) {
		
		return preco.divide(new BigDecimal("2.0"));
	}
	@Override
	public String getDescricao() {
		// TODO Auto-generated method stub
		return "Desconto Estudante";
	}
}
