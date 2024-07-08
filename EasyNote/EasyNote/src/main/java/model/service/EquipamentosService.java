package model.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.entities.EquipamentosEntity;
import model.entities.EmprestimosEntity;

import model.repositories.EmprestimosRepositories;
import model.repositories.EquipamentosRepositories;

public class EquipamentosService {

	Scanner scan = new Scanner(System.in);
	public EquipamentosRepositories repository = new EquipamentosRepositories();

	public EquipamentosEntity createEquipamentos(EquipamentosEntity equipamentos) {

		// Verifica se o equipamento existe
		for (EquipamentosEntity equipamento : repository.getAllEquipamentos()) {

			if (equipamento.getPatrimonio().equals(equipamentos.getPatrimonio())) {
				System.out.println("Equipamento já existe!");

				// Verifica se o equipamento está desativado, caso esteja pergunta se deseja
				// reativá-lo
				if (!equipamento.isAtivo()) {
					System.out.println("O equipamento está desativado. Deseja reativá-lo? S/N: ");
					Scanner scan = new Scanner(System.in);
					String choice = scan.next();
					// Faz o update do equipamento no banco de dados, setando o atributo ativo como
					// true
					if (choice.equalsIgnoreCase("S")) {
						equipamento.setAtivo(true);
						return (EquipamentosEntity) repository.update(equipamento);
					} else {
						System.out.println("Equipamento não reativado.");
					}
				} else {
					System.out.println("O equipamento está ativo no sistema! ID: " + equipamento.getIdEquipamentos());
					return null;
				}
				return null; // Caso o equipamento já exista
			}
		}

		// Cria o novo equipamento caso esteja tudo certo
		EquipamentosEntity novoEquipamento = new EquipamentosEntity(null, equipamentos.getPatrimonio(),
				equipamentos.getMarca(), equipamentos.getModelo(), equipamentos.getDtAquisicao(),
				equipamentos.getObservacao());

		return (EquipamentosEntity) repository.create(novoEquipamento);
	}

	/*public EquipamentosEntity visualizarEquipamentos() {
		// Obtém a lista de empréstimos
		List<EquipamentosEntity> equipamentos = repository.getAllEquipamentos();

		// Verifica se a lista não é nula e não está vazia
		if (equipamentos != null && !equipamentos.isEmpty()) {
			// Itera sobre cada empréstimo e imprime suas informações
			for (EquipamentosEntity equipamento : equipamentos) {
				// Mostra apenas os leitores ativos
				if (equipamento.isAtivo()) {
					System.out.println(
							"ID: " + equipamento.getIdEquipamentos() + " | Patrimônio: " + equipamento.getPatrimonio()
									+ " | Marca: " + equipamento.getMarca() + " | Modelo: " + equipamento.getModelo()
									+ " | Aquisição: " + equipamento.getDtAquisicao() + " | Observação: "
									+ equipamento.getObservacao() + " | Situação: " + equipamento.getSituacao());
				}
			}
		} else {
			System.out.println("Nenhum equipamento encontrado.");
		}
		return null;
	}*/
	
	public List<EquipamentosEntity> visualizarEquipamentos() {
			
		    // Obtém a lista de alunos do repositório
		    List<EquipamentosEntity> equipamentos = repository.getAllEquipamentos();
		    List<EquipamentosEntity> equipamentosAtivos = new ArrayList<>();
		    
		    // Verifica se a lista de alunos não é nula e não está vazia
		    if (equipamentos != null && !equipamentos.isEmpty()) {
		    	// Lista para armazenar apenas os alunos ativos
			    
		    	// Adicione print no terminal aqui da lista
		        System.out.println("Lista de todos os alunos:");
		        for (EquipamentosEntity equipamento : equipamentos) {
		            System.out.println(equipamento);
		        }
		    	
		        // Itera sobre cada aluno
		        for (EquipamentosEntity equipamento : equipamentos) {
		            // Verifica se o aluno está ativo
		            if (equipamento.isAtivo()) {
		                // Adiciona o aluno à lista de alunos ativos
		            	equipamentosAtivos.add(equipamento);
		            }
		        }
		        
		        // Retorna a lista de alunos ativos
			    return equipamentosAtivos;
		    } else {
		        System.out.println("Nenhum aluno encontrado.");
		        return new ArrayList<>();
		    }
		}

	public EquipamentosEntity updateEquipamentos(EquipamentosEntity equipamentos) {

		if (equipamentos.getIdEquipamentos() == null) {
			throw new IllegalArgumentException("O ID do equipamento não pode ser nulo");
		}

		// Encontra o aluno pelo ID fornecido
		EquipamentosEntity equipamento = (EquipamentosEntity) repository.findById(equipamentos.getIdEquipamentos());

		// Verifica se o leitor foi encontrado
		if (equipamento == null) {
			throw new IllegalArgumentException(
					"Nenhum equipamento encontrado com o ID " + equipamentos.getIdEquipamentos());
		}

		// Atualiza os dados do leitor
		equipamento.setPatrimonio(equipamentos.getPatrimonio());
		equipamento.setMarca(equipamentos.getMarca());
		equipamento.setModelo(equipamentos.getModelo());
		equipamento.setDtAquisicao(equipamentos.getDtAquisicao());
		equipamento.setObservacao(equipamentos.getObservacao());
		equipamento.setSituacao(equipamentos.getSituacao());

		// Persiste as alterações no leitor
		return (EquipamentosEntity) repository.update(equipamento);

	}

	public EquipamentosEntity deleteEquipamentos(String patrimonio) {
		EmprestimosRepositories empRep = new EmprestimosRepositories();
		// Verifica se o leitor possui empréstimos em andamento
		List<EmprestimosEntity> emprestimos = empRep.getAllEmprestimosByPatrimonioEmAndamento(patrimonio);
		EquipamentosEntity equip = repository.findByPatrimonio(patrimonio);

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
			System.out.println("Não é possível excluir o equipamento porque existem empréstimos em aberto.");
			return null;
		} else {
			// Marca o aluno como inativo
			if (equip != null) {
				equip.setAtivo(false);
				return (EquipamentosEntity) repository.update(equip);
			} else {
				System.out.println("Equipamento não encontrado!");
				return null;
			}
		}
	}
}
