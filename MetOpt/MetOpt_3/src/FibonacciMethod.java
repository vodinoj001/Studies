public class FibonacciMethod
{
    private static double f(double x)
    {
        return 2*Math.pow(x,2) - 12*x + 19;
    }

    public static void main(String[] args)
    {
        double a = 0, b = 10;
        double l = 0.2, e = 0.01, L = (b-a)/l;
        double[] F = {1,1,2,3,5,8,13,21,34,55,89,144};

        int N = 0;

        for(int i = 0; i < F.length; i++)
        {
            if(F[i] >= L)
            {
                N = i;
                break;
            }
        }

        System.out.println("Количество вычислений функции N = " + N + "\n");

        int k = 0;
        double yk = a + (F[N-2] / F[N]) * (b - a);
        double zk = (b - a) - yk;

        while(k != N - 2)
        {
            System.out.println("\n" + "На шаге " + k + "\n");
            System.out.println("y"+k+" = " + yk);
            System.out.println("z"+k+" = " + zk);
            System.out.println("f(y"+k+") = " + f(yk));
            System.out.println("f(z"+k+") = " + f(zk));

            if(f(yk) <= f(zk))
            {
                System.out.println("f(y"+k+") <= f(z"+k+")");

                b = zk;
                zk = yk;
                yk = a + (F[N-k-3] / F[N-k-1]) * (b - a);
            }
            else
            {
                System.out.println("f(y"+k+") > f(z"+k+")");

                a = yk;
                yk = zk;
                zk = a + (F[N-k-2] / F[N-k-1]) * (b - a);
            }
            k++;
        }

        System.out.println("\n" + "k = N - 3" + "\n");
        System.out.println("y"+(k+1)+" = y"+k+" = z"+k + " = " + yk);
        System.out.println("z"+(k+1) + " = " + (yk+e));

        if(f(yk) <= f(yk + e))
        {
            System.out.println("f(y"+(k+1)+") <= f(z"+(k+1)+")");
            b = yk + e;
        }
        else
        {
            System.out.println("f(y"+(k+1)+") > f(z"+(k+1)+")");
            a = yk;
        }

        System.out.println("\n" + "Точка минимума принадлежит интервалу: [" + a + ", " + b + "]");
        System.out.println("Приближенное решение x* = " + (a+b)/2);
        System.out.println("С погрешностью = " + Math.abs(3 - (a+b)/2));
    }
}
