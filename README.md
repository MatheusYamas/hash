# Tabelas Hash em Java

## 1. Introdução

Neste trabalho, foi realizada a implementação e a análise de desempenho de três diferentes abordagens para o tratamento de colisões em Tabelas Hash. O objetivo principal é comparar, na prática, a eficiência de cada método sob diferentes cenários de carga de dados e tamanho da tabela, medindo métricas como tempo de inserção, tempo de busca e número de colisões.

---

## 2. Metodologia e Implementação

Para a realização do experimento, foram adotadas as seguintes estratégias e parâmetros:

### Funções de Tratamento de Colisão Implementadas

Foram implementadas três classes distintas, cada uma representando uma técnica de tratamento de colisão:

1.  **Encadeamento Separado (`Encadeamento.java`):** Nesta técnica, as colisões são resolvidas através de listas ligadas. Todos os elementos que colidem em um mesmo índice do vetor são armazenados em uma lista naquela posição.
2.  **Sondagem Linear (`RehashingLinear.java`):** Uma técnica de endereçamento aberto onde, em caso de colisão, a próxima posição sequencial do vetor (`i+1`) é sondada até que um espaço livre seja encontrado.
3.  **Sondagem Quadrática (`RehashingQuadratica.java`):** Uma evolução da sondagem linear que, em caso de colisão, sonda o vetor com saltos quadráticos (`i*i`), visando mitigar o problema de agrupamento primário.

### Funções de Hash (h(chave))

Para garantir a originalidade do trabalho e não utilizar funções prontas dos slides, foram implementadas diferentes funções de hash para cada técnica:

* **Para o Encadeamento:** Foi utilizado o método de hash polinomial, que trata a chave como uma string e calcula o hash com base em seus caracteres.
* **Para a Sondagem Linear:** Foi utilizada uma variação do método da divisão, multiplicando a chave por um número primo (31) para melhorar a distribuição dos dados.
* **Para a Sondagem Quadrática:** Foi implementado o **Método da Multiplicação**, utilizando a constante da razão áurea (A ≈ 0.618) para um espalhamento mais uniforme das chaves.

### Ambiente de Teste

O experimento foi conduzido sob os seguintes parâmetros fixos para garantir uma comparação justa e reprodutível:

* **Tamanhos de Tabela:** `{1009, 10007, 100003}` (valores primos para otimizar a performance).
* **Conjuntos de Dados:** `{100.000, 1.000.000, 10.000.000}` de registros.
* **Reprodutibilidade:** Foi utilizada uma `SEED` fixa para o gerador de números aleatórios, garantindo que os três métodos fossem testados com conjuntos de dados idênticos.

---
## 3. Resultados



---
## 4. Análise dos Resultados
