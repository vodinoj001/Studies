public class GoldenRatio
{
    private static double f(double x)
    {
        return 2*Math.pow(x,2) - 12*x + 19;
    }

    public static void main(String[] args)
    {
        double a = 0, b = 10;
        double gr = 0.38196, l = 1, E = Math.abs(a - b);
        double  yk = a + gr * (b - a), zk = a + b - yk;

        int k = 0;
        while(E > l)
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
                yk = a + b - yk;
            }
            else
            {
                System.out.println("f(y"+k+") > f(z"+k+")");
                a = yk;
                yk = zk;
                zk = a + b - zk;
            }

            E = Math.abs(a - b);
            if(E > l)
                System.out.println("E = |a"+k+" - b"+k+"| = " + E + " > l = " + l);
            else
                System.out.println("E = |a"+k+" - b"+k+"| = " + E + " <= l = " + l);
            k++;
        }

        System.out.println("Точка минимума принадлежит интервалу [" + a + ", " + b + "]");
        System.out.println("Приближенное решение x* = " + (a+b)/2);
        System.out.println("С погрешность = " + (3 - (a+b)/2));
    }
}
