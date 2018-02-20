import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Scout {
	
	private static JFrame frame;//The JFrame that holds the InputPanel object
	private static JFrame testFrame;//The JFrame that displays width and height information
	
	public static void main(String[] args) {
		//Initializes frame and inputPanel
		frame = new JFrame("Scout");
		InputPanel inputPanel = new InputPanel();
		
		//adds inputPanel to frame
		frame.add(inputPanel);
		
		//adds frame to the screen
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(Screenfit.mainFrameWidth(), Screenfit.mainFrameHeight());
		frame.setVisible(true);
		
		//initializes Sizer and Thread objects
		Sizer s = new Sizer();
		Thread t = new Thread(s);
		//begins thread t
		t.start();
	}
	
	//Creates a JFrame that displays the current size of frame and as well as the screen size
	private static class Sizer implements Runnable {
		@Override
		public void run() {
			//initializes testFrame and sets the layout to FlowLayout
			testFrame = new JFrame();
			testFrame.setLayout(new FlowLayout());
			
			//initializes the four JLabels
			JLabel heightLbl = new JLabel();
			JLabel widthLbl = new JLabel();
			JLabel screenHLbl = new JLabel("Screen height: " +Screenfit.getScreenSize().getHeight());
			JLabel screenWLbl = new JLabel("Screen width: " + Screenfit.getScreenSize().getWidth());
			
			//adds all JLabels to testFrame
			testFrame.add(screenHLbl);
			testFrame.add(screenWLbl);
			testFrame.add(heightLbl);
			testFrame.add(widthLbl);
			
			//adds testFrame to the screen
			testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			testFrame.setSize(500,  500);
			testFrame.setVisible(true);
			
			//continually refreshes the value of heightLbl and widthLbl to display the correct width and height of frame
			while(true) {
				heightLbl.setText("Frame Height: " + frame.getHeight());
				widthLbl.setText("Frame Width: " + frame.getWidth());
			}
		}
	}
	
}
