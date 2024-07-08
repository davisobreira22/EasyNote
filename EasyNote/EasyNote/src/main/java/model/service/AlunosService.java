package model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.entities.AlunosEntity;
import model.entities.EmprestimosEntity;
import model.repositories.AlunosRepositories;
import model.repositories.EmprestimosRepositories;

public class AlunosService {

	public AlunosRepositories repository = new AlunosRepositories();

	public AlunosEntity createAlunos(AlunosEntity alunos) {

		// Verifica se o leitor existe
		for (AlunosEntity aluno : repository.getAllAlunos()) {
			
			if (aluno.getRa().equals(alunos.getRa())) {
				System.out.println("Aluno já existe!");

				// Verifica se o leitor está desativado, caso esteja pergunta se deseja
				// reativá-lo
				if (!aluno.isAtivo()) {
					System.out.println("O Aluno está desativado. Deseja reativá-lo? S/N: ");
					Scanner scan = new Scanner(System.in);
					String choice = scan.next();
					// Faz o update do leitor no banco de dados, setando o atributo ativo como true
					if (choice.equalsIgnoreCase("S")) {
						aluno.setAtivo(true);
						return (AlunosEntity) repository.update(aluno);
					} else {
						System.out.println("Aluno não reativado.");
					}
				} else {
					System.out.println("O aluno está ativo no sistema! ID: " + aluno.getIdAluno());
					return null;
				}
				return null; // Caso o aluno já exista
			}
		}

		// Cria o novo Leitor caso esteja tudo certo
		AlunosEntity novoAluno = new AlunosEntity(null, alunos.getNome(), alunos.getCpf(), alunos.getDtNascimento(),
				alunos.getEmail(), alunos.getUsuario(), alunos.getSenha(), alunos.getRa(), alunos.getCurso());
		return (AlunosEntity) repository.create(novoAluno);
	}
	
	/*
	public AlunosEntity visualizarAlunos() {
	    // Obtém a lista de empréstimos
	    List<AlunosEntity> alunos = repository.getAllAlunos();
	    
	    // Verifica se a lista não é nula e não está vazia
	    if (alunos != null && !alunos.isEmpty()) {
	        // Itera sobre cada empréstimo e imprime suas informações
	        for (AlunosEntity aluno : alunos) {
	        	// Mostra apenas os leitores ativos
	        	if (aluno.isAtivo()) {
	        		System.out.println("ID: " + aluno.getIdAluno() + 
                            " | Nome: " + aluno.getNome() + 
                            " | CPF: " + aluno.getCpf() + 
                            " | Data de Nascimento: " + aluno.getDtNascimento() +
                            " | RA: " + aluno.getRa() +
                            " | E-mail: " + aluno.getEmail() +
                            " | Curso: " + aluno.getCurso());
	        	}   
	        }
	        
	    } else {
	        System.out.println("Nenhum aluno encontrado.");
	    }
		return null;
	}
	*/
	
	public List<AlunosEntity> visualizarAlunos() {
	    // Obtém a lista de alunos do repositório
	    List<AlunosEntity> alunos = repository.getAllAlunos();
	    
	    // Lista para armazenar apenas os alunos ativos (inicializada aqui para ser limpa a cada chamada)
	    List<AlunosEntity> alunosAtivos = new ArrayList<>();
	    
	    // Verifica se a lista de alunos não é nula e não está vazia
	    if (alunos != null && !alunos.isEmpty()) {
	        // Itera sobre cada aluno
	        for (AlunosEntity aluno : alunos) {
	            // Verifica se o aluno está ativo
	            if (aluno.isAtivo()) {
	                // Adiciona o aluno à lista de alunos ativos
	                alunosAtivos.add(aluno);
	            }
	        }
	    } else {
	        System.out.println("Nenhum aluno encontrado.");
	    }
	    System.out.println("Estamos no service"+alunosAtivos.size());
	    return alunosAtivos; // Retorna a lista, que será vazia se nenhum aluno foi encontrado
	}

	
	public AlunosEntity updateAlunos(AlunosEntity alunos) {
		
		if (alunos.getIdAluno() == null) {
	        throw new IllegalArgumentException("O ID do aluno não pode ser nulo");
	    }
		
		// Encontra o aluno pelo ID fornecido
		AlunosEntity aluno = (AlunosEntity) repository.findById(alunos.getIdAluno());
	    
	    // Verifica se o leitor foi encontrado
	    if (aluno == null) {
	        throw new IllegalArgumentException("Nenhum aluno encontrado com o ID " + alunos.getIdAluno());
	    }

	    // Atualiza os dados do leitor
	    aluno.setNome(alunos.getNome());
	    aluno.setCpf(alunos.getCpf());
	    aluno.setDtNascimento(alunos.getDtNascimento());
	    aluno.setRa(alunos.getRa());
	    aluno.setCurso(alunos.getCurso());
	    aluno.setEmail(alunos.getEmail());
	    aluno.setUsuario(alunos.getUsuario());
	    aluno.setSenha(alunos.getSenha());

	    // Persiste as alterações no leitor
	    return (AlunosEntity) repository.update(aluno);
		
	}
	
	public AlunosEntity deleteAlunos(String ra) {
	    EmprestimosRepositories empRep = new EmprestimosRepositories();
	    //Verifica se o leitor possui empréstimos em andamento
	    List<EmprestimosEntity> emprestimos = empRep.getAllEmprestimosByRaEmAndamento(ra);
	    AlunosEntity aluno = repository.findByRa(ra);

	    // Se sim, mostra os dados do empréstimo em andamento e não deleta o leitor
	    if (emprestimos != null && !emprestimos.isEmpty()) {
	        for (EmprestimosEntity emprestimo : emprestimos) {
	            System.out.println("Detalhes do Empréstimo:");
	            System.out.println("ID do Empréstimo: " + emprestimo.getIdEmprestimo());
	            System.out.println("Data de Retirada: " + emprestimo.getDtRetirada());
	            System.out.println("Data de Devolução: " + emprestimo.getDtDevolucao());
	            System.out.println("Patrimonio: " + emprestimo.getEquipamento().getPatrimonio());
	            System.out.println("Status: " + emprestimo.getSituacao());
	            System.out.println("-----------------------------------");
	        }
	        System.out.println("Não é possível excluir o aluno porque existem empréstimos em aberto.");
	        return null;
	    } else {
	        // Marca o aluno como inativo
	        if (aluno != null) {
	        	aluno.setAtivo(false);
	            return (AlunosEntity) repository.update(aluno);
	        }else {
	        	System.out.println("Aluno não encontrado!");
	        	return null;
	        }
	    }
	}
}
