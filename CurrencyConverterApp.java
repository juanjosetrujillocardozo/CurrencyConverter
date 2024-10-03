import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CurrencyConverterApp {

    // Historial de conversiones
    private static List<String> historial = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            mostrarMenu();
            System.out.print("Elija una opción válida: ");
            option = obtenerEntradaNumero(scanner);

            if (option >= 1 && option <= 6) {
                System.out.print("Ingrese el valor que deseas convertir: ");
                double amount = obtenerEntradaDecimal(scanner);

                // Llamar a la función para convertir la moneda
                convertCurrency(option, amount);
            } else if (option == 7) {
                mostrarHistorial();
            }
        } while (option != 8);

        System.out.println("Gracias por usar el Conversor de Monedas.");
        scanner.close();
    }

    private static void mostrarMenu() {
        System.out.println("********************************");
        System.out.println("Bienvenido/a al Conversor de Moneda");
        System.out.println("1) Dólar => Peso argentino");
        System.out.println("2) Peso argentino => Dólar");
        System.out.println("3) Dólar => Real brasileño");
        System.out.println("4) Real brasileño => Dólar");
        System.out.println("5) Dólar => Peso colombiano");
        System.out.println("6) Peso colombiano => Dólar");
        System.out.println("7) Ver historial de conversiones");
        System.out.println("8) Salir");
        System.out.println("********************************");
    }

    private static void convertCurrency(int option, double amount) {
        String baseCurrency = "";
        String targetCurrency = "";

        switch (option) {
            case 1:
                baseCurrency = "USD";
                targetCurrency = "ARS";
                break;
            case 2:
                baseCurrency = "ARS";
                targetCurrency = "USD";
                break;
            case 3:
                baseCurrency = "USD";
                targetCurrency = "BRL";
                break;
            case 4:
                baseCurrency = "BRL";
                targetCurrency = "USD";
                break;
            case 5:
                baseCurrency = "USD";
                targetCurrency = "COP";
                break;
            case 6:
                baseCurrency = "COP";
                targetCurrency = "USD";
                break;
        }

        // Obtener la tasa de cambio desde la API
        String exchangeRateStr = getExchangeRate(baseCurrency, targetCurrency);
        if (exchangeRateStr != null) {
            double exchangeRate = Double.parseDouble(exchangeRateStr);
            double convertedAmount = amount * exchangeRate;
            String resultado = String.format("El valor %.2f [%s] corresponde al valor final de => %.2f [%s]", amount, baseCurrency, convertedAmount, targetCurrency);
            System.out.println(resultado);

            // Guardar en el historial con marca de tiempo
            guardarEnHistorial(amount, baseCurrency, convertedAmount, targetCurrency);
        } else {
            System.out.println("No se pudo obtener la tasa de cambio.");
        }
    }

    // Método para obtener la tasa de cambio desde la API
    private static String getExchangeRate(String baseCurrency, String targetCurrency) {
        try {
            String apiKey = "TU_API_KEY";  // Inserta tu clave de la API aquí
            String apiUrl = "https://api.exchangerate-api.com/v4/latest/" + baseCurrency;

            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                System.out.println("Error: No se pudo obtener la tasa de cambio.");
                return null;
            }

            String jsonResponse = response.body();
            JsonObject jsonObject = JsonParser.parseString(jsonResponse).getAsJsonObject();
            double exchangeRate = jsonObject.getAsJsonObject("rates").get(targetCurrency).getAsDouble();

            return String.valueOf(exchangeRate);

        } catch (java.net.ConnectException e) {
            // Manejo específico para problemas de conexión
            System.out.println("Error de conexión: No se pudo establecer la conexión con la API. Verifique su conexión a internet.");
            return null;
        } catch (Exception e) {
            // Manejo general de otras excepciones
            System.out.println("Error al conectarse a la API o procesar la respuesta.");
            e.printStackTrace();
            return null;
        }
    }

    // Guardar en el historial de conversiones con fecha y hora
    private static void guardarEnHistorial(double amount, String baseCurrency, double convertedAmount, String targetCurrency) {
        String timestamp = LocalDateTime.now().toString();
        String registro = String.format("%s: Convertido %.2f [%s] a %.2f [%s]", timestamp, amount, baseCurrency, convertedAmount, targetCurrency);
        historial.add(registro);
    }

    // Mostrar el historial de conversiones
    private static void mostrarHistorial() {
        System.out.println("=== Historial de conversiones ===");
        if (historial.isEmpty()) {
            System.out.println("No hay conversiones en el historial.");
        } else {
            for (String registro : historial) {
                System.out.println(registro);
            }
        }
        System.out.println("================================");
    }

    // Manejo de entradas numéricas para evitar errores
    private static int obtenerEntradaNumero(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Por favor ingresa un número válido.");
            scanner.next(); // Limpiar entrada no válida
        }
        return scanner.nextInt();
    }

    private static double obtenerEntradaDecimal(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.println("Por favor ingresa un número válido.");
            scanner.next(); // Limpiar entrada no válida
        }
        return scanner.nextDouble();
    }
}
