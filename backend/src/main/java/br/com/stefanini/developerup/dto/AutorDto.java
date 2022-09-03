package br.com.stefanini.developerup.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class AutorDto {

	private String ISNI;
	
	private String nome;
		
	private String email;
	
	private LocalDate dataNascimento;
	
	private String biografia;

	public String getISNI() {
		return ISNI;
	}

	public void setISNI(String ISNI) {
		ISNI = ISNI;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	
	
}
