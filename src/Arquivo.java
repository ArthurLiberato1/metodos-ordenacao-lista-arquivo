import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

class Arquivo
{
    private String nomearquivo;
    private RandomAccessFile arquivo;
    private long comp, mov;
    private final int qtdRegistros = 32;

    public Arquivo(String nomearquivo) {
        try
        {
            arquivo = new RandomAccessFile(nomearquivo, "rw");
        } catch (IOException e)
        {
            System.out.println("Erro ao abrir arquivo");
        }
    }

    public void truncate(long pos) //desloca eof
    {
        try
        {
            arquivo.setLength(pos * Registro.length());
        }
        catch (IOException exc)
        { }
    }

    public boolean eof()
    {
        boolean retorno = false;
        try
        {
            if (arquivo.getFilePointer() == arquivo.length())
                retorno = true;
        } catch (IOException e)
        { }
        return (retorno);
    }

    public int filesize() {
        try {
            return (int) (arquivo.length() / Registro.length());
        } catch (IOException e) {
            return 0;
        }
    }

    public void seekArq(long pos) {
        try {
            arquivo.seek(pos * Registro.length());
        } catch (IOException e) {
            System.out.println("Erro ao ler arquivo -> seekArq method");
        }
    }

     public void copiaArquivo(Arquivo arquivoOrigem) {
        Registro reg = new Registro();
        seekArq(0);
        arquivoOrigem.seekArq(0);
        while (!arquivoOrigem.eof()) {
            reg.leDoArq(arquivoOrigem.getFile());
            reg.gravaNoArq(arquivo);
        }
    }

    public RandomAccessFile getFile() {
        return this.arquivo;
    }

    public void initComp() {
        this.comp = 0;
    }
    public void initMov() {
        this.mov = 0;
    }

    public long getComp() {
        return comp;
    }
    public long getMov() {
        return mov;
    }

    public void gravaString(String frase) {
        try {
            arquivo.writeBytes(frase);
        } catch (IOException e) {
            System.out.println("ERRO: " + e);
        }
    }

    public void exibe() {
        seekArq(0);
        Registro reg = new Registro();
        int i;
        for (i = 0; i < filesize(); i++) {
            reg.leDoArq(getFile());
            System.out.print(reg.getNumero() + " ");
        }
        System.out.println();
    }

    public int buscaBinaria(int aux, int ini, int fim) {
        Registro reg = new Registro();
        int meio = (ini + fim) / 2;

        seekArq(meio);
        reg.leDoArq(arquivo);
        comp++;
        while (ini <= fim && aux != reg.getNumero()) {
            if (aux > reg.getNumero()) {
                ini = meio + 1;
            } else {
                fim = meio - 1;
            }
            meio = (ini + fim) / 2;
            seekArq(meio);
            reg.leDoArq(arquivo);
            comp++;
        }

        if (aux > reg.getNumero()) {
            return meio + 1;
        }
        return meio;
    }

    //--------ORDENACOES--------//

    public void heapSort(){
        int pai, Fd,Fe,maiorF,TL=filesize();
        Registro reg=new Registro();
        Registro regAux=new Registro();
        while (TL>1){
            for(pai=TL/2-1; pai>=0; pai--){
                Fe=2*pai+1;
                Fd=2*pai+2;
                maiorF=Fe;
                if(Fd<TL){
                    seekArq(Fe);
                    reg.leDoArq(arquivo);
                    regAux.leDoArq(arquivo);
                    if(regAux.getNumero()>reg.getNumero())
                        maiorF=Fd;
                }
                seekArq(pai);
                reg.leDoArq(arquivo);
                seekArq(maiorF);
                regAux.leDoArq(arquivo);
                comp += 2;
                if(regAux.getNumero()>reg.getNumero()){
                    seekArq(pai);
                    regAux.gravaNoArq(arquivo);
                    seekArq(maiorF);
                    reg.gravaNoArq(arquivo);
                }
            }
            seekArq(0);
            reg.leDoArq(arquivo);

            seekArq(TL - 1);
            regAux.leDoArq(arquivo);

            seekArq(0);
            regAux.gravaNoArq(arquivo);

            seekArq(TL - 1);
            reg.gravaNoArq(arquivo);
            TL--;
            mov += 2;
        }
    }

