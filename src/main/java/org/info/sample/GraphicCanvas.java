package org.info.sample;

import static org.lwjgl.opengl.GL11.*;

public class GraphicCanvas implements Canvas {

    public void drawLine(double x1, double y1, double x2, double y2) {
        // set the color of the quad (R,G,B,A)
        glColor3f(0.0f, 0.0f, 0.0f);
        glBegin(GL_LINES);
            glLineWidth(5.0f);
            glVertex2d((int) x1, (int) y1);
            glVertex2d((int) x2, (int) y2);
        glEnd();
    }

}
