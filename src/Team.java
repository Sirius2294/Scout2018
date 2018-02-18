
public class Team {
	private int name;
	
	private int round;
	public static final int numOfRounds = 4;//Change this when we get to the competition
	
	private boolean[] climb;
	private boolean[] autoScore;
	private boolean[] robotFunctioned;
	
	private int[] exchangeScore;
	private int[] switchScore;
	private int[] scaleScore;
	
	private int sortingType = 0;
	
	public Team(int teamNumber) {
		name = teamNumber;
		
		round = 0;
		
		climb = new boolean[numOfRounds];
		autoScore = new boolean[numOfRounds];
		robotFunctioned = new boolean[numOfRounds];
		
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
			return getClimbRate();
		case 2:
			return getAutoScoreRate();
		case 3:
			return getRobotFunctionedRate();
		case 4:
			return getExchangeScoreAvg();
		case 5:
			return getSwitchScoreAvg();
		case 6:
			return getScaleScoreAvg();
		default:
			return (double) name;
		}
	}
	
	
	/*
	 * "add" methods
	 */
	
	public void addClimb(boolean didClimb) {
		climb[round] = didClimb;
	}
	
	public void addAutoScore(boolean didScore) {
		autoScore[round] = didScore;
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
	
	public void addRound() {
		round++;
	}
	
	
	/*
	 * "get" methods
	 */
	
	public int getName() {
		return name;
	}
	
	public boolean getClimb(int round) {
		return climb[round];
	}
	
	public boolean getAutoScore(int round) {
		return autoScore[round];
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
	
	public int getRound() {
		return round;
	}
	
	/*
	 * Data "gets"
	 */
	
	public double getClimbRate() {
		double rate = 0;
		for(int x = 0; x < round; x++) {
			if(climb[x]) {
				rate++;
			}
		}
		return rate / round * 100;
	}
	
	public double getAutoScoreRate() {
		double rate = 0;
		for(int x = 0; x < round; x++) {
			if(autoScore[x]) {
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
}
