package org.info.sample;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static org.lwjgl.opengl.GL11.*;

public class Main {

    private static Image image;

    public static void main(String[] args) {
        QuadExample treeExample = new QuadExample();
        treeExample.start();
    }
}
