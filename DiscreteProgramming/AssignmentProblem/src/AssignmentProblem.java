import java.util.*;

public class AssignmentProblem
{
    private int[][] matrixC;
    private int [][] minCompMat;
    private ArrayList<Integer> markerColumns;

    public AssignmentProblem(int n)
    {
        matrixC = new int[n][n];
        minCompMat = new int[n][n];
        markerColumns = new ArrayList<>();

        createMatrix();
    }

    public void setMatrixC (int[][] matrix) { matrixC = matrix;}

    public int find()
    {
        findMinInLine();

        do
        {
            findMarkerColumns();
            increaseMarkerColumns();
            checkNewMinInLine();

        }while (markerColumns.size() != 0);

        for(int i = 0; i < minCompMat.length; i++)
        {
            for(int j = 0; j < minCompMat.length; j++)
                System.out.print(matrixC[i][j] + " ");
            System.out.println();
        }

        System.out.println();

        for(int i = 0; i < minCompMat.length; i++)
        {
            for(int j = 0; j < minCompMat.length; j++)
                System.out.print(minCompMat[i][j] + " ");
            System.out.println();
        }

        return getSum();
    }

    private void createMatrix()
    {
        for(int i = 0; i < matrixC.length; i++)
            for (int j = 0; j < matrixC.length; j++)
                minCompMat[i][j] = 0;
    }

    private void findMinInLine()
    {
        int min;
        int indexJ;

        for(int i = 0; i < matrixC.length; i++)
        {
            min = 10000;
            indexJ = 0;

            for(int j = 0; j < matrixC.length; j++)
            {
                if(matrixC[i][j] < min)
                {
                    min = matrixC[i][j];
                    indexJ = j;
                }
            }

            minCompMat[i][indexJ] = 1;

            for(int j = 0; j < matrixC.length; j++)
                if(j != indexJ && matrixC[i][j] == min)
                    minCompMat[i][j] = 1;
        }


        ArrayList<Integer> minInLine = new ArrayList<>();
        for(int i = 0; i < minCompMat.length; i++)
        {
            minInLine.clear();
            for(int j = 0; j < minCompMat.length; j++)
                if(minCompMat[i][j] == 1)
                    minInLine.add(j);

            if(minInLine.size() > 1)
            {
                boolean flag;
                for(int m = 0; m < minInLine.size(); m++)
                {
                    flag = false;
                    for (int k = 0; k < minCompMat.length; k++)
                    {
                        if (k != i && minCompMat[k][minInLine.get(m)] == 1)
                        {
                            flag = true;
                        }
                    }

                    if(!flag)
                    {
                         for(int n = 0; n < minInLine.size(); n++)
                         {
                             if (n != m)
                             {
                                 minCompMat[i][minInLine.get(n)] = 0;
                             }
                         }
                         break;
                    }
                    else if(m + 1 < minInLine.size())
                    {
                        minCompMat[i][minInLine.get(m)] = 0;
                    }
                }
            }
        }
    }

    private void findMarkerColumns()
    {
        int count;
        for(int j = 0; j < minCompMat.length; j++)
        {
            count = 0;
            for(int i = 0; i < minCompMat.length; i++)
            {
                if(minCompMat[i][j] == 1)
                    count++;
            }

            if(count > 1)
                markerColumns.add(j);
            else if(markerColumns.contains(j))
                markerColumns.remove(markerColumns.indexOf(j));
        }
    }

    private void increaseMarkerColumns()
    {
        int min;
        int minDifference = 100000;

        for(Integer j : markerColumns)
        {
            for(int i = 0; i < minCompMat.length; i++)
            {
                if(minCompMat[i][j] == 1)
                {
                    min = 100000;
                    for(int k = 0; k < matrixC.length; k++)
                        if(!markerColumns.contains(k) && matrixC[i][k] < min)
                            min = matrixC[i][k];

                    if(min - matrixC[i][j] < minDifference)
                        minDifference = min - matrixC[i][j];
                }
            }
        }

        int j0 = 100000;
        for(Integer j : markerColumns)
        {
            if(j0 != j)
            {
                for(int i = 0; i < matrixC.length; i++)
                {
                    matrixC[i][j] += minDifference;
                }
            }
            j0 = j;
        }
    }

    private void checkNewMinInLine()
    {
        for(Integer j : markerColumns)
            for(int i = 0; i < minCompMat.length; i++)
                if(minCompMat[i][j] == 1)
                    for(int k = 0; k < matrixC.length; k++)
                        if(!markerColumns.contains(k) && matrixC[i][k] <= matrixC[i][j])
                        {
                            minCompMat[i][j] = 0;
                            minCompMat[i][k] = 1;
                        }
    }

    private int getSum()
    {
        int sum = 0;
        for(int i = 0; i < minCompMat.length; i++)
            for(int j = 0; j < minCompMat.length; j++)
                if(minCompMat[i][j] == 1)
                    sum+=matrixC[i][j];

        return sum;
    }

}
