import javax.swing.*;

public class MyFrame extends JFrame
{
    MyFrame(int n, AssignmentProblem AP)
    {
        setBounds(500, 250, 900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        MyPanel panel = new MyPanel(n, AP);
        add(panel);
        setVisible(true);
    }
}
