import java.util.*;

public class LU
{
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);

        int n;
        double sum;
        boolean isIncompatible = false;

        System.out.print("Введите размерность: ");
        n = sc.nextInt();

        double A[][] = new double[n][n];
        double B[] = new double[n];

        double L[][] = new double[n][n];
        double U[][] = new double[n][n];

        double Y[] = new  double[n];
        double X[] = new  double[n];

        System.out.println("Введите матрицу А: ");
        for(int i = 0; i < A.length; i++)
            for(int j = 0; j < A[0].length; j++)
            {
                A[i][j] = sc.nextDouble();
                L[i][j] = 0;
                U[i][j] = 0;
            }

        System.out.println("Введите матрицу B: ");
            for(int i = 0; i < B.length; i++)
                B[i] = sc.nextDouble();

        for(int i = 0; i < A.length; i++)
        {
            for(int j = 0; j < A[0].length; j++)
            {
                if(j == 0)
                    L[i][j] = A[i][j];
                else if(j <= i)
                {
                    sum = 0;
                    for(int k = 0; k <= j ; k++)
                        sum += L[i][k] * U[k][j];

                    L[i][j] = A[i][j] - sum;
                }
                if(j >= i)
                {
                    sum = 0;
                    for(int k = 0; k <= i ; k++)
                        sum += L[i][k] * U[k][j];

                    U[i][j] = (A[i][j] - sum) / L[i][i];
                }

                if(Double.isNaN(U[i][j]) || Double.isNaN(L[i][j]) ||
                        Double.isInfinite(U[i][j]) || Double.isInfinite(L[i][j]))
                    isIncompatible = true;
            }
        }
        if(isIncompatible = false)
        {
            Y[0] = B[0] / L[0][0];
            for (int i = 1; i < Y.length; i++) {
                sum = 0;
                for (int k = 0; k <= i; k++) {
                    sum += Y[k] * L[i][k];
                }
                Y[i] = (B[i] - sum) / L[i][i];
            }

            X[X.length - 1] = Y[Y.length - 1];
            for (int i = X.length - 2; i >= 0; i--) {
                sum = 0;
                for (int k = i + 1; k < X.length; k++) {
                    sum += U[i][k] * X[k];
                }
                X[i] = Y[i] - sum;
            }


            System.out.println("L = ");
            for (int i = 0; i < L.length; i++) {
                System.out.print("(  ");
                for (int j = 0; j < L[0].length; j++) {
                    System.out.print(L[i][j] + "  ");
                }
                System.out.print(")");
                System.out.println();
            }
            System.out.println("\n");

            System.out.println("U = ");
            for (int i = 0; i < U.length; i++) {
                System.out.print("(  ");
                for (int j = 0; j < U[0].length; j++) {
                    System.out.print(U[i][j] + "  ");
                }
                System.out.print(")");
                System.out.println();
            }
            System.out.println("\n");

            for (int i = 0; i < Y.length; i++)
                System.out.println("y" + (i + 1) + " = " + Y[i]);
            System.out.println("\n");

            for (int i = 0; i < X.length; i++)
                System.out.println("x" + (i + 1) + " = " + X[i]);
        }
        else
            System.out.println("Система несовместна");
    }
}

