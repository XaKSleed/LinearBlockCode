public class Main {

    public static void main(String[] args) {
        CodeCreator create1 = new CodeCreator(4, 10);
        System.out.println("Words");
        int[][] gmtr = create1.getGmatrix();
        int[][] hmtr = create1.getMatrixH();
        int[][] code = create1.createCodeWords();

        System.out.println("Matrix G");
        System.out.println(create1.showMtr(gmtr));
        System.out.println("Matrix H");
        System.out.println(create1.showMtr(hmtr));
        System.out.println("Code words");
        System.out.println(create1.showMtr(code));
        System.out.println("Code distance");
        System.out.println(create1.getCodeDistance());
        int correct = (create1.getCodeDistance() - 1) / 2;
        System.out.println("Correct posibility  = " + correct);
        if(create1.checkVector()){
            System.out.println("Vector belong to the code");
        }
        else{
            System.out.println("Vector not belong to the code");
        }
    }
}
