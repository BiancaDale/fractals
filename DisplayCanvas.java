import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JPanel;

public class DisplayCanvas
extends JPanel {
    private int x1;
    private int y1;
    private int x2;
    private int y2;
    private double[] s;
    private double[] t;
    private int recursionLevel;

    public DisplayCanvas() {
        this.setBackground(Color.WHITE);
    }

    public void setPoints(Point[] arrpoint) {
        this.x1 = arrpoint[0].x;
        this.y1 = arrpoint[0].y;
        this.x2 = arrpoint[arrpoint.length - 1].x;
        this.y2 = arrpoint[arrpoint.length - 1].y;
        this.s = new double[arrpoint.length];
        this.t = new double[arrpoint.length];
        this.t[0] = 0.0;
        this.s[0] = 0.0;
        double d = (this.x2 - this.x1) * (this.x2 - this.x1) + (this.y2 - this.y1) * (this.y2 - this.y1);
        for (int i = 1; i < arrpoint.length; ++i) {
            this.s[i] = (double)((this.x2 - this.x1) * (arrpoint[i].x - this.x1) + (this.y2 - this.y1) * (arrpoint[i].y - this.y1)) / d;
            this.t[i] = (double)((this.x2 - this.x1) * (arrpoint[i].y - this.y1) - (this.y2 - this.y1) * (arrpoint[i].x - this.x1)) / d;
        }
        this.repaint();
    }

    public void setRecursionLevel(int n) {
        if (n != this.recursionLevel) {
            this.recursionLevel = n;
            this.repaint();
        }
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (this.s != null) {
            graphics.setColor(Color.BLACK);
            graphics.fillOval(this.x1 - 4, this.y1 - 4, 7, 7);
            graphics.fillOval(this.x2 - 4, this.y2 - 4, 7, 7);
            this.drawLines(graphics, this.recursionLevel, this.x1, this.y1, this.x2, this.y2);
        }
    }

    private void drawLines(Graphics graphics, int n, double d, double d2, double d3, double d4) {
        if (n <= 0) {
            graphics.drawLine((int)d, (int)d2, (int)d3, (int)d4);
        } else {
            double d5 = d;
            double d6 = d2;
            for (int i = 1; i < this.s.length; ++i) {
                double d7 = d + this.s[i] * (d3 - d) - this.t[i] * (d4 - d2);
                double d8 = d2 + this.t[i] * (d3 - d) + this.s[i] * (d4 - d2);
                this.drawLines(graphics, n - 1, d5, d6, d7, d8);
                d5 = d7;
                d6 = d8;
            }
        }
    }
}
