package com.onthebeach;

import java.util.ArrayList;

public class Job {

	private String jobId;
	
	private String dependencyId;
	
	private ArrayList<String> nextJobs =  new ArrayList<String>(); 

	
	public Job (String jobId, ArrayList nextJobs ) {
		this.nextJobs = nextJobs;
		this.jobId = jobId;
	}
	
	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getDependencyId() {
		return dependencyId;
	}

	public void setDependencyId(String dependencyId) {
		this.dependencyId = dependencyId;
	}

	public ArrayList<String> getNextJobs() {
		return nextJobs;
	}

	public void setNextJobs(ArrayList<String> nextJobs) {
		this.nextJobs = nextJobs;
	}
	
	public void addNextJob (String nextJob) {
		this.nextJobs.add(nextJob);
	}
	
	
}
