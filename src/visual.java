import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
public final class visual extends DataEditior{
    private int helpChance = 0;
    private int key = 0;
    private JButton endGamebtn;
    private JLabel scorekeep;
    private int score = 0;
    private String newPlayer;
    private final JFrame f;
    private JPanel p;
    private ArrayList <LinkedList> lout;
    private final ArrayList <String> secondary;
    
    private  int check;
    public visual(){
        ArrayList <String> deck = new ArrayList<>();
        try {
            BlocksGetter get = new BlocksGetter();
            deck = get.getBlocks();
        }catch (FileNotFoundException e) {
        }
            randSuffle(deck);
            secondary = deck;
            lout = formGroups(deck);
            getLayout(1,lout);
            f = new JFrame("Mahjong");
            f.setLayout( new BoxLayout( f.getContentPane(), BoxLayout.Y_AXIS ));
    }

    public void askName(){
        JLabel nm;
        JTextField name;
        JButton strbutton = new JButton("Start");
        ImageIcon bckground = new ImageIcon("myphotos/third.jpg");
        JLabel background = new JLabel(bckground);
        p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.add(background);
        nm = new JLabel("Name : ");
        name = new JTextField(30);
        //String str = name.getText();
        Container pane = new Container();
        FlowLayout flo = new FlowLayout();
        pane.setLayout(flo);
        pane.add(nm);
        pane.add(name);
        pane.add(strbutton);
        JPanel pan = new JPanel();
        pan.setLayout(new BoxLayout(pan, BoxLayout.X_AXIS));
        p.add(pane);
        f.add(p);        
        f.setSize(800,800);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        strbutton.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                String str = name.getText();
                newPlayer = str;
                p.removeAll();
               pan.removeAll();
               pan.repaint();
               p.repaint();
               lout = formGroups(secondary);
               key = 0;
               chooseDifficulty(pan);
                //reArrangeBlocks(pan,lout);
                //chooseShape(pan);
                //endGame(pan);
                //startGame(lout);                 
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
    });
    }
    public void chooselayout(JPanel pan){
        for (int i=1;i<=4;i++){
            ImageIcon layout = new ImageIcon("myphotos/"+i+".jpg");
            JButton lay = new JButton(Integer.toString(i),layout);
            lay.setHorizontalTextPosition(JButton.CENTER);
            lay.setVerticalTextPosition(JButton.CENTER);
            lay.setFont(new Font("Arial", Font.PLAIN, 1));
            lay.setContentAreaFilled( false );
            lay.setBorder( null );
            pan.add(lay);
            lay.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                String str = lay.getText();
                key = Integer.parseInt(str);
                if (key != 0){
                        p.removeAll();
                        pan.removeAll();
                        pan.repaint();
                        p.repaint();
                        getLayout(key,lout);
                        reArrangeBlocks(pan,lout);
                        chooseShape(pan);
                        endGame(pan);
                        startGame(lout);
               }
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            });
        }
        p.add(pan);
        f.add(p);
        f.setSize(800,300);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
     public void endGame(JPanel pan){
        ImageIcon endimg = new ImageIcon("myphotos/close.jpg");
        endGamebtn = new JButton(endimg);
        endGamebtn.setContentAreaFilled( false );
        endGamebtn.setBorder( null );
        pan.add(endGamebtn);
        endGamebtn.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                p.removeAll();
                pan.removeAll();
                pan.repaint();
                p.repaint();
                JLabel lb = new JLabel(newPlayer+"'s score was "+Integer.toString(score));
                pan.add(lb);
                f.add(pan);
                f.setSize(300,50);
                f.setResizable(false);
                f.setLocationRelativeTo(null);
                f.setVisible(true);
                //start = 1;                      
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
    });
        f.add(p);
    }
    public void chooseShape(JPanel pan){
            ImageIcon buttonImg = new ImageIcon("myphotos/choose.jpg");
            JButton shufflebtn = new JButton(buttonImg);
            shufflebtn.setContentAreaFilled( false );
            shufflebtn.setBorder( null );
            pan.add(shufflebtn);
            shufflebtn.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
               p.removeAll();
               pan.removeAll();
               pan.repaint();
               p.repaint();
               lout = formGroups(secondary);
               key = 0;
               chooselayout(pan);
               
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
    });
    f.add(pan);
    }

    public void reArrangeBlocks(JPanel pan,ArrayList <LinkedList> lout){

            ImageIcon buttonImg = new ImageIcon("myphotos/shuffle1.jpg");
            JButton shufflebtn = new JButton(buttonImg);
            shufflebtn.setContentAreaFilled( false );
            shufflebtn.setBorder( null );
            pan.add(shufflebtn);
            shufflebtn.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                if (helpChance>0){
                    helpChance -=1;
                    reArrange(lout);
                    p.removeAll();
                    startGame(lout);
                }
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
    });
    f.add(pan);
    }

    public void startGame(ArrayList <LinkedList> lout){
            ArrayList <String> pos = new ArrayList();
            p = new JPanel();
            p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
            //scorekeep = new JLabel(Integer.toString(score));
            for(int i=0;i<lout.size();i++){
               
                JPanel panel = new JPanel();
                panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
                LinkedList line = new LinkedList<>();
                line = lout.get(i);
                for(int j=0;j<line.size();j++){
                    ImageIcon image = new ImageIcon("myphotos/"+line.get(j)+".jpg");
                    ImageIcon image1 = new ImageIcon("myphotos/"+line.get(j)+"_2.jpg");
                    JButton button = new JButton(i+","+j,image);
                    button.setHorizontalTextPosition(JButton.CENTER);
                    button.setVerticalTextPosition(JButton.CENTER);
                    button.setFont(new Font("Arial", Font.PLAIN, 1));
                    button.setContentAreaFilled( false );
                    button.setBorder( null );

                    if (j==0 || j==line.size()-1){
                        button.addMouseListener(new MouseListener(){
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            check = 0;
                            JButton button1 = (JButton)e.getSource();
                            button1.setIcon(image1);
                            String str = button.getText();
                            pos.add(str);
                            if (pos.size() == 2){
                                deleteBlocks(pos,lout);
                                if (check == 0){
                                    p.removeAll();
                                    startGame(lout);
                                }
                            }
                        }
                        @Override
                        public void mousePressed(MouseEvent e){}                            
                        @Override
                        public void mouseReleased(MouseEvent e) {}                            
                        @Override
                        public void mouseEntered(MouseEvent e) {}
                        @Override
                        public void mouseExited(MouseEvent e) {}
                    });
                    }
                    panel.add(button);
               }
               scorekeep = new JLabel(newPlayer+"'s score : "+Integer.toString(score)+". You have "+helpChance+" chances");
               p.add(panel);              
            }
            p.add(scorekeep);
            f.add(p);
            f.setSize(800, 700);
            f.setResizable(false);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void deleteBlocks(ArrayList <String> pos,ArrayList <LinkedList> lout){
        String[] block = pos.get(0).split(",");
        int line = Integer.parseInt(block[0]);
        int plc = Integer.parseInt(block[1]);
        LinkedList ln = lout.get(line);
        String click1 = (String) ln.get(plc);
        String[] block1 = pos.get(1).split(",");
        int line1 = Integer.parseInt(block1[0]);
        int plc1 = Integer.parseInt(block1[1]);
        LinkedList ln1 = lout.get(line1);
        String click2 = (String) ln1.get(plc1);
        if(line != line1){
                if (click1.equals(click2)||(click1.charAt(0) == 'S' && click2.charAt(0) == 'S')||(click1.charAt(0) == 'F' && click2.charAt(0) == 'F')){
                    ln.remove(plc);
                    ln1.remove(plc1);
                    check = 1;
                    if (click1.charAt(0) == 'S' || click1.charAt(0) == 'F'){
                        score += 1;
                    }else{
                        score += 2;
                    }
                    p.removeAll();
                    startGame(lout);
                }
        }else{
            if (click1.equals(click2)||(click1.charAt(0) == 'S' && click2.charAt(0) == 'S')||(click1.charAt(0) == 'F' && click2.charAt(0) == 'F')){
                    ln.removeFirst();
                    ln.removeLast();
                    check = 1;
                    if (click1.charAt(0) == 'S' || click1.charAt(0) == 'F'){
                        score += 1;
                    }else{
                        score += 2;
                    }
                    p.removeAll();
                    startGame(lout);
                }
        }
    }
    public void chooseDifficulty(JPanel pan){
        for (int i=5;i<=7;i++){
            ImageIcon layout = new ImageIcon("myphotos/"+i+".jpg");
            JButton lay = new JButton(Integer.toString(i),layout);
            lay.setHorizontalTextPosition(JButton.CENTER);
            lay.setVerticalTextPosition(JButton.CENTER);
            lay.setFont(new Font("Arial", Font.PLAIN, 1));
            lay.setContentAreaFilled( false );
            lay.setBorder( null );
            pan.add(lay);
            lay.addMouseListener(new MouseListener(){
            @Override
            public void mouseClicked(MouseEvent e) {
                String str = lay.getText();
                switch (Integer.parseInt(str)) {
                    case 5:
                        helpChance = 3;
                        break;
                    case 6:
                        helpChance = 2;
                        break;
                    default:
                        helpChance = 1;
                        break;
                }
                p.removeAll();
                pan.removeAll();
                pan.repaint();
                p.repaint();
                getLayout(key,lout);
                reArrangeBlocks(pan,lout);
                chooseShape(pan);
                endGame(pan);
                startGame(lout);
            }
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
            });
        }
        p.add(pan);
        f.add(p);
        f.setSize(350,250);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setVisible(true);
    }
}

