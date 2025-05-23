package br.com.alura.conversordemoedas;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Properties;
import java.util.Scanner;


public class Conversor {
    @SerializedName("conversion_rate")
    private float conversao;

    public float getConversao() {
        return conversao;
    }

    public void recebe(String base, String alvo) throws IOException, InterruptedException {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Digite o valor numérico em " + base + " que deseja converter para " + " "
                + alvo + " (usando vírgula para separar dos centavos):");
        try {
            float valor = entrada.nextFloat();
            converte(valor, base, alvo);
        } catch (InputMismatchException e) {
            System.out.println("O formato não atende ao esperado. Tente digitar um valor numérico válido.");
            recebe(base, alvo);
        }
    }

    public void converte(float valor, String base, String alvo) throws IOException, InterruptedException {
        //Importando a chave da api do arquivo txt pra não expor no commit
        Properties props = new Properties();
        try (InputStream input = new FileInputStream("config/api_key.txt")) {
           props.load(input);
        }
        String apiKey = props.getProperty("MINHA_CHAVE_DE_API");

        //Fazendo a requisição http e convertendo para um objeto com o Gson
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://v6.exchangerate-api.com/v6/" + "apikey" + "/pair/" + base + "/" + alvo))
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient()
                .send(request, HttpResponse.BodyHandlers.ofString());

        Gson gson = new Gson();
        Conversor conversao = gson.fromJson(response.body(), Conversor.class);
        float conversaoFinal = conversao.getConversao() * valor;
        System.out.println("O valor de " + base + " " + String.format("%.2f", valor) + " é " + alvo + " "
                + String.format("%.2f", conversaoFinal) + ".");
    }
}


