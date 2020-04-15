package br.com.caelum.ingresso.model.form;

import br.com.caelum.ingresso.model.Genero;

public class GeneroForm {
	private Integer Id_genero;
	private String nome;
	
	public Integer getId_genero() {
		return Id_genero;
	}
	public void setId_genero(Integer id_genero) {
		Id_genero = id_genero;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public GeneroForm() {
		// TODO Auto-generated constructor stub
	}
	public GeneroForm(Genero genero) {
		this.Id_genero =genero.getId_genero();
		this.nome = genero.getNome();
	}
	
	public Genero toGenero() {
			Genero genero = new Genero();
			genero.setId_genero(this.Id_genero);
			genero.setNome(this.nome);
				return genero;
	}
	


}
