import java.util.*;

public class MyMain
{
    static double dy_dx (double x, double y, double z) { return 2*y - z - Math.pow(x,2); }

    static double dz_dx (double x, double y, double z) { return -3*y + z + x; }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        int N;
        double x0, y0, z0, b, h;

        System.out.print("Ведите конец отрезка: ");
        b = sc.nextDouble();

        System.out.print("x0 = ");
        x0 = sc.nextDouble();
        System.out.print("y0 = ");
        y0 = sc.nextDouble();
        System.out.print("z0 = ");
        z0 = sc.nextDouble();

        System.out.print("Введите шаг:");
        h = sc.nextDouble();

        N = (int)((b-x0)/h) +1;

        Euler (N, x0, y0, z0, h);
        Euler_modify (N, x0, y0, z0, h);
    }

    static void Euler (int N, double x, double y, double z, double h)
    {
        double y1;
        System.out.println("x   |   y   |   z");
        for (int i = 0; i <= N; i++)
        {
            y1=y+dy_dx(x, y, z)*h;
            z=z+dz_dx(x, y, z)*h;

            System.out.println(x + "   |   " + y + "   |   " + z);

            y=y1;
            x+=h;
        }
        System.out.println("-----------------------------------------------------------------------");
    }

    static void Euler_modify (int N, double x, double y, double z, double h)
    {
        double y1;
        double yi;
        double zi;
        System.out.println("x   |   y   |   z");
        for (int i = 0; i <= N; i++) {

            yi = y + dy_dx(x, y, z) * h;
            zi = z + dz_dx(x, y, z) * h;
            y1 = y + (dy_dx(x, y, z) + dy_dx(x + h, yi, zi)) * h / 2;
            z = z + (dz_dx(x, y, z) + dz_dx(x + h, yi, zi)) * h / 2;

            System.out.println(x + "   |   " + y + "   |   " + z);

            y = y1;
            x = x + h;
        }
    }
}
