package step06;

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

    Figura f;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("step06.Tetris");
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
        Figura f = Figura.creaFiguraRandom(this, t);
        figures[numFigures] = f;
        numFigures++;

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

            // Dibuixa les figures de l'array
            for(int i=0; i<numFigures; i++) {
                t.dibuixaFigura(this, figures[i], colorsTetris.colors);
            }
            
        popMatrix();

    }

    public void keyPressed(){
        if(keyCode==LEFT){
            figures[0].mouEsquerra(t);
        }
        else if(keyCode==RIGHT){
            figures[0].mouDreta(t);
        }
        else if(keyCode==DOWN){
            figures[0].mouBaix(t);
        }
        else if(key=='b' || key=='B'){
            figures[0].mouTopeBaix(t);
        }
        else if(key=='r' || key=='R'){
            numFigures = 0;
            t.inicialitzaCaselles();
            Figura f = Figura.creaFiguraRandom(this, t);
            figures[numFigures] = f;
            numFigures++;
        }
    }

}
