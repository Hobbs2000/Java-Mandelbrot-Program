import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.awt.image.BufferedImage;;

/**
 * Created by calvinfield on 9/29/17.
 */
public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Generate static image or gif?(s/g): ");
        Scanner mainScan = new Scanner(System.in);

        if (mainScan.next().toLowerCase().equals("s"))
        {
            MandelbrotImage mImg;

            System.out.println("What would you like the image filename to be?(without extension): ");
            String fileName = mainScan.next();

            System.out.println("Use Default Values? (y/n): ");

            if (mainScan.next().toLowerCase().equals("n"))
            {
                System.out.println("Image Width (pixels): ");
                int width = mainScan.nextInt();
                System.out.println("Image Height (pixels): ");
                int height = mainScan.nextInt();
                System.out.println("Iterations(int): ");
                int iterations = mainScan.nextInt();
                System.out.println("Zoom level(int): ");
                int zoom = mainScan.nextInt();
                System.out.println("X Shift(dbl): ");
                double x_shift = mainScan.nextDouble();
                System.out.println("Y Shift(dbl): ");
                double y_shift = mainScan.nextDouble();

                System.out.println("Generating Mandelbrot Image...");
                mImg = new MandelbrotImage(width, width, BufferedImage.TYPE_INT_RGB);
                mImg.generateImage(iterations, zoom, x_shift, y_shift);
            }
            else
            {
                System.out.println("Generating Mandelbrot Image...");
                mImg = new MandelbrotImage(1920, 1080, BufferedImage.TYPE_INT_RGB);
                mImg.generateImage(255, 20, .3356, .4006);
            }

            System.out.println("Saving Image...");
            File outF = new File(fileName+".jpg");
            try
            {
                ImageIO.write(mImg, "jpg", outF);
            }
            catch (IOException e)
            {
                e.printStackTrace(); //It is customary to print the stack trace, even if I don't know why
            }
            System.out.println("Finished!");

        }
        else
        {
            System.out.println("Generating GIF...");
        }
    }
}
