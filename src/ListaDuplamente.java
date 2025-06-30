public class ListaDuplamente {
    private NoDup inicio;
    private NoDup fim;

    public ListaDuplamente(NoDup inicio, NoDup fim) {
        this.inicio = inicio;
        this.fim = fim;
    }

    public ListaDuplamente() {}

    public NoDup getInicio() {
        return inicio;
    }

    public void setInicio(NoDup inicio) {
        this.inicio = inicio;
    }

    public NoDup getFim() {
        return fim;
    }

    public void setFim(NoDup fim) {
        this.fim = fim;
    }


    public void insercaoDireta(){
        NoDup pi=inicio.getProx(), ppos;
        int aux;
        while(pi!=null){
            aux= pi.getElemento();
            ppos=pi;
            while(ppos!=inicio && aux<ppos.getAnt().getElemento()){
                ppos.setElemento(ppos.getAnt().getElemento());
                ppos=ppos.getAnt();
            }
            ppos.setElemento(aux);
            pi=pi.getProx();
        }
    }

    public void inserirInicio(int info) {
        NoDup novo=new NoDup(info,null,null);
        if(this.inicio == null) {
            this.inicio=this.fim=novo;
        }
        else {
            this.inicio.setAnt(novo);
            novo.setProx(this.inicio);
            this.inicio=novo;
        }
    }

    public void geraLista(int tam) {
        limpar();
        for (int i = 0; i < tam; i++) {
            inserirInicio((int) (Math.random() * (tam * 10) + 1));
        }
    }


    public void inserirFim(int info) {
        NoDup novo=new NoDup(info,null,null), aux;
        if(this.inicio == null){
            this.inicio=this.fim=novo;
        }
        else{
            aux=this.inicio;
            while(aux.getProx()!=null){
                aux=aux.getProx();
            }
            aux.setProx(novo);
            novo.setAnt(aux);
        }
    }

    public void exibir(){
        NoDup aux= this.inicio;
        while(aux != null) {
            System.out.println(aux.getElemento());
            aux = aux.getProx();
        }
    }

    public NoDup buscaExaustiva(int elem){
        NoDup aux= this.inicio;
        while(aux!=null && aux.getElemento()!=elem) {
            aux = aux.getProx();
        }
        return aux;
    }

    public void remover(int elem) {
        NoDup aux=buscaExaustiva(elem);
        if(aux!=null) {
            if(inicio==fim){
                inicio=fim=null;
            }
            else{
                if(inicio.getElemento()==elem) {
                    inicio=inicio.getProx();
                    inicio.setAnt(null);
                }
                else{
                    if(fim==aux){
                        fim=fim.getAnt();
                        fim.setProx(null);
                    }
                    else{
                        aux.getAnt().setProx(aux.getProx());
                        aux.getProx().setAnt(aux.getAnt());
                    }
                }
            }
        }
    }

    public int calcTamanho(NoDup ini){
        NoDup aux=ini;
        int i=0;
        while (aux!= null) {
            i++;
            aux=aux.getProx();
        }
        return i;
    }
    public NoDup getFim(NoDup aux){
        while(aux.getProx()!=null){
            aux=aux.getProx();
        }
        return aux;
    }
    public NoDup getMeio(NoDup inicio, int tamanho) {
        int i = 0;
        NoDup aux = inicio;
        while (aux.getProx() != null && i < tamanho) { // Garantia de que aux não será NULL
            i++;
            aux = aux.getProx();
        }
        return aux;
    }

    public NoDup BuscaBinaria(int chave){
        NoDup ini, meio,fim;
        ini=this.inicio;
        fim=getFim(ini);
        int tamanho=calcTamanho(ini);

        meio=getMeio(ini,tamanho/2);
        while(ini!=fim && chave!=meio.getElemento()){
            if(chave>meio.getElemento())
                ini=meio.getProx();
            else
                fim=meio.getAnt();
            tamanho=calcTamanho(ini);
            meio=getMeio(ini,tamanho/2);
        }
        if(chave==meio.getElemento())
            return meio;
        return null;
    }


    public NoDup buscaBinaria2(int chave){//para a Inserção Binária
        NoDup ini, meio,fim;
        ini=this.inicio;
        fim=getFim(ini);
        int tamanho=calcTamanho(ini);

        meio=getMeio(ini,tamanho/2);
        while(ini!=fim.getAnt() && chave!=meio.getElemento()){
            if(chave>meio.getElemento())
                ini=meio.getProx();
            else
                fim=meio.getAnt();
            tamanho=calcTamanho(ini);
            meio=getMeio(ini,tamanho/2);
        }
        if(chave>meio.getElemento())
            return meio.getProx();
        return meio;
    }


    public void insercaoBinaria(){
        NoDup pi = inicio.getProx(), ppos, pj;
        int aux;
        while (pi != null) {
            aux = pi.getElemento();
            ppos = buscaBinaria2(aux);
            pj = pi;
            while (pj.getAnt() != null && pj.getAnt().getElemento() > aux) {
                pj.setElemento(pj.getAnt().getElemento());
                pj = pj.getAnt();
            }
            pj.setElemento(aux);
            pi = pi.getProx();
        }
    }


    public void selecaoDireta(){
        int aux;
        NoDup pi=this.inicio, pj, pposMenor;
        while(pi!=null){
            pposMenor=pi;
            pj=pi.getProx();
            while(pj!=null){
                if(pj.getElemento()<pposMenor.getElemento()){
                    pposMenor=pj;
                }
                pj=pj.getProx();
            }
            aux=pi.getElemento();
            pi.setElemento(pposMenor.getElemento());
            pposMenor.setElemento(aux);
            pi=pi.getProx();
        }
    }

    public void bubbleSort(){
        NoDup pi=this.inicio, pf=this.fim;
        int aux;
        boolean flag=true;
        while(pf.getAnt()!=null && flag){
            pi=inicio;
            flag=false;
            while(pi.getProx()!=null){
                if(pi.getElemento()>pi.getProx().getElemento()){
                    aux=pi.getElemento();
                    pi.setElemento(pi.getProx().getElemento());
                    pi.getProx().setElemento(aux);
                    flag=true;
                }
                pi=pi.getProx();
            }
            pf=pf.getAnt();
        }
    }

    public void shakeSort() {
        NoDup pi = this.inicio, pf = this.fim, i;
        int aux;
        boolean flag = true;

        while (pi != pf && flag) {
            flag = false;

            i = pi;
            while (i.getProx() != pf.getProx()) {
                if (i.getElemento() > i.getProx().getElemento()) {
                    aux = i.getElemento();
                    i.setElemento(i.getProx().getElemento());
                    i.getProx().setElemento(aux);
                    flag = true;
                }
                i = i.getProx();
            }
            pf = pf.getAnt();

            if (flag) {
                flag = false;
                i = pf;
                while (i != pi) {
                    if (i.getElemento() < i.getAnt().getElemento()) {
                        aux = i.getElemento();
                        i.setElemento(i.getAnt().getElemento());
                        i.getAnt().setElemento(aux);
                        flag = true;
                    }
                    i = i.getAnt();
                }
                pi = pi.getProx();
            }
        }
    }


    public NoDup posiNoIndex(int posAlvo){
        NoDup aux=this.inicio;
        int i=0;
        while(aux!=null && i<posAlvo){
            aux=aux.getProx();
            i++;
        }
        return aux;
    }

    public NoDup posiNo(int posAlvo , NoDup NoPos, int posAtual ){
        NoDup aux=NoPos;
        int i=posAtual;
        if(posAtual<posAlvo){
            while(i<posAlvo && aux!=null){
                aux=aux.getProx();
                i++;
            }
        }
        else{//pos Atual maior
            while(i>posAlvo && aux!=null){
                aux=aux.getAnt();
                i--;
            }
        }
        return aux;
    }

    public void shellSort() {
        int TL=calcTamanho(inicio),dist=1, aux, pos;
        NoDup elemAux, posDist, atual, destino;
        while (dist<TL) {
            dist=dist*3+1;
        }
        dist=dist/3;

        while (dist>0){
            for (int i=dist; i<TL; i++) {
                elemAux = posiNo(i,inicio,0);//vet[i]
                aux = elemAux.getElemento();//aux=vet[i]
                pos = i;

                posDist = posiNo(pos-dist,elemAux,i);//tem que passsar um nó que seja congruente com o índice passado, se não, da B.O
                while (pos>=dist && aux<posDist.getElemento()) {
                    atual = posiNo(pos,elemAux,i);//vet[pos]
                    atual.setElemento(posDist.getElemento());//vet[pos]=vet[pos-dist]
                    pos = pos-dist;
                    posDist = posiNo(pos - dist,elemAux,i);
                }

                destino=posiNo(pos,inicio,0);
                destino.setElemento(aux);
            }
            dist = dist / 3;
        }
    }


    public void heapSort()
    {
        int TL2,pai,FE,FD,aux,maiorF;

        TL2 = calcTamanho(inicio);
        while(TL2 >1)
        {
            for(pai=TL2/2-1;pai>=0;pai--)
            {
                FE = pai*2+1;
                FD = FE+1;
                maiorF = FE;
                if(FD < TL2 && posiNo(FD,inicio,0).getElemento() > posiNo(FE,inicio,0).getElemento())
                    maiorF = FD;

                if(posiNo(maiorF,inicio,0).getElemento() > posiNo(pai,inicio,0).getElemento())
                {
                    aux = posiNo(maiorF,inicio,0).getElemento();
                    posiNo(maiorF,inicio,0).setElemento(posiNo(pai,inicio,0).getElemento());
                    posiNo(pai,inicio,0).setElemento(aux);
                }
            }
            aux = posiNo(TL2-1,inicio,0).getElemento();
            posiNo(TL2-1,inicio,0).setElemento(posiNo(0,inicio,0).getElemento());
            posiNo(0,inicio,0).setElemento(aux);
            TL2--;
        }
    }

    public void quickSortSemPivo(){
        int TL=calcTamanho(inicio);
        quickSP(0,TL-1);
    }

    public void quickSP(int ini, int fim) {

        int i = ini, j = fim, pivo, aux;
        NoDup pi = posiNo(i,inicio,0);
        NoDup pj = posiNo(j,inicio,0);

        pivo = posiNo((ini + fim) / 2,pi,i).getElemento();

        while (i <= j) {
            while (pi != null && pi.getElemento() < pivo) {
                pi = pi.getProx();
                i++;
            }
            while (pj != null && pj.getElemento() > pivo) {
                pj = pj.getAnt();
                j--;
            }

            if (i <= j) {
                aux = pi.getElemento();
                pi.setElemento(pj.getElemento());
                pj.setElemento(aux);

                pi = pi.getProx();
                pj = pj.getAnt();
                i++;
                j--;
            }
        }

        if (ini < j) quickSP(ini, j);
        if (i < fim) quickSP(i, fim);
    }


    public void quickComPivo(){
        int TL=calcTamanho(inicio);
        quickCp(0,TL-1);
    }

    public void quickCp(int ini, int fim) {

        int i = ini, j = fim, aux, pivo;
        NoDup pI = posiNo(i, this.inicio, 0), pJ = posiNo(j, this.inicio, 0);
        pivo = posiNo((ini + fim) / 2, this.inicio, 0).getElemento();

        while (i<j) {
            while (pI != null && pI.getElemento() < pivo) {
                pI = pI.getProx();
                i++;
            }

            while (pJ != null && pJ.getElemento() > pivo) {
                pJ = pJ.getAnt();
                j--;
            }

            if (i <= j) {
                aux = pI.getElemento();
                pI.setElemento(pJ.getElemento());
                pJ.setElemento(aux);

                if (pI != null) {
                    pI = pI.getProx();
                    i++;
                }
                if (pJ != null) {
                    pJ = pJ.getAnt();
                    j--;
                }
            }
        }

        if (ini < j)
            quickCp(ini, j);
        if (i < fim)
            quickCp(i, fim);
    }


    public void mergeSort1() {
        int TL = calcTamanho(inicio) + 1, seq = 1;
        ListaDuplamente aux1 = new ListaDuplamente();
        ListaDuplamente aux2 = new ListaDuplamente();
        aux1.limpar();
        aux2.limpar();

        while (seq < TL) {
            particao1(aux1, aux2);
            fusao1(aux1, aux2, seq);
            seq *= 2;
        }
    }

    public void particao1(ListaDuplamente aux1, ListaDuplamente aux2) {
        int meio = (calcTamanho(inicio) + 1) / 2;
        aux1.limpar();
        aux2.limpar();

        for (int i = 0; i < meio; i++) {
            aux1.inserirFim(posiNoIndex(i).getElemento());
            aux2.inserirFim(posiNoIndex(meio + i).getElemento());
        }
    }

    public void fusao1(ListaDuplamente aux1, ListaDuplamente aux2, int seq) {
        int i = 0, j = 0, k = 0, auxSeq = seq;
        int tl = calcTamanho(inicio) + 1;

        while (k < tl) {
            while (i < seq && j < seq) {
                if (getNoMerge(i, aux1.getInicio()).getElemento() < getNoMerge(j, aux2.getInicio()).getElemento())
                    posiNoIndex(k++).setElemento(getNoMerge(i++, aux1.getInicio()).getElemento());
                else
                    posiNoIndex(k++).setElemento(getNoMerge(j++, aux2.getInicio()).getElemento());
            }

            while (i < seq)
                posiNoIndex(k++).setElemento(getNoMerge(i++, aux1.getInicio()).getElemento());

            while (j < seq)
                posiNoIndex(k++).setElemento(getNoMerge(j++, aux2.getInicio()).getElemento());

            seq += auxSeq;
        }
    }

    private NoDup getNoMerge(int pos, NoDup aux) {
        int cont = 0;
        NoDup auxNo = aux;

        while (auxNo != null && cont < pos) {
            auxNo = auxNo.getProx();
            cont++;
        }

        return auxNo;
    }

    public void mergeSort2(){
        ListaDuplamente aux=new ListaDuplamente(null,null);
        int tam=calcTamanho(this.inicio);
        merge2(aux,inicio,fim, 0,tam);
    }

    public void merge2(ListaDuplamente aux,NoDup pEsq,NoDup pDir, int esqInd, int dirInd){
        int meio;
        NoDup pMeio;
        if(esqInd<dirInd){
            meio=(esqInd+dirInd)/2;
            pMeio=posiNo(meio,pEsq,esqInd);
            merge2(aux,pEsq,pMeio,esqInd,meio);
            merge2(aux,pMeio.getProx(),pDir,meio+1,dirInd);
            fusaoM2(aux,pEsq,pMeio,esqInd,meio,pMeio.getProx(),pDir,meio+1,dirInd);
        }
    }

    public void limpar(){
        this.inicio=null;
        this.fim=null;
    }

    public void fusaoM2(ListaDuplamente aux, NoDup pIni1, NoDup pFim1, int ini1, int fim1, NoDup pIni2,
                        NoDup pFim2, int ini2, int fim2){
        int i=ini1, j=ini2;
        NoDup pI=pIni1, pJ=pIni2;
        aux.limpar();
        while(pI!= null && pJ!=null && i<=fim1 && j<=fim2){
            if(pI.getElemento()<pJ.getElemento()){
                aux.inserirFim(pI.getElemento());
                pI=pI.getProx();
                i++;
            }
            else{
                aux.inserirFim(pJ.getElemento());
                pJ=pJ.getProx();
                j++;
            }
        }
        while(pI!=null && i<=fim1){
            aux.inserirFim(pI.getElemento());
            pI=pI.getProx();
            i++;
        }
        while(pJ!=null && j<=fim2){
            aux.inserirFim(pJ.getElemento());
            pJ=pJ.getProx();
            j++;
        }
        for (i = 0; i < calcTamanho(aux.inicio); i++) {
            posiNo(i + ini1, this.inicio, 0).setElemento(posiNo(i, aux.inicio, 0).getElemento());
        }

    }

    public void countingSort() {
        int maiorV = verificaMaior();
        int[] vFreq = new int[maiorV + 1];
        int tl = calcTamanho(inicio);
        int[] vSaida = new int[tl];
        NoDup aux = inicio;

        // Conta as freq
        while (aux != null) {
            vFreq[aux.getElemento()] += 1;
            aux = aux.getProx();
        }

        // Soma acumulada
        for (int i = 1; i <= maiorV; i++) {
            vFreq[i] += vFreq[i - 1];
        }

        // Construção da saída
        for (aux = fim; aux != null; aux = aux.getAnt()) {
            int val = aux.getElemento();
            vSaida[vFreq[val] - 1] = val;
            vFreq[val]--;
        }

        // Valores ordenados de volta para a lista
        aux = inicio;
        for (int i = 0; i < tl && aux != null; i++) {
            aux.setElemento(vSaida[i]);
            aux = aux.getProx();
        }
    }

    public void bucketSort() {
        int tl = calcTamanho(this.inicio);
        int maiorValor = verificaMaior(), indexBalde;
        ListaDuplamente[] VetorLista;
        ListaDuplamente novaLista = new ListaDuplamente();
        NoDup lista = inicio, auxNo;

        int numeroBaldes = 1;
        while (numeroBaldes < tl)
            numeroBaldes = numeroBaldes * 4 + 1;
        numeroBaldes = numeroBaldes / 4;
        VetorLista = new ListaDuplamente[numeroBaldes];

        for (int i = 0; i < numeroBaldes; i++) {
            VetorLista[i] = new ListaDuplamente();
            VetorLista[i].limpar();
        }

        lista = inicio;
        while (lista != null) {
            indexBalde = (int) (((double) lista.getElemento() / maiorValor) * (numeroBaldes - 1));
            VetorLista[indexBalde].inserirFim(lista.getElemento());
            lista = lista.getProx();
        }

        for (int i = 0; i < numeroBaldes; i++) {
            VetorLista[i].insercaoDireta();
        }

        for (int i = 0; i < numeroBaldes; i++) {
            auxNo = VetorLista[i].getInicio();
            while (auxNo != null) {
                novaLista.inserirFim(auxNo.getElemento());
                auxNo = auxNo.getProx();
            }
        }

        NoDup aux = inicio;
        NoDup aux2 = novaLista.getInicio();
        while (aux2 != null) {
            aux.setElemento(aux2.getElemento());
            aux = aux.getProx();
            aux2 = aux2.getProx();
        }
    }

    public void zerar(ListaDuplamente count, int TL){
        int i=0;
        NoDup aux=count.inicio;
        if(count.getInicio()==null){
            while(i<TL){
                count.inserirFim(0);
                i++;
            }
        }
        else{
            while(i<TL && aux!=null){
                aux.setElemento(0);
                aux=aux.getProx();
            }
        }
    }

    public void copiarLista(ListaDuplamente cont){
        NoDup aux=this.inicio;
        NoDup auxCont=cont.inicio;
        while(aux!=null){
            aux.setElemento(auxCont.getElemento());
            aux=aux.getProx();
            auxCont=auxCont.getProx();
        }
    }

    public int digitosMaior(){
        NoDup aux=this.inicio;
        int maior=0, cont=0;
        while(aux!=null){
            if(aux.getElemento()>maior){
                maior=aux.getElemento();
            }
            aux=aux.getProx();
        }
        while(maior>0){
            maior=maior/10;
            cont++;
        }
        return  cont;
    }

    public void radixSort() {
        int resto = 10, div = 1, qtdDigito, i, dig, pos;
        int TL = calcTamanho(this.inicio);

        ListaDuplamente aux = new ListaDuplamente(null, null);
        ListaDuplamente count = new ListaDuplamente(null, null);

        NoDup aux2, auxCount, noAtual, noCount, noAux;
        qtdDigito = digitosMaior();

        while (qtdDigito > 0) {
            zerar(count, 10);
            zerar(aux, TL);

            aux2 = this.inicio;
            while (aux2 != null) {
                dig = (aux2.getElemento() % resto) / div;
                noCount = posiNo(dig, count.getInicio(), 0);
                if (noCount != null) {
                    noCount.setElemento(noCount.getElemento() + 1);
                }
                aux2 = aux2.getProx();
            }

            auxCount = count.getInicio();
            while (auxCount != null && auxCount.getProx() != null) {
                auxCount.getProx().setElemento(auxCount.getProx().getElemento() + auxCount.getElemento());
                auxCount = auxCount.getProx();
            }

            for (i = TL - 1; i >= 0; i--) {
                noAtual = posiNo(i, this.inicio, 0);
                if (noAtual != null) {
                    dig = (noAtual.getElemento() % resto) / div;
                    noCount = posiNo(dig, count.getInicio(), 0);
                    if (noCount != null) {
                        pos = noCount.getElemento();
                        noAux = posiNo(pos - 1, aux.getInicio(), 0);
                        if (noAux != null) {
                            noAux.setElemento(noAtual.getElemento());
                        }
                        noCount.setElemento(noCount.getElemento() - 1);
                    }
                }
            }

            copiarLista(aux);
            resto *= 10;
            div *= 10;
            qtdDigito--;
        }
    }

    public void combSort() {
        int TL = calcTamanho(this.inicio);
        int intervalo = (int) (TL / 1.3), index = 0, aux;

        if (TL > 1) {
            while (intervalo > 0 && index != TL) {
                index = 0;
                while ((index + intervalo) < TL) {
                    if (posiNoIndex(index).getElemento() > posiNoIndex(index + intervalo).getElemento()) {
                        aux = posiNoIndex(index).getElemento();
                        posiNoIndex(index).setElemento(posiNoIndex(index + intervalo).getElemento());
                        posiNoIndex(index + intervalo).setElemento(aux);
                    }
                    index++;
                }
                intervalo = (int) (intervalo / 1.3);
            }
        }
    }


    public void gnomeSort() {
        int i = 1, aux, pos;
        int tl = calcTamanho(this.inicio);
        while (i < tl) {
            if (posiNoIndex(i) != null && i >= 0 && posiNoIndex(i).getElemento() >= posiNoIndex(i - 1).getElemento())
                i++;
            else {
                pos = i;
                while (i > 0 && posiNoIndex(i).getElemento() < posiNoIndex(i - 1).getElemento()) {
                    aux = posiNoIndex(i).getElemento();
                    posiNoIndex(i).setElemento(posiNoIndex(i).getAnt().getElemento());
                    posiNoIndex(i).getAnt().setElemento(aux);
                    i--;
                }
                i = pos;
            }
        }
    }


    private void fusaoTim(NoDup aux, int ini1, int fim1, int ini2, int fim2) {
        int i = ini1, j = ini2, k = 0;

        while (i <= fim1 && j <= fim2) {
            if (posiNoIndex(i).getElemento() < posiNoIndex(j).getElemento())
                getNoMerge(k++, aux).setElemento(posiNoIndex(i++).getElemento());
            else
                getNoMerge(k++, aux).setElemento(posiNoIndex(j++).getElemento());
        }

        while (i <= fim1)
            getNoMerge(k++, aux).setElemento(posiNoIndex(i++).getElemento());

        while (j <= fim2)
            getNoMerge(k++, aux).setElemento(posiNoIndex(j++).getElemento());

        for (i = 0; i < k; i++)
            posiNoIndex(i + ini1).setElemento(getNoMerge(i, aux).getElemento());
    }

    private void insercaoDiretaTim(int ini, int fim) {
        int i,valor,j;
        for (i = ini + 1; i < fim; i++) {
            valor = posiNoIndex(i).getElemento();
            j = i - 1;

            while (j >= ini && posiNoIndex(j).getElemento() > valor) {
                posiNoIndex(j + 1).setElemento(posiNoIndex(j).getElemento());
                j--;
            }

            posiNoIndex(j + 1).setElemento(valor);
        }
    }

    public void timSort() {
        int div = 32, tl = calcTamanho(this.inicio),i, meio, dir,tam,esq;

        for (i = 0; i < tl; i += div) {
            insercaoDiretaTim(i, Math.min(i + div, tl));
        }

        for (tam = div; tam < tl; tam *= 2) {
            for (esq = 0; esq < tl; esq += 2 * tam) {
                meio = esq + tam - 1;
                dir = Math.min(esq + 2 * tam - 1, tl - 1);

                if (meio < dir) {
                    fusaoTim(inicio, esq, meio, meio + 1, dir);
                }
            }
        }
    }

    public int verificaMaior(){
        int maior=0;
        NoDup aux=this.inicio;
        while(aux!=null){
            if(aux.getElemento()>maior){
                maior=aux.getElemento();
            }
            aux=aux.getProx();
        }
        return maior;
    }


}
