import java.util.*;
import java.util.function.Function;

public class MetodHord
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        Function<Double, Double> equation = (x) -> Math.pow(x, 3)/3 - 3*x;

        System.out.println("Введите точку A: ");
        double a = sc.nextDouble();
        System.out.println("Введите точку B: ");
        double b = sc.nextDouble();
        System.out.println("Введите погрещность: ");
        double err = sc.nextDouble();

        double root = findRoot(equation, a, b, err);
        System.out.println("x = " + root);
        System.out.println("F(x) = " + equation.apply(root));
    }

    public static double findRoot (Function<Double, Double> equation, double a, double b, double err)
    {
        double aValue = equation.apply(a);
        double bValue = equation.apply(b);

        if(Math.signum(aValue) * Math.signum(bValue) > 0)
            throw new IllegalArgumentException("Начальные точки выбраны неверно!");

        double cValue;
        double c1 = a;
        double c2;

        while(true)
        {
            aValue = equation.apply(a);
            bValue = equation.apply(b);
            c2 = a - (aValue * (a - b)) / (aValue - bValue);
            cValue = equation.apply(c2);

            if(Math.abs(c2 - c1) < err)
                return c2;
            else
                a = c2;

            c1 = c2;
        }
    }
}
