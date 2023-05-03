import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class AdamsMethod
{
    public static void main(String[] args) throws IOException
    {
        Scanner sc = new Scanner(System.in);

        double h = 0.1;

        System.out.print("t0 = ");
        double t0 = sc.nextDouble();

        double tMax = 2.0;
        int n = (int) ((tMax - t0) / h);

        double[] t = new double[n+1];

        double[] xPred = new double[n+1];
        double[] yPred = new double[n+1];
        double[] xCor = new double[n+1];
        double[] yCor = new double[n+1];

        double[] solutionY = new double[n+1];
        double[] solutionX = new double[n+1];

// начальные значения
        t[0] = t0;
        System.out.print("x(t0) = ");
        xPred[0] = sc.nextDouble();
        System.out.print("y(t0) = ");
        yPred[0] = sc.nextDouble();

        xCor[0] = xPred[0];
        yCor[0] = yPred[0];

// вычисляем первый шаг методом Рунге-Кутта
        double k11, k12, k21, k22, k31, k32, k41, k42;
        k11 = h * dxdt(t[0], xPred[0], yPred[0]);
        k12 = h * dydt(t[0], xPred[0], yPred[0]);
        k21 = h * dxdt(t[0]+h/2, xPred[0]+k11/2, yPred[0]+k12/2);
        k22 = h * dydt(t[0]+h/2, xPred[0]+k11/2, yPred[0]+k12/2);
        k31 = h * dxdt(t[0]+h/2, xPred[0]+k21/2, yPred[0]+k22/2);
        k32 = h * dydt(t[0]+h/2, xPred[0]+k21/2, yPred[0]+k22/2);
        k41 = h * dxdt(t[0]+h, xPred[0]+k31, yPred[0]+k32);
        k42 = h * dydt(t[0]+h, xPred[0]+k31, yPred[0]+k32);

        t[1] = t0 + h;
        xPred[1] = xPred[0] + h/6 * (k11 + 2*k21 + 2*k31 + k41);
        yPred[1] = yPred[0] + h/6 * (k12 + 2*k22 + 2*k32 + k42);
        xCor[1] = xPred[1];
        yCor[1] = yPred[1];

// вычисляем остальные значения методом Адамса
        for (int i = 2; i <= n; i++)
        {
            xPred[i]= xPred[i-1] + h * dxdt(t[i-1], xPred[i-1], yPred[i-1]);
            yPred[i] = yPred[i-1] + h * dydt(t[i-1], xPred[i-1], yPred[i-1]);

            xCor[i] = xPred[i-1] + h * dxdt(t[i], xPred[i], yPred[i]);
            yCor[i] = yPred[i-1] + h * dydt(t[i], xPred[i], yPred[i]);

            t[i] = t0 + i*h;
        }

        for(int i = 0; i < t.length; i++)
        {
            solutionY[i] = y(t[i],0,0);
            solutionX[i] = x(t[i],0,0);
        }

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet1 = workbook.createSheet("Пример 1");
        Row row = sheet1.createRow(0);
        row.createCell(0).setCellValue("T");
        row.createCell(1).setCellValue("X");
        row.createCell(2).setCellValue("Y");
        row.createCell(3).setCellValue("ТочноеX");
        row.createCell(4).setCellValue("ТочноеY");
        row.createCell(5).setCellValue("ДельтаX");
        row.createCell(6).setCellValue("ДельтаY");

        for (int i = 1; i < t.length + 1; ++i)
        {
            row = sheet1.createRow(i);
            row.createCell(0).setCellValue(t[i-1]);
            row.createCell(1).setCellValue(xCor[i-1]);
            row.createCell(2).setCellValue(yCor[i-1]);
            row.createCell(3).setCellValue(solutionX[i-1]);
            row.createCell(4).setCellValue(solutionY[i-1]);
            row.createCell(5).setCellValue(Math.abs(xCor[i-1] - solutionX[i-1]));
            row.createCell(6).setCellValue(Math.abs(yCor[i-1] - solutionY[i-1]));

        }

        FileOutputStream fos = new FileOutputStream("C:/Users/vodin/OneDrive/Рабочий стол/AdamsMethod2.xls");
        workbook.write(fos);
        fos.close();

    }

    public static double dxdt(double t, double x, double y)
    {
        //return x + y;     //Пример 1  0 1 0
        //return x - y;    //Пример 2  0 0 1
        return 3*x - y;   //Пример 3  0 0 0
    }

    public static double dydt(double t, double x, double y)
    {
        //return  -x + y;       //Пример 1
        //return -4*x + y;     //Пример 2
        return 13*x - 3*y;    //Пример 3
    }

    public static double x(double t, double x, double y)
    {
        //return Math.exp(t)*Math.cos(t);           //Пример 1
        //return (Math.exp(-t) - Math.exp(3*t))/4; //Пример 2
        //return 0;                               //Пример 3
    }

    public static double y(double t, double x, double y)
    {
        //return -Math.exp(t)*Math.sin(t);          //Пример 1
        //return (Math.exp(3*t) - Math.exp(-t))/2; //Пример 2
        //return 0;                               //Пример 3
    }
}
