package stepFinal;

import processing.core.PApplet;

public class LlistaFigures {

    // Número de fiures de la llista
    int numFigures;

    // Array de les figures de la llista
    Figura.TIPUS_FIGURA[] figures;


    // Constructor
    LlistaFigures(){

        // Inicialitza el número de figures i l'array de figures
        this.numFigures = 0;
        figures = new Figura.TIPUS_FIGURA[7];

        // afegeix una figura de cada tipus
        afegeixFiguresTipus();

        // Mescla (5 vegades) les figures de l'array (per evitar que a cada partida, surtin les figures en el mateix ordre).
        mesclaFigures(5);
    }

    // Afegeix una figura de cada tipus a l'array
    void afegeixFiguresTipus(){
        int numTipus = Figura.TIPUS_FIGURA.values().length-1;
        for(int f=0; f<numTipus; f++){
            figures[f] = Figura.TIPUS_FIGURA.values()[f];
            numFigures++;
        }
    }

    // Mescla les figures de l'array
    void mesclaFigures(int t){
        int numTipus = Figura.TIPUS_FIGURA.values().length-1;
        for(int n=0; n<t; n++){
            int numOrigen = (int) Math.floor(Math.random()*numTipus);
            int numDestí  = (int) Math.floor(Math.random()*numTipus);
            Figura.TIPUS_FIGURA tipus1 = figures[numOrigen];
            Figura.TIPUS_FIGURA tipus2 = figures[numDestí];
            figures[numOrigen] = tipus2;
            figures[numDestí] = tipus1;
        }
    }

    //Retorna la següent figura a entrar a la partida, l'extreu de l'array i mou totes les altres un posició cap a l'esquerra dins l'array
    Figura nextFigura(PApplet p5, Tauler t){
        //
        Figura.TIPUS_FIGURA nf = figures[0];
        for(int i=0; i<numFigures-1; i++){
            figures[i] = figures[i+1];
        }
        numFigures--;
        return Figura.creaFigura(p5, nf, t);
    }

    // Determina si la llista de figures és buida
    boolean isEmpty(){
        return numFigures==0;
    }
}
