package mx.edu.itesca.isc.wgl2;

import java.awt.*;
import javax.swing.*;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author hectormgerardo
 */
public class UserInterface extends JFrame {
    UserInterface(){
        setTitle("WGL2 Compiler");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args){
        UserInterface UI = new UserInterface();
        JPanel main = new JPanel();
        main.add(new JLabel("Projecto de Compilador"));
        UI.add(main);
        UI.pack();
    }
}