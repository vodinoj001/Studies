import org.lwjgl.opengl.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;


public class Mymain
{
    private long window;

    private float rtri;  //for angle of rotation

    public void run()
    {
        init();
        loop();
    }

    private void init()
    {
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        window = glfwCreateWindow(800, 500, "Cat", NULL, NULL);
        glfwSetWindowPos(window, 500, 200);

        glfwMakeContextCurrent(window);
        glfwShowWindow(window);
    }

    private void loop()
    {


        GL.createCapabilities();

        glClearColor(0.0f, 0.0f, 0.0f, 0.0f);

        while ( !glfwWindowShouldClose(window) )
        {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            glLoadIdentity();

            glRotatef( rtri, 0.0f, 1.0f, 0.0f );

            glBegin(GL_QUADS);

            //Тело
            glColor3f(1.0f,0.6f,0.2f);

            glVertex3f(-0.4f, 0.25f, 0f);
            glVertex3f(0.4f,0.25f, 0f);
            glVertex3f(0.4f, -0.2f, 0f);
            glVertex3f(-0.4f, -0.2f, 0f);

            glVertex3f(-0.4f, 0.25f, 0.3f);
            glVertex3f(0.4f,0.25f, 0.3f);
            glVertex3f(0.4f, -0.2f, 0.3f);
            glVertex3f(-0.4f, -0.2f, 0.3f);

            glVertex3f(-0.4f, 0.25f, 0f);
            glVertex3f(-0.4f, 0.25f, 0.3f);
            glVertex3f(0.4f,0.25f, 0.3f);
            glVertex3f(0.4f,0.25f, 0f);

            glVertex3f(-0.4f, -0.2f, 0f);
            glVertex3f(-0.4f, -0.2f, 0.3f);
            glVertex3f(0.4f,-0.2f, 0.3f);
            glVertex3f(0.4f,-0.2f, 0f);

            glVertex3f(-0.4f, 0.25f, 0f);
            glVertex3f(-0.4f, 0.25f, 0.3f);
            glVertex3f(-0.4f, -0.2f, 0.3f);
            glVertex3f(-0.4f,-0.2f, 0f);

            glVertex3f(0.4f, 0.25f, 0f);
            glVertex3f(0.4f, 0.25f, 0.3f);
            glVertex3f(0.4f, -0.2f, 0.3f);
            glVertex3f(0.4f,-0.2f, 0f);

            //Голова
            glColor3f(1.0f,0.6f,0.2f);

            glVertex3f(-0.7f, 0.3f, 0f);
            glVertex3f(-0.4f,0.3f, 0f);
            glVertex3f(-0.4f, 0f, 0f);
            glVertex3f(-0.7f, 0f, 0f);

            glVertex3f(-0.7f, 0.3f, 0.3f);
            glVertex3f(-0.4f,0.3f, 0.3f);
            glVertex3f(-0.4f, 0f, 0.3f);
            glVertex3f(-0.7f, 0f, 0.3f);

            glVertex3f(-0.7f, 0.3f, 0f);
            glVertex3f(-0.7f, 0.3f, 0.3f);
            glVertex3f(-0.4f,0.3f, 0.3f);
            glVertex3f(-0.4f,0.3f, 0f);

            glVertex3f(-0.7f, 0f, 0f);
            glVertex3f(-0.7f, 0f, 0.3f);
            glVertex3f(-0.4f,0f, 0.3f);
            glVertex3f(-0.4f,0f, 0f);

            glVertex3f(-0.7f, 0.3f, 0f);
            glVertex3f(-0.7f, 0.3f, 0.3f);
            glVertex3f(-0.7f, 0f, 0.3f);
            glVertex3f(-0.7f,0f, 0f);

            glVertex3f(-0.4f, 0.3f, 0f);
            glVertex3f(-0.4f, 0.3f, 0.3f);
            glVertex3f(-0.4f, 0f, 0.3f);
            glVertex3f(-0.4f,0f, 0f);

            //Глаза
            //Правый
            glColor3f(1.0f,1.0f,1.0f);

            glVertex3f(-0.7f, 0.2f, 0f);
            glVertex3f(-0.7f, 0.2f, 0.05f);
            glVertex3f(-0.7f, 0.15f, 0.05f);
            glVertex3f(-0.7f, 0.15f, 0f);

            glColor3f(0.0f,1.0f,0.0f);

            glVertex3f(-0.7f, 0.2f, 0.05f);
            glVertex3f(-0.7f, 0.2f, 0.1f);
            glVertex3f(-0.7f, 0.15f, 0.1f);
            glVertex3f(-0.7f, 0.15f, 0.05f);

            //Левый
            glColor3f(0.0f,1.0f,0.0f);

            glVertex3f(-0.7f, 0.2f, 0.2f);
            glVertex3f(-0.7f, 0.2f, 0.25f);
            glVertex3f(-0.7f, 0.15f, 0.25f);
            glVertex3f(-0.7f, 0.15f, 0.2f);

            glColor3f(1.0f,1.0f,1.0f);

            glVertex3f(-0.7f, 0.2f, 0.25f);
            glVertex3f(-0.7f, 0.2f, 0.3f);
            glVertex3f(-0.7f, 0.15f, 0.3f);
            glVertex3f(-0.7f, 0.15f, 0.25f);

            //Нос
            glColor3f(0.9f,0.9f,0.9f);

            glVertex3f(-0.75f, 0.15f, 0.075f);
            glVertex3f(-0.7f,0.15f, 0.075f);
            glVertex3f(-0.7f, 0f, 0.075f);
            glVertex3f(-0.75f, 0f, 0.075f);

            glVertex3f(-0.75f, 0.15f, 0.225f);
            glVertex3f(-0.7f,0.15f, 0.225f);
            glVertex3f(-0.7f, 0f, 0.225f);
            glVertex3f(-0.75f, 0f, 0.225f);

            glVertex3f(-0.75f, 0.15f, 0.075f);
            glVertex3f(-0.75f, 0.15f, 0.225f);
            glVertex3f(-0.7f,0.15f, 0.225f);
            glVertex3f(-0.7f,0.15f, 0.075f);

            glVertex3f(-0.75f, 0f, 0.075f);
            glVertex3f(-0.75f, 0f, 0.225f);
            glVertex3f(-0.75f,0f, 0.225f);
            glVertex3f(-0.75f,0f, 0.075f);

            glVertex3f(-0.75f, 0.15f, 0.075f);
            glVertex3f(-0.75f, 0.15f, 0.225f);
            glVertex3f(-0.75f, 0f, 0.225f);
            glVertex3f(-0.75f,0f, 0.075f);

            glVertex3f(-0.7f, 0.15f, 0.075f);
            glVertex3f(-0.7f, 0.15f, 0.225f);
            glVertex3f(-0.7f, 0f, 0.225f);
            glVertex3f(-0.7f,0f, 0.075f);

            glColor3f(0.8f,0.0f,0.4f);
            glVertex3f(-0.75f, 0.15f, 0.13f);
            glVertex3f(-0.75f, 0.15f, 0.17f);
            glVertex3f(-0.75f, 0.075f, 0.17f);
            glVertex3f(-0.75f, 0.075f, 0.13f);

            //Уши
            //Левое
            glColor3f(0.9f,0.9f,0.9f);

            glVertex3f(-0.5f, 0.35f, 0.2f);
            glVertex3f(-0.4f,0.35f, 0.2f);
            glVertex3f(-0.4f, 0.3f, 0.2f);
            glVertex3f(-0.5f, 0.3f, 0.2f);

            glVertex3f(-0.5f, 0.35f, 0.25f);
            glVertex3f(-0.4f,0.35f, 0.25f);
            glVertex3f(-0.4f, 0.3f, 0.25f);
            glVertex3f(-0.5f, 0.3f, 0.25f);

            glVertex3f(-0.5f, 0.35f, 0.2f);
            glVertex3f(-0.5f, 0.35f, 0.25f);
            glVertex3f(-0.4f,0.35f, 0.25f);
            glVertex3f(-0.4f,0.35f, 0.2f);

            glVertex3f(-0.5f, 0.3f, 0.2f);
            glVertex3f(-0.5f, 0.3f, 0.25f);
            glVertex3f(-0.4f,0.3f, 0.25f);
            glVertex3f(-0.4f,0.3f, 0.2f);

            glVertex3f(-0.5f, 0.35f, 0.2f);
            glVertex3f(-0.5f, 0.35f, 0.25f);
            glVertex3f(-0.5f, 0.3f, 0.25f);
            glVertex3f(-0.5f,0.3f, 0.2f);

            glVertex3f(-0.4f, 0.35f, 0.2f);
            glVertex3f(-0.4f, 0.35f, 0.25f);
            glVertex3f(-0.4f, 0.3f, 0.25f);
            glVertex3f(-0.4f,0.3f, 0.2f);

            //Правое
            glVertex3f(-0.5f, 0.35f, 0.05f);
            glVertex3f(-0.4f,0.35f, 0.05f);
            glVertex3f(-0.4f, 0.3f, 0.05f);
            glVertex3f(-0.5f, 0.3f, 0.05f);

            glVertex3f(-0.5f, 0.35f, 0.1f);
            glVertex3f(-0.4f,0.35f, 0.1f);
            glVertex3f(-0.4f, 0.3f, 0.1f);
            glVertex3f(-0.5f, 0.3f, 0.1f);

            glVertex3f(-0.5f, 0.35f, 0.05f);
            glVertex3f(-0.5f, 0.35f, 0.1f);
            glVertex3f(-0.4f,0.35f, 0.1f);
            glVertex3f(-0.4f,0.35f, 0.05f);

            glVertex3f(-0.5f, 0.3f, 0.05f);
            glVertex3f(-0.5f, 0.3f, 0.1f);
            glVertex3f(-0.4f,0.3f, 0.1f);
            glVertex3f(-0.4f,0.3f, 0.05f);

            glVertex3f(-0.5f, 0.35f, 0.05f);
            glVertex3f(-0.5f, 0.35f, 0.1f);
            glVertex3f(-0.5f, 0.3f, 0.1f);
            glVertex3f(-0.5f,0.3f, 0.05f);

            glVertex3f(-0.4f, 0.35f, 0.05f);
            glVertex3f(-0.4f, 0.35f, 0.1f);
            glVertex3f(-0.4f, 0.3f, 0.1f);
            glVertex3f(-0.4f,0.3f, 0.05f);

            //Лапы
            glColor3f(0.9f,0.9f,0.9f);
            //Передняя левая
            glVertex3f(-0.3f, -0.2f, 0.2f);
            glVertex3f(-0.2f,-0.2f, 0.2f);
            glVertex3f(-0.2f, -0.4f, 0.2f);
            glVertex3f(-0.3f, -0.4f, 0.2f);

            glVertex3f(-0.3f, -0.2f, 0.3f);
            glVertex3f(-0.2f,-0.2f, 0.3f);
            glVertex3f(-0.2f, -0.4f, 0.3f);
            glVertex3f(-0.3f, -0.4f, 0.3f);

            glVertex3f(-0.3f, -0.2f, 0.2f);
            glVertex3f(-0.3f, -0.2f, 0.3f);
            glVertex3f(-0.2f,-0.2f, 0.3f);
            glVertex3f(-0.2f,-0.2f, 0.2f);

            glVertex3f(-0.3f, -0.4f, 0.2f);
            glVertex3f(-0.3f, -0.4f, 0.3f);
            glVertex3f(-0.2f,-0.4f, 0.3f);
            glVertex3f(-0.2f,-0.4f, 0.2f);

            glVertex3f(-0.3f, -0.2f, 0.2f);
            glVertex3f(-0.3f, -0.2f, 0.3f);
            glVertex3f(-0.3f, -0.4f, 0.3f);
            glVertex3f(-0.3f,-0.4f, 0.2f);

            glVertex3f(-0.2f, -0.2f, 0.2f);
            glVertex3f(-0.2f, -0.2f, 0.3f);
            glVertex3f(-0.2f, -0.4f, 0.3f);
            glVertex3f(-0.2f,-0.4f, 0.2f);

            //Передняя правая
            glVertex3f(-0.3f, -0.2f, 0.1f);
            glVertex3f(-0.2f,-0.2f, 0.1f);
            glVertex3f(-0.2f, -0.4f, 0.1f);
            glVertex3f(-0.3f, -0.4f, 0.1f);

            glVertex3f(-0.3f, -0.2f, 0f);
            glVertex3f(-0.2f,-0.2f, 0f);
            glVertex3f(-0.2f, -0.4f, 0f);
            glVertex3f(-0.3f, -0.4f, 0f);

            glVertex3f(-0.3f, -0.2f, 0.1f);
            glVertex3f(-0.3f, -0.2f, 0f);
            glVertex3f(-0.2f,-0.2f, 0f);
            glVertex3f(-0.2f,-0.2f, 0.1f);

            glVertex3f(-0.3f, -0.4f, 0.1f);
            glVertex3f(-0.3f, -0.4f, 0f);
            glVertex3f(-0.2f,-0.4f, 0f);
            glVertex3f(-0.2f,-0.4f, 0.1f);

            glVertex3f(-0.3f, -0.2f, 0.1f);
            glVertex3f(-0.3f, -0.2f, 0f);
            glVertex3f(-0.3f, -0.4f, 0f);
            glVertex3f(-0.3f,-0.4f, 0.1f);

            glVertex3f(-0.2f, -0.2f, 0.1f);
            glVertex3f(-0.2f, -0.2f, 0f);
            glVertex3f(-0.2f, -0.4f, 0f);
            glVertex3f(-0.2f,-0.4f, 0.1f);

            //Задняя левая
            glVertex3f(0.3f, -0.2f, 0.2f);
            glVertex3f(0.2f,-0.2f, 0.2f);
            glVertex3f(0.2f, -0.4f, 0.2f);
            glVertex3f(0.3f, -0.4f, 0.2f);

            glVertex3f(0.3f, -0.2f, 0.3f);
            glVertex3f(0.2f,-0.2f, 0.3f);
            glVertex3f(0.2f, -0.4f, 0.3f);
            glVertex3f(0.3f, -0.4f, 0.3f);

            glVertex3f(0.3f, -0.2f, 0.2f);
            glVertex3f(0.3f, -0.2f, 0.3f);
            glVertex3f(0.2f,-0.2f, 0.3f);
            glVertex3f(0.2f,-0.2f, 0.2f);

            glVertex3f(0.3f, -0.4f, 0.2f);
            glVertex3f(0.3f, -0.4f, 0.3f);
            glVertex3f(0.2f,-0.4f, 0.2f);
            glVertex3f(-0.2f,-0.4f, 0.3f);

            glVertex3f(0.3f, -0.2f, 0.2f);
            glVertex3f(0.3f, -0.2f, 0.3f);
            glVertex3f(0.3f, -0.4f, 0.2f);
            glVertex3f(0.3f,-0.4f, 0.2f);

            glVertex3f(0.2f, -0.2f, 0.2f);
            glVertex3f(0.2f, -0.2f, 0.3f);
            glVertex3f(0.2f, -0.4f, 0.3f);
            glVertex3f(0.2f,-0.4f, 0.2f);

            //Задняя правая
            glVertex3f(0.3f, -0.2f, 0.1f);
            glVertex3f(0.2f,-0.2f, 0.1f);
            glVertex3f(0.2f, -0.4f, 0.1f);
            glVertex3f(0.3f, -0.4f, 0.1f);

            glVertex3f(0.3f, -0.2f, 0f);
            glVertex3f(0.2f,-0.2f, 0f);
            glVertex3f(0.2f, -0.4f, 0f);
            glVertex3f(0.3f, -0.4f, 0f);

            glVertex3f(0.3f, -0.2f, 0.1f);
            glVertex3f(0.3f, -0.2f, 0f);
            glVertex3f(0.2f,-0.2f, 0f);
            glVertex3f(0.2f,-0.2f, 0.1f);

            glVertex3f(0.3f, -0.4f, 0.1f);
            glVertex3f(0.3f, -0.4f, 0f);
            glVertex3f(0.2f,-0.4f, 0f);
            glVertex3f(0.2f,-0.4f, 0.1f);

            glVertex3f(0.3f, -0.2f, 0.1f);
            glVertex3f(0.3f, -0.2f, 0f);
            glVertex3f(0.3f, -0.4f, 0f);
            glVertex3f(0.3f,-0.4f, 0.1f);

            glVertex3f(0.2f, -0.2f, 0.1f);
            glVertex3f(0.2f, -0.2f, 0f);
            glVertex3f(0.2f, -0.4f, 0f);
            glVertex3f(0.2f,-0.4f, 0.1f);

            //Хвост
            //Прямая часть
            glColor3f(0.9f,0.9f,0.9f);

            glVertex3f(0.5f, 0.05f, 0.1f);
            glVertex3f(0.9f,0.05f, 0.1f);
            glVertex3f(0.9f, -0.05f, 0.1f);
            glVertex3f(0.5f, -0.05f, 0.1f);

            glVertex3f(0.5f, 0.05f, 0.2f);
            glVertex3f(0.9f,0.05f, 0.2f);
            glVertex3f(0.9f, -0.05f, 0.2f);
            glVertex3f(0.5f, -0.05f, 0.2f);

            glVertex3f(0.5f, 0.05f, 0.1f);
            glVertex3f(0.5f, 0.05f, 0.2f);
            glVertex3f(0.9f,0.05f, 0.2f);
            glVertex3f(0.9f,0.05f, 0.1f);

            glVertex3f(0.5f, -0.05f, 0.1f);
            glVertex3f(0.5f, -0.05f, 0.2f);
            glVertex3f(0.9f,-0.05f, 0.2f);
            glVertex3f(0.9f,-0.05f, 0.1f);

            glVertex3f(0.5f, 0.05f, 0.1f);
            glVertex3f(0.5f, 0.05f, 0.2f);
            glVertex3f(0.5f, -0.05f, 0.2f);
            glVertex3f(0.5f,-0.05f, 0.1f);

            glVertex3f(0.9f, 0.05f, 0.1f);
            glVertex3f(0.9f, 0.05f, 0.2f);
            glVertex3f(0.9f, -0.05f, 0.2f);
            glVertex3f(0.9f,-0.05f, 0.1f);

            //Наклоненная часть
            glColor3f(1.0f,0.6f,0.2f);
            glVertex3f(0.5f, 0.05f, 0.1f);
            glVertex3f(0.5f, 0.05f, 0.2f);
            glVertex3f(0.5f, -0.05f, 0.2f);
            glVertex3f(0.5f,-0.05f, 0.1f);

            glVertex3f(0.4f, 0.2f, 0.1f);
            glVertex3f(0.4f, 0.2f, 0.2f);
            glVertex3f(0.4f, 0.1f, 0.2f);
            glVertex3f(0.4f,0.1f, 0.1f);

            glVertex3f(0.4f, 0.2f, 0.2f);
            glVertex3f(0.5f, 0.05f, 0.2f);
            glVertex3f(0.5f, -0.05f, 0.2f);
            glVertex3f(0.4f, 0.1f, 0.2f);

            glVertex3f(0.4f, 0.2f, 0.1f);
            glVertex3f(0.5f, 0.05f, 0.1f);
            glVertex3f(0.5f, -0.05f, 0.1f);
            glVertex3f(0.4f, 0.1f, 0.1f);

            glVertex3f(0.4f, 0.2f, 0.2f);
            glVertex3f(0.4f, 0.2f, 0.1f);
            glVertex3f(0.5f, 0.05f, 0.1f);
            glVertex3f(0.5f, 0.05f, 0.2f);

            glVertex3f(0.4f, 0.1f, 0.2f);
            glVertex3f(0.4f, 0.1f, 0.1f);
            glVertex3f(0.5f, -0.05f, 0.1f);
            glVertex3f(0.5f, -0.05f, 0.2f);

            glEnd();
            glFlush();

            glfwSwapBuffers(window);
            glfwPollEvents();

            rtri += 0.2f;
        }
    }
    public static void main(String[] args)
    {
        new Mymain().run();
    }
}