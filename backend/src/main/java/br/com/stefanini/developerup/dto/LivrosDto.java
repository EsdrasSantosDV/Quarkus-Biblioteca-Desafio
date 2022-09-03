package br.com.stefanini.developerup.dto;


public class LivrosDto {
	
	private String ISBN;

	private String nome;
	
	private String anoDePublicacao;
	
	private String editora;
	
	private Integer quatidadeExemplares;

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		this.ISBN = iSBN;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getAnoDePublicacao() {
		return anoDePublicacao;
	}

	public void setAnoDePublicacao(String anoDePublicacao) {
		this.anoDePublicacao = anoDePublicacao;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public Integer getQuatidadeExemplares() {
		return quatidadeExemplares;
	}

	public void setQuatidadeExemplares(Integer quatidadeExemplares) {
		this.quatidadeExemplares = quatidadeExemplares;
	}


}
