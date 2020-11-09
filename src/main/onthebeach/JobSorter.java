package main.onthebeach;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;


/**
 *  Order a sequence of jobs with the dependencies
 * 
 * @author chamika
 *
 */
public class JobSorter {

	public static void main(String[] args) {

		// Get the user input 
		System.out.println(" Please enter the jobs you want to sort below line by line. "
				+ "If there is a job that needs to be completed before the specifc job denote it after a =>"
				+ ". \n For an example if 'job a' needs to be completed before 'job b' add it as 'b=>a'");
				
		try (Scanner reader = new Scanner(System.in)) {
						
			// Iterate to get all the user inputs
			String inputString = null;
			// Create an 2 dimensional array
			ArrayList<String[]> userInput = new ArrayList<String[]>();
			
			do { 
				
				if (inputString != null) {
					String[] jobsArray = inputString.split("=>");
					
					jobsArray = JobSorterUtilities.formatInput(jobsArray);
					
					if(JobSorterUtilities.validateInput(jobsArray)) {
						userInput.add(jobsArray);
					}
					else {
						System.out.println("Your Input was incorrect please add jobs in 'a' or 'a=>b' format");	
					}
					
				}
				System.out.println(" \nPlease enter the next job or type exit if you are done adding the jobs");
				inputString = reader.nextLine();
				inputString.trim();
				
			} while (!inputString.equals("exit"));
			
			String[][] processedUserInput = userInput.toArray(new String[0][0]);
			
			// Convert the input to a processed input array
			String sortedJobs = jobHandler(processedUserInput);
			System.out.println("Sorted Job Order is :"+sortedJobs);
			
		} catch (Exception e) {
			System.out.println("Error Occured "+e.getMessage());
		}
		
	}
		
	/**
	 * Finds the Sorted Job order given the user input as a 2 dimensional array 
	 * 
	 * @param userInput HashMap with all the jobs
	 * @return JobOrder Sorted Job Order
	 * @throws Exception
	 */
	public static String jobHandler(String[][] userInput) throws Exception {
		
		
		HashMap<String, Job> allJobs = JobSorterUtilities.createJobsFromInputs(userInput);
		
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
	 * Visit a job and visit the neighbour jobs recursively  
	 * Return if it is a already visited job 
	 * If there are no neighbour nodes or all the nodes are visited add the node to sorted jobs
	 * 
	 * @param job            		A job with neighbour jobs
	 * @param sortedJobs     		Sorted Jobs
	 * @param visitedJobs    		Already Visited Jobs
	 * @param uniquelyVisitedJobs   Uniquely Visited Jobs to identify circular dependencies
	 * @param allJobs        		Hash map of all the jobs
	 * @throws Exception     		if there is a cyclic dependency
	 */
	public static void vistJob ( Job job, ArrayList<String> sortedJobs, ArrayList<String> visitedJobs, ArrayList<String> uniquelyVisitedJobs, HashMap<String, Job> allJobs ) throws Exception {
		
		// Check for Cyclic dependencies
		if (uniquelyVisitedJobs.contains(job.getJobId())) {
			throw new Exception("Cyclic dependency");
		} else {
			uniquelyVisitedJobs.add(job.getJobId());
		}
		
		// Check if this job is already visited
		if (visitedJobs.contains(job.getJobId())) {
			return;
		}
		
		visitedJobs.add(job.getJobId());
		
		if (job.getNextJobs() != null) {
			for (String temp:job.getNextJobs()) {
				vistJob(allJobs.get(temp), sortedJobs, visitedJobs, uniquelyVisitedJobs, allJobs );
			}
		}
		
		sortedJobs.add(job.getJobId());
	
	}
}
