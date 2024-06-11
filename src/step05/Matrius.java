package step05;

public class Matrius {

    public static final int[][] matrizI =  {{0,0,1,0},
                                            {0,0,1,0},
                                            {0,0,1,0},
                                            {0,0,1,0}};

    public static final int[][] matrizS =  {{0,1,1},
                                           {1,1,0},
                                           {0,0,0}};

    public static final int[][] matrizSI = {{1,1,0},
                                            {0,1,1},
                                            {0,0,0}};

    public static final int[][] matrizT =  {{0,1,0},
                                            {0,1,1},
                                            {0,1,0}};

    public static final int[][] matrizL =  {{0,1,0},
                                             {0,1,0},
                                             {0,1,1}};

    public static final int[][] matrizLI = {{0,1,0},
                                              {0,1,0},
                                              {1,1,0}};

    public static final int[][] matrizO =  {{1,1},
                                           {1,1}};


    // Copia d'una matriu
    public static int[][] copiaMatriu(int[][] input){
        int[][] output = new int[input.length][input[0].length];
        for(int f=0; f<input.length; f++){
            for(int c=0; c<input[0].length; c++){
                output[f][c] = input[f][c];
            }
        }
        return output;
    }
}
