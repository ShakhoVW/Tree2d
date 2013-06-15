package org.info.sample;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

import java.util.Stack;

public class Turtle {

    private final Canvas canvas;
    private State state;
    private Stack<State> memory = new Stack<State>();
    private final double stepLength;
    private final double deltaAngle;
    private String lSystemResult;

    public Turtle(Canvas canvas, double stepLength, double deltaAngle) {
        this.canvas = canvas;
        this.stepLength = stepLength;
        this.deltaAngle = deltaAngle;
    }

    public Turtle(Canvas canvas, State first, double stepLength, double deltaAngle) {
        this(canvas, stepLength, deltaAngle);
        state = first;
    }

    public Turtle(Canvas canvas, State first, String lSystemResult, double stepLength, double deltaAngle) {
        this(canvas, first, stepLength, deltaAngle);
        this.lSystemResult = lSystemResult;
    }


    public void draw(String string, int i) {
        drawVar(string, i);
    }

    public DrawCanvas draw(int i) {
        DrawCanvas drawCanvas = drawVar(lSystemResult, i);
        return drawCanvas;
    }

    public void draw(String string) {
        for (int i = 0; i < string.length(); ++i) {
            drawVar(string, i);
        }
    }

    public void draw(String string, State first) {
        state = first;
        for (int i = 0; i < string.length(); ++i) {
            drawVar(string, i);
        }
    }

    public void draw(State first) {
        state = first;
        for (int i = 0; i < lSystemResult.length(); ++i) {
            drawVar(lSystemResult, i);
        }
    }

    public void draw() {
        for (int i = 0; i < lSystemResult.length(); ++i) {
            drawVar(lSystemResult, i);
        }
    }

    private DrawCanvas drawVar(String string, int i) {
        DrawCanvas drawCanvas = null;
        switch (string.charAt(i)) {
            case 'F':
                drawCanvas = drawStep();
                break;
            case 'b':
                moveStep();
                break;
            case '+':
                rotateRight();
                break;
            case '-':
                rotateLeft();
                break;
            case '[':
                pushState();
                break;
            case ']':
                popState();
                break;
            default:
                break;
        }
        return drawCanvas;
    }

    private DrawCanvas drawStep() {
        double oldX = state.getX();
        double oldY = state.getY();
        double angle = state.getAngle();
        double newX = oldX + Math.cos(angle) * stepLength;
        double newY = oldY - Math.sin(angle) * stepLength;
        canvas.drawLine(oldX, oldY, newX, newY);
        DrawCanvas drawCanvas = new DrawCanvas(oldX, oldY, newX, newY);
        state = new State(newX, newY, angle);
        return drawCanvas;
    }

    private void moveStep() {
        double oldX = state.getX();
        double oldY = state.getY();
        double angle = state.getAngle();
        double newX = oldX + Math.cos(angle) * stepLength;
        double newY = oldY - Math.sin(angle) * stepLength;
        state = new State(newX, newY, angle);
    }

    private void rotateRight() {
        double x = state.getX();
        double y = state.getY();
        double oldAngle = state.getAngle();
        double newAngle = oldAngle - deltaAngle;
        state = new State(x, y, newAngle);
    }

    private void rotateLeft() {
        double x = state.getX();
        double y = state.getY();
        double oldAngle = state.getAngle();
        double newAngle = oldAngle + deltaAngle;
        state = new State(x, y, newAngle);
    }

    private void pushState() {
        memory.push(state);
    }

    private void popState() {
        state = memory.lastElement();
        memory.pop();
    }
}
