package wrld;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public class MainFrame extends JFrame implements KeyListener {

    private Color black = Color.BLACK;
    private Color blue = Color.BLUE;
    private Color cyan = Color.CYAN;
    private Color red = Color.RED;
    private Color yellow = Color.YELLOW;
    private Color gray = Color.GRAY;
    private Color light_gray = Color.LIGHT_GRAY;
    private Color green = Color.GREEN;
    private Color magenta = Color.MAGENTA;
    private Color orange = Color.ORANGE;
    private Color pink = Color.PINK;
    private Color white = Color.WHITE;
    private World world = new World();
    private Container main = getContentPane();
    private JPanel map = new JPanel();
    private JPanel controls = new JPanel();
    private JPanel comments = new JPanel();
    private JTextArea comment_area = new JTextArea(40,40);
    private JButton next_round_button = new JButton("Next Round");
    private JButton save_game_button = new JButton("Save Game");
    private JButton load_game_button = new JButton("Load Game");

    private JButton[][] squares = new JButton[20][20];

    public MainFrame() {
        super("Adam Walentkowski s171717");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        main.setLayout(new BorderLayout());
        map.setLayout(new GridLayout(20,20));


        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                squares[i][j] = new JButton();
                map.add(squares[i][j]);
            }
        }

        next_round_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                requestFocus();
                MainFrame.this.addKeyListener(MainFrame.this);
                world.SortVector();
                world.OrderedTurn();
                world.ClearVector();
                world.DrawWorld(MainFrame.this);
                comment_area.setText("");
                AppendComments();
                world.ClearComments();
            }
        });
        save_game_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    WriteToFile(world);
                } catch(IOException el) {
                    System.out.println(el.getMessage());
                }
                world.DrawWorld(MainFrame.this);
                comment_area.setText("");
                AppendComments();
                world.ClearComments();
            }
        });
        load_game_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    world=ReadFile();
                } catch(ClassNotFoundException | IOException el) {
                    System.out.println(el.getMessage());
                }
                world.DrawWorld(MainFrame.this);
                comment_area.setText("");
                AppendComments();
                world.ClearComments();
            }
        });
        controls.add(next_round_button);
        controls.add(save_game_button);
        controls.add(load_game_button);


        comment_area.setEditable(false);
        comment_area.setLineWrap(true);

        comments.add(comment_area);
        main.add(controls, BorderLayout.NORTH);
        main.add(map, BorderLayout.CENTER);
        main.add(comments, BorderLayout.EAST);

        world.DrawWorld(this);

        setVisible(true);

    }
    public void AppendComments(){
        for (int i = 0; i < world.comment_list.size(); i++) {
            comment_area.append(world.comment_list.get(i));
        }
    }

    public void ChangeColor(char c, int x, int y){
        if(c=='A') {
            squares[y][x].setBackground(pink);
            squares[y][x].setText("ANTLP");
        }
        if(c=='b') {
            squares[y][x].setBackground(cyan);
            squares[y][x].setText("BLDN");
        }
        if(c=='d') {
            squares[y][x].setBackground(yellow);
            squares[y][x].setText("DNDLN");
        }
        if(c=='F'){
            squares[y][x].setBackground(red);
            squares[y][x].setText("FOX");
        }
        if(c=='g') {
            squares[y][x].setBackground(green);
            squares[y][x].setText("GRSS");
        }
        if(c=='+') {
            squares[y][x].setBackground(orange);
            squares[y][x].setText("GRN");
        }
        if(c=='h') {
            squares[y][x].setBackground(magenta);
            squares[y][x].setText("HGWD");
        }
        if(c=='M') {
            squares[y][x].setBackground(blue);
            squares[y][x].setText("MAN");
            squares[y][x].setForeground(white);
        }
        if(c=='S') {
            squares[y][x].setBackground(light_gray);
            squares[y][x].setText("SHP");
        }
        if(c=='T') {
            squares[y][x].setBackground(black);
            squares[y][x].setText("TRTL");
            squares[y][x].setForeground(white);
        }
        if(c=='W') {
            squares[y][x].setBackground(gray);
            squares[y][x].setText("WLF");
        }
    }
    public void ClearColor(int x, int y){
        squares[y][x].setBackground(white);
        squares[y][x].setText("");
        squares[y][x].setForeground(black);
    }

    @Override
    public void keyPressed(KeyEvent evt) {
        if(evt.getKeyCode() == KeyEvent.VK_UP) {
            world.SetActionForHuman(0);
        }
        else if(evt.getKeyCode() == KeyEvent.VK_DOWN) {
            world.SetActionForHuman(1);
        }
        else if(evt.getKeyCode() == KeyEvent.VK_LEFT) {
            world.SetActionForHuman(2);
        }
        else if(evt.getKeyCode() == KeyEvent.VK_RIGHT) {
            world.SetActionForHuman(3);
        }
        else if(evt.getKeyCode() == KeyEvent.VK_P) {
            world.SetActionForHuman(4);
        }
    }

    @Override
    public void keyReleased(KeyEvent evt) {
    }

    @Override
    public void keyTyped(KeyEvent evt) {

    }
    public static void WriteToFile(World w) throws IOException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("World.bin"));

        objectOutputStream.writeObject(w);
    }
    public static World ReadFile() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("World.bin"));
        World save = (World) objectInputStream.readObject();
        return save;
    }
}
