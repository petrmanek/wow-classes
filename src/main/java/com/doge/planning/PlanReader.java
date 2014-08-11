package com.doge.planning;

import java.io.Console;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
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
public class PlanReader {

	private PlanReader() {
		
	}
	
	public static List<Course> readFile(String filename) throws Exception, FileNotFoundException {
		FileReader freader = new FileReader(filename);
		return readCourses(freader);
	}
	
	public static List<Course> readConsole() throws Exception {
		Console con = System.console();
		if(con == null) {
			throw new Exception("No Console opened.");
		}
		return readCourses(con.reader());
	}
	
	private static List<Course> readCourses(Reader reader) throws Exception {
		ArrayList<Course> courses = new ArrayList<>();
		String line;
		int lineNum = 0;
		Course tmpCourse = null;
		boolean courseProcess = false;
		
		Scanner sc = new Scanner(reader);
		while(sc.hasNext()) {
			line = sc.nextLine();
			lineNum++;
			if(line.equals("")) {
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
					throw new Exception("Invalid input format, expected 2 fields, input line " + lineNum);
				}
				try {
					tmpCourse = new Course(parsed[0], Integer.parseInt(parsed[1]));
				}
				catch(NumberFormatException e) {
					sc.close();
					throw new Exception("Unsupported number format, input line " + lineNum);
				}
				courseProcess = true;
			}
			else {
				if(parsed.length != 6) {
					sc.close();
					throw new Exception("Invalid input format, expected 6 fields, input line " + lineNum);
				}
				try {
					WeekTime start = new WeekTime(parsed[0], Integer.parseInt(parsed[1]), Integer.parseInt(parsed[2]));
					WeekTime end = new WeekTime(parsed[3], Integer.parseInt(parsed[4]), Integer.parseInt(parsed[5]));
					tmpCourse.addClass(new Lesson(start, end, tmpCourse));
				}
				catch (Exception e) {
					sc.close();
					throw new Exception("Invalid input format, not valid WeekTime range, input line " + lineNum);
				}
			}
		}
		if(courseProcess && tmpCourse != null) {
			courses.add(tmpCourse);
		}
		sc.close();
		return courses;
	}

}
