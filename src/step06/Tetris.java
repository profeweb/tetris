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

        // Crea 10 figures aleatòries evitant solapament
        while(numFigures<10){
            Figura f = Figura.creaFiguraRandom(this, t);
            if(f.posicioLliure(t, f.fila, f.col)){
                t.aplicaFigura(f);
                figures[numFigures] = f;
                numFigures++;
            }
        }

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
            for(int i=0; i<figures.length; i++) {
                t.dibuixaFigura(this, figures[i], colorsTetris.colors);
            }
            
        popMatrix();

    }

    public void keyPressed(){
        if(key=='r' || key=='R'){
            numFigures = 0;
            t.inicialitzaCaselles();
            while(numFigures<10){
                Figura f = Figura.creaFiguraRandom(this, t);
                if(f.posicioLliure(t, f.fila, f.col)){
                    t.aplicaFigura(f);
                    figures[numFigures] = f;
                    numFigures++;
                }
            }
        }
    }

}
