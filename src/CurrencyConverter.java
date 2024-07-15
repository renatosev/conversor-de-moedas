import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CurrencyConverter {
    private static final String API_BASE_URL = "https://v6.exchangerate-api.com/v6/c1e754a108f5565f332b2dd1/latest/";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Conversion> history = new ArrayList<>();

        boolean running = true;
        while (running) {
            System.out.println("Bem-vindo ao Conversor de Moedas!");
            System.out.println("Escolha uma opção:");
            System.out.println("1. Dólar =>> Peso Argentino");
            System.out.println("2. Peso Argentino =>> Dólar");
            System.out.println("3. Dólar =>> Real Brasileiro");
            System.out.println("4. Real Brasileiro =>> Dólar");
            System.out.println("5. Dólar =>> Peso Colombiano");
            System.out.println("6. Peso Colombiano =>> Dólar");
            System.out.println("7. Ver Histórico");
            System.out.println("0. Sair");
            System.out.print("Opção: ");
            int choice = scanner.nextInt();

            if (choice == 0) {
                running = false;
                break;
            }

            String fromCurrency = "";
            String toCurrency = "";
            switch (choice) {
                case 1:
                    fromCurrency = "USD";
                    toCurrency = "ARS";
                    break;
                case 2:
                    fromCurrency = "ARS";
                    toCurrency = "USD";
                    break;
                case 3:
                    fromCurrency = "USD";
                    toCurrency = "BRL";
                    break;
                case 4:
                    fromCurrency = "BRL";
                    toCurrency = "USD";
                    break;
                case 5:
                    fromCurrency = "USD";
                    toCurrency = "COP";
                    break;
                case 6:
                    fromCurrency = "COP";
                    toCurrency = "USD";
                    break;
                case 7:
                    System.out.println("Histórico de Conversões:");
                    for (Conversion conversion : history) {
                        System.out.println(conversion);
                    }
                    continue;
                default:
                    System.out.println("Opção inválida.");
                    continue;
            }

            double rate = getExchangeRate(fromCurrency, toCurrency);

            System.out.print("Digite a quantidade: ");
            double amount = Double.parseDouble(scanner.next());

            double convertedAmount = amount * rate;
            LocalDateTime dateTime = LocalDateTime.now();
            Conversion conversion = new Conversion(fromCurrency, toCurrency, amount, rate, dateTime);
            history.add(conversion);
            System.out.println(conversion);
        }

        System.out.println("Saindo do Conversor de Moedas...");
        scanner.close();
    }

    private static double getExchangeRate(String fromCurrency, String toCurrency) {
        String apiUrl = API_BASE_URL + fromCurrency;
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(apiUrl))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            JsonObject jsonResponse = JsonParser.parseString(response.body()).getAsJsonObject();
            JsonObject rates = jsonResponse.getAsJsonObject("conversion_rates");
            return rates.get(toCurrency).getAsDouble();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return 0.0;
        }
    }
}
