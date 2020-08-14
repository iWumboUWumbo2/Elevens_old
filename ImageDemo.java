import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.*;

public class ImageDemo
{
  public static void main(String[] args) throws Exception
  {
    new ImageDemo("Elevens/CardImages/3C.png");
  }

  public ImageDemo(final String filename) throws Exception
  {
    SwingUtilities.invokeLater(new Runnable()
    {
      public void run()
      {
        JFrame editorFrame = new JFrame("Image Demo");
        
        ImageIcon imageIcon = new ImageIcon("C:/2C.png");
        JLabel jLabel = new JLabel();
        jLabel.setIcon(imageIcon);
        editorFrame.getContentPane().add(jLabel, BorderLayout.CENTER);

        editorFrame.pack();
        
        editorFrame.setVisible(true);
      }
    });
  }
}
