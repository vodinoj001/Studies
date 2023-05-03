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

        System.out.print("x0 = ");
        double x0 = sc.nextDouble();

        double b = 5.0;
        int n = (int) ((b - x0) / h);

        double[] x = new double[n+1];
        double[] yPred = new double[n+1];
        double[] yCor = new double[n+1];
        double[] solutionY = new double[n+1];

        // начальные значения
        x[0] = x0;
        System.out.print("y(x0) = ");
        yPred[0] = sc.nextDouble();
        yCor[0] = yPred[0];

        // вычисляем первый шаг методом Рунге-Кутта
        double k1, k2, k3, k4;
        k1 = dydt(x[0], yPred[0]);
        k2 = dydt(x[0] + h/2, yPred[0] + h/2 * k1);
        k3 = dydt(x[0]+ h/2, yPred[0] + h/2 * k2);
        k4 = dydt(x[0] + h, yPred[0] + h*k3);

        x[1] = x0 + h;

        yPred[1] = yPred[0] + h/6 * (k1 + 2*k2 + 2*k3 + k4);
        yCor[1] = yPred[1];

        // вычисляем остальные значения методом Адамса
        for (int i = 2; i <= n; i++)
        {
            yPred[i] = yPred[i-1] + h * dydt(x[i-1], yPred[i-1]);
            yCor[i] = yPred[i-1] + h * dydt(x[i-1], yPred[i]);

            x[i] = x0 + i*h;
        }

        for(int i = 0; i < x.length; i++)
        {
            solutionY[i] = y(x[i]);
        }

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet1 = workbook.createSheet("Пример 1");
        Row row = sheet1.createRow(0);
        row.createCell(0).setCellValue("X");
        row.createCell(1).setCellValue("Y");
        row.createCell(2).setCellValue("Точное");
        row.createCell(3).setCellValue("delta");
        for (int i = 1; i < x.length + 1; ++i)
        {
            row = sheet1.createRow(i);
            row.createCell(0).setCellValue(x[i-1]);
            row.createCell(1).setCellValue(yCor[i - 1]);
            row.createCell(2).setCellValue(solutionY[i - 1]);
            row.createCell(3).setCellValue(Math.abs(yCor[i - 1] - solutionY[i - 1]));
        }

        FileOutputStream fos = new FileOutputStream("C:/Users/vodin/OneDrive/Рабочий стол/AdamsMethod.xls");
        workbook.write(fos);
        fos.close();
    }

    public static double y(double x)
    {
        //return Math.exp(x) - 2*x + 1;              //Пример1 Входные: 0,0 и 2,0
        //return Math.exp(x);                       //Пример2 Входные: 1,0 и 2,718
        return x*Math.exp((-1)*Math.sin(x));     //Пример3 Входные: 0,0 и 0,0
    }

    public static double dydt(double x, double y)
    {
        //return y + 2*x - 3;                                       //Пример 1
        //return y * Math.log(y) / x;                              //Пример 2
        return Math.exp((-1)*Math.sin(x))- (y*Math.cos(x));     //Пример 3
    }
}
