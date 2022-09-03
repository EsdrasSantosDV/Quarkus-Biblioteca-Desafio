package br.com.stefanini.developerup.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name="tb_autor")
@NamedNativeQueries(value={
		@NamedNativeQuery(name="CONSULTAR_AUTORES",query="SELECT ISNI,nome,email,data_nascimento,biografia FROM tb_autor",resultClass = Autor.class),
		@NamedNativeQuery(name="INSERIR_AUTOR",query="INSERT INTO tb_autor(ISNI,nome,email,data_nascimento,biografia) VALUES (:ISNI,:nome,:email,:data_nascimento,:biografia)"),
		@NamedNativeQuery(name="EXCLUIR_AUTOR_BYISNI",query="DELETE FROM tb_autor WHERE ISNI=:ISNI"),
		@NamedNativeQuery(name="ATUALIZAR_AUTOR_BYISNI",query="UPDATE tb_autor set nome=:nome,email=:email,biografia=:biografia WHERE ISNI=:ISNI"),
		@NamedNativeQuery(name="CONSULTAR_AUTOR_BYISNI",query="SELECT ISNI,nome,email,data_nascimento,biografia FROM tb_autor WHERE ISNI=:ISNI",resultClass = Autor.class),
		@NamedNativeQuery(name="ISNI_SENDO_USADO",query="SELECT ISNI,nome,email,data_nascimento,biografia FROM tb_autor WHERE ISNI=:ISNI",resultClass = Autor.class)
})
public class Autor implements Serializable {
	
	
	@Id
	@NotBlank(message="O campo ISNI é obrigatório!")
	@Column(name = "ISNI" ,unique=true)
	private String ISNI;
	
	@NotBlank(message="O campo nome é obrigatório!")
	@Size(max = 50, message = "o nome deve conter no maximo 50 caracteres")
	@Column(name = "nome")
	private String nome;
		
	@Schema(example = "email@email.com")
	@NotBlank(message="O campo e-mail é obrigatório!")
	@Column(name = "email")
	private String email;
	
	@NotBlank(message="O campo data de nasciemento é obrigatório!")
	@Column(name = "data_nascimento",nullable = false,updatable = false)
	private LocalDate dataNascimento;
	
	@NotBlank(message="O campo biografia é obrigatório!")
	@Size(max = 200, message = "A biografia deve possuir no maximo 200 caracteres")
	@Column(name = "biografia")
	private String biografia;
	
	@OneToMany(mappedBy = "autor", cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("autor")
	private List<Livros> livros;
	

	
	public Autor() {
	}

	public Autor( String nome, String ISNI, String email,LocalDate dataNascimento, String biografia){

		this.nome = nome;
		this.ISNI = ISNI;
		this.email = email;
		this.dataNascimento = dataNascimento;
		this.biografia = biografia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getISNI() {
		return ISNI;
	}

	public void setISNI(String iSNI) {
		ISNI = iSNI;
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

	public List<Livros> getLivros() {
		return livros;
	}

	public void setLivros(List<Livros> livros) {
		this.livros = livros;
	}
	
	
	
}

