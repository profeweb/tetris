package step03;

import processing.core.PApplet;

public class Tetris extends PApplet {

    // Tauler de la partida
    Tauler t;

    // Paleta de colors de les figures i fons.
    Colors colorsTetris;

    // Figures
    Figura f1, f2, f3, f4, f5, f6, f7;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("step03.Tetris");
    }

    public void setup(){
        // Crea el tauler amb el número de columnes i files, la posició i dimensions.
        t = new Tauler(10,20, 200, 0, width/2, height);

        // Genera els colors a utilitzar per dibuixar els elements visuals
        colorsTetris = new Colors(this);

        // Cream la figura f1 del tipus T en la primera fila (0) i 5a columna (4) mitjançant el constructor
        f1 = new Figura(Matrius.matrizT, Figura.TIPUS_FIGURA.T);
        f1.setPosicio(0, 4);

        // Cream la figura f2 del tipus S en la 5a fila (4) i 7a columna (6) mitjançant el constructor
        f2 = new Figura(Matrius.matrizS, Figura.TIPUS_FIGURA.S);
        f2.setPosicio(4, 6);

        // Cream la figura f3 del tipus O en la 11a fila (10) i 3a columna (2) mitjançant el constructor
        f3 = new Figura(Matrius.matrizO, Figura.TIPUS_FIGURA.O);
        f3.setPosicio(10, 2);

        // Cream la figura f3 del tipus I en la 5a fila (4) i 2a columna (1) mitjançant el constructor
        f4 = new Figura(Matrius.matrizI, Figura.TIPUS_FIGURA.I);
        f4.setPosicio(4, 1);

        // Cream la figura f3 del tipus O en la 11a fila (10) i 3a columna (2) mitjançant el constructor
        f5 = new Figura(Matrius.matrizL, Figura.TIPUS_FIGURA.L);
        f5.setPosicio(10, 7);

        // Cream la figura f3 del tipus O en la 16a fila (15) i 3a columna (2) mitjançant el constructor
        f6 = new Figura(Matrius.matrizSI, Figura.TIPUS_FIGURA.SI);
        f6.setPosicio(15, 2);

        // Cream la figura f3 del tipus O en la 16a fila (15) i 7a columna (6) mitjançant el constructor
        f7 = new Figura(Matrius.matrizLI, Figura.TIPUS_FIGURA.LI);
        f7.setPosicio(15, 6);

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

            // Dibuixa les figures f1, f2, f3
            t.dibuixaFigura(this, f1, colorsTetris.colors);
            t.dibuixaFigura(this, f2, colorsTetris.colors);
            t.dibuixaFigura(this, f3, colorsTetris.colors);
            t.dibuixaFigura(this, f4, colorsTetris.colors);
            t.dibuixaFigura(this, f5, colorsTetris.colors);
            t.dibuixaFigura(this, f6, colorsTetris.colors);
            t.dibuixaFigura(this, f7, colorsTetris.colors);
            
        popMatrix();

    }

}
