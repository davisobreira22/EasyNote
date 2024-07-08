package model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Alunos")
public class TipoUsuarioEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idTipoUsuario")
	private Long idTipoUsuario;
	@Column(name = "statusEmprestimo")
	private String statusEmprestimo;
	
	
	public TipoUsuarioEntity() {
		
	}
	
	public TipoUsuarioEntity(Long idTipoUsuario, String statusEmprestimo) {
		this.idTipoUsuario = idTipoUsuario;
		this.statusEmprestimo = statusEmprestimo;
	}
	
	public Long getIdTipoUsuario() {
		return idTipoUsuario;
	}
	public void setIdTipoUsuario(Long idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}
	public String getStatusEmprestimo() {
		return statusEmprestimo;
	}
	public void setStatusEmprestimo(String statusEmprestimo) {
		this.statusEmprestimo = statusEmprestimo;
	}
	
	
	
}
