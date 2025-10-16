package semcomentarios.analytics;

import hash.model.Registro;

import java.util.Random;

public class GeradorDados {
    public static Registro[] gerar(int quantidade, long seed) {
        Registro[] dados = new Registro[quantidade];
        Random random = new Random(seed);
        int maxCodigo = 999_999_999;

        System.out.println("Gerando " + quantidade + " dados aleatórios...");
        for (int i = 0; i < quantidade; i++) {
            dados[i] = new Registro(random.nextInt(maxCodigo + 1));
        }
        System.out.println("Dados gerados.");

        return dados;
    }
}
