package br.com.sevensoft.utils.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Receita {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotEmpty(message = "O título é obrigatório.")
	private String titulo;
	
	@NotEmpty(message = "Campo obrigatório.")
	private String ingrediente;
	
	@NotEmpty(message = "Campo obrigatório.")
	private String instrucao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	public String getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(String ingrediente) {
		this.ingrediente = ingrediente;
	}

	public String getInstrucao() {
		return instrucao;
	}

	public void setInstrucao(String instrucao) {
		this.instrucao = instrucao;
	}
	
	
}
