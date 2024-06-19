package step08;

import processing.core.PApplet;

public class Colors {

    // Colors de les caselles en estar buides.
    int colorBUIT;

    // Colors de les caselles contenint una figura de cert tipus
    int colorI, colorO, colorS, colorSI, colorT, colorL, colorLI;

    // Array amb tots els colors
    int[] colors;

    // Constructor
    public Colors(PApplet p5){

        // Definieix els colors de le figures i de caselles buides
        colorBUIT   = p5.color(255);
        colorI      = p5.color(0,255,255);
        colorO      = p5.color(0,0,255);
        colorS      = p5.color(255,0,255);
        colorSI     = p5.color(155,0,155);
        colorT      = p5.color(0,255,0);
        colorL      = p5.color(255,255,0);
        colorLI     = p5.color(155,155,0);


        // Crea l'array de colors amb les 8 caselles
        colors = new int[8];

        // Posam dins cada casella de l'array el color corresponent
        colors[0] = colorBUIT;
        colors[1] = colorI;
        colors[2] = colorO;
        colors[3] = colorS;
        colors[4] = colorSI;
        colors[5] = colorT;
        colors[6] = colorL;
        colors[7] = colorLI;
    }
}
