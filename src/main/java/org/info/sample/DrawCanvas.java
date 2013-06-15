package org.info.sample;

import static org.lwjgl.opengl.GL11.glLineWidth;
import static org.lwjgl.opengl.GL11.glVertex2d;

/**
 * Created with IntelliJ IDEA.
 * User: Marina
 * Date: 16.06.13
 * Time: 1:45
 * To change this template use File | Settings | File Templates.
 */
public class DrawCanvas  {

    private double x1 = 0;
    private double y1 = 0;
    private double x2 = 0;
    private double y2 = 0;

    public DrawCanvas(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public void drawLine() {
        glLineWidth(5.0f);
        glVertex2d((int) x1, (int) y1);
        glVertex2d((int) x2, (int) y2);
    }
}
