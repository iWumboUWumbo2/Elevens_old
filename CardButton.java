import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;

public class CardButton extends JToggleButton {
    private Card c;
    private Dimension d;
    private Globals g = new Globals(new File("colors.txt"));

    public CardButton(Card c, Dimension d) {
        this.c = c;
        this.d = d;

		URL url = getClass().getResource("CardImages/" + c + ".png");
        ImageIcon icon = new ImageIcon(url);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(d.width, d.height, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(newImg));
        setBackground(g.bgColor);
        setBorder(BorderFactory.createEmptyBorder());
    }

    public void changeCard(Card c) {
        this.c = c;
        URL url = getClass().getResource("CardImages/" + c + ".png");
        ImageIcon icon = new ImageIcon(url);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(d.width, d.height, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(newImg));
    }

	public void cardBack(String color) {
		// Colors include:
		// ["blue", "gray", "green", "purple", "red", "yellow"]
		
		color = (color != null) ? color : g.cardBackColor;
		
		URL url = getClass().getResource("CardImages/" + color + "_back.png");
        ImageIcon icon = new ImageIcon(url);
        Image img = icon.getImage();
        Image newImg = img.getScaledInstance(d.width, d.height, Image.SCALE_SMOOTH);
        setIcon(new ImageIcon(newImg));
	}
	
    public Card getCard(){
        return c;
    }

}

// 115, 176