package step01;

import processing.core.PApplet;

public class Tetris extends PApplet {

    // Tauler de la partida
    Tauler t;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("step01.Tetris");
    }

    public void setup(){
        // Crea el tauler amb el número de columnes i files, la posició i dimensions.
        t = new Tauler(20,20, 0, 0, width, height);
    }

    public void draw(){

        // Dibuixa el fons
        background(255);

        // Dibuixa escenari de joc
        pushMatrix();
            // Es trasllada a la posició (x, y) del tauler
            translate(t.x, t.y);
            // Dibuixa la graella del tauler.
            t.dibuixaGraella(this, color(200));
        popMatrix();

    }

}
