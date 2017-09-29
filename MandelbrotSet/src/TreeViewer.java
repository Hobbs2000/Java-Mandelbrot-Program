import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.Scanner;

public class TreeViewer extends JFrame
{
    private final int HEIGHT = 1080;
    private final int WIDTH = 1900;

    private TreePanel drawer;
    private JPanel holder;
    private boolean looping = true;

    public TreeViewer()
    {
        super("Mandelbrot Set");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        holder = new JPanel(); //This JPanel holds the TreePanel JPanel cuz reasons


        drawer = new TreePanel();
        Scanner in = new Scanner(System.in);

        System.out.println("Use Default Values? (y/n): ");

        if (in.next().toLowerCase().equals("n"))
        {
            System.out.println("Iterations(int): ");
            int iterations = in.nextInt();
            System.out.println("Zoom level(int): ");
            int zoom = in.nextInt();
            System.out.println("X Shift(dbl): ");
            double x_shift = in.nextDouble();
            System.out.println("Y Shift(dbl): ");
            double y_shift = in.nextDouble();

            drawer.standard_calc(iterations, zoom, x_shift, y_shift); //Perform the actual calculations for the mandelbrot set
        }
        else
        {
            drawer.standard_calc(255, 20, .3356, .4006); //Perform the actual calculations for the mandelbrot set
        }


        holder.add(drawer);

        this.add(holder);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setVisible(true);

        loop();
    }

    /**
     * Just makes sure the window stays open
     */
    public void loop()
    {
        while (looping == true)
        {
            try
            {
                Thread.sleep(10);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            repaint();
        }
    }


}



