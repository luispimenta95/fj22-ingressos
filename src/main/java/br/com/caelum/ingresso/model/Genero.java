package br.com.caelum.ingresso.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Genero {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	  	private Integer Id_genero;
	  	private String nome;
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public Integer getId_genero() {
			return Id_genero;
		}
		public void setId_genero(Integer id_genero) {
			Id_genero = id_genero;
		}
	  	

}
