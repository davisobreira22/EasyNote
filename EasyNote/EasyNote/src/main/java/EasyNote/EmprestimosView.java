package EasyNote;

import java.util.Scanner;

import controller.EmprestimosController;
import model.entities.AlunosEntity;
import model.entities.EmprestimosEntity;
import model.entities.EquipamentosEntity;

public class EmprestimosView {
	
	EmprestimosController control = new EmprestimosController();
	Scanner scanner = new Scanner(System.in);

	public void exibirMenuEmprestimo() {
		System.out.println("Escolha uma opção:");
		System.out.println("1. Iniciar Emprestimo");
		System.out.println("2. Vizualizar Emprestimos");
		System.out.println("3. Encerrar Emprestimo");
		System.out.println("4. Sair");
	}
	
	private void iniciarEmprestimo () {
		System.out.println("RA do aluno: ");
		String ra = scanner.nextLine();
		
		AlunosEntity aluno = new AlunosEntity();
		aluno.setRa(ra);
		
		System.out.println("Patrimônio do equipamento: ");
		String pat = scanner.nextLine();
		
		EquipamentosEntity equipamento = new EquipamentosEntity();
		equipamento.setPatrimonio(pat);
		
		EmprestimosEntity emprestimo = new EmprestimosEntity(null, aluno, equipamento);
		EmprestimosEntity novoEmp = control.createEmprestimos(emprestimo);
		
		if(novoEmp != null) {
			System.out.println("Empréstimos registrado com sucesso!");
		}else {
			System.out.println("Erro ao registrar empréstimo.");
		}
	}
	
	private void vizualizarEmprestimo() {
		control.viewEmprestimos();
	}
	
	/*private void encerrarEmprestimo() {
		System.out.println("Patrimônio do Equipamento: ");
		String patrimonio = scanner.nextLine();
		
		EmprestimosEntity encerrado = control.encerrarEmprestimos(patrimonio);
		if (encerrado != null) {
			System.out.println("Empréstimo encerrado com sucesso!");
		} else {
			System.out.println("Erro ao encerrar empréstimo!");
		}
		
	}*/
	
	public void escolhaEmprestimo() {
		int sair = 0;

		while (sair == 0) {
			exibirMenuEmprestimo();
			int escolha = scanner.nextInt();
			scanner.nextLine();

			switch (escolha) {
				case 1:
					iniciarEmprestimo();
					break;
				case 2:
					vizualizarEmprestimo();
					break;
				case 3:
					//encerrarEmprestimo();
					break;
				case 4:
					sair = 1; // Sair do loop
					break;
				default:
					System.out.println("Opção inválida!");
			}
		}
	}
}
 