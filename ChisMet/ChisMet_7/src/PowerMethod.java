import java.util.*;
import java.io.*;

public class PowerMethod
{
    private static Double _lambda;
    private static Double[] _vector;
    public static void main(String[] args)
    {
        File file = new File("C:\\Users\\vodin\\OneDrive\\Рабочий стол\\Test.txt");
        try(Scanner sc = new Scanner(file))
        {
            double e = 0.000001;
            int n = sc.nextInt();

            Double[][] A = new Double[n][n];
            Double[] y0 = new Double[n];

            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    A[i][j] = sc.nextDouble();

            Arrays.fill(y0, 1.0);

            sc.close();

            calculate(A, y0, e);

            FileOutputStream printStream = new FileOutputStream(file, true);

            printStream.write("\n Максимальное по модулю собственное значение: ".getBytes());
            printStream.write(Double.toString(_lambda).getBytes());
            printStream.write("\n Собственный вектор: ".getBytes());

            printStream.write("(| ".getBytes());
            for (double v : _vector)
            {
                printStream.write(Double.toString(v).getBytes());
                printStream.write(" | ".getBytes());
            }
            printStream.write(")".getBytes());
            printStream.close();
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    private static Double[] multiply(Double[][] matrix, Double[] y)
    {
        Double[] result = new Double[matrix.length];
        for (int i = 0; i < matrix.length; i++)
        {
            result[i] = 0.0;
            for (int j = 0; j < matrix.length; j++)
                result[i] += matrix[i][j] * y[j];
        }
        return result;
    }

    private static Double[] multiply(Double[] array, Double lambda)
    {
        Double[] result = array.clone();
        for (int i = 0; i < array.length; i++)
            result[i] *= lambda;
        return result;
    }

    private static Double multiply(Double[] vector, Double[] y)
    {
        double result = 0.0;
        for (int i = 0; i < vector.length; i++)
            result += vector[i] * y[i];
        return result;
    }

    private static Double[] subtract(Double[] array1, Double[] array2)
    {
        Double[] result = array1.clone();
        for (int i = 0; i < array1.length; i++)
            result[i] -= array2[i];
        return result;
    }

    private static Double[] residual(Double[][] matrix, Double[] x, Double lambda)
    {
        Double[] a = multiply(matrix, x);
        Double[] b = multiply(x, lambda);
        return subtract(a, b);
    }

    private static Double getNorm(Double[] i)
    {
        return Math.sqrt(multiply(i, i));
    }

    private  static Double AitkenProcess(ArrayList<Double> lambdas)
    {
        double a = Math.pow((lambdas.get(1) - lambdas.get(0)) ,2);
        double b = lambdas.get(2) - 2*lambdas.get(1) + lambdas.get(0);

        return lambdas.get(0) - a/b;
    }

    private static void calculate(Double[][] matrix, Double[] y, double e)
    {
        int count = 0;

        Double[] xk = multiply(y.clone(), 1 / getNorm(y));
        Double[] yk1 = multiply(matrix, xk);
        double lambda = multiply(yk1, xk);
        Double[] xk1 = multiply(yk1, 1 / getNorm(yk1));
        Double sk;
        Double tk;
        ArrayList<Double> lambdas = new ArrayList<>();

        while (getNorm(residual(matrix, xk1, lambda)) > e)
        {
            if(count >= 10000)
            {
                System.out.println("Итерационный процесс расходится.");
                break;
            }
            else
            {
                xk = multiply(yk1.clone(), 1 / getNorm(yk1));
                yk1 = multiply(matrix, xk);
                sk = multiply(yk1,yk1);
                tk = multiply(yk1, xk);
                lambda = sk / tk;
                lambdas.add(lambda);
                xk1 = multiply(yk1, 1 / getNorm(yk1));

                if(lambdas.size() == 3)
                {
                    lambda = AitkenProcess(lambdas);
                    lambdas.clear();
                    lambdas.add(lambda);
                }
            }
        }
        _lambda = lambda;
        _vector = xk1;
    }
}