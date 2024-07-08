package model.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.entities.AlunosEntity;
import model.entities.EmprestimosEntity;
import model.entities.EquipamentosEntity;
import model.repositories.AlunosRepositories;
import model.repositories.EmprestimosRepositories;
import model.repositories.EquipamentosRepositories;

public class EmprestimosService {

	public EmprestimosRepositories repository = new EmprestimosRepositories();
	public AlunosRepositories repositoryAlunos = new AlunosRepositories();
	public EquipamentosRepositories repositoryEquip = new EquipamentosRepositories();

	public EmprestimosEntity criarNovoEmprestimo(EmprestimosEntity emprestimo) {
		// Verificar e salvar LeitoresEntidades
		AlunosEntity aluno = emprestimo.getAlunos();
		AlunosEntity alunoExistente = repositoryAlunos.findByRa(aluno.getRa());

		// Verifica se leitor existe
		if (alunoExistente == null) {
			System.out.println("O aluno não foi encontrado");
			return null;
		}

		// Verificar se o leitor está ativo
		if (!alunoExistente.isAtivo()) {
			System.out.println("O aluno não está ativo");
			return null;
		}

		EmprestimosRepositories empRep = new EmprestimosRepositories();
		List<EmprestimosEntity> emprestimosRa = empRep.getAllEmprestimosByRaEmAndamento(emprestimo.getAlunos().getRa());
	    AlunosEntity alunoEmAndamento = repositoryAlunos.findByRa(emprestimo.getAlunos().getRa());

	    if (emprestimosRa != null && !emprestimosRa.isEmpty()) {
	        for (EmprestimosEntity emprestimoEmAndamento : emprestimosRa) {
	            System.out.println("Detalhes do Empréstimo:");
	            System.out.println("ID do Empréstimo: " + emprestimo.getIdEmprestimo());
	            System.out.println("Data de Retirada: " + emprestimo.getDtRetirada());
	            System.out.println("Data de Devolução: " + emprestimo.getDtDevolucao());
	            System.out.println("Patrimonio: " + emprestimo.getEquipamento().getPatrimonio());
	            System.out.println("Status: " + emprestimo.getSituacao());
	            System.out.println("-----------------------------------");
	        }
	        System.out.println("Não é possível iniciar o empréstimo porque aluno possui empréstimos em aberto.");
	        return null;
	    }
	    
		EquipamentosEntity equipamento = emprestimo.getEquipamento();

		// Verificar se livro existe
		EquipamentosEntity equipamentoExistente = repositoryEquip.findByPatrimonio(equipamento.getPatrimonio());
		if (equipamentoExistente == null) {
			System.out.println("O equipamento não foi encontrado");
			return null;
		}

		// Verificar se Livro está ativo
		if (!equipamentoExistente.isAtivo()) {
			System.out.println("O equipamento não está ativo");
			return null;
		}
		

		List<EmprestimosEntity> emprestimosPat = empRep.getAllEmprestimosByPatrimonioEmAndamento(emprestimo.getEquipamento().getPatrimonio());
		
		if (emprestimosPat != null && !emprestimosPat.isEmpty()) {
	        for (EmprestimosEntity emprestimoEmAndamento : emprestimosPat) {
	            System.out.println("Detalhes do Empréstimo:");
	            System.out.println("ID do Empréstimo: " + emprestimo.getIdEmprestimo());
	            System.out.println("Data de Retirada: " + emprestimo.getDtRetirada());
	            System.out.println("Data de Devolução: " + emprestimo.getDtDevolucao());
	            System.out.println("Patrimonio: " + emprestimo.getEquipamento().getPatrimonio());
	            System.out.println("Status: " + emprestimo.getSituacao());
	            System.out.println("-----------------------------------");
	        }
	        System.out.println("Não é possível iniciar o empréstimo porque o equipamento está emprestado.");
	        return null;
	    }
		
		

		// Associe o aluno e o equipamento ao empréstimo
		// Salva primeiro as chaves estrangeiras antes de criar o empréstimo
		emprestimo.setAlunos(alunoExistente);
		emprestimo.setEquipamento(equipamentoExistente);

		// Persistir a entidade EmprestimosEntidade
		return (EmprestimosEntity) repository.create(emprestimo);
	}
	
	/*
	public EmprestimosEntity visualizarEmprestimos() {
		// Obtém a lista de empréstimos
		List<EmprestimosEntity> emprestimos = repository.getAllEmprestimos();

		// Verifica se a lista não é nula e não está vazia
		if (emprestimos != null && !emprestimos.isEmpty()) {
			// Itera sobre cada empréstimo e imprime suas informações
			for (EmprestimosEntity emprestimo : emprestimos) {
				System.out.println("ID: " + emprestimo.getIdEmprestimo() + " | Aluno: "
						+ emprestimo.getAlunos().getNome() + " | Data de Retirada: " + emprestimo.getDtRetirada()
						+ " | Data de Devolução: " + emprestimo.getDtDevolucao()
						+ " | Patrimônio: " + emprestimo.getEquipamento().getPatrimonio());
			}
		} else {
			System.out.println("Nenhum empréstimo encontrado.");
		}
		return null;
	}*/
	
	public List<EmprestimosEntity> visualizarEmprestimos() {
		
	    // Obtém a lista de alunos do repositório
	    List<EmprestimosEntity> emprestimos = repository.getAllEmprestimos();
	    List<EmprestimosEntity> emprestimosLista = new ArrayList<>();
	    
	    // Verifica se a lista de alunos não é nula e não está vazia
	    if (emprestimos != null && !emprestimos.isEmpty()) {
	    	// Lista para armazenar apenas os alunos ativos
		    
	    	
	    	// Adicione print no terminal aqui da lista
	        System.out.println("Lista de todos os alunos:");
	        for (EmprestimosEntity emprestimo : emprestimos) {
	            System.out.println(emprestimo);
	        }
	    	
	        // Itera sobre cada aluno
	        for (EmprestimosEntity emprestimo : emprestimos) {
	        	emprestimosLista.add(emprestimo);
	            }
	        }
	        
	        // Retorna a lista de alunos ativos
		    return emprestimosLista;
	    } 
	
	public EmprestimosEntity encerrarEmprestimo(Long id) {

		EmprestimosEntity emp = (EmprestimosEntity) repository.findById(id);

		// Atualizar os dados do empréstimo
		emp.setDtDevolucao(LocalDateTime.now());
		emp.setSituacao("Encerrado");

		// Persistir as alterações no último empréstimo
		return (EmprestimosEntity) repository.update(emp); 

	}
	
	
	
}
