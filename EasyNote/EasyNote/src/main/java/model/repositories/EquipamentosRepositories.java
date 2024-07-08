package model.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import model.entities.EquipamentosEntity;


public class EquipamentosRepositories implements BasicCrud{
	
	// Cria a conexão com o banco de dados
	// O nome ProjetoMensal é o mesmo nome que está no arquivo persistence.xml
	EntityManager lb = Persistence.createEntityManagerFactory("EasyNote").createEntityManager();

	
	@Override
	public Object create(Object object) {
		EquipamentosEntity equip = (EquipamentosEntity)object;
		System.out.println(equip.getIdEquipamentos());
		lb.getTransaction().begin(); // Inicia a transação
		lb.persist(equip); // Persiste, ou seja, cria a entidade no banco
		lb.getTransaction().commit(); // Confirma a operação
	
		return findById(equip.getIdEquipamentos());
	}

	@Override
	public Object update(Object object) {
		EquipamentosEntity equip = (EquipamentosEntity)object;
		lb.getTransaction().begin();
		lb.merge(equip); // Junta as novas informações com as existentes no banco
		lb.getTransaction().commit();
		return findById(equip.getIdEquipamentos());
	}

	
	@Override
	public void delete(Long id) {
		lb.getTransaction().begin();
		EquipamentosEntity equip = (EquipamentosEntity) findById(id);
		lb.remove(equip);
		lb.getTransaction().commit();
	}

	@Override
	public Object findById(Long id) {
		try {
			EquipamentosEntity equipInBd = lb.find(EquipamentosEntity.class, id);
			return equipInBd;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public EquipamentosEntity findByPatrimonio(String patrimonio) {
	    try {
	        return lb.createQuery("FROM EquipamentosEntity WHERE patrimonio = :patrimonio", EquipamentosEntity.class)
	                .setParameter("patrimonio", patrimonio)
	                .getSingleResult();
	        // Consulta direta no banco de dados
	    } catch (NoResultException e) {
	        System.out.println("Nenhum equipamento encontrado com o Patrimonio: " + patrimonio);
	        return null;
	    } catch (Exception e) {
	        System.out.println("Erro ao buscar patrimonio: " + e.getMessage());
	        e.printStackTrace();
	        return null;
	    }
	}

	
	
	public List<EquipamentosEntity> getAllEquipamentos() {
        try {
        	EntityManager lb = Persistence.createEntityManagerFactory("EasyNote").createEntityManager();
            var query = lb.createQuery("SELECT le FROM EquipamentosEntity le",EquipamentosEntity.class).getResultList();
            // A Query está fazendo uma consulta direta no banco de dados
            lb.close();
            return query;
        } catch (Exception e) {
            System.out.println("Erro ao recuperar todos os equipamentos: " + e.getMessage());
            return new ArrayList<EquipamentosEntity>();
        }
    }
	

}
