package model.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "Emprestimos")
public class EmprestimosEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEmprestimos")
	private Long idEmprestimo;
	@Column(name = "dtRetirada")
	private LocalDateTime dtRetirada;
	@Column(name = "dtDevolucao")
	private LocalDateTime dtDevolucao;
	@Column (name = "situacao")
	private String situacao;
	
	@ManyToOne(fetch = FetchType.LAZY) // Mapeamento ManyToOne para a entidade LeitoresEntidades
    //FetchType.LAZY impede que o banco retorne todos os dados de uma vez, ele só retorna se for solicitado
    @JoinColumn(name = "aluno_id_fk", nullable = false) // Nome da coluna da chave estrangeira no banco de dados
    private AlunosEntity aluno;

    @ManyToOne(fetch = FetchType.LAZY) // Mapeamento ManyToOne para a entidade LivrosEntidade
    @JoinColumn(name = "equipamento_id_fk", nullable = false) // Nome da coluna da chave estrangeira no banco de dados
    private EquipamentosEntity equipamentos;
	
	//Criar método VerificarRA
	
	public EmprestimosEntity() {
		
	}
	
	public EmprestimosEntity(Long id, AlunosEntity alunos, EquipamentosEntity equipamento) {
		this.idEmprestimo = id;
		this.dtRetirada = LocalDateTime.now();
		this.dtDevolucao = null;
		this.situacao = "Em andamento";
		this.aluno = alunos;
		this.equipamentos = equipamento;
	}


	public Long getIdEmprestimo() {
		return idEmprestimo;
	}
	public void setIdEmprestimo(Long idEmprestimo) {
		this.idEmprestimo = idEmprestimo;
	}
	public LocalDateTime getDtRetirada() {
		return dtRetirada;
	}
	public void setDtRetirada(LocalDateTime dtRetirada) {
		this.dtRetirada = dtRetirada;
	}
	public LocalDateTime getDtDevolucao() {
		return dtDevolucao;
	}
	public void setDtDevolucao(LocalDateTime dtDevolucao) {
		this.dtDevolucao = dtDevolucao;
	}
	public AlunosEntity getAlunos() {
		return aluno;
	}
	public void setAlunos(AlunosEntity alunos) {
		this.aluno = alunos;
	}
	public EquipamentosEntity getEquipamento() {
		return equipamentos;
	}
	public void setEquipamento(EquipamentosEntity equipamento) {
		this.equipamentos = equipamento;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	

	
}
