import java.util.Locale;
import java.util.Scanner;

public class ConversorTemperatura {

    // Converte Celsius → Fahrenheit
    public static double celsiusParaFahrenheit(double c) {
        return (c * 9.0/5.0) + 32.0;
    }

    // Converte Celsius → Kelvin
    public static double celsiusParaKelvin(double c) {
        return c + 273.15;
    }

    // Converte Fahrenheit → Celsius
    public static double fahrenheitParaCelsius(double f) {
        return (f - 32.0) * 5.0/9.0;
    }

    // Converte Kelvin → Celsius
    public static double kelvinParaCelsius(double k) {
        return k - 273.15;
    }

    // Lê um inteiro com validação
    private static int lerInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor digite um número inteiro.");
            }
        }
    }

    // Lê um double com validação; aceita vírgula ou ponto
    private static double lerDouble(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim().replace(',', '.');
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor digite um número (ex: 12.5 ou 12,5).");
            }
        }
    }

    public static void main(String[] args) {
        // Força Locale.US para imprimir com ponto decimal no printf, se preferir vírgula
        // mude ou remova esta linha de acordo com o locale desejado.
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n====== CONVERSOR DE TEMPERATURA ======");
            System.out.println("1 - Celsius → Fahrenheit / Kelvin");
            System.out.println("2 - Fahrenheit → Celsius / Kelvin");
            System.out.println("3 - Kelvin → Celsius / Fahrenheit");
            System.out.println("0 - Sair");

            opcao = lerInt(scanner, "Escolha uma opção: ");

            switch (opcao) {
                case 1: {
                    double c = lerDouble(scanner, "\nDigite a temperatura em °C: ");
                    System.out.printf("Fahrenheit: %.2f °F%n", celsiusParaFahrenheit(c));
                    System.out.printf("Kelvin: %.2f K%n", celsiusParaKelvin(c));
                    break;
                }

                case 2: {
                    double f = lerDouble(scanner, "\nDigite a temperatura em °F: ");
                    double cFromF = fahrenheitParaCelsius(f);
                    System.out.printf("Celsius: %.2f °C%n", cFromF);
                    System.out.printf("Kelvin: %.2f K%n", celsiusParaKelvin(cFromF));
                    break;
                }

                case 3: {
                    double k = lerDouble(scanner, "\nDigite a temperatura em K: ");
                    if (k < 0) {
                        System.out.println("Kelvin não pode ser negativo. Verifique o valor e tente novamente.");
                        break;
                    }
                    double cFromK = kelvinParaCelsius(k);
                    System.out.printf("Celsius: %.2f °C%n", cFromK);
                    System.out.printf("Fahrenheit: %.2f °F%n", celsiusParaFahrenheit(cFromK));
                    break;
                }

                case 0:
                    System.out.println("Encerrando...");
                    break;

                default:
                    System.out.println("Opção inválida. Escolha 0, 1, 2 ou 3.");
            }
        }

        scanner.close();
    }
}