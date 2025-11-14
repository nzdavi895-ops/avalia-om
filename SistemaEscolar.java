import java.util.InputMismatchException;
import java.util.Scanner;

public class SistemaEscolar {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n==============================");
            System.out.println("     SISTEMA ESCOLAR 2.0");
            System.out.println("==============================");
            System.out.println("1 - Cadastrar notas do aluno");
            System.out.println("2 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // limpar buffer

            if (opcao == 1) {
                calcularNotas(scanner);
            } else if (opcao == 2) {
                System.out.println("Saindo... Até logo!");
                break;
            } else {
                System.out.println("Opção inválida.");
            }
        }

        scanner.close();
    }

    public static void calcularNotas(Scanner scanner) {

        System.out.print("\nNome do aluno: ");
        String nome = scanner.nextLine();

        double[] notas = new double[8];
        double[] bimestres = new double[4];
        double[] semestres = new double[2];

        // Entrada das notas com validação
        for (int i = 0; i < notas.length; i++) {
            notas[i] = lerNota(scanner, i + 1);
        }

        // Cálculo das médias bimestrais
        for (int i = 0; i < bimestres.length; i++) {
            bimestres[i] = (notas[2 * i] + notas[2 * i + 1]) / 2.0;
        }

        // Cálculo das médias semestrais
        for (int i = 0; i < semestres.length; i++) {
            semestres[i] = (bimestres[2 * i] + bimestres[2 * i + 1]) / 2.0;
        }

        // Média final
        double mediaFinal = (semestres[0] + semestres[1]) / 2.0;

        // Situação do aluno
        String situacao;
        if (mediaFinal >= 7) {
            situacao = "APROVADO ✅";
        } else if (mediaFinal >= 5) {
            situacao = "RECUPERAÇÃO ⚠️";
        } else {
            situacao = "REPROVADO ❌";
        }

        // Exibição dos resultados
        System.out.println("\n===== BOLETIM FINAL =====");
        System.out.println("Aluno: " + nome);

        for (int i = 0; i < bimestres.length; i++) {
            System.out.printf("%dº Bimestre: %.2f%n", (i + 1), bimestres[i]);
        }

        for (int i = 0; i < semestres.length; i++) {
            System.out.printf("%dº Semestre: %.2f%n", (i + 1), semestres[i]);
        }

        System.out.printf("Média Final: %.2f%n", mediaFinal);
        System.out.println("Situação: " + situacao);
    }

    public static double lerNota(Scanner scanner, int numero) {
        while (true) {
            try {
                System.out.print("Digite a nota " + numero + " (0.0 - 10.0): ");
                double nota = scanner.nextDouble();

                if (nota >= 0 && nota <= 10) {
                    return nota;
                } else {
                    System.out.println("Erro: A nota deve ser entre 0 e 10.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite apenas números (ex: 8.5).");
                scanner.next(); // limpar entrada inválida
            }
        }
    }
}
