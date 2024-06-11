package step05;

import processing.core.PApplet;

import java.util.ArrayList;

public class Tauler {

    int x, y;
    int numCols, numFils;
    float cellWidth, cellHeight;
    Figura.TIPUS[][] cells;

    ArrayList<Figura> figures;

    Tauler(int nc, int nf, int x, int y, int w, int h){
        this.numCols = nc;
        this.numFils = nf;
        this.x = x;
        this.y = y;
        this.cellWidth = w/(float)numCols;
        this.cellHeight = h/(float)numFils;

        figures = new ArrayList<Figura>();
        reset();
    }

    void afegirFigura(Figura f){
        figures.add(f);
    }

    Figura getFigActual(){
        return this.figures.get(0);
    }

    int getNumFigures(){
        return this.figures.size();
    }


    void reset(){
        cells = new int[numFils][numCols];
        for(int c=0; c<numCols; c++){
            for(int f=0; f<numFils; f++){
                cells[f][c]= Figura.TIPUS.BUIDA;
            }
        }
    }

    void dibuixa(PApplet p5, int colorBUIT, int[] colors){
        dibuixaGraella(p5, colorBUIT);
        dibuixaCelles(p5, colorBUIT, colors);
    }

    void dibuixaGraella(PApplet p5, int colorBUIT){
        for(float c=0; c<numCols; c++){
            for(float f=0; f<numFils; f++){
                p5.fill(colorBUIT);
                p5.stroke(0);
                p5.rect(c*cellWidth, f*cellHeight, cellWidth, cellHeight);
            }
        }
    }

    void dibuixaCelles(PApplet p5, int colorBUIT, int[] colors){
        for(int c=0; c<numCols; c++){
            for(int f=0; f<numFils; f++){
                p5.fill(colorBUIT);
                if(cells[f][c]!=0){
                    int numColor = cells[f][c].ordinal();
                    p5.fill(colors[numColor]);
                }
                p5.stroke(0);
                p5.rect(c*cellWidth, f*cellHeight, cellWidth, cellHeight);
            }
        }
    }


    void aplica(Figura fig){
        for(int f=0; f<fig.quadres.length; f++){
            for(int c=0; c<fig.quadres[0].length; c++){
                if(fig.quadres[f][c]==1){
                    int ct = c + fig.col;
                    int ft = f + fig.fila;
                    this.cells[ft][ct] = fig.tipus;
                }
            }
        }
    }

    void dibuixa(PApplet p5, Figura fig, int[] colors){
        for(int f=0; f<fig.quadres.length; f++){
            for(int c=0; c<fig.quadres[0].length; c++){
                int ct = c + fig.col;
                int ft = f + fig.fila;
                if(fig.quadres[f][c]==1){
                    p5.fill(colors[fig.tipus.ordinal()]);
                    p5.stroke(0);
                    p5.rect(ct*cellWidth, ft*cellHeight, cellWidth, cellHeight);
                }
                else{
                    //fill(100, 50);
                    //rect(ct*cellWidth, ft*cellHeight, cellWidth, cellHeight);
                }

            }
        }
    }

    boolean[] comprovaFilesPlenes(){
        boolean[] plenes = new boolean[cells.length];
        for(int nf = cells.length -1; nf>=0; nf--){
            plenes[nf] = filaPlena(nf);
        }
        return plenes;
    }

    boolean filaPlena(int nf){
        for(int c=0; c<cells[nf].length; c++){
            if(cells[nf][c]==0){
                return false;
            }
        }
        return true;
    }

    void baixarFiguresAbansDe(int numf){

        // Baixar files de 0 a numF-1
        for(int f=numf; f>0; f--){
            for(int c = 0; c < cells[f].length; c++){
                cells[f][c] = cells[f-1][c];
            }
        }

        // Buidar fila zero
        for(int c = 0; c < cells[0].length; c++){
            cells[0][c] = 0;
        }

    }

}