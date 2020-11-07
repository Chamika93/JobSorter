package main.onthebeach;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
		String[][] processedUserInput = {{"a"},{"b","c"},{"c"}};

		HashMap<String, Job> allJobs = createJobsFromInputs(processedUserInput);
		ArrayList<String> sortedJobs = jobHandler(allJobs);
		
	}
	
	
	public static HashMap<String, Job> createJobsFromInputs (String[][] userInput ) {
	   
		// Iterate user input and create job objects with neighbour jobs
		
		Job x = new Job ("a", new ArrayList<String>(Arrays.asList("b")));
		Job y = new Job ("b",new ArrayList<String>(Arrays.asList("c")));
		Job z = new Job ("c",null);
		Job el = new Job ("r",null);
		
		
		// Create the allJobs hashmap
		
		HashMap<String, Job> allJobs = new HashMap<String, Job>();
		
		allJobs.put(x.getJobId(), x);
		allJobs.put(y.getJobId(), y);
		allJobs.put(z.getJobId(), z);
		allJobs.put(el.getJobId(), el);
		
		return allJobs;
	}
	
	
	/**
	 * Finds the Sorted Job order given a HashMap of jobs 
	 * 
	 * @param allJobs HashMap with all the jobs
	 * @return Sorted Jobs Arraylist
	 */
	public static ArrayList<String> jobHandler(HashMap<String, Job> allJobs) {
		
		// sorted jobs
		ArrayList<String> sortedJobs = new ArrayList<String>();

		// visited jobs
		ArrayList<String> visitedJobs = new ArrayList<String>();

		// Visit all the jobs recursively according to the topological sort
		for (Job jobx : allJobs.values()) {
			vistJob(jobx, sortedJobs, visitedJobs, allJobs);
		}

		return sortedJobs;
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
	public static void vistJob ( Job job, ArrayList<String> sortedJobs, ArrayList<String> visitedJobs, HashMap<String, Job> allJobs ) {
		
		// Check if this job is already visited
		if (visitedJobs.contains(job.getJobId())) {
			return;
		}
		
		// Need to identify circular dependencies and throw error
		
		visitedJobs.add(job.getJobId());
		
		if (job.getNextJobs() != null) {
			for (String temp:job.getNextJobs()) {
				vistJob(allJobs.get(temp), sortedJobs, visitedJobs, allJobs );
			}
		}
		
		sortedJobs.add(job.getJobId());
	
	}
	
	public static int getLucky() {
        return 7;
    }

}
