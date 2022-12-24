import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel
{
    private JTextField[][] matTextField;
    private JTextField answer;
    private JLabel l1, l2;
    private JButton button;

    MyPanel(int n, AssignmentProblem AP)
    {
        l1 = new JLabel("Введите матрицу");
        add(l1);

        GridLayout gridLayout = new GridLayout(5, 5);
        JPanel matrixPanel = new JPanel();
        add(matrixPanel);
        matTextField = new JTextField[n][n];
        for(int i = 0; i < matTextField.length; i++)
        {
            for (int j = 0; j < matTextField.length; j++)
            {
                matTextField[i][j] = new JTextField(3);
                add(matTextField[i][j]);
                matrixPanel.add(matTextField[i][j]);
            }
        }
        matrixPanel.setLayout(gridLayout);

        button = new JButton("Найти");
        ButtonListener myListener = new ButtonListener(n, AP);
        button.addActionListener(myListener);
        add(button);

        l2 = new JLabel("Ответ");
        add(l2);

        answer = new JTextField(3);
        add(answer);
    }

    class ButtonListener implements ActionListener
    {
        int n;
        AssignmentProblem AP;

        ButtonListener(int n, AssignmentProblem AP)
        {
            this.n = n;
            this.AP = AP;
        }

        public void actionPerformed(ActionEvent e)
        {
            int[][] matrix = new int[n][n];
            for(int i = 0; i < matrix.length; i++)
                for (int j = 0; j < matrix.length; j++)
                    matrix[i][j] = Integer.parseInt(matTextField[i][j].getText());

           AP.setMatrixC(matrix);

           answer.setText(String.valueOf(AP.find()));
        }
    }
}
