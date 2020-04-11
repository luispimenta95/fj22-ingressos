package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.descontos.SemDesconto;


public class IngressoTest {
@Test
public void naoDeveConcederDesconto() {
	Sala sala = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
	Genero g = new Genero();
	g.setNome("Teste");
	Filme filme = new Filme(g, "Teste", Duration.ofMinutes(120), new BigDecimal("12"));
	Sessao sessao = new Sessao(filme,LocalTime.parse("10:00:00"), sala);
	Ingresso ingresso = new Ingresso(sessao, new SemDesconto());
	BigDecimal precoEsperado = new BigDecimal("32.50");
	Assert.assertEquals(precoEsperado, ingresso.getPreco());
	
}
}
