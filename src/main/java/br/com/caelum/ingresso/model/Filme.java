package br.com.caelum.ingresso.model;



import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
 
public class Filme implements Serializable{

	public Filme() {
		
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "Id_genero")
	private Genero genero;
	private String nome;
	private Duration duracao;
	public Duration getDuracao() {
		return duracao;
	}
	public void setDuracao(Duration duracao) {
		this.duracao = duracao;
	}

	private BigDecimal preco = BigDecimal.ZERO;
	
	
	
	public BigDecimal getPreco() {
		return preco.setScale(2,RoundingMode.HALF_UP);
	}
	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}
	public Genero getGenero() {
		return genero;
	}
	public void setGenero(Genero genero) {
		this.genero = genero;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Filme(Genero genero, String nome, Duration duracao,  BigDecimal preco) {
		
		this.genero = genero;
		this.nome = nome;
		this.duracao = duracao;
		this.preco =preco;
		
		
	}
		


}
