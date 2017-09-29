import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JScrollBar;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.AdjustmentEvent;
import java.awt.Color;

public class TreeViewer extends JFrame
{
    private final int HEIGHT = 1080;
    private final int WIDTH = 1900;

    private int order = 1;

    private TreePanel drawer;
    private JPanel holder;
    private JButton increase;
    private JButton decrease;
    private JScrollBar length;
    private JScrollBar editAngle;
    private JScrollBar shiftAngle;
    private JTextField editLength;

    private JButton stopper;
    private boolean looping = true;

    public TreeViewer()
    {
        super("Fractal Tree");
        this.setSize(WIDTH, HEIGHT);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        holder = new JPanel();


        drawer = new TreePanel();
        //drawer.calc(200);
        //drawer.draw_zoom(2, 1,0);
        drawer.other_calc(250, 20, .3352, .4006);
        holder.add(drawer);

        this.add(holder);

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setVisible(true);



        loop();
    }

    public void loop()
    {
        int count = 1;
        while (looping == true)
        {
            //drawer.draw_zoom(count, 0,0);
            //drawer.draw_zoom(3, 1,0);
            //drawer.other_calc(250, count, .3352, .4006);
            try
            {
                Thread.sleep(10);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            repaint();
            count++;
        }
    }

    public static void main(String[] args)
    {
        JFrame frame = new TreeViewer();
        frame.setBackground(Color.WHITE);
        frame.repaint();

    }

    public class ClickListener implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            if (event.getSource() == increase)
            {

            }
            else if (event.getSource() == decrease)
            {

            }
            else if (event.getSource() == stopper)
            {
            }
        }
    }

    public class ScrollListener implements AdjustmentListener
    {
        public void adjustmentValueChanged(AdjustmentEvent e)
        {
            if (e.getSource() == editAngle)
            {
            }
            else if (e.getSource() == shiftAngle)
            {

            }
            else
            {

            }
        }
    }

}
