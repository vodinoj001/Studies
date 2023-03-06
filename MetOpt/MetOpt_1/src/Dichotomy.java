public class Dichotomy
{
    private static double f(double x)
    {
        return 2*Math.pow(x,2) - 12*x + 19;
    }

    public static void main(String[] args)
    {
        double a = 0, b = 10;
        double e = 0.01, l = 1;
        double yk,zk;

        int k = 0;
        while(b-a >= l)
        {
           yk = (a + b - e)/2;
           zk = yk + e;

            System.out.println("\n" + "На шаге " + k + "\n");
            System.out.println("y"+k+" = " + yk);
            System.out.println("z"+k+" = " + zk);
            System.out.println("f(y"+k+") = " + f(yk));
            System.out.println("f(z"+k+") = " + f(zk));

           if(f(yk) <= f(zk))
           {
               System.out.println("f(y"+k+") <= f(z"+k+")");
               b = zk;
           }
           else
           {
               System.out.println("f(y"+k+") > f(z"+k+")");
               a = yk;
           }
           k++;
        }

        System.out.println("Точка минимума принадлежит интервалу [" + a + ", " + b + "]");
        System.out.println("Приближенное решение x* = " + (a+b)/2);
        System.out.println("С погрешность = " + (b-a)/2);
    }
}
