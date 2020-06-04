import java.util.Scanner;

public class CodeCreator {

    private int[][] matrixG;
    private int[][] matrixH;
    private int [][] codeWords;
    private int codeDistance;
    private int k;
    private int n;
    private int r;
    private int l;



public CodeCreator(int kNew, int nNew){
    n = nNew;
    k = kNew;
    r = (n - k);
    codeDistance = 0;
    l = (int) Math.pow(2, k);
    createGmatrix();
    createHmatrix();
}

    private void createGmatrix(){
        matrixG = new int[k][n];
        for(int i = 0; i < k; i++) {
            for(int j = k; j < n; j++ ) {
                matrixG[i][i] = 1;
                int val = (int) (Math.random()*(15 - 5 + 1)) + 5;
                if(val >= 10){
                    matrixG[i][j] = 1;
                }
            }
        }
    }

    public int[][] createCodeWords(){
        int[][] words = new int[l][k];
        codeWords = new int[l][n];
        for(int i  = 0; i < l; i++){
            int number = i;
            for(int j = 0; j < k; j++){
                words[i][j] = number % 2;
                number = number / 2;
            }
        }

        System.out.println(showMtr(words));

        for(int i = 0; i < l; i++){
            for(int j = 0; j < k; j++){
                if(words[i][j] != 0){
                    for(int v = 0; v < n; v++){
                        codeWords[i][v] = codeWords[i][v] + matrixG[j][v];
                        if(codeWords[i][v] == 2){
                            codeWords[i][v] = 0;
                        }
                    }
                }
            }
        }
        countCodeDistance();
        return codeWords;
    }

    private void createHmatrix(){

        int rows = matrixG[0].length - k;
        int columns = matrixG[0].length;
        matrixH = new int[rows][columns];
        int v = k;
        for(int i = 0; i < k; i++){
            for(int j = 0; j < rows; j++){
                matrixH[j][i] = matrixG[i][v];
                v++;
            }
            v = k;
        }
        v = 0;
        for(int i = k; i < columns ; i++ ){
            matrixH[v][i] = 1;
            v++;
        }
    }

    private void countCodeDistance(){
        codeDistance = codeWords[0].length;
        int tmpd = 0;
        for(int i = 1; i < codeWords.length; i++){
            for(int j = 0; j < codeWords[0].length; j++){
                if(codeWords[i][j] != 0){
                    tmpd++;
                }
            }
            if(tmpd < codeDistance){
                codeDistance = tmpd;
            }
            tmpd = 0;
        }
    }

    public boolean checkVector(){
    Scanner input = new Scanner(System.in);
        int[] vector = new int[codeWords[0].length];
        System.out.println("Input vector for check: ");

        for (int i = 0; i < vector.length; i++){
            vector[i] = input.nextInt();
        }
        int[][] transpH = matrixTrans(matrixH);

        int check = 0;
        int[] resVector = prodVectorMatrix(vector, transpH);
        for(int i = 0; i < resVector.length; i++){
            check = check + resVector[i];
        }
        return check == 0;
    }

    private int [][] matrixTrans(int[][] matrix){
        int rows = matrix.length;
        int columns = matrix[0].length;;
        int[][] resmtr = new int[columns][rows];
        for(int i = 0; i < columns; i++){
            for(int j = 0; j < rows; j++){
                resmtr[i][j] = matrix[j][i];
            }
        }
        return resmtr;
    }

    private int[] prodVectorMatrix(int[] vector, int[][] matrix){
        int[] resVector = new int[codeWords[0].length];
        for(int i = 0; i < matrix.length; i++){
            for(int j = 0; j < matrix[0].length; j++){
                resVector[j] += matrix[i][j] * vector[i];
                while(resVector[j] >= 2){
                    resVector[j] = resVector[j] - 2;
                }
            }
        }
        return resVector;
    }

    public int[][] getGmatrix(){
        return matrixG;
    }
    public int[][] getMatrixH(){return matrixH;}
    public int[][] getCodeWords(){return codeWords;}
    public int getCodeDistance(){ return codeDistance;}

    public String toString(){

        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < k; i++){
            for(int j = 0; j < n; j++){
                builder.append(matrixG[i][j]).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public String showMtr(int[][] matrix ){
        int rows = matrix.length;
        int columns = matrix[0].length;
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                builder.append(matrix[i][j]).append(" ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }
}