    public void shellSort(){
        int pos, i, dist, aux;
        int TL=filesize();
        Registro reg1=new Registro();
        Registro reg2=new Registro();
        dist=1;
        while(dist<TL)
            dist=dist*3;
        dist=dist/3;
        while(dist>0){
            for(i=dist; i<TL; i++){
                seekArq(i);
                reg1.leDoArq(arquivo);
                pos=i;
                seekArq(pos-dist);
                reg2.leDoArq(arquivo);
                comp++;
                while(pos>=dist && reg1.getNumero()<reg2.getNumero()){
                    mov++;
                    seekArq(pos);
                    reg2.gravaNoArq(arquivo);
                    pos=pos-dist;
                    if(pos>=dist){
                        seekArq(pos-dist);
                        reg2.leDoArq(arquivo);
                    }
                    comp++;
                }
                seekArq(pos);
                reg1.gravaNoArq(arquivo);
                mov++;
            }
            dist=dist/3;
        }
    }

    public void bubbleSort(){
        int TL2=filesize(),i;
        boolean flag=true;
        Registro reg1=new Registro();
        Registro reg2=new Registro();
        while(TL2>1 && flag){
            flag=false;
            for(i=0;i<TL2-1;i++){
                seekArq(i);
                reg1.leDoArq(arquivo);
                seekArq(i+1);
                reg2.leDoArq(arquivo);
                comp++;
                if(reg1.getNumero()>reg2.getNumero()){
                    mov += 2;
                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    seekArq(i+1);
                    reg1.gravaNoArq(arquivo);
                    flag=true;
                }
            }
            TL2--;
        }
    }

    public void selecaoDireta() {
        Registro reg = new Registro();
        Registro regAux = new Registro();
        int i, menor, j;
        for (i = 0; i < filesize(); i++) {
            menor = i;
            for (j = i + 1; j < filesize(); j++) {
                seekArq(menor);
                reg.leDoArq(arquivo);
                seekArq(j);
                regAux.leDoArq(arquivo);
                comp++;
                if (reg.getNumero() > regAux.getNumero()) {
                    menor = j;
                }
            }

            if (menor != i) {
                mov += 2;
                seekArq(menor);
                reg.leDoArq(arquivo);
                seekArq(i);
                regAux.leDoArq(arquivo);
                seekArq(i);
                reg.gravaNoArq(arquivo);
                seekArq(menor);
                regAux.gravaNoArq(arquivo);
            }
        }
    }

    public void quickSortComPivo(){
        quickcp(0,filesize()-1);
    }

    private void quickcp(int ini, int fim) {
        int i = ini, j = fim, pivo;
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();


        seekArq((ini + fim) / 2);
        reg1.leDoArq(arquivo);
        pivo = reg1.getNumero();

        while (i < j) {

            seekArq(i);
            reg1.leDoArq(arquivo);
            while (reg1.getNumero() < pivo && i < fim) {
                comp++;
                i++;
                seekArq(i);
                reg1.leDoArq(arquivo);
            }

            seekArq(j);
            reg2.leDoArq(arquivo);
            while (reg2.getNumero() > pivo && j > ini) {
                comp++;
                j--;
                seekArq(j);
                reg2.leDoArq(arquivo);
            }

            // Troca de elementos
            if (i <= j) {
                seekArq(i);
                reg2.gravaNoArq(arquivo);
                seekArq(j);
                reg1.gravaNoArq(arquivo);
                i++;
                j--;
                mov += 2;
            }
        }

        if (ini < j)
            quickcp(ini, j);
        if (i < fim)
            quickcp(i, fim);
    }

    public void quickSortSemPivo(){
        quicksp(0,filesize()-1);
    }

