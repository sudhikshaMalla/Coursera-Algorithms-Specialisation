package src.divideAndConquerSortingSearching;

public class StrassenMatrixMultiplication {

    public static int[][] multiplyMatrix(int[][] A, int[][] B, int n) {

        if(n==2) {

            int[][] C = new int[2][2];
            int p = (A[0][0]+A[1][1])*(B[0][0]+B[1][1]);
            int q = (A[1][0]+A[1][1])*B[0][0];
            int r = A[0][0]*(B[0][1]-B[1][1]);
            int s = A[1][1]*(B[1][0]-B[0][0]);
            int t = (A[0][0] + A[0][1])*B[1][1];
            int u = (A[1][0]-A[0][0])*(B[0][0]+B[0][1]);
            int v = (A[0][1]-A[1][1])*(B[1][0]+B[1][1]);
            C[0][0] = p+s-t+v;
            C[0][1] = r+t;
            C[1][0] = q+s;
            C[1][1] = p+r-q+u;
            return C;
        }
        else {

            int mid = n/2;
            int[][] q1 = addMatrix(multiplyMatrix(getFirstQuadrant(A,n),getFirstQuadrant(B,n),mid),multiplyMatrix(getSecondQuadrant(A,n),getThirdQuadrant(B,n),mid),mid);
            int[][] q2 = addMatrix(multiplyMatrix(getFirstQuadrant(A,n),getSecondQuadrant(B,n),mid),multiplyMatrix(getSecondQuadrant(A,n),getFourthQuadrant(B,n),mid),mid);
            int[][] q3 = addMatrix(multiplyMatrix(getThirdQuadrant(A,n),getFirstQuadrant(B,n),mid),multiplyMatrix(getFourthQuadrant(A,n),getThirdQuadrant(B,n),mid),mid);
            int[][] q4 = addMatrix(multiplyMatrix(getThirdQuadrant(A,n),getSecondQuadrant(B,n),mid),multiplyMatrix(getFourthQuadrant(A,n),getFourthQuadrant(B,n),mid),mid);

            return combineQuadrants(q1,q2,q3,q4,n);
        }
    }

    public static int[][] combineQuadrants(int[][] q1, int[][] q2, int[][] q3, int[][] q4, int array_length) {
        int quadrant_length = array_length/2;
        int[][] C = new int[array_length][array_length];

        for(int i=0;i<quadrant_length;i++) {
            for(int j=0;j<quadrant_length;j++) {

                C[i][j] = q1[i][j];
            }
        }

        for(int i=0;i<quadrant_length;i++) {

            for(int j=0;j<quadrant_length;j++) {

                C[i][j+quadrant_length] = q2[i][j];
            }
        }

        for(int i=0;i<quadrant_length;i++) {

            for(int j=0;j<quadrant_length;j++) {

                C[i+quadrant_length][j] = q3[i][j];
            }
        }

        for(int i=0;i<quadrant_length;i++) {

            for(int j=0;j<quadrant_length;j++) {

                C[i+quadrant_length][j+quadrant_length] = q4[i][j];
            }
        }

        return C;
    }

    public static int[][] addMatrix(int[][] A, int[][] B, int n) {

        int[][] C = new int[n][n];
        for(int i=0;i<n;i++) {
            for(int j=0; j<n; j++) {

                C[i][j] = A[i][j] + B[i][j];
            }
        }

        return C;
    }

    public static int[][] getFirstQuadrant(int[][] A, int array_length) {
        int quadrant_length = array_length/2;
        int[][] C = new int[quadrant_length][quadrant_length];
        for(int i=0;i<quadrant_length;i++) {
            for(int j=0;j<quadrant_length;j++) {

                C[i][j] = A[i][j];
            }
        }

        return C;
    }

    public static int[][] getSecondQuadrant(int[][] A, int array_length) {
        int quadrant_length = array_length/2;
        int[][] C = new int[quadrant_length][quadrant_length];
        for(int i=0;i<quadrant_length;i++) {
            for(int j=quadrant_length;j<array_length;j++) {
                C[i][j-quadrant_length] = A[i][j];
            }
        }

        return C;
    }

    public static int[][] getThirdQuadrant(int[][] A, int array_length) {
        int quadrant_length = array_length/2;
        int[][] C = new int[quadrant_length][quadrant_length];
        for(int i=quadrant_length;i<array_length;i++) {
            for(int j=0;j<quadrant_length;j++) {
                C[i-quadrant_length][j] = A[i][j];
            }
        }

        return C;
    }

    public static int[][] getFourthQuadrant(int[][] A, int array_length) {
        int quadrant_length = array_length/2;
        int[][] C = new int[quadrant_length][quadrant_length];
        for(int i=quadrant_length;i<array_length;i++) {
            for(int j=quadrant_length;j<array_length;j++) {

                C[i-quadrant_length][j-quadrant_length] = A[i][j];
            }
        }

        return C;
    }

    public static void main(String[] args) {

        int A[][] = {{1,2,3,2},
                {4,2,6,5},
                {2,1,3,4},
                {6,5,3,2}};

        int B[][] = {{1,2,3,4},
                {2,3,1,2},
                {5,6,6,5},
                {3,3,2,1}};

        int C[][] = multiplyMatrix(A,B,A.length);

        for (int i=0;i< A.length;i++) {
            for (int j=0;j<A.length;j++) {

                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }

    }

}


