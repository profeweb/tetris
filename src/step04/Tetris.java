package step04;

import processing.core.PApplet;

public class Tetris extends PApplet {

    // Tauler de la partida
    Tauler t;

    // Paleta de colors de les figures i fons.
    Colors colorsTetris;

    // Figura
    Figura f1;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("step04.Tetris");
    }

    public void setup(){
        // Crea el tauler amb el número de columnes i files, la posició i dimensions.
        t = new Tauler(10,20, 200, 0, width/2, height);

        // Genera els colors a utilitzar per dibuixar els elements visuals
        colorsTetris = new Colors(this);

        // Cream la figura f1 del tipus T en una posició aleatòria
        f1 = Figura.creaFigura(this, Figura.TIPUS_FIGURA.T, t);

    }

    public void draw(){

        // Dibuixa el fons
        background(200);

        // Dibuixa escenari de joc
        pushMatrix();

            // Es trasllada a la posició (x, y) del tauler
            translate(t.x, t.y);

            // Dibuixa la graella del tauler.
            t.dibuixaGraella(this,colorsTetris.colorBUIT);

            // Dibuixa la figura f1
            t.dibuixaFigura(this, f1, colorsTetris.colors);
            
        popMatrix();

    }

    public void keyPressed(){
        if(key=='r' || key=='R'){
            // Instancia la figura f1 del tipus T en una posició aleatòria
            f1 = Figura.creaFiguraRandom(this, t);
        }
    }

}
