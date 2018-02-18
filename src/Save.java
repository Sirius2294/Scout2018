import java.util.ArrayList;

import javax.swing.JOptionPane;

import java.util.*;
import java.io.*;

public class Save {
	File saveFile;
	
	Writer writer;
	BufferedWriter pen;
	Scanner book;
	
	public Save() {
		saveFile = new File("ScoutingInfo.txt");
	}
	
	//saves the contents of the ArrayList to the txt file
	public void saveTextFile(ArrayList<Team> list) throws IOException {
		int length = list.size();
		writer = new FileWriter(saveFile);
		pen = new BufferedWriter(writer);
		
		for(int x = 0; x < length; x++) { //Loops through each team in the ArrayList
			/*
			 * Print out (neatly) all of the info for the team, including the match number
			 */
			pen.write(format(list.get(x).getName()) + " ");
			pen.write(format(list.get(x).getRound()));
			
			for(int q = 0; q < list.get(x).getRound(); q++) {
				pen.newLine();
				//pen.write(format(q) + " ");
				pen.write(format(list.get(x).getClimb(q) ? 1 : 0) + " ");
				pen.write(format(list.get(x).getAutoScore(q) ? 1 : 0) + " ");
				pen.write(format(list.get(x).getRobotFunctioned(q) ? 1 : 0) + " ");
				pen.write(format(list.get(x).getExchangeScore(q)) + " ");
				pen.write(format(list.get(x).getSwitchScore(q)) + " ");
				pen.write(format(list.get(x).getScaleScore(q)));
			}
			pen.newLine();
			pen.newLine();
	}
		pen.close();
	}
		
	
	//returns an ArrayList containing the contents of the file
	public ArrayList<Team> loadArrayList() throws IOException {
		/*
		 * Read the contents of the text file and parse the information about each team
		 */
		ArrayList<Team> teamList = new ArrayList<>();
		book = new Scanner(saveFile);
		
		int numOfRounds;
		
		while(book.hasNext()) {
			Team t = new Team(Integer.parseInt(book.next()));
			numOfRounds = Integer.parseInt(book.next());
			
			for(int x = 0; x < numOfRounds; x++) {
				t.addClimb(book.next().equals("1"));
				t.addAutoScore(book.next().equals("1"));
				t.addRobotFunctioned(book.next().equals("1"));
				
				t.addExchangeScore(Integer.parseInt(book.next()));
				t.addSwitchScore(Integer.parseInt(book.next()));
				t.addScaleScore(Integer.parseInt(book.next()));
				t.addRound();
			}
			
			teamList.add(t);
		}
		
		book.close();
		return teamList;
	}
	
	private String format(int num) {
		return String.format("%d", num);
	}
}
