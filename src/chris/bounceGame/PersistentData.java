package chris.bounceGame;

import android.content.SharedPreferences;

public class PersistentData {
	
	public boolean isVolumeOn;
	public int[][] levelScores;
	public boolean devMode;

	private SharedPreferences persistentData;
	
	public PersistentData(SharedPreferences persistentData) {
		this.persistentData = persistentData;
		read();		
	}
	
	public void read() {
		isVolumeOn = persistentData.getBoolean("isVolumeOn", true);
		devMode = persistentData.getBoolean("devMode", false);
		String defaultLevelScores = "0 0 0 0 0 0;0 0 0 0 0 0;0 0 0 0 0 0;0 0 0 0 0 0";
		String levelScoresPacked = persistentData.getString("levelScoresPacked", defaultLevelScores);
		levelScores = unpack(levelScoresPacked);		
	}
	
	public void write() {
		SharedPreferences.Editor editor = persistentData.edit();
		
		editor.putBoolean("isVolumeOn", isVolumeOn);
		editor.putBoolean("devMode", devMode);
		
		//pack the level data
		String packedString = "";
		
		for(int[] world : levelScores) {
			for(int score : world)
				packedString += String.valueOf(score) + " ";
			//this line replaces the last space with a semicolon
			packedString = packedString.substring(0, packedString.length() - 1) + ";";
		}
		//this line gets rid of the last line
		packedString = packedString.substring(0, packedString.length() - 1);
		
		editor.putString("levelScoresPacked", packedString);
		
		editor.commit();
	}
	
	private int[][] unpack(String packed) {
		int [][] result;
		
		String[][] splitString = new String[packed.split(";").length][];
		for(int i = 0; i < packed.split(";").length; i++) {
			splitString[i] = packed.split(";")[i].split(" ");
		}
		
		result = new int[splitString.length][];
		for(int worldIndex = 0; worldIndex < splitString.length; worldIndex++) {			
			result[worldIndex] = new int[splitString[worldIndex].length];
			
			for(int levelIndex = 0; levelIndex < splitString[worldIndex].length; levelIndex++) {
				result[worldIndex][levelIndex] = Integer.parseInt(splitString[worldIndex][levelIndex]);
			}
		}
		return result;
	}
	
	public int[][] testUnpack(String packed) {
		return unpack(packed);
	}


}
