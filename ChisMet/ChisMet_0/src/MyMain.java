import java.util.*;

public class MyMain
{
    private static void printMatrices(double[][] matrixA, double[] matrixB)
    {
        System.out.println("A = ");
        for(int i = 0; i < matrixA.length; i++)
        {
            System.out.print("(  ");
            for(int j = 0; j < matrixA[0].length; j++)
            {
                System.out.print(matrixA[i][j] + "  ");
            }
            System.out.print(")");
            System.out.println();
        }
        System.out.println("\n");

        System.out.println("B = ");
        for(int i = 0; i < matrixB.length; i++)
        {
            System.out.print("( " + matrixB[i] + " )");
            System.out.println();
        }
        System.out.println();
    }

    private static double findDeterminant(double[][] matrix)
    {
        double determinant = 0;

        if (matrix.length == 2)
        {
            determinant = matrix[0][0] * matrix[1][1] - matrix[1][0] * matrix[0][1];
        }
        else
        {
            int coeff;

            for(int i = 0; i < matrix.length; i++)
            {
                if(i % 2 == 1)
                    coeff =- 1;
                else
                    coeff = 1;

                determinant += coeff * matrix[0][i] * findDeterminant(getMinor(matrix,0,i));
            }
        }
        return determinant;
    }

    private static double[][] getMinor(double[][] matrix, int row, int column)
    {
        int minorLength = matrix.length-1;
        double[][] minor = new double[minorLength][minorLength];
        int dI = 0, dJ;

        for(int i = 0; i <= minorLength; i++)
        {
            dJ = 0;

            for(int j = 0; j <= minorLength; j++)
            {
                if(i == row)
                    dI = 1;
                else
                if(j == column)
                    dJ = 1;
                else
                    minor[i-dI][j-dJ] = matrix[i][j];

            }
        }
        return minor;
    }

    private static double[] Gaussian(double[][] matrixA, double[] matrixB)
    {
        double[][] mA = new double[matrixA.length][matrixA[0].length];
        double[] mB = new double[matrixB.length];

        for(int i = 0; i < mA.length; i++)
            for (int j = 0; j < mA[0].length; j++)
                mA[i][j] = matrixA[i][j];

        for(int i = 0; i < mB.length;i++)
            mB[i] = matrixB[i];

        for (int p = 0; p < mA.length; p++)
        {
            int max = p;
            for (int i = p + 1; i < mA.length; i++)
            {
                if (Math.abs(mA[i][p]) > Math.abs(mA[max][p]))
                {
                    max = i;
                }
            }
            double[] temp = mA[p];
            mA[p] = mA[max];
            mA[max] = temp;

            double t = mB[p];
            mB[p] = mB[max];
            mB[max] = t;

            for (int i = p + 1; i < mA.length; i++)
            {
                double alpha = mA[i][p] / mA[p][p];
                mB[i] -= alpha * mB[p];
                for (int j = p; j < mA.length; j++)
                {
                    mA[i][j] -= alpha * mA[p][j];
                }
            }
        }

        // Обратный проход

        double[] matrixX = new double[mA.length];
        for (int i = mA.length - 1; i >= 0; i--)
        {
            double sum = 0.0;
            for (int j = i + 1; j < mA.length; j++)
            {
                sum += mA[i][j] * matrixX[j];
            }
            matrixX[i] = (mB[i] - sum) / mA[i][i];
        }

        for (int i = 0; i < mA.length; i++)
            System.out.print("x" + (i+1) + " = " + matrixX[i] + ", ");
        System.out.println();
        return matrixX;
    }

    public static void main(String[] args)
    {
        double[][] matrixA;
        double[] matrixB;
        double[] matrixX;

        Scanner sc = new Scanner(System.in);
        System.out.print("Введите размерность: ");
        int size = sc.nextInt();

        matrixA = new double[size][size];
        matrixB = new double[size];

        System.out.println("Введите матрицу А: ");
        for(int i = 0; i < matrixA.length; i++)
            for(int j = 0; j < matrixA[0].length; j++)
                matrixA[i][j] = sc.nextDouble();

        System.out.println("Введите матрицу B: ");
        for (int i = 0; i < matrixB.length; i++)
            matrixB[i] = sc.nextDouble();

        printMatrices(matrixA, matrixB);

        if(findDeterminant(matrixA) == 0)
            System.out.println("Система несовместна");
        else
        {
            matrixX = Gaussian(matrixA, matrixB);

            double[] b = new double[matrixB.length];
            for(int i = 0; i < matrixA.length; i++)
            {
                double sum = 0;
                for(int j = 0; j < matrixA[0].length; j++)
                    sum += matrixA[i][j] * matrixX[j];
                b[i] = sum;
            }

            double[] infelicity = new double[matrixB.length];

            for(int i = 0; i < matrixB.length; i++)
                infelicity[i] = matrixB[i] - b[i];

            System.out.print("Погрешность: ");
            for(int i = 0; i < infelicity.length; i++)
                System.out.print(infelicity[i] + ", ");
        }
    }
}

