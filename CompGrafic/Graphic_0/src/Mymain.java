import org.lwjgl.opengl.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Mymain
{
    private long window;

    public void run()
    {
        init();
        loop();
    }

    private void init()
    {
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        window = glfwCreateWindow(800, 500, "Greece", NULL, NULL);
        glfwSetWindowPos(window, 500, 200);

        glfwMakeContextCurrent(window);
        glfwShowWindow(window);
    }

    private void loop()
    {
        GL.createCapabilities();

        glClearColor(0.1f, 0.3f, 1.0f, 0.0f);

        while ( !glfwWindowShouldClose(window) )
        {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            glBegin(GL_QUADS);

            glColor3f(1.0f,1.0f,1.0f);

            glVertex2f(-1f, -0.78f);
            glVertex2f(-1f, -0.56f);
            glVertex2f(1f, -0.56f);
            glVertex2f(1f, -0.78f);

            glVertex2f(-1f, -0.34f);
            glVertex2f(-1f, -0.12f);
            glVertex2f(1f, -0.12f);
            glVertex2f(1f, -0.34f);

            glVertex2f(-0.3f, 0.1f);
            glVertex2f(-0.3f, 0.32f);
            glVertex2f(1f, 0.32f);
            glVertex2f(1f, 0.1f);

            glVertex2f(-0.3f, 0.54f);
            glVertex2f(-0.3f, 0.76f);
            glVertex2f(1f, 0.76f);
            glVertex2f(1f, 0.54f);

            glVertex2f(-1f, 0.32f);
            glVertex2f(-1f, 0.54f);
            glVertex2f(-0.3f, 0.54f);
            glVertex2f(-0.3f, 0.32f);

            glVertex2f(-0.74f, 1f);
            glVertex2f(-0.56f, 1f);
            glVertex2f(-0.56f, -0.12f);
            glVertex2f(-0.74f, -0.12f);

            glEnd();

            glfwSwapBuffers(window);
            glfwPollEvents();
        }
    }

    public static void main(String[] args)
    {
        new Mymain().run();
    }
}