    private void quicksp(int inicio, int fim)
    {
        int i = inicio, j = fim;
        Registro reg = new Registro(), regAux = new Registro();

        while (i<j)
        {
            seekArq(i);
            reg.leDoArq(arquivo);
            seekArq(j);
            regAux.leDoArq(arquivo);

            while(i<j && reg.getNumero()<= regAux.getNumero())
            {
                comp++;
                seekArq(++i);
                reg.leDoArq(arquivo);
            }
            seekArq(i);
            regAux.gravaNoArq(arquivo);
            seekArq(j);
            reg.gravaNoArq(arquivo);

            while(i<j && reg.getNumero() <= regAux.getNumero())
            {
                mov += 2;
                seekArq(--j);
                regAux.leDoArq(arquivo);
            }
            seekArq(i);
            regAux.gravaNoArq(arquivo);
            seekArq(j);
            reg.gravaNoArq(arquivo);
        }
        if(inicio < i-1)
            quicksp(inicio, i-1);
        if(j+1<fim)
            quicksp(j+1, fim);
    }

    public void shakeSort(){
        int inicio=0,fim=filesize()-1, i;
        boolean flag=true;
        Registro reg1=new Registro();
        Registro reg2=new Registro();
        while(inicio<fim && flag){
            flag=false;
            for(i=inicio;i<fim;i++){
                seekArq(i);
                reg1.leDoArq(arquivo);
                seekArq(i+1);
                reg2.leDoArq(arquivo);
                comp++;
                if(reg1.getNumero()>reg2.getNumero()){
                    mov += 2;
                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    seekArq(i+1);
                    reg1.gravaNoArq(arquivo);
                    flag=true;
                }
            }
            fim--;
            if(flag){
                flag=false;
                for(i=fim;i>inicio;i--){
                    seekArq(i);
                    reg1.leDoArq(arquivo);
                    seekArq(i-1);
                    reg2.leDoArq(arquivo);
                    comp++;
                    if(reg1.getNumero()<reg2.getNumero()){
                        mov += 2;
                        seekArq(i);
                        reg2.gravaNoArq(arquivo);
                        seekArq(i-1);
                        reg1.gravaNoArq(arquivo);
                        flag=true;
                    }
                }
                inicio++;
            }
        }
    }

    public void insercaoBinaria() {
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        int i, j, info, busq;
        for (i = 1; i < filesize(); i++) {
            j = i - 1;
            seekArq(i);
            reg1.leDoArq(arquivo);
            info = reg1.getNumero();
            busq = buscaBinaria(info, 0, j);
            while (j >= busq) {
                mov++;
                seekArq(j);
                reg2.leDoArq(arquivo);
                seekArq(j + 1);
                reg2.gravaNoArq(arquivo);
                j--;
            }
            seekArq(j + 1);
            reg1.gravaNoArq(arquivo);
            mov++;
        }
    }

    public void insercaoDireta() {
        int pos, i, TL=filesize();
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        for (i = 1; i < TL; i++) {
            seekArq(i);
            reg2.leDoArq(arquivo);
            pos = i;
            seekArq(pos - 1);
            reg1.leDoArq(arquivo);
            comp++;
            //System.out.println(comp);
            while (pos > 0 && reg2.getNumero() < reg1.getNumero()) {
                mov++;
                //System.out.println(mov);

                seekArq(pos);
                reg1.gravaNoArq(arquivo);
                pos--;
                if (pos > 0) {
                    seekArq(pos - 1);
                    reg1.leDoArq(arquivo);
                }
                comp++;
            }
            seekArq(pos);
            reg2.gravaNoArq(arquivo);
            mov++;
        }
    }

    public void mergeSort1(){

        Arquivo parte1 = new Arquivo("auxMerge1p1.dat");
        Arquivo parte2 = new Arquivo("auxMerge1p2.dat");
        int seq = 1, TL=filesize();

        while(seq < TL) {
            parte1.truncate(0);
            parte2.truncate(0);

            particao1(parte1, parte2);
            fusao1(parte1, parte2, seq);

            seq*=2;
        }
    }

    public void particao1(Arquivo arq1, Arquivo arq2){
        Registro reg = new Registro();

        int meio = filesize()/2;
        for(int i = 0; i<meio; i++){
            mov+=2;

            seekArq(i);
            reg.leDoArq(arquivo);

            arq1.seekArq(i);
            reg.gravaNoArq(arq1.getFile());

            seekArq(i+meio);
            reg.leDoArq(arquivo);

            arq2.seekArq(i);
            reg.gravaNoArq(arq2.getFile());
        }
    }

