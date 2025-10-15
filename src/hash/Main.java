package hash;

import hash.analytics.GeradorDados;
import hash.hashing.*;
import hash.model.Registro;

public class Main {
    public static void main(String[] args) {
        final long SEED = 12345L;
        final int[] TAMANHOS_TABELA = {1009, 10007, 100003};
        final int[] TAMANHOS_DADOS = {100_000, 1_000_000, 10_000_000};

        System.out.println("Iniciando Tabela Hash...");
        System.out.println("-------------------------------------------");

        for (int tamanhoTabela : TAMANHOS_TABELA) {
            for (int tamanhoDados : TAMANHOS_DADOS) {

                System.out.printf("\n--- CONFIGURAÇÃO: Tabela de %d, Dados de %d ---\n", tamanhoTabela, tamanhoDados);
                Registro[] dados = GeradorDados.gerar(tamanhoDados, SEED);

                // Teste com encadeamento seperado
                Encadeamento tabelaEncadeada = new Encadeamento(tamanhoTabela);
                long totalColisoesEnc = 0;
                long startTimeEnc = System.nanoTime();
                for (Registro reg : dados) {
                    totalColisoesEnc += tabelaEncadeada.inserir(reg);
                }
                long endTimeEnc = System.nanoTime();
                long duracaoMsEnc = (endTimeEnc - startTimeEnc) / 1_000_000;
                System.out.printf("[Encadeamento] Inserção: %d ms, Colisões: %d\n", duracaoMsEnc, totalColisoesEnc);

                long startTimeBuscaEnc = System.nanoTime();
                for (Registro reg : dados) {
                    tabelaEncadeada.buscar(reg.getCodigo());
                }
                long endTimeBuscaEnc = System.nanoTime();
                long duracaoMsBuscaEnc = (endTimeBuscaEnc - startTimeBuscaEnc) / 1_000_000;
                System.out.printf("[Encadeamento] Busca: %d ms\n", duracaoMsBuscaEnc);
                tabelaEncadeada.analisarListas();


                // teste com sondagem linear
                if (tamanhoDados < tamanhoTabela) {
                    RehashingLinear tabelaLinear = new RehashingLinear(tamanhoTabela);
                    long totalColisoesLinear = 0;
                    long startTimeLinear = System.nanoTime();
                    for (Registro reg : dados) {
                        totalColisoesLinear += tabelaLinear.inserir(reg);
                    }
                    long endTimeLinear = System.nanoTime();
                    long duracaoMsLinear = (endTimeLinear - startTimeLinear) / 1_000_000;
                    System.out.printf("[Linear] Inserção: %d ms, Colisões: %d\n", duracaoMsLinear, totalColisoesLinear);

                    long startTimeBuscaLinear = System.nanoTime();
                    for(Registro reg : dados) {
                        tabelaLinear.buscar(reg.getCodigo());
                    }
                    long endTimeBuscaLinear = System.nanoTime();
                    long duracaoMsBuscaLinear = (endTimeBuscaLinear - startTimeBuscaLinear) / 1_000_000;
                    System.out.printf("[Linear] Busca: %d ms\n", duracaoMsBuscaLinear);
                    tabelaLinear.analisarGaps();

                } else {
                    System.out.println("[Linear] Teste pulado (mais dados do que o tamanho da tabela).");
                }


                // Teste com sondagem quadratica
                if (tamanhoDados < tamanhoTabela) {
                    RehashingQuadratica tabelaQuadratica = new RehashingQuadratica(tamanhoTabela);
                    long totalColisoesQuad = 0;
                    long startTimeQuad = System.nanoTime();
                    for (Registro reg : dados) {
                        totalColisoesQuad += tabelaQuadratica.inserir(reg);
                    }
                    long endTimeQuad = System.nanoTime();
                    long duracaoMsQuad = (endTimeQuad - startTimeQuad) / 1_000_000;
                    System.out.printf("[Quadrática] Inserção: %d ms, Colisões: %d\n", duracaoMsQuad, totalColisoesQuad);

                    long startTimeBuscaQuad = System.nanoTime();
                    for (Registro reg : dados) {
                        tabelaQuadratica.buscar(reg.getCodigo());
                    }
                    long endTimeBuscaQuad = System.nanoTime();
                    long duracaoMsBuscaQuad = (endTimeBuscaQuad - startTimeBuscaQuad) / 1_000_000;
                    System.out.printf("[Quadrática] Busca: %d ms\n", duracaoMsBuscaQuad);
                    tabelaQuadratica.analisarGaps();

                } else {
                    System.out.println("[Quadrática] Teste pulado (mais dados do que o tamanho da tabela).");
                }
            }
        }
        System.out.println("\n--- Experimento Finalizado ---");
    }
}