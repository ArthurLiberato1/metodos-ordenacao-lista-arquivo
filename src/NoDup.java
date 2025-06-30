public class NoDup {
    private int elemento;
    private NoDup prox;
    private NoDup ant;

    public NoDup(int elemento, NoDup prox, NoDup ant) {
        this.elemento = elemento;
        this.prox = prox;
        this.ant = ant;
    }

    public int getElemento() {
        return elemento;
    }

    public void setElemento(int elemento) {
        this.elemento = elemento;
    }

    public NoDup getProx() {
        return prox;
    }

    public void setProx(NoDup prox) {
        this.prox = prox;
    }

    public NoDup getAnt() {
        return ant;
    }

    public void setAnt(NoDup ant) {
        this.ant = ant;
    }
}

