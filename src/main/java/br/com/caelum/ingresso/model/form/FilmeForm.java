package br.com.caelum.ingresso.model.form;

import java.math.BigDecimal;
import java.time.Duration;

import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;

import br.com.caelum.ingresso.dao.GeneroDao;
import br.com.caelum.ingresso.model.Filme;
import br.com.caelum.ingresso.model.Genero;

public class FilmeForm {
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getGeneroId() {
		return generoId;
	}

	public void setGeneroId(Integer generoId) {
		this.generoId = generoId;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Duration getDuracao() {
		return duracao;
	}

	public void setDuracao(long duracao) {
		this.duracao = Duration.ofMinutes(duracao);
	}
	@NotNull
	private Integer generoId;
	@NotNull
	private String nome;
	@NotNull
	private Duration duracao;
	@NotNull
	private BigDecimal preco; 

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Filme toFilme(GeneroDao gd) {
		Filme filme = new Filme();
		filme.setGenero(gd.findOne(generoId));
		filme.setNome(nome);
		filme.setDuracao(duracao);
		filme.setId(id);
		filme.setPreco(preco);
		return filme;
		
		
	}

	public void fromFilme(Filme filme) {
		nome=filme.getNome();
		duracao=filme.getDuracao();
		id=filme.getId();
		generoId=filme.getGenero().getId_genero();
		preco =filme.getPreco();
	}
}
