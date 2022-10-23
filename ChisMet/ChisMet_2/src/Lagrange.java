import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;


import java.util.*;
import java.util.function.Function;
import java.util.ArrayList;

public class Lagrange // (x^3)/3 - 3x
{
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Введите количество точек: ");
        int n = sc.nextInt();

        Workbook wb = new HSSFWorkbook();
        Sheet sheet = wb.createSheet("1");

        Row row0 = sheet.createRow(0);
        Row row1 = sheet.createRow(1);
        Cell[] cells0 = new Cell[n];
        Cell[] cells1 = new Cell[n];

        for(int i = 0; i < cells0.length; i++)
        {
            cells0[i] = row0.createCell(i);
            cells1[i] = row1.createCell(i);
        }

        double[] xValues = new double[n];
        double[] yValues = new double[n];

        for(int i = 0, j = -xValues.length / 2; i < xValues.length; i++, j++)
            xValues[i] = j;
        for(int i = 0; i < yValues.length; i++)
            yValues[i] = (Math.pow(xValues[i], 3))/3 - 3 * xValues[i];

        Function<Double, Double> lagrangePolynomial = createLagrangePolynomial(xValues, yValues);

        int i = 0;
        for(double x : xValues)
        {
            cells0[i].setCellValue(x);
            cells1[i].setCellValue(lagrangePolynomial.apply(x));
            i++;
        }

        FileOutputStream fos = new FileOutputStream("C:/Users/vodin/OneDrive/Рабочий стол/ChisMet.xls");
        wb.write(fos);
        fos.close();
    }

    public static Function<Double, Double> createBasicPolynomial(double[] xValues, int i)
    {
        Function<Double, Double> basicPolynomial = (x) -> {
            double divider = 1;
            double result = 1;
            for(int j = 0; j < xValues.length; j++)
            {
                if(j != i)
                {
                    result *= x - xValues[j];
                    divider *= xValues[i] - xValues[j];
                }
            }
            return result / divider;
        };
        return basicPolynomial;
    }

    public static Function<Double, Double> createLagrangePolynomial(double[] xValues, double[] yValues)
    {
        List<Function<Double, Double>> basicPolynomial = new ArrayList<>();
        for(int i = 0; i < xValues.length; i++)
            basicPolynomial.add(createBasicPolynomial(xValues, i));

        Function<Double, Double> lagrangePolinome = (x) -> {
            double result = 0;
            for(int i = 0; i < xValues.length; i++)
                result += yValues[i] * basicPolynomial.get(i).apply(x);
            return result;
        };
        return lagrangePolinome;
    }
}
