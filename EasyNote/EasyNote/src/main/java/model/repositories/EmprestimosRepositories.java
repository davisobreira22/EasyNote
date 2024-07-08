package model.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.entities.EmprestimosEntity;

public class EmprestimosRepositories implements BasicCrud{
	
	EntityManager lb = Persistence.createEntityManagerFactory("EasyNote").createEntityManager();

	
	@Override
	public Object create(Object object) {
		EmprestimosEntity emprestimo = (EmprestimosEntity)object;
		lb.getTransaction().begin();
		lb.persist(emprestimo);
		lb.getTransaction().commit();
	
		return findById(emprestimo.getIdEmprestimo());
	}

	@Override
	public Object update(Object object) {
		EmprestimosEntity emprestimoUpdate = (EmprestimosEntity) object;
		lb.getTransaction().begin();
		lb.merge(emprestimoUpdate);
		lb.getTransaction().commit();
		return findById(emprestimoUpdate.getIdEmprestimo());
	}

	@Override
	public void delete(Long id) {
		lb.getTransaction().begin();
		EmprestimosEntity emprestimo = (EmprestimosEntity) findById(id);
		lb.remove(emprestimo);
		lb.getTransaction().commit();
	}

	@Override
	public Object findById(Long id) {
		try {
			EmprestimosEntity emprestimoInBd = lb.find(EmprestimosEntity.class, id);
			return emprestimoInBd;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public EmprestimosEntity findByPatrimonio(String patrimonio) {
        try {
        	 TypedQuery query = lb.createQuery(
                     "SELECT e FROM EmprestimosEntity e WHERE e.equipamentos.patrimonio = :patrimonio AND e.situacao = :situacao", 
                     EmprestimosEntity.class
                 );
            query.setParameter("patrimonio", patrimonio);
            query.setParameter("situacao", "Em andamento");
            return (EmprestimosEntity) query.getSingleResult();
        } catch (Exception e) {
        	System.out.println(e);
        }
        return null;
    }
	
	public List<EmprestimosEntity> findByRa(String ra) {
        try {
            // Cria uma query para buscar os empréstimos pelo CPF do leitor
            TypedQuery<EmprestimosEntity> query = lb.createQuery(
                "SELECT e FROM EmprestimosEntity e WHERE e.aluno.ra = :ra", 
                EmprestimosEntity.class
            );
            query.setParameter("ra", ra);
            
            // Executa a query e retorna a lista de empréstimos encontrados
            return query.getResultList();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
	
	public List<EmprestimosEntity> getAllEmprestimos(){
		try {
			EntityManager lb = Persistence.createEntityManagerFactory("EasyNote").createEntityManager();
			var query = lb.createQuery("SELECT le FROM EmprestimosEntity le",EmprestimosEntity.class).getResultList();

			return query;
		}catch (Exception e) {
			System.out.println("Erro ao recuperar todos os empréstimos: " + e.getMessage());
	         return new ArrayList<EmprestimosEntity>();
		}
	}
	
	public List<EmprestimosEntity> getAllEmprestimosEmAndamento(){
		try {
			TypedQuery<EmprestimosEntity> query = lb.createQuery(
				    "SELECT e FROM EmprestimosEntity e WHERE e.situacao = :situacao", 
				    EmprestimosEntity.class
				);
				query.setParameter("situacao", "Em andamento");
			
			return query.getResultList();
		}catch (Exception e) {
			System.out.println("Erro ao recuperar todos os alunos: " + e.getMessage());
	         return new ArrayList<EmprestimosEntity>();
		}
	}
	
	public List<EmprestimosEntity> getAllEmprestimosByRa(String ra){
		try {
			TypedQuery<EmprestimosEntity> query = lb.createQuery(
	                "SELECT e FROM EmprestimosEntity e WHERE e.aluno.ra = :ra", 
	                EmprestimosEntity.class
	            );
	            query.setParameter("ra", ra);
			
			return query.getResultList();
		}catch (Exception e) {
			System.out.println("Erro ao recuperar todos os alunos: " + e.getMessage());
	         return new ArrayList<EmprestimosEntity>();
		}
	}
	
	public List<EmprestimosEntity> getAllEmprestimosByPatrimonio(String patrimonio){
		try {
			TypedQuery<EmprestimosEntity> query = lb.createQuery(
	                "SELECT e FROM EmprestimosEntity e WHERE e.equipamentos.patrimonio = :patrimonio", 
	                EmprestimosEntity.class
	            );
	            query.setParameter("patrimonio", patrimonio);
			
			return query.getResultList();
		}catch (Exception e) {
			System.out.println("Erro ao recuperar todos os equipamentos: " + e.getMessage());
	         return new ArrayList<EmprestimosEntity>();
		}
	}
	
	
	public List<EmprestimosEntity> getAllEmprestimosByRaEmAndamento(String ra){
		try {
			TypedQuery<EmprestimosEntity> query = lb.createQuery(
				    "SELECT e FROM EmprestimosEntity e WHERE e.aluno.ra = :ra AND e.situacao = :situacao", 
				    EmprestimosEntity.class
				);
				query.setParameter("ra", ra);
				query.setParameter("situacao", "Em andamento");
			
			return query.getResultList();
		}catch (Exception e) {
			System.out.println("Erro ao recuperar todos os alunos aqui: " + e.getMessage());
	         return new ArrayList<EmprestimosEntity>();
		}
	}
	
	public List<EmprestimosEntity> getAllEmprestimosByPatrimonioEmAndamento(String patrimonio){
		try {
			TypedQuery<EmprestimosEntity> query = lb.createQuery(
				    "SELECT e FROM EmprestimosEntity e WHERE e.equipamentos.patrimonio = :patrimonio AND e.situacao = :situacao", 
				    EmprestimosEntity.class
				);
				query.setParameter("patrimonio", patrimonio);
				query.setParameter("situacao", "Em andamento");
			
			return query.getResultList();
		}catch (Exception e) {
			System.out.println("Erro ao recuperar todos os equipamentos: " + e.getMessage());
	         return new ArrayList<EmprestimosEntity>();
		}
	}

}
