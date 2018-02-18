import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class Scout {
	
	private static JFrame frame;
	private static JFrame testFrame;
	
	public static void main(String[] args) {
		frame = new JFrame("Scout");
		InputPanel inputPanel = new InputPanel();
		
		frame.add(inputPanel);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(Screenfit.mainFrameWidth(), Screenfit.mainFrameHeight());
		frame.setVisible(true);
		
		
		
		
		Sizer s = new Sizer();
		Thread t = new Thread(s);
		t.start();
	}
	
	private static class Sizer implements Runnable {
		@Override
		public void run() {
			testFrame = new JFrame();
			testFrame.setLayout(new FlowLayout());
			JLabel heightLbl = new JLabel();
			JLabel widthLbl = new JLabel();
			JLabel screenHLbl = new JLabel("Screen height: " +Screenfit.getScreenSize().getHeight());
			JLabel screenWLbl = new JLabel("Screen width: " + Screenfit.getScreenSize().getWidth());
			testFrame.add(screenHLbl);
			testFrame.add(screenWLbl);
			testFrame.add(heightLbl);
			testFrame.add(widthLbl);
			testFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			testFrame.setSize(500,  500);
			testFrame.setVisible(true);
			
			while(true) {
				heightLbl.setText("Frame Height: " + frame.getHeight());
				widthLbl.setText("Frame Width: " + frame.getWidth());
			}
		}
	}
	
}
