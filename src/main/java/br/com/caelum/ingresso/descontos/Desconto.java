package br.com.caelum.ingresso.descontos;

import java.math.BigDecimal;

public interface Desconto {
	BigDecimal aplicarDesconto(BigDecimal preco);
}
