package org.example;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            ProdutoService produtoService = new ProdutoService();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("1. Adicionar Produto");
                System.out.println("2. Buscar Produto");
                System.out.println("3. Atualizar Produto");
                System.out.println("4. Deletar Produto");
                System.out.println("5. Listar Todos Produtos");
                System.out.println("6. Sair");
                System.out.print("Escolha uma opção: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        System.out.print("Tipo: ");
                        String tipo = scanner.nextLine();
                        System.out.print("Descrição: ");
                        String descricao = scanner.nextLine();
                        System.out.print("Peso: ");
                        double peso = scanner.nextDouble();
                        System.out.print("Quantidade: ");
                        int quantidade = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        System.out.print("Unidade (metro, metro quadrado, litro, kg): ");
                        String unidade = scanner.nextLine();
                        Produto produto = new Produto(0, tipo, descricao, peso, quantidade, unidade);
                        produtoService.addProduto(produto);
                        break;
                    case 2:
                        System.out.print("ID do produto: ");
                        int id = scanner.nextInt();
                        Produto p = produtoService.getProduct(id);
                        if (p != null) {
                            System.out.println(p);
                        } else {
                            System.out.println("Produto não encontrado.");
                        }
                        break;
                    case 3:
                        System.out.print("ID do produto: ");
                        int idToUpdate = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        System.out.print("Novo Tipo: ");
                        String newTipo = scanner.nextLine();
                        System.out.print("Nova Descrição: ");
                        String newDescricao = scanner.nextLine();
                        System.out.print("Novo Peso: ");
                        double newPeso = scanner.nextDouble();
                        System.out.print("Nova Quantidade: ");
                        int newQuantidade = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        System.out.print("Nova Unidade (metro, metro quadrado, litro, kg): ");
                        String newUnidade = scanner.nextLine();
                        Produto updatedProduto = new Produto(idToUpdate, newTipo, newDescricao, newPeso, newQuantidade, newUnidade);
                        produtoService.updateProduto(updatedProduto);
                        break;
                    case 4:
                        System.out.print("ID do produto: ");
                        int idToDelete = scanner.nextInt();
                        produtoService.deleteProduto(idToDelete);
                        break;
                    case 5:
                        for (Produto prod : produtoService.getAllProdutos()) {
                            System.out.println(prod);
                        }
                        break;
                    case 6:
                        System.exit(0);
                    default:
                        System.out.println("Opção inválida.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
