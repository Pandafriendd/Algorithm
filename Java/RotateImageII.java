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
/**
The idea was firstly transpose the matrix and then flip it symmetrically. For instance,

1  2  3             
4  5  6
7  8  9
after transpose, it will be swap(matrix[i][j], matrix[j][i])

1  4  7
2  5  8
3  6  9
Then flip the matrix horizontally. (swap(matrix[i][j], matrix[i][matrix.length-1-j])

7  4  1
8  5  2
9  6  3
**/
public class Solution {
    public void rotate(int[][] matrix) {
        for(int i = 0; i<matrix.length; i++){
            for(int j = i; j<matrix[0].length; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for(int i =0 ; i<matrix.length; i++){
            for(int j = 0; j<matrix.length/2; j++){
                int temp = 0;
                temp = matrix[i][j];
                matrix[i][j] = matrix[i][matrix.length-1-j];
                matrix[i][matrix.length-1-j] = temp;
            }
        }
    }
}