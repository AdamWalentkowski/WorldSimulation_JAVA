import org.*;
import wrld.*;

//import javax.swing.*;
import java.awt.*;

public class Simulation {

    public static void main(String[] args){
         EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MainFrame();
            }
        });
    }
}