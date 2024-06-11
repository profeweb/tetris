package step05;

import processing.core.PApplet;

public class Tetris extends PApplet {

    enum Pantalla {MENU, JOC};
    Pantalla pantalla = Pantalla.MENU;

    Tauler t;
    Figura figActual;
    FigList figs;
    Colors colors;
    boolean gameOver = false;
    int numLinies = 0;

    public void settings(){
        size(800, 800);
    }

    public static void main(String[] args) {
        PApplet.main("step05.Tetris");
    }

    public void setup(){
        t = new Tauler(10,20, 200, 0, 400, 800);
        figs = new FigList();
        figActual = figs.nextFigura(this);
        colors = new Colors(this);
    }

    public void draw(){
        background(255);
        pushMatrix();
        translate(t.x, t.y);

        // Dibuixa el tauler
        t.dibuixa(this, colors.colorBUIT, colors.colors);

        // Dibuixa la figura actual
        t.dibuixa(this, figActual, colors.colors);

        popMatrix();

        // Bucle del joc
        if(!gameOver){
            if(!figActual.mouBaix(t)){
                println("Figura bloquejada!!");
                if(figActual.fila==0){
                    println("Partida acabada!!");
                    gameOver = true;
                }
                else{
                    // Afegir figura al tauler
                    t.afegirFigura(figActual);
                    t.aplica(figActual);

                    // Comprovar linies
                    boolean[] plenes = t.comprovaFilesPlenes();
                    for(int f=0; f<plenes.length; f++){
                        if(plenes[f]==true){
                            t.baixarFiguresAbansDe(f);
                            numLinies++;
                        }
                    }

                    // Crear nova figura
                    if(figs.isEmpty()){
                        figs = new FigList();
                    }
                    figActual = figs.nextFigura(this, t);

                }
            }
        }
        else {
            // Si la partida ha acabat!!!
            fill(0); textAlign(CENTER); textSize(50);
            text("GAME OVER", width/2, height/2);
            // Número de figures col·locades
            text("FIGURES:"+t.getNumFigures(), width/2, height/2 + 100);
            // Número de línies
            text("LINIES:"+numLinies, width/2, height/2+200);
        }
    }


    public void keyPressed(){
        if(key=='j' || key=='J'){
            pantalla = Pantalla.JOC;
        }
        else if(keyCode==LEFT){
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
