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
//        image = LCollection.drawKochCurve(4);
//        image = LCollection.drawKochSnowflake(4);
//        image = LCollection.drawTriangle(7);
//        image = LCollection.drawCrystal(4);
//        image = LCollection.drawDragon(11);
//        image = LCollection.drawHexagonalGosper(4);
//        image = LCollection.drawSquareSierpinski(4);
//        image = LCollection.drawHilbertCurve(5);
//        image = LCollection.drawBoard(4);
//        image = LCollection.drawYetAnotherKochCurve(3);
//        image = LCollection.drawQuadraticKochIslandA(3);
//        image = LCollection.drawQuadraticKochIslandB(2);
//        image = LCollection.drawQuadraticSnowflake(4);
//        image = LCollection.drawSierpinskiArrowhead(7);
//        image = LCollection.drawCrossB(5);
//        image = LCollection.drawRings(4);
//        image = LCollection.drawBush1(5);
//        image = LCollection.drawBush2(4);
//        image = LCollection.drawBush3(4);
//        image = LCollection.drawSticks(6);
//        image = LCollection.drawBoxFractal(4);
//        image = LCollection.drawWeed(4);
//        image = LCollection.drawTree(4);
//        image = LCollection.drawRings2(5);
//        image = LCollection.drawSierpinskiTriangle(6);
//        image = LCollection.drawSierpinskiCarpet(5);
//        image = LCollection.drawMosaic(2);
//        image = LCollection.drawSnowflake(2);
//        image = LCollection.drawFlower(2);
//        image = LCollection.drawLevyCurve(12);
//        image = LCollection.drawPentaplexity(4);
//        image = LCollection.drawTerdragon(7);
//        image = LCollection.drawCross(5);
//        image = LCollection.drawSierpinskiCarpet2(4);
//        image = LCollection.drawPentigree(4);
//        image = LCollection.drawHex7B(4);
//        image = LCollection.drawPeanoC(3);
//        image = LCollection.drawBorder(3);
//        image = LCollection.drawDoily(4);
//        image = LCollection.drawMaze1(5);
//        image = LCollection.drawMaze2(6);
//        image = LCollection.drawMoore(4);
//        image = LCollection.drawPentant(3);
//        image = LCollection.drawPentl(4);
//        image = LCollection.drawSierpinskiCurve(10);
//        image = LCollection.drawTiling(6);
//        image = LCollection.drawADH231A(4);
//        image = LCollection.drawADH256A(4);
//        image = LCollection.drawADH258A(3);

        
//        JFrame frame = new JFrame();
//        frame.addNotify();
//        frame.setSize(frame.getInsets().left
//                + frame.getInsets().right + image.getWidth(null),
//                frame.getInsets().top
//                + frame.getInsets().bottom + image.getHeight(null));
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        frame.add(new JPanel() {
//
//            @Override
//            public void paintComponent(Graphics g) {
//                Graphics2D G = (Graphics2D) g;
//                if (image != null) {
//                    G.drawImage(image, 0, 0, null);
//                }
//            }
//        });
//        frame.setVisible(true);

        QuadExample treeExample = new QuadExample();
        treeExample.start();
    }
}
