import java.util.*;

public class Monte_Carlo
{
    private static double f(double x) { return 1/Math.pow(Math.cos(x),2);}

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите начало отрезка: ");
        double a = sc.nextDouble();
        System.out.println("Введите конец отрезка:");
        double b = sc.nextDouble();

        System.out.println("Вветите количество случайных чисел: ");
        int n = sc.nextInt();

        double sum = 0;
        double length = b-a;
        for(int i = 0; i < n; i++)
            sum += f( a + (Math.random() * length));

        System.out.println((sum/n) * length);
    }
}
