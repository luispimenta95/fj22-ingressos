package br.com.caelum.ingresso.model;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;

public class SessaoTest {
	@Test
	public void oPrecoDaSessaoDeveSerIgualASomaDoPrecoDaSalaMaisOPrecoDoFilme() {
	Sala sala = new Sala("Eldorado - IMax", new BigDecimal("22.5"));
	Genero g = new Genero();
	g.setNome("Teste");
	
	Filme filme = new Filme(g,"Rogue One", Duration.ofMinutes(120), new BigDecimal("12.0"));
	BigDecimal somaDosPrecosDaSalaEFilme = sala.getPreco().add(filme.getPreco());
	Sessao sessao = new Sessao(filme,LocalTime.parse("10:00:00"), sala);
	Assert.assertEquals( somaDosPrecosDaSalaEFilme, sessao.getPreco() );
	}
}
