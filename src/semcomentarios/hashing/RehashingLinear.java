package semcomentarios.hashing;

import hash.model.Registro;

public class RehashingLinear {
    private Registro[] tabela;
    private int tamanho;

    public RehashingLinear(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new Registro[tamanho];
    }

    private int hash(int chave) {
        return Math.abs(chave * 31) % this.tamanho;
    }

    public int inserir(Registro registro) {
        int chave = registro.getCodigo();
        int index = hash(chave);
        int colisoes = 0;
        while (tabela[index] != null) {
            colisoes++;
            index = (index + 1) % this.tamanho;
        }
        tabela[index] = registro;
        return colisoes;
    }

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

    public void analisarGaps() {
        int maiorGap = 0;
        int menorGap = this.tamanho;
        int gapAtual = 0;
        long somaGaps = 0;
        int totalDeGaps = 0;
        boolean encontrouPrimeiroElemento = false;
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
        if (gapAtual > 0 && encontrouPrimeiroElemento) {
            if (gapAtual > maiorGap) maiorGap = gapAtual;
            if (gapAtual < menorGap) menorGap = gapAtual;
            somaGaps += gapAtual;
            totalDeGaps++;
        }
        double mediaGaps = (totalDeGaps > 0) ? (double)somaGaps / totalDeGaps : 0;
        if (menorGap == this.tamanho) menorGap = 0;
        String nomeDaClasse = this.getClass().getSimpleName();
        System.out.printf("[%s] Gaps: Menor=%d, Maior=%d, Média=%.2f\n", nomeDaClasse, menorGap, maiorGap, mediaGaps);
    }
}