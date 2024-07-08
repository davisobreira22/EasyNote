package EasyNote;

import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
    	
    	AlunosView alunosView = new AlunosView();
    	EmprestimosView emprestimosView = new EmprestimosView();
    	EquipamentoView equipamentosView = new EquipamentoView();
    	
        System.out.println("Bem-vindo ao EasyNote!");
        int sair = 0;
        Scanner scanner = new Scanner(System.in);
        
        while (sair == 0) {
            System.out.println("MENU PRINCIPAL");
            System.out.println("Escolha uma opção:");
            System.out.println("1 - Aluno");
            System.out.println("2 - Emprestimo");
            System.out.println("3 - Equipamento");
            System.out.println("4 - Sair");

            int escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer do scanner
            
            switch (escolha) {
                case 1:
                    alunosView.escolhaAluno();
                    break;
                case 2:
                    emprestimosView.escolhaEmprestimo();
                    break;
                case 3:
                    equipamentosView.escolhaEquipamento();
                    break;
                case 4:
                    sair = 1; // Sair do loop
                    break;
                default:
                    System.out.println("Opção Inválida!");
            }
        }
        scanner.close();
    }
}
