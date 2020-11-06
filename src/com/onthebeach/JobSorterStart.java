package com.onthebeach;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class JobSorterStart {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		// h
		
		// g
		
		//[ a, b]
		
		//[ c, b]
				
		//[ e, f]
		
		//get the list of entries and put all the jobs to an array
		
		//create nodes with following jobs
		
		Job x = new Job ("a", new ArrayList<String>(Arrays.asList("b")));
		Job y = new Job ("b",new ArrayList<String>(Arrays.asList("c")));
		Job z = new Job ("c",null);
		Job el = new Job ("r",null);
		
		HashMap<String, Job> allJobs = new HashMap<String, Job>();
		
		allJobs.put(x.getJobId(), x);
		allJobs.put(y.getJobId(), y);
		allJobs.put(z.getJobId(), z);
		allJobs.put(el.getJobId(), el);
		
		//sorted jobs
		ArrayList<String> sortedJobs = new ArrayList<String>();
		//visited jobs	
		ArrayList<String> visitedJobs = new ArrayList<String>();
		
		//
		for (Job jobx: allJobs.values()) {
			vistJob(jobx,sortedJobs, visitedJobs, allJobs );
		}
		
		String asd = "test";
		//

	}
	
	
	public static void vistJob ( Job job, ArrayList<String> sortedJobs, ArrayList<String> visitedJobs, HashMap<String, Job> allJobs ) {
		
		//check if this job is already visited
		if ( visitedJobs.contains(job.getJobId())) {
			return;
		}
		
		visitedJobs.add(job.getJobId());
		
		if (job.getNextJobs() != null) {
			for (String temp:job.getNextJobs()) {
				vistJob(allJobs.get(temp), sortedJobs, visitedJobs, allJobs );
			}
		}
		
		sortedJobs.add(job.getJobId());
	
	}

}
