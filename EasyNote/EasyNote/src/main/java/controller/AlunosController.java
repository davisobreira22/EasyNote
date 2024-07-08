package controller;

import model.entities.AlunosEntity;
import model.service.AlunosService;

public class AlunosController {

public AlunosService service = new AlunosService();
	
	public AlunosEntity createAlunos(AlunosEntity entity) {
		
		return service.createAlunos(entity);
	}
	public AlunosEntity updateAlunos(AlunosEntity entity) {
		return service.updateAlunos(entity);
	}
	public AlunosEntity viewAlunos() {
		return (AlunosEntity) service.visualizarAlunos();
	}
	public AlunosEntity deleteAlunos(String ra) {
		return service.deleteAlunos(ra);
	}
	public AlunosEntity findAluno(Long id) {
		return (AlunosEntity) service.repository.findById(id);
	}
	public AlunosEntity findRa(String ra) {
		return (AlunosEntity) service.repository.findByRa(ra);
	}
}
