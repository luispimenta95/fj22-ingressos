package br.com.caelum.ingresso.testes;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Genero;
import br.com.caelum.ingresso.model.Sala;
import br.com.caelum.ingresso.model.Sessao;

public class GerenciadorSessaoTeste {



	public class GerenciadorDeSessaoTest {

		private Filme filme;
		private Sala sala;
		private Sessao Sessao10;
		private Sessao Sessao13;
		private Sessao Sessao18;
		private Genero genero;

		@Before
		public void preparaSessao() {
			this.genero = new Genero();
			genero.setNome("Ação");
			this.filme = new Filme(genero, "Teste1", Duration.ofMinutes(120),BigDecimal.TEN);
			this.sala = new Sala("Sala teste",BigDecimal.TEN);
			this.Sessao10 = new Sessao(filme, LocalTime.parse("10:00:00"), sala);
			this.Sessao13 = new Sessao(filme, LocalTime.parse("13:00:00"), sala);
			this.Sessao18 = new Sessao(filme, LocalTime.parse("18:00:00"), sala);
		}

		@Test
		public void garanteQueNaoDevePermitirSessaoNoMesmoHorario() {
			List<Sessao> sessoes = Arrays.asList(Sessao10);
			GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
			Assert.assertFalse(gerenciador.cabe(Sessao10));
		}
		@Test
		public void garanteQueNaoDevePermitirSessoesTerminandoDentroDoHorarioDeUmaSessaoJaExistente()
		{
		List<Sessao> sessoes = Arrays.asList(Sessao10);
		Sessao sessao = new Sessao(filme,Sessao10.getHorario().minusHours(1),sala);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		Assert.assertFalse(gerenciador.cabe(sessao));
		}
		@Test
		public void garanteQueNaoDevePermitirSessoesIniciandoDentroDoHorarioDeUmaSessaoJaExistente()
		{
		List<Sessao> sessoesDaSala = Arrays.asList(Sessao10);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoesDaSala);
		Sessao sessao = new Sessao(filme,Sessao10.getHorario().plusHours(1), sala);
		Assert.assertFalse(gerenciador.cabe(sessao));
		}
		@Test
		public void garanteQueDevePermitirUmaInsercaoEntreDoisFilmes(){
		List<Sessao> sessoes = Arrays.asList(Sessao10, Sessao18);
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		Assert.assertTrue(gerenciador.cabe(Sessao13));
		}
		@Test
		public void garanteQueDeveNaoPermitirUmaSessaoQueTerminaNoProximoDia() {
		List<Sessao> sessoes = Collections.emptyList();
		GerenciadorDeSessao gerenciador = new GerenciadorDeSessao(sessoes);
		Sessao sessaoQueTerminaAmanha = new Sessao(filme,LocalTime.parse("23:00:00"),sala);
		

		Assert.assertFalse(gerenciador.cabe(sessaoQueTerminaAmanha));
		}
	}

}
