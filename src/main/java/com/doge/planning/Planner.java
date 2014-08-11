package com.doge.planning;

import java.io.File;
import java.util.List;

/**
 * The main class wrapper for the project
 * @author salmelu
 * @author petrmanek
 */
public class Planner {
	
	/**
	 * Holder for our courses
	 */
	private static List<Course> m_courses;
	private static Plan m_plan;
	
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
	 * @return Overlap in minutes
	 */
	public static int getOverlap(Class c1, Class c2) {
		// Swap the classes to get rid of many unneccesary conditions
		if(c2.getStart().isSooner(c1.getStart())) {
			Class tmp = c1;
			c1 = c2;
			c2 = tmp;
		}
		
		if(c1.getEnd().isSooner(c2.getStart())) {
			return 0;
		}
		else if(c2.getEnd().isSooner(c1.getEnd())) {
			return WeekTime.getLength(c2.getStart(), c2.getEnd());
		}
		else {
			return WeekTime.getLength(c2.getStart(), c1.getEnd());
		}
	}
	
    public static void main(String[] args) {
        System.out.println("such plan. wow. very make. such progress. much soon.");
        try { 
        	m_courses = PlanReader.readFile("testInput.in");
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
        System.out.println(m_courses.get(0).toString());
        System.out.println(m_courses.get(1).toString());
        m_plan = getOptimalPlan(m_courses);
        //m_plan.printClasses();
    }
}
