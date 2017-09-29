import javax.swing.JPanel;
import java.awt.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class TreePanel extends JPanel
{
    private final int WIDTH = 1920;
    private final int HEIGHT = 1080;

    private BufferedImage drawing = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
    private BufferedImage displayed_drawing;


    private Random numGen;

    public TreePanel()
    {
        //this.setBackground(Color.BLACK);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    public ArrayList<Double> c_reals = new ArrayList<Double>();
    public ArrayList<Double>  c_imgs = new ArrayList<Double>();
    public ArrayList<Integer> iterations = new ArrayList<Integer>();


    /**
     *
     * @param maxIteration
     * @param zoom
     * @param x_shift
     * @param y_shift
     */
    public void standard_calc(int maxIteration, double zoom, double x_shift, double y_shift)
    {
        zoom = zoom*zoom;
        zoom -= 1;
        if (zoom != 0 && zoom > 0) {
            zoom = 1 / zoom;
            zoom = (4 - zoom);}
        else if (zoom < 0)
            zoom = 0;

        for (int row = 0; row < HEIGHT; row++) {
            for (int col = 0; col < WIDTH; col++) {
                double c_re = ((col - WIDTH/2.0)*(4-zoom)/WIDTH) + x_shift;

                double c_im = ((row - HEIGHT/2.0)*(4-zoom)/WIDTH) + y_shift;

                int n = findMandelbrot(c_re, c_im, maxIteration);
                iterations.add(n);

                int R = (int)((n)%256);
                int G = (int)((n)%256);
                int B = (int)((n)%256);
                Color newColor = new Color(R, G, B, 255);

                drawing.setRGB(col, row, newColor.getRGB());

            }
        }
    }

    /**
     *
     * @param c_re
     * @param c_im
     * @param max
     * @return
     */
    public int findMandelbrot(double c_re, double c_im, int max)
    {
        double x = 0, y = 0;
        int i = 0;
        while (x*x+y*y <= 4 && i < max) {
            double x_new = x*x - y*y + c_re;
            y = 2*x*y + c_im;
            x = x_new;
            i++;
        }
        return i;
    }


    public void paintComponent(Graphics g)
    {
        g.setColor(Color.RED);
        Graphics2D g2 = (Graphics2D)g;

        g2.drawImage(drawing, null, null);
    }

}