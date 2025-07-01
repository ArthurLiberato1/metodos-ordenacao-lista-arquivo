# Métodos de Ordenação - Lista Encadeada e Arquivo Binário (Java)

Projeto acadêmico com a implementação de diversos algoritmos de ordenação aplicados em **listas encadeadas** e **arquivos binários**.

---

## 📚 Descrição

Este projeto foi desenvolvido com o objetivo de estudar, implementar e comparar algoritmos de ordenação clássicos e avançados, tanto em memória (usando listas encadeadas) quanto em disco (usando arquivos binários).

Além da implementação, foram incluídas métricas de desempenho como:
- 📊 Quantidade de comparações
- 🔁 Quantidade de movimentações
- ⏱ Tempo de execução

---

## 🧠 Algoritmos Implementados

### ✅ Estudados nas aulas da faculdade:
- Inserção Direta
- Inserção Binária
- Seleção Direta
- Bolha (Bubble Sort)
- Shake Sort
- Shell Sort
- Heap Sort
- Quick Sort (com e sem pivô)
- Merge Sort (2 implementações)

### 🔍 Pesquisados e adicionados:
- Counting Sort
- Bucket Sort
- Radix Sort
- Comb Sort
- Gnome Sort
- Tim Sort

---

## 🗂 Organização

O projeto está dividido em dois módulos principais:

### 1️⃣ Lista Encadeada
Implementação de todos os algoritmos acima utilizando uma estrutura de lista encadeada genérica, simulando ordenação em memória principal.

### 2️⃣ Arquivo Binário
Os algoritmos foram adaptados para trabalhar com arquivos binários contendo registros. Os testes são realizados com três tipos de arquivos:
- Ordenado
- Ordem Reversa
- Randômico

Cada execução gera dados de desempenho e resultados em um **arquivo `.txt`** no formato de tabela.

---

## 📄 Exemplo de Tabela Gerada no arquivo txt

| Método        | Comparações | Movimentações | Tempo (ms) |
|---------------|-------------|----------------|-------------|
| Inserção Dir. |      1023   |       1023     |     5       |
| Quick (pivô)  |      755    |       780      |     2       |
| Merge 2       |      900    |       860      |     3       |

---

## 🚀 Como Executar

1. Clone o repositório:
```bash
git clone https://github.com/ArthurLiberato1/metodos-ordenacao-lista-arquivo.git
