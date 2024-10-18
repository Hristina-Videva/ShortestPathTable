import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        int size = Integer.parseInt(JOptionPane.showInputDialog("Enter the size of the array:"));

        int[][] table = TableArray(size);
        int first = Integer.parseInt(JOptionPane.showInputDialog("Enter the beginning point:"));

        Calculate(table,size,first);
    }
    //#region Enter the table
    public static int[][] TableArray(int size){
        int[][] table = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                String valueInput = JOptionPane.showInputDialog("Enter value for element [" +  (i + 1) + "][" + (j +1 ) + "]:");
                table[i][j] = Integer.parseInt(valueInput);
            }
        }

        StringBuilder result = new StringBuilder("You entered:\n");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                result.append(table[i][j]).append(" ");
            }
            result.append("\n");
        }
        JOptionPane.showMessageDialog(null, result.toString());
        return table;
    }
    //#endregion
    //#region Calculations
    public static void Calculate(int table[][], int size, int first){
        StringBuilder result = new StringBuilder("LPC Table:\n");
        int[] L = new int[size];
        int[] P = new int[size];
        int[] C = new int[size];

        //#region First LPC
        L[first - 1] = 1;
        for(int i = 0; i < size; i++){
            result.append(L[i]).append(" ");
        }
        result.append("\n");
        for (int i = 0; i < size; i++){
            P[i] = first;
            if(i == first - 1){
                P[i] = 0;
            }
            result.append(P[i]).append(" ");
        }
        result.append("\n");
        for(int i = 0; i < size; i++){
            C[i] = table[first - 1][i];
            result.append(C[i]).append(" ");
        }
        result.append("\n");
        result.append("------------------");
        result.append("\n");
        //#endregion

        //#region Cycling the rest of the LPC tables
        for(int n = 0; n < size - 1; n++){
            int min = C[first - 1];
            int minI = first - 1;
            for(int j = 0; j < size; j++){
                if(L[j] == 0 && min > C[j]){
                    min = C[j];
                    minI = j;
                }
            }
            for(int j = 0; j < size; j++){
                if(L[j] == 0) {
                    int pC = min + table[minI][j];
                    if (pC < C[j]){
                        P[j] = minI;
                        C[j] = pC;
                    }
                }
            }
            L[minI] = 1;
            for(int k = 0; k < size; k++){
                result.append(L[k]).append(" ");
            }
            result.append("\n");
            for(int k = 0; k < size; k++){
                result.append(P[k]).append(" ");
            }
            result.append("\n");
            for(int k = 0; k < size; k++){
                result.append(C[k]).append(" ");
            }
            result.append("\n");
            result.append("------------------");
            result.append("\n");
        }
        //#endregion
        JOptionPane.showMessageDialog(null, result.toString());
    }
    //#endregion
}