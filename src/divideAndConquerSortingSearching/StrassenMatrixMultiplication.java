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
            int[][] q1 = addMatrix(multiplyMatrix(getFirstQuadrant(A),getFirstQuadrant(B),mid),multiplyMatrix(getSecondQuadrant(A),getThirdQuadrant(B),mid),mid);
            int[][] q2 = addMatrix(multiplyMatrix(getFirstQuadrant(A),getSecondQuadrant(B),mid),multiplyMatrix(getSecondQuadrant(A),getFourthQuadrant(B),mid),mid);
            int[][] q3 = addMatrix(multiplyMatrix(getThirdQuadrant(A),getFirstQuadrant(B),mid),multiplyMatrix(getFourthQuadrant(A),getThirdQuadrant(B),mid),mid);
            int[][] q4 = addMatrix(multiplyMatrix(getThirdQuadrant(A),getSecondQuadrant(B),mid),multiplyMatrix(getFourthQuadrant(A),getFourthQuadrant(B),mid),mid);

            return combineQuadrants(q1,q2,q3,q4);
        }
    }



    public static int[][] combineQuadrants(int[][] q1, int[][] q2, int[][] q3, int[][] q4) {

        int[][] C = new int[4][4];

        for(int i=0;i<2;i++) {
            for(int j=0;j<2;j++) {

                C[i][j] = q1[i][j];
            }
        }



        for(int i=0;i<2;i++) {

            for(int j=0;j<2;j++) {

                C[i][j+2] = q2[i][j];
            }
        }



        for(int i=0;i<2;i++) {

            for(int j=0;j<2;j++) {

                C[i+2][j] = q3[i][j];
            }
        }



        for(int i=0;i<2;i++) {

            for(int j=0;j<2;j++) {

                C[i+2][j+2] = q4[i][j];
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


    public static int[][] getFirstQuadrant(int[][] A) {

        int[][] C = new int[2][2];
        for(int i=0;i<2;i++) {
            for(int j=0;j<2;j++) {

                C[i][j] = A[i][j];
            }
        }

        return C;
    }


    public static int[][] getSecondQuadrant(int[][] A) {

        int[][] C = new int[2][2];
        for(int i=0;i<2;i++) {
            for(int j=2;j<4;j++) {
                C[i][j-2] = A[i][j];
            }
        }

        return C;
    }


    public static int[][] getThirdQuadrant(int[][] A) {

        int[][] C = new int[2][2];
        for(int i=2;i<4;i++) {
            for(int j=0;j<2;j++) {

                C[i-2][j] = A[i][j];
            }
        }

        return C;
    }


    public static int[][] getFourthQuadrant(int[][] A) {

        int[][] C = new int[2][2];
        for(int i=2;i<4;i++) {
            for(int j=2;j<4;j++) {

                C[i-2][j-2] = A[i][j];
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

        int C[][] = multiplyMatrix(A,B,4);

        for (int i=0;i<4;i++) {
            for (int j=0;j<4;j++) {

                System.out.print(C[i][j] + " ");
            }
            System.out.println();
        }


    }

}


