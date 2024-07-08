package model.repositories;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import model.entities.AlunosEntity;


public class AlunosRepositories implements BasicCrud{
	
	// Cria a conexão com o banco de dados
	// O nome ProjetoMensal é o mesmo nome que está no arquivo persistence.xml
	EntityManager lb = Persistence.createEntityManagerFactory("EasyNote").createEntityManager();

	
	@Override
	public Object create(Object object) {
		AlunosEntity aluno = (AlunosEntity)object;
		System.out.println(aluno.getIdAluno());
		lb.getTransaction().begin(); // Inicia a transação
		lb.persist(aluno); // Persiste, ou seja, cria a entidade no banco
		lb.getTransaction().commit(); // Confirma a operação
	
		return findById(aluno.getIdAluno());
	}

	@Override
	public Object update(Object object) {
		AlunosEntity aluno = (AlunosEntity)object;
		lb.getTransaction().begin();
		lb.merge(aluno); // Junta as novas informações com as existentes no banco
		lb.getTransaction().commit();
		return findById(aluno.getIdAluno());
	}

	
	@Override
	public void delete(Long id) {
		lb.getTransaction().begin();
		AlunosEntity aluno = (AlunosEntity) findById(id);
		lb.remove(aluno);
		lb.getTransaction().commit();
	}

	@Override
	public Object findById(Long id) {
		try {
			AlunosEntity alunoInBd = lb.find(AlunosEntity.class, id);
			return alunoInBd;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	
	public AlunosEntity findByRa(String ra) {
	    try {
	        return lb.createQuery("FROM AlunosEntity WHERE ra = :ra", AlunosEntity.class)
	                .setParameter("ra", ra)
	                .getSingleResult();
	        // Consulta direta no banco de dados
	    } catch (NoResultException e) {
	        System.out.println("Nenhum aluno encontrado com o RA: " + ra);
	        return null;
	    } catch (Exception e) {
	        System.out.println("Erro ao buscar aluno: " + e.getMessage());
	        e.printStackTrace();
	        return null;
	    }
	}

	
	
	public List<AlunosEntity> getAllAlunos() {
        try {
        	EntityManager lb = Persistence.createEntityManagerFactory("EasyNote").createEntityManager();
            var query = lb.createQuery("SELECT le FROM AlunosEntity le",AlunosEntity.class).getResultList();
            // A Query está fazendo uma consulta direta no banco de dados
            lb.close();
            return query;
        } catch (Exception e) {
            System.out.println("Erro ao recuperar todos os alunos: " + e.getMessage());
            return new ArrayList<AlunosEntity>();
        }
    }
	

}
