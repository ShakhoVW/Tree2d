package org.info.sample;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;


public class QuadExample {

    public static final int DEFAULT_WIDTH = 450;
    public static final int DEFAULT_HEIGHT = 450;
    public static final int DEFAULT_BORDER = 20;

    public void start() {
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        // init OpenGL
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 800, 0, 600, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        // Setup an XNA like background color
        GL11.glClearColor(1.0f, 1.0f, 1.0f, 0f);

        while (!Display.isCloseRequested()) {
            // Clear the screen and depth buffer
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

            drawTree(4);


            //drawLine
//            drawLine();

            Display.update();
        }
        Display.destroy();
    }

    private void drawLine() {
        // Clear the screen and depth buffer
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
// set the color of the quad (R,G,B,A)
        GL11.glColor3f(0.0f, 0.5f, 0.0f);
// draw quad
        GL11.glBegin(GL11.GL_LINES);
        GL11.glLineWidth(5.0f);
        GL11.glVertex2d(100, 100);
        GL11.glVertex2d(200, 200);
        GL11.glEnd();
    }

    public static void main(String[] argv) {
        QuadExample quadExample = new QuadExample();
        quadExample.start();
    }

    public void drawTree(int depth) {
        draw(new LSystem("F", new String[][]{{"F", "FF+[+F-F-F]-[-F+F+F]"}}), Math.PI / 8, depth, Math.PI / 2);
    }

    public void draw(LSystem lSystem, double deltaAngle, int depth, double alpha) {
        draw(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_BORDER, lSystem, deltaAngle, depth, 0);
    }

    public void draw(int width, int height, int border, LSystem lSystem, double deltaAngle, int depth, double alpha) {
        BoundCanvas boundCanvas = new BoundCanvas();
        Turtle boundTurtle = new Turtle(boundCanvas, 1, deltaAngle);
        boundTurtle.draw(lSystem.getResult(depth), new State(0, 0, alpha));
        double scale = Math.min(
                (width - 2 * border) / (boundCanvas.getMaxX() - boundCanvas.getMinX()),
                (height - 2 * border) / (boundCanvas.getMaxY() - boundCanvas.getMinY()));
        double x = width / 2 - (boundCanvas.getMaxX() + boundCanvas.getMinX()) / 2 * scale;
        double y = height / 2 - (boundCanvas.getMaxY() + boundCanvas.getMinY()) / 2 * scale;
        GraphicCanvas graphicCanvas = new GraphicCanvas();
        Turtle graphicTurtle = new Turtle(graphicCanvas, scale, deltaAngle);
        graphicTurtle.draw(lSystem.getResult(depth), new State(x, y, alpha));

    }
}
