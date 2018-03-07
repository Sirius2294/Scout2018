
public class Team {
	private int name;
	
	private int round;
	public static final int numOfRounds = 10;//Change this when we get to the competition
	
	private int[] climb;
	private boolean[] autoCrossLine;
	private boolean[] autoSwitchScore;
	private boolean[] autoScaleScore;
	private boolean[] robotFunctioned;
	private boolean[] win;
	
	private int[] exchangeScore;
	private int[] switchScore;
	private int[] scaleScore;
	
	private int sortingType = 0;
	
	public Team(int teamNumber) {
		name = teamNumber;
		
		round = 0;
		
		climb = new int[numOfRounds];
		autoCrossLine = new boolean[numOfRounds];
		autoSwitchScore = new boolean[numOfRounds];
		autoScaleScore = new boolean[numOfRounds];
		robotFunctioned = new boolean[numOfRounds];
		win = new boolean[numOfRounds];
		
		exchangeScore = new int[numOfRounds];
		switchScore = new int[numOfRounds];
		scaleScore = new int[numOfRounds];
	}
	
	/*
	 * Sorting methods
	 */
	
	public void setSortingType(int type) {
		sortingType = type;
	}
	
	public double getSortingData() {
		switch(sortingType) {
		case 0:
			return (double) name;
		case 1:
			return getClimbAvg();
		case 2:
			return getAutoCrossLineRate();
		case 3:
			return getAutoSwitchScoreRate();
		case 4:
			return getAutoScaleScoreRate();
		case 5:
			return getRobotFunctionedRate();
		case 6:
			return getExchangeScoreAvg();
		case 7:
			return getSwitchScoreAvg();
		case 8:
			return getScaleScoreAvg();
		case 9:
			return getWinRate();
		default:
			return (double) name;
		}
	}
	
	
	/*
	 * "add" methods
	 */
	
	public void addClimb(int numOfClimbs) {
		climb[round] = numOfClimbs;
	}
	
	public void addAutoCrossLine(boolean didScore) {
		autoCrossLine[round] = didScore;
	}
	
	public void addAutoSwitchScore(boolean didScore) {
		autoSwitchScore[round] = didScore;
	}
	
	public void addAutoScaleScore(boolean didScore) {
		autoScaleScore[round] = didScore;
	}
	
	public void addRobotFunctioned(boolean didFunction) {
		robotFunctioned[round] = didFunction;
	}
	
	public void addExchangeScore(int numOfBoxes) {
		exchangeScore[round] = numOfBoxes;
	}
	
	public void addSwitchScore(int numOfBoxes) {
		switchScore[round] = numOfBoxes;
	}
	
	public void addScaleScore(int numOfBoxes) {
		scaleScore[round] = numOfBoxes;
	}
	
	public void addWin(boolean didWin) {
		win[round] = didWin;
	}
	
	public void addRound() {
		round++;
	}
	
	
	/*
	 * "get" methods
	 */
	
	public int getName() {
		return name;
	}
	
	public int getClimb(int round) {
		return climb[round];
	}
	
	public boolean getAutoCrossLine(int round) {
		return autoCrossLine[round];
	}
	
	public boolean getAutoSwitchScore(int round) {
		return autoSwitchScore[round];
	}
	
	public boolean getAutoScaleScore(int round) {
		return autoScaleScore[round];
	}
	
	public boolean getRobotFunctioned(int round) {
		return robotFunctioned[round];
	}
	
	public int getExchangeScore(int round) {
		return exchangeScore[round];
	}
	
	public int getSwitchScore(int round) {
		return switchScore[round];
	}
	
	public int getScaleScore(int round) {
		return scaleScore[round];
	}
	
	public boolean getWin(int round) {
		return win[round];
	}
	
	public int getRound() {
		return round;
	}
	
	/*
	 * Data "gets"
	 */
	
	public double getClimbAvg() {
		double avg = 0;
		for(int x = 0; x < round; x++) {
			avg += climb[x];
		}
		return avg / round;
	}
	
	public double getAutoCrossLineRate() {
		double rate = 0;
		for(int x = 0; x < round; x++) {
			if(autoCrossLine[x]) {
				rate++;
			}
		}
		return rate/ round * 100;
	}
	
	public double getAutoSwitchScoreRate() {
		double rate = 0;
		for(int x = 0; x < round; x++) {
			if(autoSwitchScore[x]) {
				rate++;
			}
		}
		return rate / round * 100;
	}
	
	public double getAutoScaleScoreRate() {
		double rate = 0;
		for(int x = 0; x < round; x++) {
			if(autoScaleScore[x]) {
				rate++;
			}
		}
		return rate / round * 100;
	}
	
	public double getRobotFunctionedRate() {
		double rate = 0;
		for(int x = 0; x < round; x++) {
			if(robotFunctioned[x]) {
				rate++;
			}
		}
		return rate / round * 100;
	}
	
	public double getExchangeScoreAvg() {
		double avg = 0;
		for(int x = 0; x < round; x++) {
			avg += exchangeScore[x];
		}
		return avg / round;
	}
	
	public double getSwitchScoreAvg() {
		double avg = 0;
		for(int x = 0; x < round; x++) {
			avg += switchScore[x];
		}
		return avg / round;
	}
	
	public double getScaleScoreAvg() {
		double avg = 0;
		for(int x = 0; x < round; x++) {
			avg += scaleScore[x];
		}
		return avg / round;
	}
	
	public double getWinRate() {
		double rate = 0;
		for(int x = 0; x < round; x++) {
			if(win[x])
				rate++;
		}
		return rate / round * 100;
	}
}
