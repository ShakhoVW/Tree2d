package org.info.sample;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2d;


public class QuadExample {

    public static final int UPDATE_FPS = 5;
    public static final int DELTA_SEC = 5;

    public static final int DEFAULT_WIDTH = 450;
    public static final int DEFAULT_HEIGHT = 450;
    public static final int DEFAULT_BORDER = 20;
    public static final String RULE = "FF+[+F-F-F]-[-F+F+F]";
    public static final String AXIOM = "F";
    int depth = 2;
    //    double alpha =0;
    double alpha = -Math.PI / 2;
    double scale = 0;
    double scaleY = 0;
    double scaleX = 0;
    double deltaAngle = Math.PI / 8;
    State state;

    List<DrawCanvas> drawLines = new ArrayList<DrawCanvas>();

    Turtle graphicTurtle;

    int counter = 0;
    long lastFrame;

    // frames per second
    int fps;
    // last fps time
    long lastFPS;

    public void updateFPS() {
        if (UPDATE_FPS < getTime() - lastFPS) {
            Display.setTitle("FPS: " + fps);
            fps = 0;
            lastFPS += UPDATE_FPS;
        }
        fps++;
    }

    public long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    public int getDelta() {
        long time = getTime();
        int delta = (int) (time - lastFrame);
        if (DELTA_SEC < delta) {
            lastFrame = time;
        }
        return delta;
    }

    public void start() {
        try {
            Display.setDisplayMode(new DisplayMode(800, 600));
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            System.exit(0);
        }

        initGL();
        getDelta(); // call once before loop to initialise lastFrame
        lastFPS = getTime(); // call before loop to initialise fps timer

        // Setup an XNA like background color
        GL11.glClearColor(1.0f, 1.0f, 1.0f, 0f);

        long currentFPS = 0;

        String lSystemResult = initLSystem();

        int innerCounter = 0;
        // Clear the screen and depth buffer
        while (!Display.isCloseRequested()) {
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
//            int delta = getDelta();

            updateFPS();

            if (counter == 0 || innerCounter != counter) {
                DrawCanvas drawCanvas = drawTree();
                if (drawCanvas != null) {
                    drawLines.add(drawCanvas);
                }
                innerCounter = counter;
            }

            if (lastFPS != currentFPS) {
                currentFPS = lastFPS;
                counter++;
            }
            if (lSystemResult.length() <= counter) {
                counter = 0;
                innerCounter = 0;
                lSystemResult = initLSystem();
                drawLines = new ArrayList<DrawCanvas>();
            }
            drawLines(drawLines);

            Display.update();
            Display.sync(60); // cap fps to 60fps
        }
        Display.destroy();
    }

    private void drawLines(List<DrawCanvas> drawLines) {
        glColor3f(0.0f, 0.0f, 0.0f);
        glBegin(GL_LINES);
        for (DrawCanvas line : drawLines) {
            line.drawLine();
        }
        glEnd();
    }

    private String initLSystem() {
        LSystem lSystem = new LSystem(AXIOM, new String[][]{{AXIOM, RULE}});
        String lSystemResult = lSystem.getResult(depth);
        initScale(lSystemResult);
        return lSystemResult;
    }

    private void initScale(String lSystemResult) {
        BoundCanvas boundCanvas = new BoundCanvas();
        Turtle boundTurtle = new Turtle(boundCanvas, 1, deltaAngle);
        boundTurtle.draw(lSystemResult, new State(0, 0, alpha));
        scale = getScale(DEFAULT_WIDTH, DEFAULT_HEIGHT, DEFAULT_BORDER, boundCanvas);
        scaleX = getX(DEFAULT_WIDTH, boundCanvas, scale);
        scaleY = getY(DEFAULT_HEIGHT, boundCanvas, scale);

        GraphicCanvas graphicCanvas = new GraphicCanvas();
        state = new State(scaleX, scaleY, alpha);
        graphicTurtle = new Turtle(graphicCanvas, state, lSystemResult, scale, deltaAngle);

    }

    private void initGL() {
        // init OpenGL
        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();
        GL11.glOrtho(0, 800, 0, 600, 1, -1);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
    }

    public static void main(String[] argv) {
        QuadExample quadExample = new QuadExample();
        quadExample.start();
    }

    public DrawCanvas drawTree() {
//        graphicTurtle.draw(new State(scaleX, scaleY, alpha));
        DrawCanvas drawCanvas = graphicTurtle.draw(counter);
//        graphicTurtle.draw();
        return drawCanvas;
    }

    private double getY(int height, BoundCanvas boundCanvas, double scale) {
        return height / 2 - (boundCanvas.getMaxY() + boundCanvas.getMinY()) / 2 * scale;
    }

    private double getX(int width, BoundCanvas boundCanvas, double scale) {
        return width / 2 - (boundCanvas.getMaxX() + boundCanvas.getMinX()) / 2 * scale;
    }

    private double getScale(int width, int height, int border, BoundCanvas boundCanvas) {
        return Math.min(
                (width - 2 * border) / (boundCanvas.getMaxX() - boundCanvas.getMinX()),
                (height - 2 * border) / (boundCanvas.getMaxY() - boundCanvas.getMinY())
        );
    }
}
