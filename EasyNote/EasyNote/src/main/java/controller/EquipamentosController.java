package controller;

import model.entities.EquipamentosEntity;
import model.service.EquipamentosService;

public class EquipamentosController {

public EquipamentosService service = new EquipamentosService();
	
	public EquipamentosEntity createEquipamentos(EquipamentosEntity entity) {
		
		return service.createEquipamentos(entity);
	}
	public EquipamentosEntity updateEquipamentos(EquipamentosEntity entity) {
		return service.updateEquipamentos(entity);
	}
	public EquipamentosEntity viewEquipamentos() {
		return (EquipamentosEntity) service.visualizarEquipamentos();
	}
	public EquipamentosEntity deleteEquipamentos(String patrimonio) {
		return service.deleteEquipamentos(patrimonio);
	}
	public EquipamentosEntity findEquipamento(Long id) {
		return (EquipamentosEntity) service.repository.findById(id);
	}
	public EquipamentosEntity findPat(String pat) {
		return (EquipamentosEntity) service.repository.findByPatrimonio(pat);
	}
}
