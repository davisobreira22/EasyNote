package EasyNote;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import controller.EquipamentosController;
import model.entities.EquipamentosEntity;

public class EquipamentoView {
	
	EquipamentosController control = new EquipamentosController();
	Scanner scanner = new Scanner(System.in);
	
	public void  exibirMenuEquipamento() {
		System.out.println("Escolha uma opção:");
		System.out.println("1. Cadastrar Equipamento");
		System.out.println("2. Atualizar Equipamento");
		System.out.println("3. Visualizar Equipamento");
		System.out.println("4. Deletar  Equipamento");
		System.out.println("5. Sair");
	}
	
	public void cadastrarEquipamento() {
		System.out.println("Patrimonio: ");
		String patrimonio= scanner.nextLine();
		System.out.println("Marca: ");
		String marca = scanner.nextLine();
		System.out.println("Modelo: ");
		String modelo= scanner.nextLine();
		System.out.println("Data de Aquisição (dd/MM/yyyy): ");
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
		System.out.println("Observação: ");
		String observacao = scanner.nextLine();
		
		EquipamentosEntity equipamento = new EquipamentosEntity(null, patrimonio, marca, modelo, date, observacao);
		EquipamentosEntity equipNovo = control.createEquipamentos(equipamento);
		
		if(equipNovo != null) {
			System.out.println("Equipamento cadastrado com sucesso!");
		}
		else {
			System.out.println("Erro ao cadastrar equipamento!");
		}
		
	}
	
	
	public void atualizarEquipamento () {
		vizualizarEquipamento();
		System.out.println("Digite o ID do equipamento que deseja atualizar: ");
		Long id = scanner.nextLong();
		scanner.nextLine(); // Limpar o buffer do scanner
		System.out.println("Patrimonio (Enter para pular): ");
		String patrimonio= scanner.nextLine();
		System.out.println("Marca (Enter para pular): ");
		String marca = scanner.nextLine();
		System.out.println("Modelo (Enter para pular): ");
		String modelo= scanner.nextLine();
		
		System.out.println("Data de Aquisição (dd/MM/yyyy) (Obrigatório): ");
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
		
		System.out.println("Observação (Enter para pular): ");
		String obs = scanner.nextLine();
		

		EquipamentosEntity equipamentoUpdate = control.findEquipamento(id);
		if(patrimonio.isEmpty()) {
			patrimonio = equipamentoUpdate.getPatrimonio();
		}
		if(marca.isEmpty()) {
			marca = equipamentoUpdate.getMarca();
		}
		if(modelo.isEmpty()) {
			modelo = equipamentoUpdate.getModelo();
		}
		if(data.isEmpty()) {
			date = equipamentoUpdate.getDtAquisicao();
		}
		if(obs.isEmpty()) {
			obs = equipamentoUpdate.getObservacao();
		}
		
		EquipamentosEntity equipamentoUp = new EquipamentosEntity(id, patrimonio, marca, modelo, date, obs);
		EquipamentosEntity novosDados = control.updateEquipamentos(equipamentoUp);
		
		if(novosDados != null) {
			System.out.println("Equipamento atualizado com sucesso!");
		}
		else {
			System.out.println("Erro ao atualizar equipamento!");
		}

 	}

	public void vizualizarEquipamento() {
		control.viewEquipamentos();
	}
	
	
	public void deletarEquipamento() {
		vizualizarEquipamento();
		System.out.println("Informe o Patrimônio do equipamento: ");
		String pat = scanner.nextLine();// Limpar o buffer do scanner

		EquipamentosEntity equipamentoDeletado = control.deleteEquipamentos(pat);

		if (equipamentoDeletado == null) {
			System.out.println("Erro ao excluir equipamento! Empréstimos em Aberto.");
		} else {
			System.out.println("Equipamento excluído com sucesso! ");
		}
	}

	public void escolhaEquipamento() {
		int sair = 0;

		while (sair == 0) {
			exibirMenuEquipamento();
			int escolha = scanner.nextInt();
			scanner.nextLine();

			switch (escolha) {
				case 1:
					cadastrarEquipamento();
					break;
				case 2:
					atualizarEquipamento();
					break;
				case 3:
					vizualizarEquipamento();
					break;
				case 4:
					deletarEquipamento();
				case 5:
					sair = 1; // Sair do loop
					break;
				default:
					System.out.println("Opção inválida!");
			}
		}
	}
}
