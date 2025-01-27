package e2;

import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import java.io.Serial;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class GUI extends JFrame {
    
    @Serial
    private static final long serialVersionUID = -6218820567019985015L;
    private final Map<JButton,Pair<Integer,Integer>> buttons = new HashMap<>();
    private final Logics logics;
    
    public GUI(int size) {
        this.logics = new LogicsImpl(size);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(BorderLayout.CENTER,panel);
        
        ActionListener onClick = (e)->{
            final JButton bt = (JButton)e.getSource();
            final Pair<Integer,Integer> pos = buttons.get(bt);
            if (!this.logics.getFlags().contains(pos)){
                boolean aMineWasFound = this.logics.hit(pos) == -1; // call the logic here to tell it that cell at 'pos' has been selected
                if (aMineWasFound) {
                    quitGame();
                    JOptionPane.showMessageDialog(this, "You lost!!");
                    System.exit(0);
                } else {
                    drawBoard();
                }
                boolean isThereVictory = this.logics.isWin(); // call the logic here to ask if there is victory
                if (isThereVictory) {
                    quitGame();
                    JOptionPane.showMessageDialog(this, "You won!!");
                    System.exit(0);
                }
            }
        };

        MouseInputListener onRightClick = new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                final JButton bt = (JButton)e.getSource();
                if (bt.isEnabled()){
                    final Pair<Integer,Integer> pos = buttons.get(bt);
                    logics.setFlag(pos);
                    // call the logic here to put/remove a flag
                }
                drawBoard(); 
            }
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                jb.addActionListener(onClick);
                jb.addMouseListener(onRightClick);
                this.buttons.put(jb,new Pair<>(i,j));
                panel.add(jb);
            }
        }
        this.drawBoard();
        this.setVisible(true);
    }
    
    private void quitGame() {
        this.drawBoard();
    	for (var entry: this.buttons.entrySet()) {
            if (this.logics.hit(entry.getValue()) < 0){
                entry.getKey().setText("*");
                entry.getKey().setEnabled(false);
            }
            // call the logic here
            // if this button is a mine, draw it "*"
            // disable the button
    	}
    }

    private void drawBoard() {
        for (var entry : this.buttons.entrySet()) {
            if (this.logics.getOpenCells().containsKey(entry.getValue())){
                int num = this.logics.getOpenCells().get(entry.getValue());
                if (num >= 0) {
                    if (num == 0) {
                        entry.getKey().setText(" ");
                    } else {
                        entry.getKey().setText(String.valueOf(num));
                    }
                    entry.getKey().setEnabled(false);
                }
                    // call the logic here
                    // if this button is a cell with counter, put the number
                    // if this button has a flag, put the flag
            }
            if (this.logics.getFlags().contains(entry.getValue())){
                entry.getKey().setText("X");
            }
            if (!this.logics.getOpenCells().containsKey(entry.getValue()) && !this.logics.getFlags().contains(entry.getValue())){
                entry.getKey().setText(" ");
            }
        }
    }
}
