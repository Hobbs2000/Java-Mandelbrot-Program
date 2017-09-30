import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by calvinfield on 9/29/17.
 */
public class MandelbrotImage extends BufferedImage
{
    private ArrayList<Integer> iterations = new ArrayList<Integer>();

    /**
     * Constructs a new Mandelbrot Image
     *
     * @param width
     * @param height
     * @param imageType
     */
    public MandelbrotImage(int width, int height, int imageType) {
        super(width, height, imageType);
    }

    /**
     * Will fill the pixels of the image with the appropriate colors based off of the
     * calculated mandelbrot set
     *
     * @param maxIteration
     * @param zoom
     * @param x_shift
     * @param y_shift
     */
    public void generateImage(int maxIteration, double zoom, double x_shift, double y_shift)
    {
        zoom = zoom*zoom;
        zoom -= 1;
        if (zoom != 0 && zoom > 0) {
            zoom = 1 / zoom;
            zoom = (4 - zoom);}
        else if (zoom < 0)
            zoom = 0;

        for (int row = 0; row < this.getHeight(); row++) {
            for (int col = 0; col < this.getWidth(); col++) {
                double c_re = ((col - this.getWidth()/2.0)*(4-zoom)/this.getWidth()) + x_shift;

                double c_im = ((row - this.getHeight()/2.0)*(4-zoom)/this.getWidth()) + y_shift;

                int n = findMandelbrotNum(c_re, c_im, maxIteration);
                iterations.add(n);

                int R = ((n)%256);
                int G = ((n)%256);
                int B = ((n)%256);
                Color newColor = new Color(R, G, B, 255);

                this.setRGB(col, row, newColor.getRGB());

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
    public int findMandelbrotNum(double c_re, double c_im, int max)
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

}
