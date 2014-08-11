package com.doge.planning;

import java.io.Console;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Static class used for reading the input
 * Temporary format:
 * 
 * course_name;priority 		-- Course
 * start_day;start_hour;start_minute;end_day;end_hour;end_minute;	-- Each class
 * empty_line			-- Separator
 * 
 * @author salmelu
 */
public class Reader {

	private Reader() {
		
	}
	
	public static List<Course> readCourses() throws Exception {
		ArrayList<Course> courses = new ArrayList<>();
		Console con = System.console();
		String line;
		Course tmpCourse = null;
		boolean courseProcess = false;
		
		
		if(con != null) {
			Scanner sc = new Scanner(con.reader());
			while(sc.hasNext()) {
				line = sc.nextLine();
				if(line == "") {
					if(courseProcess && tmpCourse != null) {
						courses.add(tmpCourse);
					}
					courseProcess = false;
					continue;
				}
				
				String[] parsed = line.split(";");
				if(!courseProcess) {
					if(parsed.length != 2) {
						sc.close();
						throw new Exception("Invalid input format");
					}
					tmpCourse = new Course(parsed[0], Integer.parseInt(parsed[1]));
					courseProcess = true;
				}
				else {
					if(parsed.length != 6) {
						sc.close();
						throw new Exception("Invalid input format");
					}
					try {
						WeekTime start = new WeekTime(parsed[0], Integer.parseInt(parsed[1]), Integer.parseInt(parsed[2]));
						WeekTime end = new WeekTime(parsed[3], Integer.parseInt(parsed[4]), Integer.parseInt(parsed[5]));
						tmpCourse.addClass(new Class(start, end, tmpCourse));
					}
					catch (Exception e) {
						sc.close();
						throw new Exception("Invalid input format");
					}
				}
			}
			sc.close();
		}
		else {
			throw new Exception("No Console opened.");
		}
		return courses;
	}

}
