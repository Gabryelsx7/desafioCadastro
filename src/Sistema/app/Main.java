package Sistema.app;


import Sistema.services.ArquivoLeitor;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArquivoLeitor menu = new ArquivoLeitor();

        boolean rodando = true;

        while (rodando) {
            System.out.println("===Menu===");
            System.out.println("Digite uma opção: ");
            System.out.println("[1]Cadastrar um novo pet");
            System.out.println("[2]Alterar os dados do pet cadastrado");
            System.out.println("[3]Deletar um pet cadastrado");
            System.out.println("[4]Listar todos os pets cadastrados");
            System.out.println("[5]Listar pets por algum critério");
            System.out.println("[6]Sair");

            String input = sc.nextLine();

            try {
                int opcao = Integer.parseInt(input);
                if (opcao <= 0 || opcao > 6) {
                    System.out.println("Opação invalida. Escolha um numero de 1 a 6.");
                    continue;
                }

                switch (opcao) {
                    case 1:
                        System.out.println("Iniciando Cadastrado de novo pet");
                        ArrayList<String> perguntas =

                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                    case 4:
                        break;
                    case 5:
                        break;
                    case 6:
                        break;
                    default:
                }
            }catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite apenas numeros");
            }
        }
    }
}