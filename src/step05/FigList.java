package step05;

import processing.core.PApplet;

public class FigList {

    int numFigures;
    Figura.TIPUS[] figs;

    FigList(){

        this.numFigures = 0;
        figs = new Figura.TIPUS[7];

        addFigNumbers();
        shuffleFigs();
    }

    void addFigNumbers(){
        for(int f=0; f<7; f++){
            figs[f] = Figura.TIPUS.values()[f];
            numFigures++;
        }
    }

    void shuffleFigs(){
        //figs.shuffle();
    }

    Figura nextFigura(PApplet p5, Tauler t){
        Figura.TIPUS nf = figs[0];
        for(int i=0; i<numFigures; i++){
            figs[i] = figs[i+1];
        }
        numFigures--;
        System.out.println("CREA FIGURA "+nf);
        return Figura.creaFigura(p5, nf, t);
    }

    boolean isEmpty(){
        return numFigures==0;
    }
}
