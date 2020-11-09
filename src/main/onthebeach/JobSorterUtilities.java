package main.onthebeach;

import java.util.HashMap;
import java.util.regex.Pattern;

/**
 * Utilities class to handle the input
 * 
 * @author chamika
 */
public class JobSorterUtilities {

	/**
	 * Create a HashMap of Jobs from the user input
	 * 
	 * @param userInput   A 2 dimensional array 
	 * @return allJobs    A HashMap with all the jobs
	 * @throws Exception  if there is a job dependent on it self
	 */
	public static HashMap<String, Job> createJobsFromInputs(String[][] userInput) throws Exception {

		HashMap<String, Job> allJobs = new HashMap<String, Job>();

		// Iterate user input and create job objects with neighbour jobs
		for (int i = 0; i < userInput.length; i++) {

			if ((userInput[i].length == 2) && userInput[i][0].equals(userInput[i][1])) {
				throw new Exception("Jobs can’t depend on themselves");
			}

			for (int j = 0; j < userInput[i].length; j++) {

				String value = userInput[i][j];

				if (allJobs.containsKey(value) && j == 1) {
					allJobs.get(value).addNextJob(userInput[i][0]);
				}

				if (!allJobs.containsKey(value)) {
					Job tmpJob = new Job(value);
					if (j == 1) {
						tmpJob.addNextJob(userInput[i][0]);
					}
					allJobs.put(value, tmpJob);
				}

			}
		}
		return allJobs;
	}
	
	/**
	 * Validate the User Inputs
	 * 
	 * @param inputs 
	 * @return true if inputs are correct
	 */
	public static boolean validateInput(String[] inputs) {

		if(inputs.length == 0) {
			return false;
		}
		
		for (String input : inputs) {
			input.trim();
			if ((input.length() != 1) || (!Pattern.matches("[a-z]*", input))) {
				return false;
			}
		}

		return true;
	}
	
	/**
	 * Format the User inputs
	 * 
	 * @param inputs
	 * @return Array of formatted inputs
	 */
	public static String[] formatInput(String[] inputs) {
		
		String[] formattedInput = new String[inputs.length];
		
		for (int i=0; i< inputs.length; i++) {
			
			String temp = inputs[i];
			
			temp = temp.trim();
			temp = temp.toLowerCase();
			formattedInput[i] = temp;
		}
		return formattedInput;
	}
}
