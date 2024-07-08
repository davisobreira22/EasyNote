package model.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "Alunos")
public class AlunosEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAluno")
	private Long idAluno;
	@Column(name = "nome")
	private String nome;
	@Column(name = "dtNascimento")
	private Date dtNascimento;
	@Column(name = "cpf")
	private String cpf;
	@Column(name = "email")
	private String email;
	@Column(name = "usuario")
	private String usuario;
	@Column(name = "senha")
	private String senha;
	@Column(name = "ra")
	private String ra;
	@Column(name = "curso")
	private String curso;
	@Column (name = "ativo")
	private boolean ativo;
	
	@OneToMany(mappedBy = "aluno")
	private List<EmprestimosEntity> emprestimos;
	
	
	public AlunosEntity() {
		
	}
	
	public AlunosEntity(Long id, String nome, String cpf, Date dtNascimento, String email, String usuario,
			String senha, String ra, String curso) {
		this.idAluno = id;
		this.nome = nome;
		this.dtNascimento = dtNascimento;
		this.cpf = cpf;
		this.email = email;
		this.usuario = usuario;
		this.senha = senha;
		this.ra = ra;
		this.curso = curso;
		this.ativo = true;
	}
	
	
	public Long getIdAluno() {
		return idAluno;
	}
	public void setIdAluno(Long idAluno) {
		this.idAluno = idAluno;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Date getDtNascimento() {
		return dtNascimento;
	}
	public void setDtNascimento(Date dtNascimento) {
		this.dtNascimento = dtNascimento;
	}
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getRa() {
		return ra;
	}
	public void setRa(String ra) {
		this.ra = ra;
	}
	public String getCurso() {
		return curso;
	}
	public void setCurso(String curso) {
		this.curso = curso;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	

	
}
