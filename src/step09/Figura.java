package step09;

import processing.core.PApplet;

public class Figura {

    // Enumerat amb els tipus de figures
    public enum TIPUS_FIGURA { BUIDA, I, S, SI, T, L, LI, O}

    // Tipus de la figura
    TIPUS_FIGURA tipusFigura;

    // Matriu per emmagetzemar la figura
    int[][] matriu;

    // Fila i columna superior esquerra de la figura
    int fila, col;

    // Array de figures en el tauler
    Figura[] figures;

    // Número màxim de figures d'una partida
    final int maxNumFigures = 100;

    // Número de figures actual.
    int numFigures;

    // Constructor de la figura, indicant-li la matriu i el tipus.
    Figura(int[][] matriu, TIPUS_FIGURA t){
        this.matriu = matriu;
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

    // Mètode que crea una figura d'un determinat tipus i la posiciona en el tauler en una la fila i columna aleatòria.
    public static Figura creaFigura(PApplet p5, TIPUS_FIGURA tipus, Tauler t){

        int f = 0;  // Primera fila (0)
        int c = (int) p5.random(0, t.numCols - 4);  // columna aleatòria

        Figura fig;

        switch(tipus){
            case L:     fig = new FiguraL();  break;
            case T:     fig = new FiguraT();  break;
            case S:     fig = new FiguraS();  break;
            case O:     fig = new FiguraO();  break;
            case I:     fig = new FiguraI();  break;
            case LI:    fig = new FiguraLI(); break;
            case SI:    fig = new FiguraSI(); break;
            default:    fig = new FiguraO();  break;
        }

        fig.setPosicio(f, c);
        return fig;
    }

    // Mètode per crear una figura de tipus i posició aleatòria
    public static Figura creaFiguraRandom(PApplet p5, Tauler tauler){
        int numFiguresDiferents = TIPUS_FIGURA.values().length;
        int n  = (int) p5.random(0, numFiguresDiferents);
        return creaFigura(p5, TIPUS_FIGURA.values()[n], tauler);
    }

    // Determina si la posicions en el tauler estan lliure per posar-hi la figura
    boolean posicioLliure(Tauler t, int ff, int cf){
        for(int f = 0; f< matriu.length; f++){
            for(int c = 0; c< matriu[0].length; c++){
                //println("MIRANT FILA: "+(ff+f)+", I COL:"+(cf+c));
                if(matriu[f][c]!=0 && t.caselles[ff + f][cf + c]!= TIPUS_FIGURA.BUIDA){
                    //println("OCUPADA");
                    return false;
                }
            }
        }
        return true;
    }


    // Retorna la columna màxima que ocupa una figura
    int getMaxCol(){
        int maxc=0;
        for(int f = 0; f< matriu.length; f++){
            for(int c = 0; c< matriu[0].length; c++){
                if(matriu[f][c]==1 && c>maxc){
                    maxc = c;
                }
            }
        }
        return maxc;
    }

    // Retorna la fila màxima que ocupa una figura
    int getMaxFil(){
        int n=0;
        for(int f = 0; f< matriu.length; f++){
            for(int c = 0; c< matriu[0].length; c++){
                if(matriu[f][c]==1){
                    n = f;
                }
            }
        }
        return n;
    }

    // Retorna la columna mínima que ocupa una figura
    int getMinCol(){
        int minc= matriu[0].length;
        for(int f = 0; f< matriu.length; f++){
            for(int c = 0; c< matriu[0].length; c++){
                if(matriu[f][c]==1 && c<minc){
                    minc=c;
                }
            }
        }
        return minc;
    }

    // Retorna la fila mínima que ocupa una figura
    int getMinFil(){
        int n=0;
        for(int f = 0; f< matriu.length; f++){
            for(int c = 0; c< matriu[0].length; c++){
                if(matriu[f][c]==1){
                    return f;
                }
            }
        }
        return n;
    }

    // Mou la figura cap a l'esquerra en el tauler
    void mouEsquerra(Tauler t){
        if(this.col + this.getMinCol() > 0){
            int newCol = this.col - 1;
            if(posicioLliure(t, this.fila, newCol)){
                this.col--;
            }
        }
    }

    // Mou la figura cap a la dreta en el tauler
    void mouDreta(Tauler t){
        if(this.col + this.getMaxCol() < t.numCols - 1){
            int newCol = this.col + 1;
            if(posicioLliure(t, this.fila, newCol)) {
                this.col++;
            }
        }
    }

    // Determina si la figura es pot moure cap a baix en el tauler
    boolean mouBaix(Tauler t){

        if(this.fila + this.getMaxFil() < t.numFiles - 1){
            int newFila = this.fila + 1;
            if(posicioLliure(t, newFila, this.col)){
                this.fila++;
                return true;
            }
        }
        return false;
    }

    // Mou la figura fins a baix del tot en el tauler
    void mouTopeBaix(Tauler t){
        while(mouBaix(t));
    }

    // Rota la figura des del seu centre
    void rota(){

        int[][] q = this.copia();

        for(int f = 0; f< matriu.length; f++){
            for(int c = 0; c< matriu[0].length; c++){
                matriu[f][c]=q[matriu.length - c -1][f];
            }
        }
    }

    // Retorna una còpia de la matriu que representa la figura
    int[][] copia(){

        int[][] q = new int[this.matriu.length][this.matriu[0].length];
        for(int f = 0; f< matriu.length; f++){
            for(int c = 0; c< matriu[0].length; c++){
                q[f][c] = matriu[f][c];
            }
        }
        return q;
    }

    // Afegeix la figura a l'array de figures, incrementant el comptador numFigures
    void afegirFigura(Figura f){
        if(numFigures<maxNumFigures) {
            figures[numFigures] = f;
            numFigures++;
        }
    }


}
