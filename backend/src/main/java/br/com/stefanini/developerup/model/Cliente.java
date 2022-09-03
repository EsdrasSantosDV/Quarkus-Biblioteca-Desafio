package br.com.stefanini.developerup.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

/**
 * @author Danilo Dorgam
 * email danilodorgam@gmail.com
 * created 30/03/2022
 * @version 0.1.0
 */
@Entity
@Table(name = "cliente")
@NamedNativeQueries(value={
        @NamedNativeQuery(name="CONSULTAR_CLIENTES",query="SELECT id,email,nome,contato FROM cliente",resultClass = Cliente.class),
        @NamedNativeQuery(name="INSERIR_CLIENTE",query="INSERT INTO cliente(email,nome,contato) VALUES (:email,:nome,:contato)"),
        @NamedNativeQuery(name="EXCLUIR_CLIENTE_BYEMAIL",query="DELETE FROM cliente WHERE email=:email"),
        @NamedNativeQuery(name="ATUALIZAR_CLIENTE_BYEMAIL",query="UPDATE cliente set nome=:nome,contato=:contato WHERE email=:email"),
        @NamedNativeQuery(name="CONSULTAR_CLIENTE_BYEMAIL",query="SELECT id,nome,contato,email FROM cliente WHERE email=:email",resultClass = Cliente.class),
        @NamedNativeQuery(name="EMAIL_SENDO_USADO",query="SELECT id,nome,contato,email FROM cliente WHERE email=:email",resultClass = Cliente.class)
})

public class Cliente  implements Serializable {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;


    @Schema(example = "email@email.com")
    @NotBlank(message="O campo e-mail é obrigatório!")
    @Email(message="O email deve ser Valido")
    @Column(name = "email" ,unique=true)
    private String email;


    @NotBlank(message="O campo nome é obrigatório!")
    @Column(name = "nome")
    @Size(max = 50)
    private String nome;

    @NotBlank(message="O campo contato é obrigatório!")
    @Column(name = "contato")
    private String contato;

    @OneToMany
    private List<Emprestimo> emprestimos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(List<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}
}
