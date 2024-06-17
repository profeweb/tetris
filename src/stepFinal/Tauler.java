package stepFinal;

import processing.core.PApplet;

public class Tauler {

    // Posició del tauler dins l'espai 2D
    int x, y;

    // Número de columnes i files del tauler
    int numCols, numFiles;

    // Mides (amplada i altura) de les caselles del tauler
    float ampleCella, altCella;

    // Array de caselles guardant el tipus de figura
    Figura.TIPUS_FIGURA[][] caselles;

    // Array de figures en el tauler
    Figura[] figures;
    // Número màxim de figures d'una partida
    final int maxNumFigures = 100;
    // Número de figures actual.
    int numFigures;

    // Constructor
    Tauler(int nc, int nf, int x, int y, int w, int h){

        this.numCols = nc;
        this.numFiles = nf;
        this.x = x;
        this.y = y;

        // Calcula l'amplada i altura de les caselles del tauler
        this.ampleCella = w/(float)numCols;
        this.altCella = h/(float) numFiles;

        // Crea l'array de figures
        figures = new Figura[maxNumFigures];
        numFigures = 0;

        // Inicialitza les caselles del tauler amb el valor BUIDA
        inicialitzaCaselles();
    }

    void afegirFigura(Figura f){
        if(numFigures<maxNumFigures) {
            figures[numFigures] = f;
            numFigures++;
        }
    }

    // Retorna la figura en joc
    Figura getFigActual(){
        return figures[0];
    }

    // Getter de la propietat del número de figures
    int getNumFigures(){
        return numFigures;
    }


    // Crear i inicialitza totes les caselles del tauler amb el valor BUIDA
    void inicialitzaCaselles(){
        caselles = new Figura.TIPUS_FIGURA[numFiles][numCols];
        for(int c=0; c<numCols; c++){
            for(int f = 0; f< numFiles; f++){
                caselles[f][c]= Figura.TIPUS_FIGURA.BUIDA;
            }
        }
    }

    // Dibuixa la graella i les caselles del tauler.
    void dibuixaFigura(PApplet p5, int colorBUIT, int[] colors){
        dibuixaGraella(p5, colorBUIT);
        dibuixaCaselles(p5, colorBUIT, colors);
    }

    // Dibuixa les línies del tauler
    void dibuixaGraella(PApplet p5, int colorBUIT){
        for(float c=0; c<numCols; c++){
            for(float f = 0; f< numFiles; f++){
                p5.fill(colorBUIT);
                p5.stroke(0);
                p5.rect(c* ampleCella, f* altCella, ampleCella, altCella);
            }
        }
    }

    // Dibuixa les caselles del tauler del color corresponent
    void dibuixaCaselles(PApplet p5, int colorBUIT, int[] colors){
        for(int c=0; c<numCols; c++){
            for(int f = 0; f< numFiles; f++){
                p5.fill(colorBUIT);
                if(caselles[f][c]!= Figura.TIPUS_FIGURA.BUIDA){
                    int numColor = caselles[f][c].ordinal();
                    p5.fill(colors[numColor]);
                }
                p5.stroke(0);
                p5.rect(c* ampleCella, f* altCella, ampleCella, altCella);
            }
        }
    }

    // Dibuixa les figures guardades en l'array figures
    void dibuixaFigures(PApplet p5, int[] colors){
        for(int i=0; i<numFigures; i++){
            dibuixaFigura(p5, figures[i], colors);
        }
    }


    // Aplica una figura al tauler, posant les caselles que ocupa al tipus de la figura.
    void aplica(Figura fig){
        for(int f = 0; f<fig.matriu.length; f++){
            for(int c = 0; c<fig.matriu[0].length; c++){
                if(fig.matriu[f][c]==1){
                    int ct = c + fig.col;
                    int ft = f + fig.fila;
                    this.caselles[ft][ct] = fig.tipusFigura;
                }
            }
        }
    }

    // Dibuixa una determinada figura
    void dibuixaFigura(PApplet p5, Figura fig, int[] colors){
        for(int f = 0; f<fig.matriu.length; f++){
            for(int c = 0; c<fig.matriu[0].length; c++){
                int ct = c + fig.col;
                int ft = f + fig.fila;
                if(fig.matriu[f][c]==1){
                    p5.fill(colors[fig.tipusFigura.ordinal()]);
                    p5.stroke(0);
                    p5.rect(ct* ampleCella, ft* altCella, ampleCella, altCella);
                }
            }
        }
    }

    // Determina quines files del tauler estan completament plenes, per tal d'eliminar-les.
    boolean[] comprovaFilesPlenes(){
        boolean[] plenes = new boolean[caselles.length];
        for(int nf = caselles.length -1; nf>=0; nf--){
            plenes[nf] = filaPlena(nf);
        }
        return plenes;
    }

    // Determina si una fila nf té totes les caselles plenes amb alguna figura
    boolean filaPlena(int nf){
        for(int c = 0; c< caselles[nf].length; c++){
            if(caselles[nf][c]== Figura.TIPUS_FIGURA.BUIDA){
                return false;
            }
        }
        return true;
    }

    // Baixa les figures fins una determinada fila
    void baixarFiguresAbansDe(int numf){

        // Baixar files de 0 a numF-1
        for(int f=numf; f>0; f--){
            for(int c = 0; c < caselles[f].length; c++){
                caselles[f][c] = caselles[f-1][c];
            }
        }

        // Buidar fila zero
        for(int c = 0; c < caselles[0].length; c++){
            caselles[0][c] = Figura.TIPUS_FIGURA.BUIDA;
        }

    }

}