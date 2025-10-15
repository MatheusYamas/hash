package hash.hashing;

import hash.model.Registro;

public class RehashingLinear {
    private Registro[] tabela;
    private int tamanho;

    public RehashingLinear(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new Registro[tamanho];
    }

    // Multiplicação por um número primo para melhorar o espalhamento.
    private int hash(int chave) {
        return Math.abs(chave * 31) % this.tamanho;
    }

    // Método inserir
    public int inserir(Registro registro) {
        int chave = registro.getCodigo();
        int index = hash(chave);
        int colisoes = 0;

        //busca para inserir
        while (tabela[index] != null) {
            colisoes++;
            index = (index + 1) % this.tamanho;
        }
        tabela[index] = registro;
        return colisoes;
    }

    // metodo buscar
    public boolean buscar(int chave) {
        int index = hash(chave);
        int i = 0;
        while (tabela[index] != null) {
            if (tabela[index].getCodigo() == chave) {
                return true;
            }
            index = (index + 1) % this.tamanho;
            if (i++ > this.tamanho) break;
        }
        return false;
    }

    // (O método analisarGaps
    public void analisarGaps() {
        int maiorGap = 0;
        // Inicia o menorGap com um valor impossivelmente alto
        int menorGap = this.tamanho;
        int gapAtual = 0;
        long somaGaps = 0;
        int totalDeGaps = 0;
        boolean encontrouPrimeiroElemento = false;

        // Percorre toda a tabela para encontrar os espaços vazios
        for (int i = 0; i < this.tamanho; i++) {
            if (tabela[i] == null) {
                gapAtual++;
            }
            else {
                encontrouPrimeiroElemento = true;
                if (gapAtual > 0) {
                    if (gapAtual > maiorGap) maiorGap = gapAtual;
                    if (gapAtual < menorGap) menorGap = gapAtual;
                    somaGaps += gapAtual;
                    totalDeGaps++;
                }
                gapAtual = 0;
            }
        }

        // Caso especial: se a tabela terminar com um gap, precisamos contá-lo também
        if (gapAtual > 0 && encontrouPrimeiroElemento) {
            if (gapAtual > maiorGap) maiorGap = gapAtual;
            if (gapAtual < menorGap) menorGap = gapAtual;
            somaGaps += gapAtual;
            totalDeGaps++;
        }

        // Calcula a média. Se não houver gaps, a média é 0.
        double mediaGaps = (totalDeGaps > 0) ? (double)somaGaps / totalDeGaps : 0;

        // Se a tabela estiver cheia ou vazia, não existe "menor gap"
        if (menorGap == this.tamanho) menorGap = 0;

        // Imprime o resultado formatado
        String nomeDaClasse = this.getClass().getSimpleName();
        System.out.printf("[%s] Gaps: Menor=%d, Maior=%d, Média=%.2f\n", nomeDaClasse, menorGap, maiorGap, mediaGaps);
    }
}