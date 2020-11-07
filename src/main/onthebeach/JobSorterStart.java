package main.onthebeach;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 *  Order a sequence of jobs with the dependencies
 * 
 * @author chamika
 *
 */
public class JobSorterStart {

	public static void main(String[] args) {

		// Get the user input 
		/**
		 * In this format
		 * 
		 * a =>
		 * b => c
		 * c =>
		 */
		
		
		// Create an 2 dimensional array
		/**
		 * { {a} , {b,c}, {c} }
		 * 
		 */
		String[][] processedUserInput = { { "a" }, { "b", "c" }, { "c" } };

		try {
			String sortedJobs = jobHandler(processedUserInput);
		} catch (Exception e) {
			System.out.println("Error Occured "+e.getMessage());
		}
		
	}
		
	/**
	 * Finds the Sorted Job order given a HashMap of jobs 
	 * 
	 * @param allJobs HashMap with all the jobs
	 * @return Sorted Jobs Arraylist
	 */
	public static String jobHandler(String[][] userInput) throws Exception {
		
		
		HashMap<String, Job> allJobs = createJobsFromInputs(userInput);
		
		// sorted jobs
		ArrayList<String> sortedJobs = new ArrayList<String>();

		// visited jobs
		ArrayList<String> visitedJobs = new ArrayList<String>();
		
		// uniquely visited jobs ( to identify cyclic dependencies )
		ArrayList<String> uniquelyVisitedJobs = new ArrayList<String>();

		// Visit all the jobs recursively according to the topological sort
		for (Job jobx : allJobs.values()) {
			uniquelyVisitedJobs.clear();
			vistJob(jobx, sortedJobs, visitedJobs, uniquelyVisitedJobs, allJobs);
		}

		Collections.reverse(sortedJobs);
		
		String JobOrder = "";
		
		for (String tmp : sortedJobs) {
			JobOrder += tmp;
		}
		
		return JobOrder;
	}
	
	/**
	 * 
	 * 
	 * 
	 * @param userInput
	 * @return
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
	 * Visit a job and visit the neighbour jobs recursively  
	 * Return if it is a already visited job 
	 * If there are no neighbour nodes or all the nodes are visited add the node to sorted jobs
	 * 
	 * @param job            A job with neighbour jobs
	 * @param sortedJobs     Sorted Jobs
	 * @param visitedJobs    Already Visited Jobs
	 * @param allJobs        Hash map of all the jobs
	 */
	public static void vistJob ( Job job, ArrayList<String> sortedJobs, ArrayList<String> visitedJobs, ArrayList<String> uniquelyVisitedJobs, HashMap<String, Job> allJobs ) throws Exception {
		
		if (uniquelyVisitedJobs.contains(job.getJobId())) {
			throw new Exception("Cyclic dependency");
		} else {
			uniquelyVisitedJobs.add(job.getJobId());
		}
		
		// Check if this job is already visited
		if (visitedJobs.contains(job.getJobId())) {
			return;
		}
		
		// Need to identify circular dependencies and throw error
		
		visitedJobs.add(job.getJobId());
		
		if (job.getNextJobs() != null) {
			for (String temp:job.getNextJobs()) {
				vistJob(allJobs.get(temp), sortedJobs, visitedJobs, uniquelyVisitedJobs, allJobs );
			}
		}
		
		sortedJobs.add(job.getJobId());
	
	}

}
