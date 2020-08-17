import java.io.*;
import java.net.URL;
import javax.sound.sampled.*;

public class AudioPlayer {
	
	private Clip clip;
	
	public AudioPlayer(String fileName) {
		try {
			URL url = this.getClass().getClassLoader().getResource(fileName);
			AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
			AudioFormat format = audioInputStream.getFormat();
 
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip audioClip = (Clip) AudioSystem.getLine(info);
			
			audioClip.open(audioInputStream);
			audioClip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

