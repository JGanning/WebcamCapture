package WebcamCapture;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import com.github.sarxos.webcam.Webcam;

public class Keylogger implements NativeKeyListener {
	static String pass = "";
	static int seconds = 0;
	static Timer timer = new Timer();
	static TimerTask task = new TimerTask() {
		public void run() {
			seconds++;
			System.out.println("Seconds passed: " + seconds);
		}
	};
	
	public void Start() throws IOException {
		try {
			GlobalScreen.registerNativeHook();
		} catch(NativeHookException e) {
			e.printStackTrace();
		}
		GlobalScreen.getInstance().addNativeKeyListener(new Keylogger());
		
		Webcam webcam = Webcam.getDefault();
		webcam.open();
		timer.schedule(task, 1000, 1000);
		
		while(true) {
			System.out.println("Current pass: " + pass);
			if(pass.equals("69") || pass == "69") {
				System.out.println("You are safe.");
				webcam.close();
				System.exit(1);
			} else if(seconds >= 20) {
				ImageIO.write(webcam.getImage(), "PNG", new File("Caught.png"));
				System.out.println("You were caught!");
				webcam.close();
				System.exit(1);
			}
		}
	}

	@Override
	public void nativeKeyPressed(NativeKeyEvent event) {
		// TODO Auto-generated method stub
		if(event.getKeyCode() == NativeKeyEvent.VK_BACK_SPACE) {
			pass = "";
		} else {
			pass = pass + NativeKeyEvent.getKeyText(event.getKeyCode());
		}
		System.out.println("Pass is: " + pass);
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
