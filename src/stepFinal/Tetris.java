package stepFinal;

import processing.core.PApplet;

public class Tetris extends PApplet {

    // Enumerat de les pantalles del joc
    enum Pantalla {RESULTAT, JOC};

    // Pantalla actual a mostrar
    Pantalla pantallaActual = Pantalla.JOC;

    // Tauler de la partida
    Tauler t;

    // Figura actual de la partida
    Figura figActual;

    // Llista de figures generades
    LlistaFigures figs;

    // Paleta de colors de les figures i fons.
    Colors colorsTetris;

    // Estat de la partida
    boolean gameOver = false;

    // Número de línies aconseguides
    int numLinies = 0;

    // Velocitat de les figures en baixar
    int speed = 30;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("step05.Tetris");
    }

    public void setup(){
        // Crea el tauler amb el número de columnes i files, la posició i dimensions.
        t = new Tauler(10,20, 200, 0, 400, 800);

        // Crea la llista de figures
        figs = new LlistaFigures();

        // Obté la primera figura de la llista
        figActual = figs.nextFigura(this, t);

        // Genera la paleta de colors a utilitzar per dibuixar els elements visuals
        colorsTetris = new Colors(this);
    }

    public void draw(){

        if(pantallaActual==Pantalla.JOC) {

            // Dibuixa la pantalla del joc (tauler, figures, marcador).
            dibuixaPantallaJoc();

            // Bucle del joc
            if (frameCount % speed == 0) {

                if (!figActual.mouBaix(t)) {

                    // Si la figura actual no pot baixar i està a la fila 0, s'ha acabat la partida
                    if (figActual.fila == 0) {
                        gameOver = true;
                        pantallaActual = Pantalla.RESULTAT;
                    }
                    // Si la figura actual no pot baixar però no està a la fila 0, s'ha de generar una nova figura
                    else {
                        // Afegir la nova figura al tauler
                        t.afegirFigura(figActual);
                        t.aplica(figActual);

                        // Comprovar linies
                        boolean[] plenes = t.comprovaFilesPlenes();
                        for (int f = 0; f < plenes.length; f++) {
                            if (plenes[f] == true) {
                                t.baixarFiguresAbansDe(f);
                                numLinies++;
                            }
                        }

                        // Obté una nova figura de la llista de figures
                        if (figs.isEmpty()) {
                            // Si la llista està buida, la torna emplenar
                            figs = new LlistaFigures();
                        }
                        // Actualitza la nova figura
                        figActual = figs.nextFigura(this, t);

                    }
                }
            }
        }
        else if(pantallaActual==Pantalla.RESULTAT){
            // Mostra la pantalla de resultats
            dibuixaPantallaResultat();
        }
    }


    public void dibuixaPantallaJoc(){

        // Dibuixa el fons
        background(255);

        // Dibuixa escenari de joc
        pushMatrix();
            translate(t.x, t.y);
            // Dibuixa el tauler
            t.dibuixaFigura(this, colorsTetris.colorBUIT, colorsTetris.colors);
            // Dibuixa la figura actual
            t.dibuixaFigura(this, figActual, colorsTetris.colors);
        popMatrix();

        // Dibuixa estadístiques de la partida
        fill(0); textAlign(LEFT); textSize(20);

        // Número de figures col·locades en el tauler
        text("Figures:" + t.getNumFigures(), 50, 50);

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
        text("FIGURES:" + t.getNumFigures(), width / 2, height / 2 + 100);

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
        else if(key=='r' || key=='R'){
            figActual.rota();
        }
        else if(key=='b' || key=='B'){
            figActual.mouTopeBaix(t);
        }
    }
}
