package step02;

public class Figura {

    // Enumerat amb els tipus de figures
    public enum TIPUS_FIGURA { BUIDA, I, S, SI, T, L, LI, O}

    // Tipus de la figura
    TIPUS_FIGURA tipusFigura;

    // Matriu per emmagetzemar la figura
    int[][] matriu;

    // Fila i columna superior esquerra de la figura
    int fila, col;

    // Constructor de la figura, indicant-li la matriu i el tipus.
    Figura(int[][] matriu, TIPUS_FIGURA t){
        this.matriu = matriu; //Matrius.copiaMatriu(matriu);
        this.tipusFigura = t;
    }

    // Mètode setter de la posició (fila, columna) de la figura.
    void setPosicio(int f, int c){
        this.fila = f;
        this.col = c;
    }

    // Mètode setter del tipus de figura.
    void setTipusFigura(TIPUS_FIGURA tipusFigura){
        this.tipusFigura = tipusFigura;
    }

}
