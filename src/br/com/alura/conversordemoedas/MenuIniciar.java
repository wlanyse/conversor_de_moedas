package br.com.alura.conversordemoedas;

import java.io.IOException;
import java.util.Scanner;

public class MenuIniciar {
    private String opcao;

    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }

public void menuIniciar() throws IOException, InterruptedException {
    Scanner entrada = new Scanner(System.in);

    System.out.println("******************************************************");
    System.out.println("Seja bem-vindo(a) ao Conversor de Moedas : )\n");
    System.out.println("1) Dólar ==>> Peso Argentino");
    System.out.println("2) Peso Argentino ==>> Dólar");
    System.out.println("3) Dólar ==>> Real Brasileiro");
    System.out.println("4) Real Brasileiro ==>> Dólar");
    System.out.println("5) Dólar ==>> Peso Colombiano");
    System.out.println("6) Peso Colombiano ==>> Dólar");
    System.out.println("7) Sair");
    System.out.println("****************************************************");
    System.out.println("Escolha uma opção: ");
    setOpcao(entrada.nextLine());
    System.out.println(" ");
    decide();
}

    public void decide() throws IOException, InterruptedException {

        if(getOpcao().equals("1")){
            Conversor conversor = new Conversor();
            conversor.recebe("USD", "ARS");
            menuIniciar();
        } else if (getOpcao().equals("2")) {
            Conversor conversor = new Conversor();
            conversor.recebe("ARS", "USD");
            menuIniciar();
        } else if (getOpcao().equals("3")) {
            Conversor conversor = new Conversor();
            conversor.recebe("USD", "BRL");
            menuIniciar();
        } else if (getOpcao().equals("4")) {
            Conversor conversor = new Conversor();
            conversor.recebe("BRL", "USD");
            menuIniciar();
        } else if (getOpcao().equals("5")) {
            Conversor conversor = new Conversor();
            conversor.recebe("USD", "COP");
            menuIniciar();
        } else if (getOpcao().equals("6")) {
            Conversor conversor = new Conversor();
            conversor.recebe("COP", "USD");
            menuIniciar();
        } else if (getOpcao().equals("7")) {
            System.out.println("Saindo da aplicação.");
        } else {
            System.out.println("Opção inválida, tente novamente!");
            menuIniciar();
        }
    }
}







