package EasyNote;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import controller.AlunosController;
import model.entities.AlunosEntity;

public class AlunosView {
	Scanner scanner = new Scanner(System.in);
	AlunosController control = new AlunosController();
	
	public void exibirMenuAluno() {
		System.out.println("Escolha uma opção:");
		System.out.println("1. Cadastrar Alunos");
		System.out.println("2. Atualizar Alunos");
		System.out.println("3. Visualizar Alunos");
		System.out.println("4. Deletar Alunos");
		System.out.println("5. Sair");
	}

	private void cadastrarAluno() {
		System.out.println("Nome do aluno: ");
		String nome = scanner.nextLine();
		System.out.println("RA: ");
		String ra = scanner.nextLine();
		System.out.println("CPF: ");
		String cpf = scanner.nextLine();
		System.out.println("Data de Nascimento (dd/MM/yyyy): ");
		String data = scanner.nextLine();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = formatter.parse(data);
		} catch (ParseException e) {
			System.out.println("Formato inválido. Use dd/MM/yyyy.");
			return; // Exit the program if the date format is invalid
		} catch (Exception ex) {
			System.out.println(ex);
		}
		System.out.println("Email: ");
		String email = scanner.nextLine();
		System.out.println("Curso: ");
		String curso = scanner.nextLine();
		System.out.println("Usuário: ");
		String user = scanner.nextLine();
		System.out.println("Senha: ");
		String senha = scanner.nextLine();

		AlunosEntity aluno = new AlunosEntity(null, nome, cpf, date, email, user, senha, ra, curso);
		AlunosEntity alunoNovo = control.createAlunos(aluno);
		
		if(alunoNovo != null) {
			System.out.println("Aluno criado com sucesso!");
		}
		else {
			System.out.println("Erro ao criar aluno!");
		}
	}

	private void atualizarAluno() {
		visualizarAluno();
		System.out.println("Digite o ID do aluno que deseja atualizar: ");
		Long id = scanner.nextLong();
		scanner.nextLine(); // Limpar o buffer do scanner
		System.out.println("Nome (Enter para pular): ");
		String nome = scanner.nextLine();
		System.out.println("RA (Enter para pular): ");
		String ra = scanner.nextLine();
		System.out.println("CPF (Enter para pular): ");
		String cpf = scanner.nextLine();
		
		System.out.println("Data de Nascimento (dd/MM/yyyy) (Obrigatório): ");
		String data = scanner.nextLine();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		Date date = null;
		try {
			date = formatter.parse(data);
		} catch (ParseException e) {
			System.out.println("Formato inválido. Use dd/MM/yyyy.");
			return; // Exit the program if the date format is invalid
		} catch (Exception ex) {
			System.out.println(ex);
		}
		
		System.out.println("Email (Enter para pular): ");
		String email = scanner.nextLine();
		System.out.println("Curso (Enter para pular): ");
		String curso = scanner.nextLine();
		System.out.println("Usuário (Enter para pular): ");
		String user = scanner.nextLine();
		System.out.println("Senha (Enter para pular): ");
		String senha = scanner.nextLine();

		AlunosEntity alunoUpdate = control.findAluno(id);
		if(nome.isEmpty()) {
			nome = alunoUpdate.getNome();
		}
		if(cpf.isEmpty()) {
			cpf = alunoUpdate.getCpf();
		}
		if(ra.isEmpty()) {
			ra = alunoUpdate.getRa();
		}
		if(data.isEmpty()) {
			date = alunoUpdate.getDtNascimento();
		}
		if(email.isEmpty()) {
			email = alunoUpdate.getEmail();
		}
		if(curso.isEmpty()) {
			curso = alunoUpdate.getCurso();
		}
		if(user.isEmpty()) {
			user = alunoUpdate.getUsuario();
		}
		if(senha.isEmpty()) {
			senha = alunoUpdate.getSenha();
		}
		
		AlunosEntity alunoUp = new AlunosEntity(id, nome, cpf, date, email, user, senha, ra, curso);
		AlunosEntity novosDados = control.updateAlunos(alunoUp);
		
		if(novosDados != null) {
			System.out.println("Aluno atualizado com sucesso!");
		}
		else {
			System.out.println("Erro ao atualizar aluno!");
		}
	}

	private void visualizarAluno() {
		control.viewAlunos();
	}

	private void excluirAluno() {
		visualizarAluno();
		System.out.println("Informe o RA do aluno: ");
		String ra = scanner.nextLine();// Limpar o buffer do scanner

		AlunosEntity alunoDeletado = control.deleteAlunos(ra);

		if (alunoDeletado == null) {
			System.out.println("Erro ao excluir aluno! Empréstimos em Aberto.");
		} else {
			System.out.println("Aluno excluído com sucesso! ");
		}
	}

	public void escolhaAluno() {
		int sair = 0;

		while (sair == 0) {
			exibirMenuAluno();
			int escolha = scanner.nextInt();
			scanner.nextLine();

			switch (escolha) {
				case 1:
					cadastrarAluno();
					break;
				case 2:
					atualizarAluno();
					break;
				case 3:
					visualizarAluno();
					break;
				case 4:
					excluirAluno();
					break;
				case 5:
					sair = 1; // Sair do loop
					break;
				default:
					System.out.println("Opção inválida!");
			}
		}
	}
}
