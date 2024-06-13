package step02;

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


    // Constructor
    Tauler(int nc, int nf, int x, int y, int w, int h){

        this.numCols = nc;
        this.numFiles = nf;
        this.x = x;
        this.y = y;

        // Calcula l'amplada i altura de les caselles del tauler
        this.ampleCella = w/(float)numCols;
        this.altCella = h/(float) numFiles;
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

}