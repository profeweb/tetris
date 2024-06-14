package stepFinal;

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
        this.matriu = matriu; //Matrius.copiaMatriu(matriu);
        this.tipusFigura = t;
    }

    // Mètode setter de la posició (fila, columna) de la figura.
    void setPosicio(int f, int c){
        this.fila = f;
        this.col = c;
    }

    // Métode setter del tipus de figura.
    void setTipusFigura(TIPUS_FIGURA tipusFigura){
        this.tipusFigura = tipusFigura;
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

    // Esborra (posa a cero) el contigut d'una fila de la matriu de la figura
    void borrarFila(int nf){

        int df = nf - this.fila;

        // Si és la fila superior
        if(nf==df){
            for(int c = 0; c< matriu[0].length; c++){
                matriu[0][c] = 0;
            }
        }
        else if(df>0 && df< matriu.length) {
            // Baixam totes les files superiors
            for(int f=df; f>0; f--){
                for(int c = 0; c< matriu[f].length; c++){
                    matriu[df][c] = matriu[df-1][c];
                }
            }
            // Buidam la 1era fila
            for(int c = 0; c< matriu[0].length; c++){
                matriu[0][c] = 0;
            }
        }
    }

    // Determina si la posicions en el tauler estan lliure per posar-hi la figura
    boolean posicioLliure(Tauler t, int ff, int cf){
        for(int f = 0; f< matriu.length; f++){
            for(int c = 0; c< matriu[0].length; c++){
                //println("MIRANT FILA: "+(ff+f)+", I COL:"+(cf+c));
                if(matriu[f][c]!=0 && t.caselles[ff+f][cf+c]!= TIPUS_FIGURA.BUIDA){
                    //println("OCUPADA");
                    return false;
                }
            }
        }
        return true;
    }

    // Mou la figura cap a l'esquerra en el tauler
    void mouEsquerra(Tauler t){
        if(this.col + this.getMinCol() > 0){
            //println("COL:"+this.col+",FILA: "+this.fila);

            int newCol = this.col - 1;

            System.out.println("COL DESTI:"+newCol);
            if(posicioLliure(t, this.fila, newCol)){
                System.out.println("SE POT MOURE");
                this.col--;
            }
        }
    }

    // Mou la figura cap a la dreta en el tauler
    void mouDreta(Tauler t){
        if(this.col + this.getMaxCol() < t.numCols - 1){
            this.col++;
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


    // Mètode per crear una figura aleatòria
    public static Figura creaFiguraRandom(PApplet p5, Tauler tauler){
        int n  = (int) p5.random(0, TIPUS_FIGURA.values().length);
        return creaFigura(p5, TIPUS_FIGURA.values()[n], tauler);
    }

    // Mètode que crea una figura d'un determinat tipus i la posiciona en el tauler a la fila inicial i en una columna aleatòria.
    public static Figura creaFigura(PApplet p5, TIPUS_FIGURA tipus, Tauler tauler){

        int f = 0;  // primera fila
        int rc = (int) p5.random(0, tauler.numCols-4);  // columna aleatòria

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

        fig.setPosicio(f, rc);
        return fig;
    }


}
