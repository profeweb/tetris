package step08;

import processing.core.PApplet;

public class Tetris extends PApplet {

    // Tauler de la partida
    Tauler t;

    // Paleta de colors de les figures i fons.
    Colors colorsTetris;

    // Array de Figures
    Figura[] figures;

    // Comptador de figures
    int numFigures = 0;

    // Figura actual de la partida
    Figura figActual;

    // Velocitat de les figures en baixar
    int speed = 30;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("step08.Tetris");
    }

    public void setup(){
        // Crea el tauler amb el número de columnes i files, la posició i dimensions.
        t = new Tauler(10,20, 200, 0, width/2, height);

        // Inicialitza les caselles del tauler a BUIDA
        t.inicialitzaCaselles();

        // Genera els colors a utilitzar per dibuixar els elements visuals
        colorsTetris = new Colors(this);

        // Cream l'array per guardar 10 figures
        figures = new Figura[10];

        // Crea una figura aleatòria a la primera fila del tauler
        figActual = Figura.creaFiguraRandom(this, t);

    }

    public void draw(){

        // Lògica del joc
        if (frameCount % speed == 0) {

            if(!figActual.mouBaix(t)){
                t.afegirFigura(figActual);
                t.aplicaFigura(figActual);
                figActual = Figura.creaFiguraRandom(this, t);
                t.printTauler();
            }
        }

        // Dibuix dels elements del joc
        dibuixaJOC();
    }

    public void dibuixaJOC(){
        // Dibuixa el fons
        background(200);

        // Dibuixa escenari de joc
        pushMatrix();

        // Es trasllada a la posició (x, y) del tauler
        translate(t.x, t.y);

        // Dibuixa la graella del tauler.
        t.dibuixaGraella(this,colorsTetris.colorBUIT);

        // Dibuixa la figura actual
        t.dibuixaFigura(this, figActual, colorsTetris.colors);

        // Dibuixa les figures bloquejades
        t.dibuixaFigures(this, colorsTetris.colors);

        popMatrix();
    }

    public void keyPressed(){
        if(keyCode==LEFT){
            figActual.mouEsquerra(t);
        }
        else if(keyCode==RIGHT){
            figActual.mouDreta(t);
        }
        else if(keyCode==DOWN){
            figActual.mouBaix(t);
        }
        else if(key=='b' || key=='B'){
            figActual.mouTopeBaix(t);
        }
        else if(key=='r' || key=='R'){
            numFigures = 0;
            t.inicialitzaCaselles();
            figActual = Figura.creaFiguraRandom(this, t);
        }
    }

}
