package com.doge.planning;

import java.util.List;

import com.doge.planning.Plan;

/**
 * The main class wrapper for the project
 * @author salmelu
 * @author petrmanek
 */
public class Planner {
	
	/**
	 * Returns the most optimal plan with the lowest total overlap
	 * @param courses Courses requested in the plan
	 * @return The most optimal plan
	 */
	public static Plan getOptimalPlan(List<Course> courses) {
		// TODO implement the algorithm
		return null;
	}
	
	/**
	 * Method calculating overlap among the two classes
	 * @param c1 First class
	 * @param c2 Second class
	 * @return Overlap in seconds
	 */
	public static int getOverlap(Class c1, Class c2) {
		// TODO make the method
		return 0;
	}
	
    public static void main(String[] args) {
        System.out.println("such plan. wow. very make. such progress. much soon.");
    }
}
