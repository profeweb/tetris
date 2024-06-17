package step07;

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
        // Inicialment no hi ha figures bloquejades
        numFigures = 0;
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

    // Dibuixa una determinada figura en el tauler
    void dibuixaFigura(PApplet p5, Figura fig, int[] colors){
        for(int f = 0; f<fig.matriu.length; f++){
            for(int c = 0; c<fig.matriu[0].length; c++){
                int ct = c + fig.col;
                int ft = f + fig.fila;
                if(fig.matriu[f][c]==1){
                    p5.fill(colors[fig.tipusFigura.ordinal()]);
                    p5.stroke(0);
                    p5.rect(ct*ampleCella, ft*altCella,ampleCella,altCella);
                }
            }
        }
    }

    // Aplica una figura al tauler, posant les caselles que ocupa al tipus de la figura.
    void aplicaFigura(Figura fig){
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

    // Imprimeix el contingut del tauler a la cónsola
    public void printTauler(){
        System.out.println("\nESTAT DEL TAULER: ");
        for(int f = 0; f< numFiles; f++){
            for(int c=0; c<numCols; c++){
                if(caselles[f][c] != Figura.TIPUS_FIGURA.BUIDA) {
                    System.out.print(caselles[f][c]);
                }
                System.out.print("\t|");
            }
            System.out.println();
        }
    }

    void afegirFigura(Figura f){
        if(numFigures<maxNumFigures) {
            figures[numFigures] = f;
            numFigures++;
        }
    }

    // Dibuixa les figures guardades en l'array figures
    void dibuixaFigures(PApplet p5, int[] colors){
        for(int i=0; i<numFigures; i++){
            dibuixaFigura(p5, figures[i], colors);
        }
    }


}