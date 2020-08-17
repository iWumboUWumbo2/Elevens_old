import javax.swing.*;
import javax.swing.border.*;
import javax.swing.colorchooser.*;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.event.*;
import java.io.File;

public class AppearanceChanger extends JFrame implements ActionListener, ChangeListener {
	
	private JPanel appearancePanel;
	private Globals g = new Globals(new File("colors.txt"));
	private JColorChooser cc;
	private String btnColor;
	private Color bgColor;
	
	private TitledBorder makeBorder(String s) {
		TitledBorder leftBorder = BorderFactory.createTitledBorder("Left");
    	leftBorder.setTitleJustification(TitledBorder.LEFT);
		
		return leftBorder;
	}
	
	public AppearanceChanger() {
        btnColor = g.cardBackColor;
       	bgColor = g.bgColor;
       
       	
        
        appearancePanel = new JPanel();
        appearancePanel.setLayout(new BoxLayout(appearancePanel, BoxLayout.X_AXIS));
       
        ButtonGroup btnGroup = new ButtonGroup();
        String[] colors = new String[]{"blue", "gray", "green", "purple", "red", "yellow"};
        JPanel btnPanel = new JPanel(new GridLayout(6, 0));
        btnPanel.setBorder(makeBorder("Card Back"));
        appearancePanel.add(btnPanel);
        	
       	for (int i = 0; i < colors.length; i++) {
       		JRadioButton rb = new JRadioButton(colors[i]);
       		
       		rb.setSelected(g.cardBackColor.equalsIgnoreCase(colors[i]));
       		
       		rb.addActionListener(this);
       		btnGroup.add(rb);
       		btnPanel.add(rb);
       	}
        		
       	cc = new JColorChooser(new Color(39, 100, 20));
       	cc.getSelectionModel().addChangeListener(this);
       	cc.setBorder(makeBorder("Background"));
       	appearancePanel.add(cc);
       	
       	JButton btnSet;
       	btnSet = new JButton("Set colors");
       	btnSet.addActionListener(new ActionListener() {
        	public void actionPerformed (ActionEvent e) {
        		System.out.println(btnColor + " " + bgColor.getRGB());
        		
        		g = new Globals(btnColor, bgColor);
        		g.writeToFile(new File("colors.txt"));
        
        		JOptionPane.showMessageDialog(appearancePanel, "Changes will take place after restart");
        	}
        });
       	appearancePanel.add(btnSet);
       		
       	getContentPane().add(appearancePanel);
       	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       	pack();
       	setTitle("Change Appearance");
       	setVisible(true);
	}

	public void actionPerformed (ActionEvent e) {
        btnColor = ((JRadioButton) e.getSource()).getText();
    }
    
    public void stateChanged(ChangeEvent e) {
        bgColor = cc.getColor();
    }
}

