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
@Table(name = "Equipamentos")
public class EquipamentosEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idEquipamento")
	private Long idEquipamentos;
	@Column(name = "patrimonio")
	private String patrimonio;
	@Column(name = "marca")
	private String marca;
	@Column(name = "modelo")
	private String modelo;
	@Column(name = "dtAquisicao")
	private Date dtAquisicao;
	@Column(name = "observacao")
	private String observacao;
	@Column(name = "situacao")
	private String situacao;
	@Column(name = "ativo")
	private boolean ativo;
	
	@OneToMany (mappedBy = "equipamentos")
	private List<EmprestimosEntity> emprestimos;
	
	public EquipamentosEntity(){
		
	}
	public EquipamentosEntity(Long id, String patrimonio, String marca, String modelo,
			Date dtAquisicao, String observacao) {
		this.idEquipamentos = id;
		this.patrimonio = patrimonio;
		this.marca = marca;
		this.modelo = modelo;
		this.dtAquisicao = dtAquisicao;
		this.observacao = observacao;
		this.situacao = "Disponível";
		this.ativo = true;
	}
	
	
	public Long getIdEquipamentos() {
		return idEquipamentos;
	}
	public void setIdEquipamentos(Long idEquipamentos) {
		this.idEquipamentos = idEquipamentos;
	}
	public String getPatrimonio() {
		return patrimonio;
	}
	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Date getDtAquisicao() {
		return dtAquisicao;
	}
	public void setDtAquisicao(Date dtAquisicao) {
		this.dtAquisicao = dtAquisicao;
	}
	public String getObservacao() {
		return observacao;
	}
	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	public boolean isAtivo() {
		return ativo;
	}
	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}
	
	
	
}
