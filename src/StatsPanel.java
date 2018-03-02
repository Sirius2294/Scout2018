import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class StatsPanel extends JPanel {

	private JPanel inPanel;

	private Font font;

	private JButton[] headers;
	private int[] selectionMode;

	private Team[] teamList;

	private JTextField[][] table;

	private int pivotPosition;

	public StatsPanel(ArrayList<Team> list) {
		setLayout(new FlowLayout());
		inPanel.setLayout(new GridLayout(list.size() + 1, 10));

		font = new Font("Sansserif", Font.PLAIN, 15);

		headers = new JButton[10];

		selectionMode = new int[headers.length];

		headers[0] = new JButton("Team #");
		headers[1] = new JButton("Climb Rate");
		headers[2] = new JButton("Auto CrossLine Rate");
		headers[3] = new JButton("Auto Switch Score Rate");
		headers[4] = new JButton("Auto Scale Score Rate");
		headers[5] = new JButton("Robot Functioned Rate");
		headers[6] = new JButton("Exchange Score AVG");
		headers[7] = new JButton("Switch Score AVG");
		headers[8] = new JButton("Scale Score AVG");
		headers[9] = new JButton("Win Rate");

		for (int x = 0; x < headers.length; x++) {
			headers[x].addActionListener(new HeaderListener(x));
		}

		teamList = new Team[list.size()];
		list.toArray(teamList);

		table = new JTextField[teamList.length][10];

		for (int x = 0; x < headers.length; x++) {
			headers[x].setFont(font);
			inPanel.add(headers[x]);
		}

		for (int row = 0; row < table.length; row++) {
			for (int col = 0; col < table[0].length; col++) {
				table[row][col] = new JTextField();
				table[row][col].setFont(font);
				table[row][col].setEditable(false);
				inPanel.add(table[row][col]);
			}
		}
		
		add(new JScrollPane(inPanel));
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
			for (int x = 0; x < teamList.length; x++) {
				teamList[x].setSortingType(index);
			}
			switch (selectionMode[index]) {
			case 0:
				for (int x = 0; x < headers.length; x++) {
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

		if (forward) {
			for (int x = 0; x < table.length; x++) {
				table[x][0].setText(format(teamList[x].getName()));
				table[x][1].setText(format(teamList[x].getClimbRate()) + "%");
				table[x][2].setText(format(teamList[x].getAutoCrossLineRate()) + "%");
				table[x][3].setText(format(teamList[x].getAutoSwitchScoreRate()) + "%");
				table[x][4].setText(format(teamList[x].getAutoScaleScoreRate()) + "%");
				table[x][5].setText(format(teamList[x].getRobotFunctionedRate()) + "%");
				table[x][6].setText(format(teamList[x].getExchangeScoreAvg()));
				table[x][7].setText(format(teamList[x].getSwitchScoreAvg()));
				table[x][8].setText(format(teamList[x].getScaleScoreAvg()));
				table[x][9].setText(format(teamList[x].getWinRate()) + "%");
			}
		} else {
			for (int x = 0; x < table.length; x++) {
				table[x][0].setText(format(teamList[table.length - x - 1].getName()));
				table[x][1].setText(format(teamList[table.length - x - 1].getClimbRate()) + "%");
				table[x][2].setText(format(teamList[table.length - x - 1].getAutoCrossLineRate()) + "%");
				table[x][3].setText(format(teamList[table.length - x - 1].getAutoSwitchScoreRate()) + "%");
				table[x][4].setText(format(teamList[table.length - x - 1].getAutoScaleScoreRate()) + "%");
				table[x][5].setText(format(teamList[table.length - x - 1].getRobotFunctionedRate()) + "%");
				table[x][6].setText(format(teamList[table.length - x - 1].getExchangeScoreAvg()));
				table[x][7].setText(format(teamList[table.length - x - 1].getSwitchScoreAvg()));
				table[x][8].setText(format(teamList[table.length - x - 1].getScaleScoreAvg()));
				table[x][9].setText(format(teamList[table.length - x - 1].getWinRate()) + "%");
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

		if (pivotPosition != low) {
			list = quicksort(list, low, pivotPosition);
		}

		if (high != pivotPosition + 1) {
			list = quicksort(list, pivotPosition + 1, high);
		}
		return list;
	}

	private Team[] partition(Team[] list, int low, int high) {
		Team[] result = new Team[list.length];
		Team pivot = list[low];
		int counter = low;

		for (int x = low + 1; x < high; x++) {
			if (list[x].getSortingData() < pivot.getSortingData()) {
				result[counter] = list[x];
				counter++;
			}
		}

		pivotPosition = counter;
		result[counter] = pivot;
		counter++;

		for (int x = low + 1; x < high; x++) {
			if (list[x].getSortingData() >= pivot.getSortingData()) {
				result[counter] = list[x];
				counter++;
			}
		}

		for (int x = 0; x < list.length; x++) {
			if (x < low || x >= high) {
				result[x] = list[x];
			}
		}

		return result;
	}
}
