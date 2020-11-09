package main.onthebeach;

import java.util.HashMap;
import java.util.regex.Pattern;

public class JobSorterUtilities {

	/**
	 * Create a HashMap of Jobs from the user input
	 * 
	 * @param userInput   A 2 dimensional array 
	 * @return allJobs    A HashMap with all the jobs
	 * @throws Exception  if there is a job dependent on it self
	 */
	public static HashMap<String, Job> createJobsFromInputs (String[][] userInput ) throws Exception {
	   
		// Iterate user input and create job objects with neighbour jobs
	
		HashMap<String, Job> allJobs = new HashMap<String, Job>();
		
		for ( int i = 0; i < userInput.length ; i++  ) {
			
			if ((userInput[i].length == 2) && userInput[i][0].equals(userInput[i][1])) {
				throw new Exception("Jobs can’t depend on themselves");
			}
			
			for (int j = 0; j < userInput[i].length ; j++) {
					
				String value = userInput[i][j];
				
				if (allJobs.containsKey(value) && j==1) {
					allJobs.get(value).addNextJob(userInput[i][0]);
				}
				
				if(!allJobs.containsKey(value)) {
					Job tmpJob = new Job (value);
					if(j == 1) {
						tmpJob.addNextJob(userInput[i][0]);
					}
					allJobs.put(value, tmpJob);
				}
				
			}
		}
		return allJobs;		
	}
	
	/**
	 * 
	 * @param inputs
	 * @return
	 */
	public static boolean validateInput(String[] inputs) {

		for (String input : inputs) {
			input.trim();
			if ((input.length() != 1) || (!Pattern.matches("[a-z]*", input))) {
				return false;
			}
		}

		return true;
	}
	
	/**
	 * 
	 * @param inputs
	 * @return
	 */
	public static String[] formatInput(String[] inputs) {
		
		String[] formattedInput = new String[inputs.length];
		
		for (int i=0; i< inputs.length; i++) {
			
			String temp = inputs[i];
			
			temp.trim();
			temp.toLowerCase();
			formattedInput[i] = temp;
		}
		return inputs;
	}
}