    public void fusao1(Arquivo arq1, Arquivo arq2, int seq){
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();

        int TL = filesize();
        int i = 0, j = 0, k = 0;

        while (k < TL) {
            int fim1 = i + seq;
            int fim2 = j + seq;

            if (fim1 > TL) fim1 = TL;
            if (fim2 > TL) fim2 = TL;

            int ptr1 = i;
            int ptr2 = j;

            while (ptr1 < fim1 && ptr2 < fim2) {
                arq1.seekArq(ptr1);
                reg1.leDoArq(arq1.getFile());

                arq2.seekArq(ptr2);
                reg2.leDoArq(arq2.getFile());

                comp++;
                if (reg1.getNumero() < reg2.getNumero()) {
                    seekArq(k++);
                    reg1.gravaNoArq(arquivo);
                    ptr1++;
                } else {
                    seekArq(k++);
                    reg2.gravaNoArq(arquivo);
                    ptr2++;
                }
                mov++;
            }

            while (ptr1 < fim1) {
                arq1.seekArq(ptr1++);
                reg1.leDoArq(arq1.getFile());
                seekArq(k++);
                reg1.gravaNoArq(arquivo);
                mov++;
            }

            while (ptr2 < fim2) {
                arq2.seekArq(ptr2++);
                reg2.leDoArq(arq2.getFile());
                seekArq(k++);
                reg2.gravaNoArq(arquivo);
                mov++;
            }

            i += seq;
            j += seq;
        }
    }
    public void mergeSort2(){
        Arquivo aux=new Arquivo("auxMerge2");
        Merge2(aux,0,filesize()-1);
    }

    public void Merge2(Arquivo aux,int esq, int dir){
        int meio;
        if(esq<dir){
            meio=(esq+dir)/2;
            Merge2(aux,esq,meio);
            Merge2(aux,meio+1,dir);
            fusaoM2(aux,esq,meio,meio+1,dir);
        }
    }

    public void fusaoM2(Arquivo aux,int ini1, int fim1, int ini2, int fim2){
        int i=ini1, j=ini2, k=0;
        Registro regI=new Registro();
        Registro regJ=new Registro();
        aux.truncate(0);
        while(i<=fim1 && j<=fim2){
            seekArq(i);
            regI.leDoArq(arquivo);
            seekArq(j);
            regJ.leDoArq(arquivo);
            comp++;
            if(regI.getNumero()<regJ.getNumero()){
                aux.seekArq(k++);
                regI.gravaNoArq(aux.arquivo);
                i++;
            }
            else{
                aux.seekArq(k++);
                regJ.gravaNoArq(aux.arquivo);
                j++;
            }
            mov++;
        }
        while(i<=fim1){
            mov++;
            seekArq(i);
            regI.leDoArq(arquivo);
            aux.seekArq(k++);
            regI.gravaNoArq(aux.arquivo);
            i++;
        }
        while(j<=fim2){
            mov++;
            seekArq(j);
            regJ.leDoArq(arquivo);
            aux.seekArq(k++);
            regJ.gravaNoArq(aux.arquivo);
            j++;
        }
        for(i=0;i<k;i++){
            mov++;
            aux.seekArq(i);
            regJ.leDoArq(aux.arquivo);
            seekArq(i+ini1);
            regJ.gravaNoArq(arquivo);
        }
    }

    public void copiaArquivo2(Arquivo arq) {
        Registro reg = new Registro();
        this.seekArq(0);
        arq.initComp();
        arq.initMov();
        arq.truncate(0);
        while (!this.eof()) {
            reg.leDoArq(this.arquivo);
            reg.gravaNoArq(arq.arquivo);
        }
    }

    public int maiorElem() {
        Registro reg1 = new Registro();
        int maior;

        seekArq(0);

        reg1.leDoArq(arquivo);
        maior = reg1.getNumero();
        for (int i = 1; i < filesize(); i++) {
            reg1.leDoArq(arquivo);
            if (reg1.getNumero() > maior)
                maior = reg1.getNumero();
        }

        return maior;
    }

