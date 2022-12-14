package br.com.stefanini.developerup.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import java.io.Serializable;

@Entity
@Table(name="livros")
public class Livros  extends PanacheEntityBase implements Serializable {

	@Id
	@Column(name = "ISBN")
	private String ISBN;

	@NotBlank(message="O campo nome é obrigatório!")
	@Size(max = 50, message = "o nome deve conter no maximo 50 caracteres")
	@Column(name = "nome")
	private String nome;

	@NotBlank(message="O campo ano de publicação é obrigatório!")
	@Column(name = "ano_publicacao")
	private String anoDePublicacao;

	@NotBlank(message="O campo editora é obrigatório!")
	@Size(max = 50, message = "A editora deve conter no maximo 50 caracteres")
	private String editora;


	@Column(name = "quantidade_exemplares")
	private Integer quatidadeExemplares;

	@ManyToOne
	@JsonIgnoreProperties("livros")
	private Autor autor;

	@ManyToOne
	private Emprestimo emprestimo;

	public Livros() {
		super();
	}

	public Livros( String nome, String anoDePublicacao, String editora, String ISBN, Integer quatidadeExemplares) {

		this.nome = nome;
		this.anoDePublicacao = anoDePublicacao;
		this.editora = editora;
		this.ISBN = ISBN;
		this.quatidadeExemplares = quatidadeExemplares;
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

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Emprestimo getEmprestimo() {
		return emprestimo;
	}

	public void setEmprestimo(Emprestimo emprestimo) {
		this.emprestimo = emprestimo;
	}




}
