import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Iterator;

public class Main extends JFrame {
    private JPanel panel;
    private Deck deck;
    private CardButtonGroup cbg;
    private int elevenCount = 0;
    private JMenuBar menuBar;
    private JLabel score = new JLabel("0");
    private JLabel cardsRemaining = new JLabel("43");

    private void setMenu() {
        menuBar = new JMenuBar();
        JMenu menu = new JMenu("Game");
        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(new ActionListener() {
        	public void actionPerformed (ActionEvent e) {
        		new Main();
            	dispose();
        	}
        });
        
        menu.add(newGame);
        menu.add(new JSeparator());
        JMenuItem hint = new JMenuItem("Hint");
        hint.addActionListener(new ActionListener() {
        	public void actionPerformed (ActionEvent e) {
        		cbg.highlightHint();
        		new AudioPlayer("audio/hint.wav");
        	}
        });
        menu.add(hint);
        
        menu.add(new JSeparator());
        JMenuItem appearance = new JMenuItem("Change Appearance");
        appearance.addActionListener(new ActionListener() {
        	public void actionPerformed (ActionEvent e) {
        		new AppearanceChanger();
        	}
        });
        menu.add(appearance);
        
        menuBar.add(menu);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(new JLabel("Score: "));
        menuBar.add(score);
        menuBar.add(Box.createHorizontalStrut(20));
        menuBar.add(new JLabel("Cards Remaining: "));
        menuBar.add(cardsRemaining);
        menuBar.add(Box.createHorizontalStrut(10));
    }

    private void setPlayField() {
        for (int i = 0; i < 9; i++) {
            cbg.add(new CardButton(deck.get(i), new Dimension(115,176)));
            deck.remove(i);
            cbg.get(i).addActionListener(new ActionListener() {
        		public void actionPerformed (ActionEvent e) {
        			if (cbg.isEleven()) {
        				
        				Iterator it = cbg.selectedButtons().iterator();
        				
        				while (it.hasNext()) {
        					CardButton cb = (CardButton) it.next();
        					cb.changeCard(deck.get(0));
                        	deck.remove(0);
        				}
        				
                    	cbg.deselectAll();
                    	score.setText(++elevenCount + "");
                    	cardsRemaining.setText(deck.size() + "");
                    	new AudioPlayer("audio/cardsclear.wav");
                	}

                	if (deck.size() < 2) {
                    	setToCardBack(null);
                    	new AudioPlayer("audio/win.wav");
                    	JOptionPane.showMessageDialog(panel, "You Win!");
                	}

                	if (cbg.isGameOver()) {
                		setToCardBack(null);
                		new AudioPlayer("audio/loss.wav");
                    	JOptionPane.showMessageDialog(panel, "Game Over!");                    	
                	}
        		}
            });
            panel.add(cbg.get(i));
        }
    }
    
    public void setToCardBack(String color) {
    	Iterator it = cbg.getCardButtons().iterator();
    	
    	while (it.hasNext()) {
    		CardButton cb = (CardButton) it.next();
        	cb.cardBack(color);
    	}
    }

    public Main() {
        setSize(600,620);
        panel = new JPanel(new GridLayout(3, 3));
        deck = new Deck();
        deck.shuffleDeck();
        cbg = new CardButtonGroup();
        new AudioPlayer("audio/begin.wav");
        setPlayField();

        setMenu();
        setJMenuBar(menuBar);
        getContentPane().add(panel);
        setTitle("Elevens");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String[] args) {
	new Main();
    }
}
