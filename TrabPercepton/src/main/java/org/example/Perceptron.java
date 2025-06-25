package org.example;

import java.util.*;

public class Perceptron {
    private List<Ponto> entrada;  //input
    private List<Integer> saidas; //output
    private double taxaAprendizado;
    private int maxIteracoes;
    private int bias;
    private double[] pesos;

    public Perceptron(List<Ponto> entrada, List<Integer> saidas, double taxaAprendizado, int maxIteracoes, int bias) {
        this.entrada = entrada;
        this.saidas = saidas;
        this.taxaAprendizado = taxaAprendizado;
        this.maxIteracoes = maxIteracoes;
        this.bias = bias;
        this.pesos = new double[3];
    }

    private int funcaoAtivacaoSignal(double soma) {
        return soma >= 0 ? 1 : -1;
    }

    public void treinar() {
        for (Ponto ponto : entrada) {
            ponto.limiar = bias;
        }

        Random rand = new Random();
        pesos[0] = bias;
        pesos[1] = rand.nextDouble();
        pesos[2] = rand.nextDouble();

        int iteracao = 0;
        boolean aprendeu;

        while (true) {
            aprendeu = true;

            for (int i = 0; i < entrada.size(); i++) {
                Ponto p = entrada.get(i);
                double soma = p.limiar * pesos[0] + p.x * pesos[1] + p.y * pesos[2];
                int saidaGerada = funcaoAtivacaoSignal(soma);

                if (saidaGerada != saidas.get(i)) {
                    aprendeu = false;
                    double erro = saidas.get(i) - saidaGerada;
                    pesos[0] += taxaAprendizado * erro * p.limiar;
                    pesos[1] += taxaAprendizado * erro * p.x;
                    pesos[2] += taxaAprendizado * erro * p.y;
                }
            }

            iteracao++;
            if (aprendeu || iteracao > maxIteracoes) {
                System.out.println("Iteracoes de treinamento: " + iteracao);
                break;
            }
        }
    }

    public void testar(Ponto amostra) {
        amostra.limiar = bias;
        double soma = amostra.limiar * pesos[0] + amostra.x * pesos[1] + amostra.y * pesos[2];
        int saidaGerada = funcaoAtivacaoSignal(soma);

        if (saidaGerada == 1) {
            System.out.println( + saidaGerada + " ou Time Azul");
        } else {
            System.out.println( + saidaGerada + " ou Time Vermelho");
        }
    }
}
