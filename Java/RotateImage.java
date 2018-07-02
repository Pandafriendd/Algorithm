class Solution {
    public void rotate(int[][] matrix) {
        if(matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return;
        int len = matrix.length; //how many rows
        
        for(int i = 0; i < len / 2; i++){
            for(int j = i; j < len - i - 1; j++){
                int temp = matrix[i][j]; //save in temp
                matrix[i][j] = matrix[len - j - 1][i]; //first col;
                matrix[len - j - 1][i] = matrix[len - i -1][len - j - 1]; //last row
                matrix[len - i - 1][len - j - 1] = matrix[j][len - i - 1]; //last col;
                matrix[j][len - i - 1] = temp;
                for(int k = 0; k < matrix.length; k++){
                    for(int l = 0; l < matrix[k].length; l++){
                        System.out.print(matrix[k][l] + " ");
                    }
                    System.out.println();
            }
            
            }
        }
    }
}