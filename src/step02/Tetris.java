package step02;

import processing.core.PApplet;

public class Tetris extends PApplet {

    // Tauler de la partida
    Tauler t;

    // Paleta de colors de les figures i fons.
    Colors colorsTetris;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("step02.Tetris");
    }

    public void setup(){
        // Crea el tauler amb el número de columnes i files, la posició i dimensions.
        t = new Tauler(10,20, 200, 0, width/2, height);

        // Genera els colors a utilitzar per dibuixar els elements visuals
        colorsTetris = new Colors(this);
    }

    public void draw(){

        // Dibuixa el fons
        background(255);

        // Dibuixa escenari de joc
        pushMatrix();
            // Es trasllada a la posició (x, y) del tauler
            translate(t.x, t.y);
            // Dibuixa la graella del tauler.
            t.dibuixaGraella(this,colorsTetris.colorBUIT);
        popMatrix();

    }

}
