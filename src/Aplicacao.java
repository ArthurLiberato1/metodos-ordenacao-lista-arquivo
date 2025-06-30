public class Aplicacao {
    private ListaDuplamente lista;
    private Arquivo arq;

    public Aplicacao() {
        lista = new ListaDuplamente(null, null);
        lista.geraLista(10);

        arq = new Arquivo("arquivo.dat");
    }

    public void executaLista()
    {
        System.out.println("Lista antes da ordenacao");
        lista.exibir();

        System.out.println("Lista depois da ordenacao");
//        lista.insercaoDireta();
//        lista.insercaoBinaria();
//        lista.selecaoDireta();
//        lista.bubbleSort();
//        lista.shakeSort();
//        lista.shellSort();
//        lista.heapSort();
//        lista.quickSortSemPivo();
//        lista.quickComPivo();
//        lista.mergeSort1();
//        lista.mergeSort2();
//        lista.countingSort();
//        lista.bucketSort();
//        lista.radixSort();
//        lista.combSort();
//        lista.gnomeSort();
//        lista.timSort();

        lista.exibir();
    }

    public void executaArquivo()
    {
//        System.out.println("Inserção Direta");
//        arq.geraArquivoRandomico();
//        arq.exibe();
//        arq.insercaoDireta();
//        arq.exibe();
//
//        System.out.println("Inserção Binária");
//        arq.geraArquivoOrdenado();
//        arq.exibe();
//        arq.insercaoBinaria();
//        arq.exibe();
//
//        System.out.println("Seleção Direta");
//        arq.geraArquivoReverso();
//        arq.exibe();
//        arq.selecaoDireta();
//        arq.exibe();
//
//        System.out.println("Bubble Sort");
//        arq.geraArquivoOrdenado();
//        arq.exibe();
//        arq.bubbleSort();
//        arq.exibe();
//
//        System.out.println("Shake Sort");
//        arq.geraArquivoReverso();
//        arq.exibe();
//        arq.shakeSort();
//        arq.exibe();
//
//        System.out.println("Heap Sort");
//        arq.geraArquivoOrdenado();
//        arq.exibe();
//        arq.heapSort();
//        arq.exibe();
//
//        System.out.println("Shell Sort");
//        arq.geraArquivoReverso();
//        arq.exibe();
//        arq.shellSort();
//        arq.exibe();
//
//        System.out.println("Quick Sort sem Pivo");
//        arq.geraArquivoOrdenado();
//        arq.exibe();
//        System.out.println("Quick Sort sem Pivo: \n");
//        arq.quickSortSemPivo();
//        arq.exibe();
//
//        System.out.println("Quick Sort com Pivo");
//        arq.geraArquivoOrdenado();
//        arq.exibe();
//        System.out.println("Quick Sort com Pivo: \n");
//        arq.quickSortComPivo();
//        arq.exibe();
//

//        System.out.println("Merge Sort 1");
//        arq.geraArquivoOrdenado();
//        arq.exibe();
//        System.out.println("Merge Sort 1: \n");
//        arq.mergeSort1();
//        arq.exibe();
//
//        System.out.println("Merge Sort 2");
//        arq.geraArquivoReverso();
//        arq.exibe();
//        arq.mergeSort2();
//        arq.exibe();
//
//        System.out.println("Counting Sort");
//        arq.geraArquivoOrdenado();
//        arq.exibe();
//        arq.countingSort();
//        arq.exibe();
//
//        System.out.println("Bucket Sort");
//        arq.geraArquivoReverso();
//        arq.exibe();
//        arq.bucketSort();
//        arq.exibe();
//
//        System.out.println("Radix Sort");
//        arq.geraArquivoReverso();
//        arq.exibe();
//        arq.radixSort();
//        arq.exibe();
//
//        System.out.println("Comb Sort");
//        arq.geraArquivoOrdenado();
//        arq.exibe();
//        arq.combSort();
//        arq.exibe();
//
//        System.out.println("Gnome Sort");
//        arq.geraArquivoReverso();
//        arq.exibe();
//        arq.gnomeSort();
//        arq.exibe();

//        System.out.println("Tim Sort");
//        arq.geraArquivoReverso();
//        arq.exibe();
//        arq.timSort();
//        arq.exibe();

    }

    public static void main(String[] args) {
        Aplicacao ap = new Aplicacao();
      //  ap.executaArquivo();
     //   ap.executaLista();

        //tabela
        Relatorio relatorio = new Relatorio();
        relatorio.gerarTabela();
    }
}