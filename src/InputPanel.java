import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

public class InputPanel extends JPanel{
	
	ArrayList<Team> teamList;//ArrayList<Team> holding all the team objects
	
	Save saveList;//Save object used for saving teamList to a txt file
	
	/*
	 * GUI Components
	 */
	Font font;//Font object to be assigned to all components
	
	/*
	 * Headers
	 */
	JLabel nameLbl;
	
	JLabel exchangeScoreLbl;
	JLabel switchScoreLbl;
	JLabel scaleScoreLbl;
	
	/*
	 * Fields and Buttons to input team data
	 */
	JTextField nameFld;
	
	JRadioButton climbBtn;
	JRadioButton autoCrossLineBtn;
	JRadioButton autoSwitchScoreBtn;
	JRadioButton autoScaleScoreBtn;
	JRadioButton robotFunctionedBtn;
	JRadioButton winBtn;
	
	JTextField exchangeScoreFld;
	JTextField switchScoreFld;
	JTextField scaleScoreFld;
	
	/*
	 * Buttons to save or show the data
	 */
	JButton submitBtn;
	JButton statsBtn;
	
	
	
	public InputPanel() {
		//sets the layout to GridBagLayout
		setLayout(new GridBagLayout());
		
		//initializes the saveList
		saveList = new Save();
		
		//Initializes teamList by reading in the data from ScoutingInfo.txt
		try {
			teamList = saveList.loadArrayList();
		}catch(IOException e) {
			JOptionPane.showMessageDialog(null, "File \"ScoutingInfo.txt\" cannot be found.");
		}
		
		//Initializes font to a specific size based on the size of the screen
		font = Screenfit.getFont();
		
		//Initializes all the JLabel headers
		nameLbl = new JLabel("Name");
		
		exchangeScoreLbl = new JLabel("Exchange Score");
		switchScoreLbl = new JLabel("Switch Score");
		scaleScoreLbl = new JLabel("Scale Score");
		
		//Sets the font of all JLabel headers to font
		nameLbl.setFont(font);
		exchangeScoreLbl.setFont(font);
		switchScoreLbl.setFont(font);
		scaleScoreLbl.setFont(font);
		
		
		//Initializes all the Fields and Buttons for team data input
		nameFld = new JTextField(4);
		
		climbBtn = new JRadioButton("Climb");
		autoCrossLineBtn = new JRadioButton("Auto Cross Line");
		autoSwitchScoreBtn = new JRadioButton("Auto Switch Score");
		autoScaleScoreBtn = new JRadioButton("Auto Scale Score");
		robotFunctionedBtn = new JRadioButton("Functioned");
		winBtn = new JRadioButton("Win");
		
		exchangeScoreFld = new JTextField(4);
		switchScoreFld = new JTextField(4);
		scaleScoreFld = new JTextField(4);
		
		submitBtn = new JButton("Submit");
		statsBtn = new JButton("Stats");
		
		//sets the font of all Fields and Buttons for team data input to font
		nameLbl.setFont(font);
		climbBtn.setFont(font);
		autoCrossLineBtn.setFont(font);
		autoSwitchScoreBtn.setFont(font);
		autoScaleScoreBtn.setFont(font);
		robotFunctionedBtn.setFont(font);
		winBtn.setFont(font);
		exchangeScoreFld.setFont(font);
		switchScoreFld.setFont(font);
		scaleScoreFld.setFont(font);
		submitBtn.setFont(font);
		statsBtn.setFont(font);
		
		
		//declares and initializes radioListener
		radioButtonListener radioListener = new radioButtonListener();
		//adds radioListener to all JRadioButton objects
		climbBtn.addActionListener(radioListener);
		autoCrossLineBtn.addActionListener(radioListener);
		autoSwitchScoreBtn.addActionListener(radioListener);
		autoScaleScoreBtn.addActionListener(radioListener);
		robotFunctionedBtn.addActionListener(radioListener);
		winBtn.addActionListener(radioListener);
		
		//simulates a click on the robotFunctionedBtn JRadioButton
		robotFunctionedBtn.doClick();
		
		//adds a new ActionListener to submitBtn
		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int index = 0;//initializes index to 0
				try {
					//sets index to the index in teamList of the element with the same name as the contents of nameFld or -1 if no element has the same name as the contents of nameFld.
					index = getTeamIndex(Integer.parseInt(nameFld.getText()));
					if(index >= 0) {
						if(robotFunctionedBtn.isSelected()) {
							teamList.get(index).addClimb(climbBtn.isSelected());
							teamList.get(index).addAutoScore(autoScoreBtn.isSelected());
							teamList.get(index).addRobotFunctioned(robotFunctionedBtn.isSelected());
							teamList.get(index).addExchangeScore(Integer.parseInt(exchangeScoreFld.getText()));
							teamList.get(index).addSwitchScore(Integer.parseInt(switchScoreFld.getText()));
							teamList.get(index).addScaleScore(Integer.parseInt(scaleScoreFld.getText()));
						}
						else {
							teamList.get(index).addClimb(false);
							teamList.get(index).addAutoScore(false);
							teamList.get(index).addRobotFunctioned(false);
							teamList.get(index).addExchangeScore(0);
							teamList.get(index).addSwitchScore(0);
							teamList.get(index).addScaleScore(0);
						}
						teamList.get(index).addRound();
						saveList.saveTextFile(teamList);
						resetComponents();
					}
					else {
						teamList.add(new Team(Integer.parseInt(nameFld.getText())));
						actionPerformed(event);
					}
					
						
				}catch(NumberFormatException e) {
					if(e.getMessage().equals("For Input String: \"\"")) {
						JOptionPane.showMessageDialog(null, "One or more of the TextFields are empty.");
						teamList.remove(teamList.get(index));
					}
					else {
						JOptionPane.showMessageDialog(null, "One or more of the TextFields contain non-numbers.");
						teamList.remove(teamList.get(index));
					}
				}
				catch(IOException e) {
					JOptionPane.showMessageDialog(null, "File \"ScoutingInfo.txt\" cannot be found.");;
				}
			}
		});
		
		statsBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				JFrame frame = new JFrame();
				StatsPanel sp = new StatsPanel(teamList);
				frame.add(sp);
				frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				frame.setSize(Screenfit.getScreenSize().width / 2, Screenfit.getScreenSize().height / 2);
				frame.setVisible(true);
			}
		});
		
		/*
		 * Adding the components
		 */
		
		add(nameLbl, getLabelConstraints(0));
		add(exchangeScoreLbl, getLabelConstraints(3));
		add(switchScoreLbl, getLabelConstraints(4));
		add(scaleScoreLbl, getLabelConstraints(5));
		
		add(nameFld, getComponentConstraints(0));
		add(climbBtn, getComponentConstraints(6));
		add(autoScoreBtn, getComponentConstraints(1));
		add(robotFunctionedBtn, getComponentConstraints(2));
		add(exchangeScoreFld, getComponentConstraints(3));
		add(switchScoreFld, getComponentConstraints(4));
		add(scaleScoreFld, getComponentConstraints(5));
		
		add(submitBtn, getComponentConstraints(7));
		add(statsBtn, getLabelConstraints(7));
	}
	
	private int getTeamIndex(int name) {
		for(int x = 0; x < teamList.size(); x++) {
			if(name == teamList.get(x).getName()) {
				return x;
			}
		}
		return -1;
	}
	
	private void resetComponents() {
		nameFld.setText("");
		
		climbBtn.setSelected(false);
		autoScoreBtn.setSelected(false);
		robotFunctionedBtn.setSelected(true);
		robotFunctionedBtn.setForeground(new Color(0 ,200, 0));
		
		exchangeScoreFld.setText("");
		switchScoreFld.setText("");
		scaleScoreFld.setText("");
	}
	
	
	private class radioButtonListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			JRadioButton button = (JRadioButton) event.getSource();
			if(button.isSelected())
				button.setForeground(new Color(0, 200, 0));
			else
				button.setForeground(Color.BLACK);
		}
	}
	
	
	/*
	 * GUI Constraint Methods
	 */
	
	private GridBagConstraints getLabelConstraints(int column) {
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = column;
		c.gridy = 0;
		
		c.gridwidth = 1;
		c.gridheight = 1;
		
		c.anchor = GridBagConstraints.CENTER;
		
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		return c;
	}
	
	private GridBagConstraints getComponentConstraints(int column) {
		GridBagConstraints c = new GridBagConstraints();
		
		c.gridx = column;
		c.gridy = 1;
		
		c.gridwidth = 1;
		c.gridheight = 1;
		
		c.anchor = GridBagConstraints.CENTER;
		
		c.weightx = 1.0;
		c.weighty = 1.0;
		
		return c;
	}
}
