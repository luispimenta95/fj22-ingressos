package br.com.caelum.ingresso.testes;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Genero;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;


public class IngressoTest {
@Test
public void naoDeveConcederDesconto() {
	Sala sala = new Sala("Eldorado - IMAX", new BigDecimal("20.5"));
	Genero g = new Genero();
	g.setNome("Teste");
	Filme filme = new Filme(g, "Teste", Duration.ofMinutes(120), new BigDecimal("12"));
	Sessao sessao = new Sessao(filme,LocalTime.parse("10:00:00"), sala);
	
Lugar lugar = new Lugar("A", 1);
	
	Ingresso ingresso = new Ingresso(sessao,TipoDeIngresso.INTEIRO,lugar); 
	BigDecimal precoEsperado = new BigDecimal("32.50");
	Assert.assertEquals(precoEsperado, ingresso.getPreco());
	
}
}