    public int menorElem() {
        int menor;
        Registro reg = new Registro();
        seekArq(0);

        if (filesize() == 0) return 0; // ou lançar exceção

        reg.leDoArq(arquivo);
        menor = reg.getNumero();

        for (int i = 1; i < filesize(); i++) {
            reg.leDoArq(arquivo);
            if (reg.getNumero() < menor) {
                menor = reg.getNumero();
            }
        }

        return menor;
    }

    public void countingSort() {
        Arquivo arqAux = new Arquivo("auxCounting.dat");
        Registro reg = new Registro();
        int maior = maiorElem(),tl = filesize();;
        int[] vetAux = new int[maior + 1];

        copiaArquivo2(arqAux);

        arqAux.seekArq(0);
        for (int i = 0; i < tl; i++) {
            reg.leDoArq(arqAux.getFile());
            vetAux[reg.getNumero()]++;
        }

        for (int i = 0; i < maior; i++) {
            vetAux[i + 1] = vetAux[i] + vetAux[i + 1];
        }

        arqAux.seekArq(0);
        for (int i = 0; i < tl; i++) {
            reg.leDoArq(arqAux.getFile());
            seekArq(vetAux[reg.getNumero()] - 1);
            vetAux[reg.getNumero()]--;
            reg.gravaNoArq(arquivo);
            mov++;
        }
    }

    public void bucketSort() {
        int intervalo = 0, TL = 0, i;
        Registro reg = new Registro();
        ArrayList<Arquivo> listBaldes = new ArrayList<>();
        int maior = maiorElem();

        while (intervalo <= maior) {
            listBaldes.add(new Arquivo("bucket" + TL + ".dat"));
            listBaldes.get(TL).truncate(0);
            for(i=0; i< filesize(); i++) {
                seekArq(i);
                reg.leDoArq(arquivo);
                comp++;
                if(reg.getNumero() >= intervalo && reg.getNumero() < intervalo+10) {
                    reg.gravaNoArq(listBaldes.get(TL).getFile());
                    mov++;
                }
            }
            intervalo+=10;
            TL++;
        }

        for(i=0; i< TL; i++) {
            listBaldes.get(i).bubbleSort();
        }
        truncate(0);
        seekArq(0);
        for(i=0;i <TL;i++){
            for(int j=0;j<listBaldes.get(i).filesize();j++){
                mov++;
                listBaldes.get(i).seekArq(j);
                reg.leDoArq(listBaldes.get(i).getFile());
                reg.gravaNoArq(arquivo);
            }
        }
    }

    public void radixSort() {
        Arquivo arqAux = new Arquivo("auxCountingRadix.dat");
        int maior = maiorElem();
        arqAux.truncate(0);

        for (int i = 1; maior / i > 0; i *= 10) {
            countingSortRadix(arqAux, i);
        }
    }

    public void countingSortRadix(Arquivo auxArq, int num) {
        int[] vet = new int[10];
        int tl = filesize(), i;
        long pos;
        Registro reg = new Registro();

        for (i = 0; i < tl; i++) {
            seekArq(i);
            reg.leDoArq(arquivo);
            vet[(reg.getNumero() / num) % 10]++;
        }

        for (i = 1; i < vet.length; i++) {
            vet[i] = vet[i] + vet[i - 1];
        }

        for (i = tl - 1; i >= 0; i--) {
            seekArq(i);
            reg.leDoArq(arquivo);
            pos = (long) (vet[(reg.getNumero() / num) % 10] - 1) * Registro.length();
            auxArq.seekArq(pos);
            reg.gravaNoArq(auxArq.getFile());
            vet[(reg.getNumero() / num) % 10]--;
            mov++;
        }

        for (i = 0; i < filesize(); i++) {
            auxArq.seekArq((long) i * Registro.length());
            reg.leDoArq(auxArq.getFile());
            seekArq(i);
            reg.gravaNoArq(arquivo);
            mov++;
        }
    }


    public int getProxInter(int inter) {
        inter = (inter * 10) / 13;

        if (inter < 1)
            return 1;
        return inter;
    }

