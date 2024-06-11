import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Main extends JFrame implements ActionListener{
    private JButton b[] = new JButton[9];
    private boolean XTurn = false;
    
    Main(String title){
        setTitle(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel pnl = new JPanel(new GridLayout(3,3));
        pnl.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        for(int i=0;i<9;i++){
            b[i]=new JButton();
            pnl.add(b[i]);
            b[i].addActionListener(this);
            b[i].setFont(new Font("default",Font.BOLD,40));
        }
        add(pnl,BorderLayout.CENTER);
        setSize(500, 500);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e){
        JButton currentButton = (JButton)e.getSource();
        if(XTurn) currentButton.setText("X");
        else currentButton.setText("O");
        currentButton.setEnabled(false);
        XTurn = !XTurn;
        checkWin();
    }
    void checkWin(){
        //ROW AND COLUMN
        for(int i=0;i<3;i++){
            int j=i*3;
            //ROW CHECK
            if(!b[j].isEnabled() && (b[j].getText().equals(b[j+1].getText())) && (b[j].getText().equals(b[j+2].getText()))){
                JOptionPane.showMessageDialog(this,"Player " + b[j].getText()+" Wins!");
                reset();
                return;
            } 
            //COLUMN CHECK
            if(!b[i].isEnabled() && (b[i].getText().equals(b[i+3].getText())) && (b[i].getText().equals(b[i+6].getText()))){
                JOptionPane.showMessageDialog(this,"Player " +  b[i].getText()+" Wins!");
                reset();
                return;
            } 
        }
        //DIAGONALS CHECK
        if(!b[0].isEnabled() && (b[0].getText().equals(b[4].getText())) && (b[0].getText().equals(b[8].getText()))){
            JOptionPane.showMessageDialog(this,"Player " +  b[0].getText()+" Wins!");
            reset();
            return;
        } 
        if(!b[2].isEnabled() && (b[2].getText().equals(b[4].getText())) && (b[2].getText().equals(b[6].getText()))){
            JOptionPane.showMessageDialog(this,"Player " +  b[2].getText()+" Wins!");
            reset();
            return;
        } 
        //CHECK FOR DRAW
        for(int i=0;i<9;i++){
            if(b[i].isEnabled()){
                return;
            }
        }
        JOptionPane.showMessageDialog(this, " Game Tie! ");
        reset();
        return;
    }
    void reset(){
        for(int i=0;i<9;i++){
            b[i].setEnabled(true);
            b[i].setText("");
        }
        XTurn=false;
        return;
    }
    public static void main(String[] args) {
        new Main("TIC TAC TIE");
    }
}