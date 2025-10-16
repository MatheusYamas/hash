package semcomentarios.hashing;

import hash.model.Registro;

public class Encadeamento {
    private static class Node {
        Registro registro;
        Node proximo;
        Node(Registro registro) {
            this.registro = registro;
            this.proximo = null;
        }
    }
    private Node[] tabela;
    private int tamanho;

    public Encadeamento(int tamanho) {
        this.tamanho = tamanho;
        this.tabela = new Node[tamanho];
    }
    private int hash(int chave) {
        return chave % this.tamanho;
    }

    public int inserir(Registro registro) {
        int chave = registro.getCodigo();
        int index = hash(chave);
        int colisoes = 0;
        Node novoNode = new Node(registro);

        if (tabela[index] == null) {
            tabela[index] = novoNode;
            return 0;
        }
        else {
            colisoes = 1;
            Node atual = tabela[index];
            while (atual.proximo != null) {
                colisoes++;
                atual = atual.proximo;
            }
            atual.proximo = novoNode;
            return colisoes;
        }
    }

    public boolean buscar(int chave) {
        int index = hash(chave);
        Node atual = tabela[index];
        while (atual != null) {
            if (atual.registro.getCodigo() == chave) {
                return true;
            }
            atual = atual.proximo;
        }
        return false;
    }

    public void analisarListas() {
        Integer[] tamanhos = new Integer[3];
        tamanhos[0] = 0;
        tamanhos[1] = 0;
        tamanhos[2] = 0;

        for (int i = 0; i < this.tamanho; i++) {
            if (tabela[i] != null) {
                int count = 0;
                Node atual = tabela[i];
                while(atual != null) {
                    count++;
                    atual = atual.proximo;
                }

                if (count > tamanhos[0]) {
                    tamanhos[2] = tamanhos[1];
                    tamanhos[1] = tamanhos[0];
                    tamanhos[0] = count;
                } else if (count > tamanhos[1]) {
                    tamanhos[2] = tamanhos[1];
                    tamanhos[1] = count;
                } else if (count > tamanhos[2]) {
                    tamanhos[2] = count;
                }
            }
        }
        System.out.printf("[Encadeamento] 3 Maiores listas: %d, %d, %d\n", tamanhos[0], tamanhos[1], tamanhos[2]);
    }
}