    public void combSort() {
        Registro reg = new Registro();
        Registro regAux = new Registro();
        int TL = filesize(),inter=TL, i ;
        boolean flag = true;

        while (inter != 1 || flag) {
            inter = getProxInter(inter);
            flag = false;

            for (i = 0; i < TL - inter; i++) {
                seekArq(i);
                reg.leDoArq(arquivo);
                seekArq(i + inter);
                regAux.leDoArq(arquivo);
                comp++;
                if (reg.getNumero() > regAux.getNumero()) {
                    seekArq(i + inter);
                    reg.gravaNoArq(arquivo);
                    seekArq(i);
                    regAux.gravaNoArq(arquivo);
                    mov += 2;
                    flag = true;
                }
            }
        }
    }

    public void gnomeSort() {
        int i = 1, pos, TL=filesize();
        Registro reg1 = new Registro();
        Registro reg2 = new Registro();
        while (i < TL) {
            seekArq(i);
            reg1.leDoArq(arquivo);
            if (i >= 1) {
                seekArq(i - 1);
                reg2.leDoArq(arquivo);
            }
            if (reg1 != null && reg1.getNumero() >= reg2.getNumero())
                i++;
            else {
                pos = i;
                seekArq(i);
                reg1.leDoArq(arquivo);
                if (i > 0) {
                    seekArq(i - 1);
                    reg2.leDoArq(arquivo);
                }
                while (i > 0 && reg1.getNumero() < reg2.getNumero()) {
                    seekArq(i);
                    reg2.gravaNoArq(arquivo);
                    seekArq(i - 1);
                    reg1.gravaNoArq(arquivo);
                    i--;
                    seekArq(i);
                    reg1.leDoArq(arquivo);
                    if (i > 0) {
                        seekArq(i - 1);
                        reg2.leDoArq(arquivo);
                    }
                    mov += 2;
                }
                i = pos;
            }
            comp++;
        }
    }

    public void insercaoDiretaTim(int ini, int fim) {
        Registro aux = new Registro(), reg1 = new Registro(), reg2 = new Registro();
        int tl, i = ini + 1, pos;
        if (fim < 0)
            tl = filesize();
        else
            tl = fim;

        while (i < tl) {
            seekArq(i);
            aux.leDoArq(arquivo);
            pos = buscaBinaria(aux.getNumero(), 0, i);
            for (int j = i; j > pos; j--) {
                seekArq(j - 1);
                reg1.leDoArq(arquivo);
                reg2.leDoArq(arquivo);
                seekArq(j - 1);
                reg2.gravaNoArq(arquivo);
                reg1.gravaNoArq(arquivo);
                mov += 2;
            }
            i++;
        }
    }

    public void timSort() {
        int div = 32, tl = filesize(), dir, meio;
        Arquivo aux = new Arquivo("tim.dat");
        aux.truncate(tl);
        for (int i = 0; i < tl; i += div) {
            if (i + div < tl)
                insercaoDiretaTim(i, i + div);
            else
                insercaoDiretaTim(i, tl);
        }

        for (int tam = div; tam < tl; tam *= 2) {
            for (int esq = 0; esq < tl; esq += 2 * tam) {
                if (esq + 2 * tam < tl)
                    dir = esq + 2 * tam - 1;
                else
                    dir = tl - 1;
                meio = (esq + dir) / 2;
                fusaoM2(aux,esq, meio, meio + 1, dir);
            }
        }
    }

    public int getQtdRegistros() {
        return qtdRegistros;
    }

    public void geraArquivoOrdenado() {
        Registro reg = new Registro();
        truncate(0);
        int i;
        for (i = 0; i < qtdRegistros; i++) {
            reg.setNumero(i);
            reg.gravaNoArq(arquivo);
        }
    }

    public void geraArquivoReverso() {
        Registro reg = new Registro();
        truncate(0);
        int i;
        for (i = qtdRegistros; i > 0; i--) {
            reg.setNumero(i);
            reg.gravaNoArq(arquivo);
        }
    }

    public void geraArquivoRandomico() {
        Registro reg = new Registro();
        int i;
        truncate(0);
        for (i = 1; i <= qtdRegistros; i++) {
            reg.setNumero((int) (Math.random() * (qtdRegistros * 2) + 1));
            reg.gravaNoArq(arquivo);
        }
    }
}