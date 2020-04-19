package br.com.caelum.ingresso.testes;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Genero;
import br.com.caelum.ingresso.model.Ingresso;
import br.com.caelum.ingresso.model.Lugar;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;
import br.com.caelum.ingresso.model.TipoDeIngresso;

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
	
	@Test
	public void TestaLugaresOcupados() {
		Lugar a1 = new Lugar("A",1);
		Lugar a2 = new Lugar("A",2);
		Lugar a3 = new Lugar("A",3);
		
		Sala sala = new Sala("Eldorado - IMax", new BigDecimal("22.5"));
		Genero g = new Genero();
		g.setNome("Teste");
		
		Filme filme = new Filme(g,"Rogue One", Duration.ofMinutes(120), new BigDecimal("12.0"));
		BigDecimal somaDosPrecosDaSalaEFilme = sala.getPreco().add(filme.getPreco());
		Sessao sessao = new Sessao(filme,LocalTime.parse("10:00:00"), sala);
		Ingresso ingresso = new Ingresso(sessao, TipoDeIngresso.INTEIRO, a1);
		Set<Ingresso> ingressos = Stream.of(ingresso).collect(Collectors.toSet());
		sessao.setIngressos(ingressos);
		Assert.assertFalse(sessao.isDisponivel(a1));
		Assert.assertTrue(sessao.isDisponivel(a2));
		Assert.assertTrue(sessao.isDisponivel(a3));	
	}
}
