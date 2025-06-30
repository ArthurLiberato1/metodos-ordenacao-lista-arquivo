public class Resultado {
    long comp,mov,temp;
    double compEQ,movEQ;

    public Resultado(long comp, double compEQ, long mov, double movEQ, long temp) {
        this.comp = comp;
        this.mov = mov;
        this.temp = temp;
        this.compEQ = compEQ;
        this.movEQ = movEQ;
    }

    public String formatar() {
        return String.format(
                "%12d |%12.2f |%12d |%12.2f |%8s |",
                comp, compEQ, mov, movEQ, temp + ""
        );
    }
}
