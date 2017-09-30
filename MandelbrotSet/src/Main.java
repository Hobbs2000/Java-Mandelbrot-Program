import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
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
                mImg = new MandelbrotImage(1280, 720, BufferedImage.TYPE_INT_RGB);
                mImg.generateImage(255, 3200, .335598, .4008);
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
            System.out.println("How long would you like the GIF to be?(s): ");
            double time = mainScan.nextDouble();
            System.out.println("What would you like the framerate to be?(fps): ");
            int fps = mainScan.nextInt();
            System.out.println("Name of gif file?: ");
            String fileName = mainScan.next();


            AnimatedGifEncoder ge = new AnimatedGifEncoder();
            ge.start(fileName+".gif");
            //int fps = 15; //The framerate in frames per second

            ge.setDelay(1000/fps); //1000 ms in a second


            for (int i = 1; i < ((time*1000)/((1000/fps))); i++)
            {
                System.out.print(i+"/"+((int)(time*1000)/(1000/fps)));
                MandelbrotImage mImg = new MandelbrotImage(1280, 720, BufferedImage.TYPE_INT_RGB);

                double zoom = (i*i)*0.05;

                System.out.print(" - Current Zoom: "+zoom);
                System.out.println();

                mImg.generateImage(255, zoom, .335598, .4008);
                ge.addFrame(mImg);
            }
            ge.finish();


        }
    }
}
