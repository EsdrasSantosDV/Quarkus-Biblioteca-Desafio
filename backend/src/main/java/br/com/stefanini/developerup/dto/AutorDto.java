package br.com.stefanini.developerup.dto;

import java.util.Date;

public class AutorDto {

	private String ISNI;
	
	private String nome;
		
	private String email;
	
	private Date dataNascimento;
	
	private String biografia;

	public String getISNI() {
		return ISNI;
	}

	public void setISNI(String iSNI) {
		ISNI = iSNI;
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

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getBiografia() {
		return biografia;
	}

	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	
	
}
