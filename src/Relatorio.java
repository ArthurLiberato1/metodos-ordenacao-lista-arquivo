public class Relatorio {

    Arquivo tabela;
    Arquivo arqOrd, arqRev, arqRand;
    Arquivo auxOrd, auxRev, auxRand;
    long totalReg,inicio,fim,totalOrd,compOrd,movOrd,compRev,movRev,compRand,movRand,totalRev,totalRand;

    //abertura dos arquivos aqui
    public Relatorio() {
        arqOrd = new Arquivo("arquivoOrdenado.dat");
        arqRev = new Arquivo("arquivoReverso.dat");
        arqRand = new Arquivo("arquivoRandomico.dat");
        auxOrd = new Arquivo("auxOrdenado.dat");
        auxRev = new Arquivo("auxReverso.dat");
        auxRand = new Arquivo("auxRandomico.dat");
    }

    public void gravarColunas() {
        String linha = String.format(
                "%-18s|%-60s|%-60s|%-60s|%n%-18s|%12s |%12s |%12s |%12s |%8s |%12s |%12s |%12s |%12s |%8s |%12s |%12s |%12s |%12s |%8s |",
                "Metodos Ordenacao",
                "Arquivo Ordenado",
                "Arquivo em Ordem Reversa",
                "Arquivo Randomico",
                "",
                "Comp.Prog.", "Comp.Equa.", "Mov.Prog", "Mov.Equa.", "Tempo",
                "Comp.Prog.", "Comp.Equa.", "Mov.Prog", "Mov.Equa.", "Tempo",
                "Comp.Prog.", "Comp.Equa.", "Mov.Prog", "Mov.Equa.", "Tempo"
        );

        tabela.gravaString(linha);
    }

    public void gravarMetodo(String metodo, Resultado ord, Resultado rev, Resultado rand) {
        String linha = "\n" + String.format("%-18s|", metodo)
                + ord.formatar() + rev.formatar() + rand.formatar();

        tabela.gravaString(linha);
    }

    public void gerarTabela(){
        tabela = new Arquivo("arquivoTabela.txt");
        this.gravarColunas();

        double compEqOrd,movEqOrd,compEqRev,movEqRev,compEqRand,movEqRand;

        arqOrd.geraArquivoOrdenado();
        arqRev.geraArquivoReverso();
        arqRand.geraArquivoRandomico();
        totalReg = arqOrd.getQtdRegistros();

        //---INSERCAO DIRETA---//
        System.out.println("insercao direta comecou");

        // ORDENADO
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp(); auxOrd.initMov();
        inicio = System.currentTimeMillis();
        auxOrd.insercaoDireta();
        fim = System.currentTimeMillis();
        totalOrd = (fim - inicio) / 1000;
        compOrd = auxOrd.getComp();
        movOrd = auxOrd.getMov();
        compEqOrd = totalReg - 1;
        movEqOrd = 3 * (totalReg - 1);
        Resultado resultOrdenadoID = new Resultado(compOrd, compEqOrd, movOrd, movEqOrd, totalOrd);
        System.out.println("ordenado finalizou");

        // REVERSO
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp(); auxRev.initMov();
        inicio = System.currentTimeMillis();
        auxRev.insercaoDireta();
        fim = System.currentTimeMillis();
        totalRev = (fim - inicio) / 1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        compEqRev = (double) (totalReg * totalReg + totalReg - 4) / 4;
        movEqRev = (double) (totalReg * totalReg + 3 * totalReg - 4) / 2;
        Resultado resultReversoID = new Resultado(compRev, compEqRev, movRev, movEqRev, totalRev);
        System.out.println("reverso finalizou");

        // RANDOMICO
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();auxRand.initMov();
        inicio = System.currentTimeMillis();
        auxRand.insercaoDireta();
        fim = System.currentTimeMillis();
        totalRand = (fim - inicio) / 1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        compEqRand = (double) (totalReg * totalReg + totalReg - 2) / 4;
        movEqRand = (double) (totalReg * totalReg + 9 * totalReg - 10) / 4;
        Resultado resultRandomicoID = new Resultado(compRand, compEqRand, movRand, movEqRand, totalRand);
        System.out.println("randomico finalizou");

        System.out.println("insercao direta terminou\n");

        // Grava na tabela
        gravarMetodo("Insercao Direta", resultOrdenadoID, resultReversoID, resultRandomicoID);

        //*SELECAO DIRETA*
        System.out.println("Seleção Direta Inicio");

        // ORDENADO
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        inicio = System.currentTimeMillis();
        auxOrd.selecaoDireta();
        fim = System.currentTimeMillis();
        totalOrd = (fim - inicio) / 1000;
        compOrd = auxOrd.getComp();
        movOrd = auxOrd.getMov();
        compEqOrd = (double) (totalReg * totalReg - totalReg) / 2;
        movEqOrd = 3 * (totalReg - 1);
        Resultado resultOrdenadoSD = new Resultado(compOrd, compEqOrd, movOrd, movEqOrd, totalOrd);
        System.out.println("Ordenado finalizou");

        // REVERSO
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        inicio = System.currentTimeMillis();
        auxRev.selecaoDireta();
        fim = System.currentTimeMillis();
        totalRev = (fim - inicio) / 1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        compEqRev = (double) (totalReg * totalReg - totalReg) / 2;
        movEqRev = (double) (totalReg * totalReg + 3 * totalReg - 4) / 2;
        Resultado resultReversoSD = new Resultado(compRev, compEqRev, movRev, movEqRev, totalRev);
        System.out.println("Reverso finalizou");

        // RANDOMICO
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        inicio = System.currentTimeMillis();
        auxRand.selecaoDireta();
        fim = System.currentTimeMillis();
        totalRand = (fim - inicio) / 1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        compEqRand = (double) (totalReg * totalReg - totalReg) / 2;
        movEqRand = (totalReg * (Math.log10(totalReg) + 0.577216));
        Resultado resultRandomicoSD = new Resultado(compRand, compEqRand, movRand, movEqRand, totalRand);
        System.out.println("Randomico finalizou");

        System.out.println("Seleção direta terminou\n");

        // Grava na tabela
        gravarMetodo("Selecao Direta", resultOrdenadoSD, resultReversoSD, resultRandomicoSD);

        //*INSERCAO BINARIA*
        System.out.println("Inserção Binária Inicio");

        // ORDENADO
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        inicio = System.currentTimeMillis();
        auxOrd.insercaoBinaria();
        fim = System.currentTimeMillis();
        totalOrd = (fim - inicio) / 1000;
        compOrd = auxOrd.getComp();
        movOrd = auxOrd.getMov();
        compEqOrd = totalReg * (Math.log10(totalReg) - Math.log(Math.E) + 0.5);
        movEqOrd = 3 * (totalReg - 1);
        Resultado resultOrdenadoIB = new Resultado(compOrd, compEqOrd, movOrd, movEqOrd, totalOrd);
        System.out.println("Ordenado finalizou");

        // REVERSO
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        inicio = System.currentTimeMillis();
        auxRev.insercaoBinaria();
        fim = System.currentTimeMillis();
        totalRev = (fim - inicio) / 1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        compEqRev = totalReg * (Math.log10(totalReg) - Math.log(Math.E) + 0.5);
        movEqRev = (double) (totalReg * totalReg + 3 * totalReg - 4) / 2;
        Resultado resultReversoIB = new Resultado(compRev, compEqRev, movRev, movEqRev, totalRev);
        System.out.println("Reverso finalizou");

        // RANDOMICO
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        inicio = System.currentTimeMillis();
        auxRand.insercaoBinaria();
        fim = System.currentTimeMillis();
        totalRand = (fim - inicio) / 1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        compEqRand = totalReg * (Math.log10(totalReg) - Math.log(Math.E) + 0.5);
        movEqRand = (double) (totalReg * totalReg + 9 * totalReg - 10) / 4;
        Resultado resultRandomicoIB = new Resultado(compRand, compEqRand, movRand, movEqRand, totalRand);
        System.out.println("Randomico finalizou");

        System.out.println("Inserção Binária terminou\n");

        // Grava na tabela
        gravarMetodo("Insercao Binaria", resultOrdenadoIB, resultReversoIB, resultRandomicoIB);

        //*BUBBLE SORT*
        System.out.println("Bubble Sort Inicio");

        // ORDENADO
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        inicio = System.currentTimeMillis();
        auxOrd.bubbleSort();
        fim = System.currentTimeMillis();
        totalOrd = (fim - inicio) / 1000;
        compOrd = auxOrd.getComp();
        movOrd = auxOrd.getMov();
        compEqOrd = (double) (totalReg * totalReg - totalReg) / 2;
        movEqOrd = 0;
        Resultado resultOrdenadoBS = new Resultado(compOrd, compEqOrd, movOrd, movEqOrd, totalOrd);
        System.out.println("Ordenado finalizou");

        // REVERSO
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        inicio = System.currentTimeMillis();
        auxRev.bubbleSort();
        fim = System.currentTimeMillis();
        totalRev = (fim - inicio) / 1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        compEqRev = (double) (totalReg * totalReg - totalReg) / 2;
        movEqRev = (double) (3 * (totalReg * totalReg - totalReg)) / 4;
        Resultado resultReversoBS = new Resultado(compRev, compEqRev, movRev, movEqRev, totalRev);
        System.out.println("Reverso finalizou");

        // RANDOMICO
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        inicio = System.currentTimeMillis();
        auxRand.bubbleSort();
        fim = System.currentTimeMillis();
        totalRand = (fim - inicio) / 1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        compEqRand = (double) (totalReg * totalReg - totalReg) / 2;
        movEqRand = (double) (3 * (totalReg * totalReg - totalReg)) / 4;
        Resultado resultRandomicoBS = new Resultado(compRand, compEqRand, movRand, movEqRand, totalRand);
        System.out.println("Randomico finalizou");

        System.out.println("Bubble Sort terminou\n");

        // Grava na tabela
        gravarMetodo("Bubble Sort", resultOrdenadoBS, resultReversoBS, resultRandomicoBS);

        //*SHAKE SORT*
        System.out.println("Shake Sort Inicio");

        // ORDENADO
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        inicio = System.currentTimeMillis();
        auxOrd.shakeSort();
        fim = System.currentTimeMillis();
        totalOrd = (fim - inicio) / 1000;
        compOrd = auxOrd.getComp();
        movOrd = auxOrd.getMov();
        compEqOrd = (double) (totalReg * totalReg - totalReg) / 2;
        movEqOrd = 0;
        Resultado resultOrdenadoSS = new Resultado(compOrd, compEqOrd, movOrd, movEqOrd, totalOrd);
        System.out.println("Ordenado finalizou");

        // REVERSO
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        inicio = System.currentTimeMillis();
        auxRev.shakeSort();
        fim = System.currentTimeMillis();
        totalRev = (fim - inicio) / 1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        compEqRev = (double) (totalReg * totalReg - totalReg) / 2;
        movEqRev = (double) (3 * (totalReg * totalReg - totalReg)) / 4;
        Resultado resultReversoSS = new Resultado(compRev, compEqRev, movRev, movEqRev, totalRev);
        System.out.println("Reverso finalizou");

        // RANDOMICO
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        inicio = System.currentTimeMillis();
        auxRand.shakeSort();
        fim = System.currentTimeMillis();
        totalRand = (fim - inicio) / 1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        compEqRand = (double) (totalReg * totalReg - totalReg) / 2;
        movEqRand = (double) (3 * (totalReg * totalReg - totalReg)) / 4;
        Resultado resultRandomicoSS = new Resultado(compRand, compEqRand, movRand, movEqRand, totalRand);
        System.out.println("Randomico finalizou");

        System.out.println("Shake Sort terminou\n");

        // Grava na tabela
        gravarMetodo("Shake Sort", resultOrdenadoSS, resultReversoSS, resultRandomicoSS);

        //*SHELL SORT*
        System.out.println("Shell Sort Inicio");

        // ORDENADO
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        inicio = System.currentTimeMillis();
        auxOrd.shellSort();
        fim = System.currentTimeMillis();
        totalOrd = (fim - inicio) / 1000;
        compOrd = auxOrd.getComp();
        movOrd = auxOrd.getMov();
        compEqOrd = 0;
        movEqOrd = 0;
        Resultado resultOrdenadoSH = new Resultado(compOrd, compEqOrd, movOrd, movEqOrd, totalOrd);
        System.out.println("Ordenado finalizou");

        // REVERSO
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        inicio = System.currentTimeMillis();
        auxRev.shellSort();
        fim = System.currentTimeMillis();
        totalRev = (fim - inicio) / 1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        compEqRev = 0;
        movEqRev = 0;
        Resultado resultReversoSH = new Resultado(compRev, compEqRev, movRev, movEqRev, totalRev);
        System.out.println("Reverso finalizou");

        // RANDOMICO
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        inicio = System.currentTimeMillis();
        auxRand.shellSort();
        fim = System.currentTimeMillis();
        totalRand = (fim - inicio) / 1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        compEqRand = 0;
        movEqRand = 0;
        Resultado resultRandomicoSH = new Resultado(compRand, compEqRand, movRand, movEqRand, totalRand);
        System.out.println("Randomico finalizou");

        System.out.println("Shell Sort terminou\n");

        // Grava na tabela
        gravarMetodo("Shell Sort", resultOrdenadoSH, resultReversoSH, resultRandomicoSH);

        //*HEAP SORT*
        System.out.println("Heap Sort Inicio");

        // ORDENADO
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        inicio = System.currentTimeMillis();
        auxOrd.heapSort();
        fim = System.currentTimeMillis();
        totalOrd = (fim - inicio) / 1000;
        compOrd = auxOrd.getComp();
        movOrd = auxOrd.getMov();
        compEqOrd = 0;
        movEqOrd = 0;
        Resultado resultOrdenadoHP = new Resultado(compOrd, compEqOrd, movOrd, movEqOrd, totalOrd);
        System.out.println("Ordenado finalizou");

        // REVERSO
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        inicio = System.currentTimeMillis();
        auxRev.heapSort();
        fim = System.currentTimeMillis();
        totalRev = (fim - inicio) / 1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        compEqRev = 0;
        movEqRev = 0;
        Resultado resultReversoHP = new Resultado(compRev, compEqRev, movRev, movEqRev, totalRev);
        System.out.println("Reverso finalizou");

        // RANDOMICO
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        inicio = System.currentTimeMillis();
        auxRand.heapSort();
        fim = System.currentTimeMillis();
        totalRand = (fim - inicio) / 1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        compEqRand = 0;
        movEqRand = 0;
        Resultado resultRandomicoHP = new Resultado(compRand, compEqRand, movRand, movEqRand, totalRand);
        System.out.println("Randomico finalizou");

        System.out.println("Heap Sort terminou\n");

        // Grava na tabela
        gravarMetodo("Heap Sort", resultOrdenadoHP, resultReversoHP, resultRandomicoHP);

        //*QUICK SEM PIVO*
        System.out.println("Quick Sort s/ pivô Inicio");

        // ORDENADO
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        inicio = System.currentTimeMillis();
        auxOrd.quickSortSemPivo();
        fim = System.currentTimeMillis();
        totalOrd = (fim - inicio) / 1000;
        compOrd = auxOrd.getComp();
        movOrd = auxOrd.getMov();
        compEqOrd = 0;
        movEqOrd = 0;
        Resultado resultOrdenadoQSP = new Resultado(compOrd, compEqOrd, movOrd, movEqOrd, totalOrd);
        System.out.println("Ordenado finalizou");

        // REVERSO
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        inicio = System.currentTimeMillis();
        auxRev.quickSortSemPivo();
        fim = System.currentTimeMillis();
        totalRev = (fim - inicio) / 1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        compEqRev = 0;
        movEqRev = 0;
        Resultado resultReversoQSP = new Resultado(compRev, compEqRev, movRev, movEqRev, totalRev);
        System.out.println("Reverso finalizou");

        // RANDOMICO
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        inicio = System.currentTimeMillis();
        auxRand.quickSortSemPivo();
        fim = System.currentTimeMillis();
        totalRand = (fim - inicio) / 1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        compEqRand = 0;
        movEqRand = 0;
        Resultado resultRandomicoQSP = new Resultado(compRand, compEqRand, movRand, movEqRand, totalRand);
        System.out.println("Randomico finalizou");

        System.out.println("Quick Sort s/ pivô terminou\n");

        // Grava na tabela
        gravarMetodo("Quick Sort S/ Pivo", resultOrdenadoQSP, resultReversoQSP, resultRandomicoQSP);

        //*QUICK COM PIVO*
        System.out.println("Quick Sort c/ pivo Inicio");

        // ORDENADO
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        inicio = System.currentTimeMillis();
        auxOrd.quickSortComPivo();
        fim = System.currentTimeMillis();
        totalOrd = (fim - inicio) / 1000;
        compOrd = auxOrd.getComp();
        movOrd = auxOrd.getMov();
        compEqOrd = 0;
        movEqOrd = 0;
        Resultado resultOrdenadoQCP = new Resultado(compOrd, compEqOrd, movOrd, movEqOrd, totalOrd);
        System.out.println("Ordenado finalizou");

        // REVERSO
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        inicio = System.currentTimeMillis();
        auxRev.quickSortComPivo();
        fim = System.currentTimeMillis();
        totalRev = (fim - inicio) / 1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        compEqRev = 0;
        movEqRev= 0;
        Resultado resultReversoQCP = new Resultado(compRev, compEqRev, movRev, movEqRev, totalRev);
        System.out.println("Reverso finalizou");

        // RANDOMICO
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        inicio = System.currentTimeMillis();
        auxRand.quickSortComPivo();
        fim = System.currentTimeMillis();
        totalRand = (fim - inicio) / 1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        compEqRand = 0;
        movEqRand = 0;
        Resultado resultRandomicoQCP = new Resultado(compRand, compEqRand, movRand, movEqRand, totalRand);
        System.out.println("Randomico finalizou");

        System.out.println("Quick Sort c/ pivô terminou\n");

        // Grava na tabela
        gravarMetodo("Quick Sort C/ Pivo", resultOrdenadoQCP, resultReversoQCP, resultRandomicoQCP);

        //*MERGE 1*
        System.out.println("Merge Sort 1 Inicio");

        // ORDENADO
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        inicio = System.currentTimeMillis();
        auxOrd.mergeSort1();
        fim = System.currentTimeMillis();
        totalOrd = (fim - inicio) / 1000;
        compOrd = auxOrd.getComp();
        movOrd = auxOrd.getMov();
        compEqOrd = 0;
        movEqOrd = 0;
        Resultado resultOrdenadoM1 = new Resultado(compOrd, compEqOrd, movOrd, movEqOrd, totalOrd);
        System.out.println("Ordenado finalizou");

        // REVERSO
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        inicio = System.currentTimeMillis();
        auxRev.mergeSort1();
        fim = System.currentTimeMillis();
        totalRev = (fim - inicio) / 1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        compEqRev = 0;
        movEqRev = 0;
        Resultado resultReversoM1 = new Resultado(compRev, compEqRev, movRev, movEqRev, totalRev);
        System.out.println("Reverso finalizou");

        // RANDOMICO
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        inicio = System.currentTimeMillis();
        auxRand.mergeSort1();
        fim = System.currentTimeMillis();
        totalRand = (fim - inicio) / 1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        compEqRand = 0;
        movEqRand = 0;
        Resultado resultRandomicoM1 = new Resultado(compRand, compEqRand, movRand, movEqRand, totalRand);
        System.out.println("Randomico finalizou");

        System.out.println("Merge Sort 1 terminou\n");

        // Grava na tabela
        gravarMetodo("Merge Sort 1", resultOrdenadoM1, resultReversoM1, resultRandomicoM1);

        //*MERGE 2*
        System.out.println("Merge Sort 2 Inicio");

        // ORDENADO
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        inicio = System.currentTimeMillis();
        auxOrd.mergeSort2();
        fim = System.currentTimeMillis();
        totalOrd = (fim - inicio) / 1000;
        compOrd = auxOrd.getComp();
        movOrd = auxOrd.getMov();
        compEqOrd = 0;
        movEqOrd = 0;
        Resultado resultOrdenadoM2 = new Resultado(compOrd, compEqOrd, movOrd, movEqOrd, totalOrd);
        System.out.println("Ordenado finalizou");

        // REVERSO
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        inicio = System.currentTimeMillis();
        auxRev.mergeSort2();
        fim = System.currentTimeMillis();
        totalRev = (fim - inicio) / 1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        compEqOrd = 0;
        movEqOrd = 0;
        Resultado resultReversoM2 = new Resultado(compRev, compEqRev, movRev, movEqRev, totalRev);
        System.out.println("Reverso finalizou");

        // RANDOMICO
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        inicio = System.currentTimeMillis();
        auxRand.mergeSort2();
        fim = System.currentTimeMillis();
        totalRand = (fim - inicio) / 1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        compEqRand = 0;
        movEqRand = 0;
        Resultado resultRandomicoM2 = new Resultado(compRand, compEqRand, movRand, movEqRand, totalRand);
        System.out.println("Randomico finalizou");

        System.out.println("Merge Sort 2 terminou\n");

        // Grava na tabela
        gravarMetodo("Merge Sort 2", resultOrdenadoM2, resultReversoM2, resultRandomicoM2);

        //*COUNTING SORT INICIO*
        System.out.println("Counting Sort Inicio");

        // ORDENADO
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        inicio = System.currentTimeMillis();
        auxOrd.countingSort();
        fim = System.currentTimeMillis();
        totalOrd = (fim - inicio) / 1000;
        compOrd = auxOrd.getComp();
        movOrd = auxOrd.getMov();
        compEqOrd = 0;
        movEqOrd = 0;
        Resultado resultOrdenadoCS = new Resultado(compOrd, compEqOrd, movOrd, movEqOrd, totalOrd);
        System.out.println("Ordenado finalizou");

        // REVERSO
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        inicio = System.currentTimeMillis();
        auxRev.countingSort();
        fim = System.currentTimeMillis();
        totalRev = (fim - inicio) / 1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        compEqRev = 0;
        movEqRev = 0;
        Resultado resultReversoCS = new Resultado(compRev, compEqRev, movRev, movEqRev, totalRev);
        System.out.println("Reverso finalizou");

        // RANDOMICO
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        inicio = System.currentTimeMillis();
        auxRand.countingSort();
        fim = System.currentTimeMillis();
        totalRand = (fim - inicio) / 1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        compEqRand = 0;
        movEqRand = 0;
        Resultado resultRandomicoCS = new Resultado(compRand, compEqRand, movRand, movEqRand, totalRand);
        System.out.println("Randomico finalizou");

        System.out.println("Counting Sort terminou\n");

        // Grava na tabela
        gravarMetodo("Counting Sort", resultOrdenadoCS, resultReversoCS, resultRandomicoCS);

        //*BUCKET SORT*
        System.out.println("Bucket Sort Inicio");

        // ORDENADO
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        inicio = System.currentTimeMillis();
        auxOrd.bucketSort();
        fim = System.currentTimeMillis();
        totalOrd = (fim - inicio) / 1000;
        compOrd = auxOrd.getComp();
        movOrd = auxOrd.getMov();
        compEqOrd =0;
        movEqOrd = 0;
        Resultado resultOrdenadoBUS = new Resultado(compOrd, compEqOrd, movOrd, movEqOrd, totalOrd);
        System.out.println("Ordenado finalizou");

        // REVERSO
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        inicio = System.currentTimeMillis();
        auxRev.bucketSort();
        fim = System.currentTimeMillis();
        totalRev = (fim - inicio) / 1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        compEqRev = 0;
        movEqRev = 0;
        Resultado resultReversoBUS = new Resultado(compRev, compEqRev, movRev, movEqRev, totalRev);
        System.out.println("Reverso finalizou");

        // RANDOMICO
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        inicio = System.currentTimeMillis();
        auxRand.bucketSort();
        fim = System.currentTimeMillis();
        totalRand = (fim - inicio) / 1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        compEqRand =0;
        movEqRand = 0;
        Resultado resultRandomicoBUS = new Resultado(compRand, compEqRand, movRand, movEqRand, totalRand);
        System.out.println("Randomico finalizou");

        System.out.println("Bucket Sort terminou\n");

        // Grava na tabela
        gravarMetodo("Bucket Sort", resultOrdenadoBUS, resultReversoBUS, resultRandomicoBUS);

        //*RADIX SORT*
        System.out.println("Radix Sort Inicio");

        // Defina esses valores conforme sua implementação

        // ORDENADO
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        inicio = System.currentTimeMillis();
        auxOrd.radixSort();
        fim = System.currentTimeMillis();
        totalOrd = (fim - inicio) / 1000;
        compOrd = auxOrd.getComp();
        movOrd = auxOrd.getMov();
        compEqOrd = 0;
        movEqOrd = 0;
        Resultado resultOrdenadoRX = new Resultado(compOrd, compEqOrd, movOrd, movEqOrd, totalOrd);
        System.out.println("Ordenado finalizou");

        // REVERSO
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        inicio = System.currentTimeMillis();
        auxRev.radixSort();
        fim = System.currentTimeMillis();
        totalRev = (fim - inicio) / 1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        compEqRev = 0;
        movEqRev = 0;
        Resultado resultReversoRX = new Resultado(compRev, compEqRev, movRev, movEqRev, totalRev);
        System.out.println("Reverso finalizou");

        // RANDOMICO
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        inicio = System.currentTimeMillis();
        auxRand.radixSort();
        fim = System.currentTimeMillis();
        totalRand = (fim - inicio) / 1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        compEqRand = 0;
        movEqRand = 0;
        Resultado resultRandomicoRX = new Resultado(compRand, compEqRand, movRand, movEqRand, totalRand);
        System.out.println("Randomico finalizou");

        System.out.println("Radix Sort terminou\n");

        // Grava na tabela
        gravarMetodo("Radix Sort", resultOrdenadoRX, resultReversoRX, resultRandomicoRX);

        //*COMB SORT*
        System.out.println("Comb Sort Inicio");


        // ORDENADO
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        inicio = System.currentTimeMillis();
        auxOrd.combSort();
        fim = System.currentTimeMillis();
        totalOrd = (fim - inicio) / 1000;
        compOrd = auxOrd.getComp();
        movOrd = auxOrd.getMov();
        compEqOrd = 0;
        movEqOrd = 0;
        Resultado resultOrdenadoCMS = new Resultado(compOrd, compEqOrd, movOrd, movEqOrd, totalOrd);
        System.out.println("Ordenado finalizou");

        // REVERSO
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        inicio = System.currentTimeMillis();
        auxRev.combSort();
        fim = System.currentTimeMillis();
        totalRev = (fim - inicio) / 1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        compEqRev = 0;
        movEqRev = 0;
        Resultado resultReversoCMS = new Resultado(compRev, compEqRev, movRev, movEqRev, totalRev);
        System.out.println("Reverso finalizou");

        // RANDOMICO
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        inicio = System.currentTimeMillis();
        auxRand.combSort();
        fim = System.currentTimeMillis();
        totalRand = (fim - inicio) / 1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        compEqRand = 0;
        movEqRand = 0;
        Resultado resultRandomicoCMS = new Resultado(compRand, compEqRand, movRand, movEqRand, totalRand);
        System.out.println("Randomico finalizou");

        System.out.println("Comb Sort terminou\n");

        // Grava na tabela
        gravarMetodo("Comb Sort", resultOrdenadoCMS, resultReversoCMS, resultRandomicoCMS);

        //*GNOME SORT*
        System.out.println("Gnome Sort Inicio");

        // ORDENADO
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        inicio = System.currentTimeMillis();
        auxOrd.gnomeSort();
        fim = System.currentTimeMillis();
        totalOrd = (fim - inicio) / 1000;
        compOrd = auxOrd.getComp();
        movOrd = auxOrd.getMov();
        compEqOrd =0;
        movEqOrd =0;
        Resultado resultOrdenadoGNO = new Resultado(compOrd, compEqOrd, movOrd, movEqOrd, totalOrd);
        System.out.println("Ordenado finalizou");

        // REVERSO
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        inicio = System.currentTimeMillis();
        auxRev.gnomeSort();
        fim = System.currentTimeMillis();
        totalRev = (fim - inicio) / 1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        compEqRev = 0;
        movEqRev = 0;
        Resultado resultReversoGNO = new Resultado(compRev, compEqRev, movRev, movEqRev, totalRev);
        System.out.println("Reverso finalizou");

        // RANDOMICO
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        inicio = System.currentTimeMillis();
        auxRand.gnomeSort();
        fim = System.currentTimeMillis();
        totalRand = (fim - inicio) / 1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        compEqRand = 0;
        movEqRand = 0;
        Resultado resultRandomicoGNO = new Resultado(compRand, compEqRand, movRand, movEqRand, totalRand);
        System.out.println("Randomico finalizou");

        System.out.println("Gnome Sort terminou\n");

        // Grava na tabela
        gravarMetodo("Gnome Sort", resultOrdenadoGNO, resultReversoGNO, resultRandomicoGNO);

        //*TIM SORT*
        System.out.println("Tim Sort Inicio");

        // Ordenado
        auxOrd.copiaArquivo(arqOrd);
        auxOrd.initComp();
        auxOrd.initMov();
        inicio = System.currentTimeMillis();
        auxOrd.timSort();
        fim = System.currentTimeMillis();
        totalOrd = (fim - inicio) / 1000;
        compOrd = auxOrd.getComp();
        movOrd = auxOrd.getMov();
        compEqOrd = 0;
        movEqOrd = 0;
        System.out.println("Ordenado Finalizado");

        // Reverso
        auxRev.copiaArquivo(arqRev);
        auxRev.initComp();
        auxRev.initMov();
        inicio = System.currentTimeMillis();
        auxRev.timSort();
        fim = System.currentTimeMillis();
        totalRev = (fim - inicio) / 1000;
        compRev = auxRev.getComp();
        movRev = auxRev.getMov();
        compEqRev = 0;
        movEqRev = 0;
        System.out.println("Reverso Finalizado");

        // Randomico
        auxRand.copiaArquivo(arqRand);
        auxRand.initComp();
        auxRand.initMov();
        inicio = System.currentTimeMillis();
        auxRand.timSort();
        fim = System.currentTimeMillis();
        totalRand = (fim - inicio) / 1000;
        compRand = auxRand.getComp();
        movRand = auxRand.getMov();
        compEqRand = 0;
        movEqRand = 0;
        System.out.println("Randomico Finalizado");

        // Criando os Resultados para Ordenado, Reverso e Randomico
        Resultado resultOrdenadoTIM = new Resultado(compOrd, compEqOrd, movOrd, movEqOrd, totalOrd);
        Resultado resultReversoTIM = new Resultado(compRev, compEqRev, movRev, movEqRev, totalRev);
        Resultado resultRandomicoTIM = new Resultado(compRand, compEqRand, movRand, movEqRand, totalRand);

        // Grava na tabela
        gravarMetodo("Tim Sort", resultOrdenadoTIM, resultReversoTIM, resultRandomicoTIM);

        System.out.println("Tim Sort Finalizado\n");
    }
}
