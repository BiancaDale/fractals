import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;

public class InputCanvas extends JPanel implements MouseListener, MouseMotionListener {
    private Point[] points;
    private DisplayCanvas display;
    private int pointDragged = -1;

    public InputCanvas(DisplayCanvas displayCanvas) {
        this.display = displayCanvas;
        this.setBackground(Color.WHITE);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
    }

    public void setPointCount(int n) {
        this.points = new Point[n];
        for (int i = 0; i < n; ++i) {
            this.points[i] = new Point(50 + i * 400 / (n - 1), 250);
        }
        this.repaint();
        this.display.setPoints(this.points);
    }

    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (this.points != null) {
            int n;
            graphics.setColor(Color.BLACK);
            for (n = 1; n < this.points.length; ++n) {
                graphics.drawLine(this.points[n - 1].x, this.points[n - 1].y, this.points[n].x, this.points[n].y);
            }
            for (n = 0; n < this.points.length; ++n) {
                graphics.setColor(n == 0 || n == this.points.length - 1 ? Color.BLUE : Color.RED);
                graphics.fillOval(this.points[n].x - 5, this.points[n].y - 5, 9, 9);
            }
        }
    }

    public void mousePressed(MouseEvent mouseEvent) {
        if (this.pointDragged > 0) {
            return;
        }
        int n = mouseEvent.getX();
        int n2 = mouseEvent.getY();
        for (int i = 0; i < this.points.length; ++i) {
            int n3 = this.points[i].x;
            int n4 = this.points[i].y;
            int n5 = (n3 - n) * (n3 - n) + (n4 - n2) * (n4 - n2);
            if (n5 > 36) continue;
            this.pointDragged = i;
            return;
        }
    }

    public void mouseDragged(MouseEvent mouseEvent) {
        if (this.pointDragged >= 0) {
            int n = mouseEvent.getX();
            int n2 = mouseEvent.getY();
            n = Math.min(500, Math.max(0, n));
            n2 = Math.min(500, Math.max(0, n2));
            if (n != this.points[this.pointDragged].x || n2 != this.points[this.pointDragged].y) {
                this.points[this.pointDragged].setLocation(n, n2);
                this.display.setPoints(this.points);
                this.repaint();
            }
        }
    }

    public void mouseReleased(MouseEvent mouseEvent) {
        this.pointDragged = -1;
    }

    public void mouseClicked(MouseEvent mouseEvent) {
    }

    public void mouseEntered(MouseEvent mouseEvent) {
    }

    public void mouseExited(MouseEvent mouseEvent) {
    }

    public void mouseMoved(MouseEvent mouseEvent) {
    }

    public void install(int[] arrn) {
        int n = arrn.length / 2;
        this.points = new Point[n];
        for (int i = 0; i < n; ++i) {
            this.points[i] = new Point(arrn[2 * i], arrn[2 * i + 1]);
        }
        this.repaint();
        this.display.setPoints(this.points);
    }
}
