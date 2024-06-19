package step11;

import processing.core.PApplet;

public class Tetris extends PApplet {

    // Tauler de la partida
    Tauler t;

    // Paleta de colors de les figures i fons.
    Colors colorsTetris;

    // Comptador de figures
    int numFigures = 0;

    // Figura actual de la partida
    Figura figActual;

    // Velocitat de les figures en baixar
    int speed = 30;

    // Número de línies aconseguides
    int numLinies = 0;

    // Estat de la partida
    boolean gameOver = false;

    // Llista de figures generades
    LlistaFigures llistaFigs;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("step11.Tetris");
    }

    public void setup(){
        // Crea el tauler amb el número de columnes i files, la posició i dimensions.
        t = new Tauler(10,20, 200, 0, width/2, height);

        // Inicialitza les caselles del tauler a BUIDA
        t.inicialitzaCaselles();

        // Genera els colors a utilitzar per dibuixar els elements visuals
        colorsTetris = new Colors(this);

        // Crea la llista de figures
        llistaFigs = new LlistaFigures();

        // Agafa la següent figura de la llista de figures
        figActual = llistaFigs.nextFigura(this, t);

    }

    public void draw(){

        // Lògica del joc
        if (!gameOver && frameCount % speed == 0) {

            if (!figActual.mouBaix(t)) {

                // Si la figura actual no pot baixar i està a la fila 0, s'ha acabat la partida
                if (figActual.fila == 0) {
                    gameOver = true;
                }
                // Si la figura actual no pot baixar però no està a la fila 0, s'ha de generar una nova figura
                else {
                    // Actualitza la nova figura
                    t.afegirFigura(figActual);
                    t.aplicaFigura(figActual);

                    // Obté una nova figura de la llista de figures
                    if (llistaFigs.estaBuida()) {
                        // Si la llista està buida, la torna emplenar
                        llistaFigs = new LlistaFigures();
                    }

                    // Actualitza la nova figura
                    figActual = llistaFigs.nextFigura(this, t);
                    numFigures++;
                }
            }
            else {

                // Comprovar linies
                boolean[] plenes = t.comprovaFilesPlenes();
                for (int f = 0; f < plenes.length; f++) {
                    if (plenes[f] == true) {
                        t.baixarFiguresAbansDe(f);
                        numLinies++;
                    }
                }
            }
        }

        if(gameOver){
            // Dibuixa els resultats de la partida
            dibuixaPantallaResultat();
        }
        else {
            // Dibuix dels elements del joc
            dibuixaJOC();
        }
    }

    public void dibuixaJOC(){
        // Dibuixa el fons
        background(200);

        // Dibuixa escenari de joc
        pushMatrix();

        // Es trasllada a la posició (x, y) del tauler
        translate(t.x, t.y);

        // Dibuixa les caselles del tauler
        t.dibuixaCaselles(this, colorsTetris.colorBUIT, colorsTetris.colors);

        // Dibuixa la figura actual
        t.dibuixaFigura(this, figActual, colorsTetris.colors);

        popMatrix();

        // Dibuixa estadístiques de la partida
        fill(0); textAlign(LEFT); textSize(20);

        // Número de figures col·locades en el tauler
        text("Figures:" + numFigures, 50, 50);

        // Número de línies resoltes
        text("Línies:" + numLinies, 50, 70);
    }

    public void dibuixaPantallaResultat(){

        // Dibuixa el fons
        background(255, 100, 100);

        fill(0); textAlign(CENTER); textSize(50);

        // Missatge de final de partida
        text("GAME OVER", width / 2, height / 2);

        // Número de figures col·locades en el tauler
        text("FIGURES:" + numFigures, width / 2, height / 2 + 100);

        // Número de línies resoltes
        text("LÍNIES:" + numLinies, width / 2, height / 2 + 200);
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
            figActual.rota();
        }
    }

}
