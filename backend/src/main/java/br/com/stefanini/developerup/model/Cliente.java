package br.com.stefanini.developerup.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

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
public class Cliente extends PanacheEntityBase {

    @Id
    @Schema(example = "email@email.com")
    @NotBlank(message="O campo e-mail é obrigatório!")
    private String email;

    @NotBlank(message="O campo nome é obrigatório!")
    @Column(name = "nome")
    private String nome;

    @NotBlank(message="O campo contato é obrigatório!")
    @Column(name = "contato")
    private String contato;

    @OneToMany
    private List<Emprestimo> emprestimos;

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