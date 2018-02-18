import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class Screenfit {
	private static Toolkit tk;
	
	public Screenfit() {
		tk = Toolkit.getDefaultToolkit();
	}
	
	public static Dimension getScreenSize() {
		tk = Toolkit.getDefaultToolkit();
		return tk.getScreenSize();
	}
	
	public static Font getFont() {
		Font f = new Font("Sansserif", Font.PLAIN, (int) getScreenSize().getHeight() / 54);
		return f;
	}
	
	public static int mainFrameWidth() {
		return 50 *((int) getScreenSize().getHeight() / 54);
	}
	
	public static int mainFrameHeight () {
		return 6 * ((int) getScreenSize().getHeight() / 54);
	}
	
}
