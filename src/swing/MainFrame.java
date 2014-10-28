package swing;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ThanhLong on 2014.10.28..
 */
public class MainFrame extends JFrame {

    public MainFrame(){
        super("Minesweeper");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setMinimumSize(new Dimension(250, 400));
    }
}
