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
    public void other_calc(int maxIteration, double zoom, double x_shift, double y_shift)
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

                int R = (int)((Math.log(n))%256);
                int G = (int)((n)%256);
                int B = (int)((n)%256);
                Color newColor = new Color(R, G, B, 255);

                drawing.setRGB(col, row, newColor.getRGB());

            }
        }
    }


    /**
     *
     */
    public void calc(int maxIteration)
    {
        /*
        zoom = zoom*zoom;
        zoom -= 1;
        if (zoom != 0 && zoom > 0) {
            zoom = 1 / zoom;
            zoom = (4 - zoom);}
        else if (zoom < 0)
            zoom = 0;
*/
        double WIDTH_better = WIDTH*2;
        double HEIGHT_better = HEIGHT*2;

        for (int row = 0; row < HEIGHT_better; row++) {
            for (int col = 0; col < WIDTH_better; col++) {
                //double c_re = ((col - WIDTH/2.0)*(4-zoom)/WIDTH) + x_shift;
                double c_re = ((col - WIDTH_better/2.0)*4/WIDTH_better);
                c_reals.add(c_re);

                //double c_im = ((row - HEIGHT/2.0)*(4-zoom)/WIDTH) + y_shift;
                double c_im = ((row - HEIGHT_better/2.0)*4/WIDTH_better);
                c_imgs.add(c_im);

                int n = findMandelbrot(c_re, c_im, maxIteration);
                iterations.add(n);
            }
        }

        System.out.println("Total coords:"+ c_reals.size());

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

    /**
     *
     */
    public void clear_drawing()
    {
        for (int r = 0; r < WIDTH; r++)
        {
            for (int c = 0; c < HEIGHT; c++)
            {
                drawing.setRGB(r, c, Color.WHITE.getRGB());
            }
        }
    }

    /**
     *
     */
    public void draw_zoom(double zoom, double x_shift, double y_shift) {
        clear_drawing();


        double max_re = (2/zoom) + x_shift;
        double min_re = -(max_re - x_shift);
        double max_im = (2/zoom) + y_shift;
        double min_im = -(max_im - y_shift);
        System.out.println("max_re: "+max_re+" max_im: "+max_im);

        for (int i = 0; i<c_reals.size(); i++)
        {

            if (c_reals.get(i).doubleValue() > min_re && c_reals.get(i).doubleValue() < max_re && c_imgs.get(i).doubleValue() > min_im && c_imgs.get(i).doubleValue() < max_im)
            {
                //System.out.println("real:"+reals.get(i).doubleValue()+"  im:"+imgs.get(i).doubleValue());
                double WIDTH_db = (double)WIDTH;
                double HEIGHT_db = (double)HEIGHT;
                double x = (((c_reals.get(i).doubleValue())-min_re)/(2*max_re))*WIDTH_db;//(c_reals.get(i).doubleValue()/(4/WIDTH_db))+(WIDTH_db/2); //Get the X pixel coordinate

                double y = (((c_imgs.get(i).doubleValue())-min_im)/(2*max_im))*HEIGHT_db;//((c_imgs.get(i).doubleValue()/(4/WIDTH_db))+(HEIGHT_db/2)); //Get the Y pixel coordinate
                //System.out.println("X:"+x+" Y:"+y);

                int n = iterations.get(i).intValue();
                //System.out.println("N:"+ns.get(i).intValue());

                int R = (int)((n)%256);
                int G = (int)((n)%256);
                int B = (int)((n)%256);
                Color newColor = new Color(R, G, B, 255);
                drawing.setRGB((int)x, (int)y, newColor.getRGB());
            }
        }




    }

    public void paintComponent(Graphics g)
    {
        g.setColor(Color.RED);
        Graphics2D g2 = (Graphics2D)g;

        g2.drawImage(drawing, null, null);
    }

}