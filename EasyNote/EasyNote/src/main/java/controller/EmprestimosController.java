package controller;

import java.util.List;

import model.entities.EmprestimosEntity;
import model.service.EmprestimosService;


public class EmprestimosController {

public EmprestimosService service = new EmprestimosService();
	
	public EmprestimosEntity createEmprestimos(EmprestimosEntity entity) {
		return service.criarNovoEmprestimo(entity);
	}
	
	public List<EmprestimosEntity> viewEmprestimos() {
		return service.visualizarEmprestimos();
	}
	public EmprestimosEntity encerrarEmprestimos(Long id) {
		return service.encerrarEmprestimo(id);
	}
	
	public List<EmprestimosEntity> getEmpByPat(String patrimonio) {
		return service.repository.getAllEmprestimosByPatrimonio(patrimonio);
	}
	
	public EmprestimosEntity findPat(String pat) {
		return (EmprestimosEntity) service.repository.findByPatrimonio(pat);
	}
}
