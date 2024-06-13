package step04;

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

    // Determina si la posicions en el tauler estan lliure per posar-hi la figura
    boolean posicioLliure(Tauler t, int ff, int cf){
        for(int f = 0; f< matriu.length; f++){
            for(int c = 0; c< matriu[0].length; c++){
                if(matriu[f][c]!=0 && t.caselles[ff+f][cf+c]!= Figura.TIPUS_FIGURA.BUIDA){
                    return false;
                }
            }
        }
        return true;
    }

    // Mètode que crea una figura d'un determinat tipus i la posiciona en el tauler en una la fila i columna aleatòria.
    public static Figura creaFigura(PApplet p5, Figura.TIPUS_FIGURA tipus, Tauler t){

        int f = (int) p5.random(0, t.numFiles - 4);  // fila aleatòria
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
        int numFiguresDiferents = Figura.TIPUS_FIGURA.values().length;
        int n  = (int) p5.random(0, numFiguresDiferents);
        return creaFigura(p5, Figura.TIPUS_FIGURA.values()[n], tauler);
    }


}
