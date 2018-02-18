import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.*;

public class InputPanel extends JPanel{
	
	ArrayList<Team> teamList;
	
	Save saveList;
	
	/*
	 * GUI Components
	 */
	Font font;
	
	JLabel nameLbl;
	
	JLabel exchangeScoreLbl;
	JLabel switchScoreLbl;
	JLabel scaleScoreLbl;
	
	
	JTextField nameFld;
	
	JRadioButton climbBtn;
	JRadioButton autoScoreBtn;
	JRadioButton robotFunctionedBtn;
	
	JTextField exchangeScoreFld;
	JTextField switchScoreFld;
	JTextField scaleScoreFld;
	
	JButton submitBtn;
	JButton statsBtn;
	
	
	
	public InputPanel() {
		setLayout(new GridBagLayout());
		
		saveList = new Save();
		
		try {
			teamList = saveList.loadArrayList();
		}catch(IOException e) {
			JOptionPane.showMessageDialog(null, "File \"ScoutingInfo.txt\" cannot be found.");
		}
		
		font = Screenfit.getFont();
		
		/*
		 * Lbls
		 */
		
		nameLbl = new JLabel("Name");
		
		exchangeScoreLbl = new JLabel("Exchange Score");
		switchScoreLbl = new JLabel("Switch Score");
		scaleScoreLbl = new JLabel("Scale Score");
		
		
		nameLbl.setFont(font);
		exchangeScoreLbl.setFont(font);
		switchScoreLbl.setFont(font);
		scaleScoreLbl.setFont(font);
		
		
		/*
		 * Fld and Btns
		 */
		
		nameFld = new JTextField(4);
		
		climbBtn = new JRadioButton("Climb");
		autoScoreBtn = new JRadioButton("Auto Score");
		robotFunctionedBtn = new JRadioButton("Functioned");
		
		exchangeScoreFld = new JTextField(4);
		switchScoreFld = new JTextField(4);
		scaleScoreFld = new JTextField(4);
		
		submitBtn = new JButton("Submit");
		statsBtn = new JButton("Stats");
		
		
		nameLbl.setFont(font);
		climbBtn.setFont(font);
		autoScoreBtn.setFont(font);
		robotFunctionedBtn.setFont(font);
		exchangeScoreFld.setFont(font);
		switchScoreFld.setFont(font);
		scaleScoreFld.setFont(font);
		submitBtn.setFont(font);
		statsBtn.setFont(font);
		
		
		radioButtonListener radioListener = new radioButtonListener();
		climbBtn.addActionListener(radioListener);
		autoScoreBtn.addActionListener(radioListener);
		robotFunctionedBtn.addActionListener(radioListener);
		
		
		robotFunctionedBtn.doClick();
		submitBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int index = 0;
				try {
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
