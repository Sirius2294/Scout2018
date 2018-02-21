import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class StatsPanel extends JPanel {

	private Font font;

	private JButton[] headers;
	private int[] selectionMode;
	
	private Team[] teamList;

	private JTextField[][] table;
	
	private int pivotPosition;

	public StatsPanel(ArrayList<Team> list) {
		setLayout(new GridLayout(list.size()+1, 7));

		font = Screenfit.getFont();
		
		headers = new JButton[7];
		
		selectionMode = new int[7];

		headers[0] = new JButton("Team #");
		headers[1] = new JButton("Climb Rate");
		headers[2] = new JButton("Auto Score Rate");
		headers[3] = new JButton("Robot Functioned Rate");
		headers[4] = new JButton("Exchange Score AVG");
		headers[5] = new JButton("Switch Score AVG");
		headers[6] = new JButton("Scale Score AVG");
		
		for(int x = 0; x < headers.length; x++) {
			headers[x].addActionListener(new HeaderListener(x));
		}
		
		
		teamList = new Team[list.size()];
		list.toArray(teamList);

		table = new JTextField[teamList.length][7];

		for (int x = 0; x < headers.length; x++) {
			headers[x].setFont(font);
			add(headers[x]);
		}

		for (int row = 0; row < table.length; row++) {
			for (int col = 0; col < table[0].length; col++) {
				table[row][col] = new JTextField();
				table[row][col].setFont(font);
				table[row][col].setEditable(false);
				add(table[row][col]);
			}
		}
		headers[0].doClick();
	}

	private class HeaderListener implements ActionListener {
		private int index;
		private Color defaultBackground;

		public HeaderListener(int buttonIndex) {
			index = buttonIndex;
			selectionMode[index] = 0;
			defaultBackground = headers[0].getBackground();
		}

		@Override
		public void actionPerformed(ActionEvent event) {
			for(int x = 0; x < teamList.length; x++) {
				teamList[x].setSortingType(index);
			}
			switch(selectionMode[index]) {
			case 0:
				for(int x = 0; x < headers.length; x++) {
					headers[x].setBackground(defaultBackground);
					selectionMode[x] = 0;
				}
				selectionMode[index] = 1;
				headers[index].setBackground(new Color(0, 200, 0));
				arrangeTable(true);
				break;
			case 1:
				selectionMode[index] = 2;
				headers[index].setBackground(new Color(200, 0, 0));
				arrangeTable(false);
				break;
			case 2:
				selectionMode[index] = 1;
				headers[index].setBackground(new Color(0, 200, 0));
				arrangeTable(true);
				break;
			}
			
		}
	}
	
	/*
	 * Sorting Table
	 */
	
	private void arrangeTable(boolean forward) {
		teamList = quicksort(teamList, 0, teamList.length);
		
		if(forward) {
			for(int x = 0; x < table.length; x++) {
				table[x][0].setText(format(teamList[x].getName()));
				table[x][1].setText(format(teamList[x].getClimbRate()) + "%");
				table[x][2].setText(format(teamList[x].getAutoScoreRate()) + "%");
				table[x][3].setText(format(teamList[x].getRobotFunctionedRate()) + "%");
				table[x][4].setText(format(teamList[x].getExchangeScoreAvg()));
				table[x][5].setText(format(teamList[x].getSwitchScoreAvg()));
				table[x][6].setText(format(teamList[x].getScaleScoreAvg()));
			}
		}
		else {
			for(int x = 0; x < table.length; x++) {
				table[x][0].setText(format(teamList[table.length - x - 1].getName()));
				table[x][1].setText(format(teamList[table.length - x - 1].getClimbRate()) + "%");
				table[x][2].setText(format(teamList[table.length - x - 1].getAutoScoreRate()) + "%");
				table[x][3].setText(format(teamList[table.length - x - 1].getRobotFunctionedRate()) + "%");
				table[x][4].setText(format(teamList[table.length - x - 1].getExchangeScoreAvg()));
				table[x][5].setText(format(teamList[table.length - x - 1].getSwitchScoreAvg()));
				table[x][6].setText(format(teamList[table.length - x - 1].getScaleScoreAvg()));
			}
		}
	}
	
	private <T> String format(T n) {
		return "" + n;
	}
	
	
	/*
	 * Sorting Algorithm
	 */
	
	private Team[] quicksort(Team[] list, int low, int high) {
		
		list = partition(list, low, high);
		
		if(pivotPosition != low) {
			list = quicksort(list, low, pivotPosition);
		}
		
		if(high != pivotPosition+1) {
			list = quicksort(list, pivotPosition+1, high);
		}
		return list;
	}
	
	private Team[] partition(Team[] list, int low, int high) {
		Team[] result = new Team[list.length];
		Team pivot = list[low];
		int counter = low;
		
		for(int x = low+1; x < high; x++) {
			if(list[x].getSortingData() < pivot.getSortingData()) {
				result[counter] = list[x];
				counter++;
			}
		}
		
		pivotPosition = counter;
		result[counter] = pivot;
		counter++;
		
		for(int x = low+1; x < high; x++) {
			if(list[x].getSortingData() >= pivot.getSortingData()) {
				result[counter] = list[x];
				counter++;
			}
		}
		
		for(int x = 0; x < list.length; x++) {
			if(x < low || x >= high) {
				result[x] = list[x];
			}
		}
		
		return result;
	}
}
