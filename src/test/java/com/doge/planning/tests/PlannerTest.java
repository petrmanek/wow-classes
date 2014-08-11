package com.doge.planning.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.doge.planning.*;

/**
 * Contains important test for our Planner
 * More testing is always welcomed
 * Uses JUnit 4 test framework for testing
 * @author salmelu
 */
public class PlannerTest {

	private void prepareTestFileReader() throws IOException {
		String lf = System.getProperty("line.separator");
		String testContent = "Diskretka;20" + lf 
				+ "Mon;9;00;Mon;10;30" + lf 
				+ "Wed;15;00;Wed;16;30" + lf + lf 
				+ "Analyza;5" + lf
				+ "Mon;10;40;Mon;12;10" + lf 
				+ "Fri;12;20;Fri;13;50";
		File f = new File("testInput.in");
		FileOutputStream fos = new FileOutputStream(f);
		
		fos.write(testContent.getBytes());
		fos.flush();
		fos.close();
	}
	
	private void finalizeTestFileReader() throws IOException, Exception {
		File file = new File("testInput.in");
		if(!file.exists())
			throw new IOException("Where is the file?");
		if(!file.delete())
			throw new Exception("Can't delete!");
	}
	
	/**
	 * Tests the correctness of file reading
	 */
	@Test
	public void testFileReader() {
		try {
			prepareTestFileReader();
		} catch (IOException e1) {
			e1.printStackTrace();
			fail("Couldn't prepare the file for testing.");
		}
		try {
			List<Course> courses = PlanReader.readFile("testInput.in");
			if(courses.size() < 1) {
				fail("Nothing was read.");
			}
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
			fail("File not found.");
		}
		catch (Exception e) {
			e.printStackTrace();
			fail("Input read error.");
		}
		try {
			finalizeTestFileReader();
		} catch(IOException e) {
			e.printStackTrace();
			fail("Dafuq? No file.");
		} catch(Exception e) {
			e.printStackTrace();
			fail("Couldn't delete.");
		}
	}
	/**
	 * Tests the getLength method of WeekTime
	 */
	@Test
	public void testGetLength() {
		class Tclass {
			WeekTime w1;
			WeekTime w2;
			int result;
			
			Tclass(WeekTime w1, WeekTime w2, int result) {
				this.w1 = w1;
				this.w2 = w2;
				this.result = result;
			}
		}
		try {
			/*
			 * TESTS - feel free to add more
			 * Format: starting time, ending time, expected results
			 */
			Tclass[] tests = {
					new Tclass(new WeekTime("Mon", 3, 50), new WeekTime("Tue", 9, 10), 1760),
					new Tclass(new WeekTime("Fri", 18, 10), new WeekTime("Fri", 19, 05), 55),
					new Tclass(new WeekTime("Tue", 23, 50), new WeekTime("Wed", 0, 05), 15)
			};
			
			for(Tclass t : tests) {
				assertEquals(t.result, WeekTime.getLength(t.w1, t.w2));
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed creating of the objects");
		}
	}
	
	@Test
	public void testIsSooner() {
		class Tclass {
			WeekTime w1;
			WeekTime w2;
			boolean result;
			
			Tclass(WeekTime w1, WeekTime w2, boolean result) {
				this.w1 = w1;
				this.w2 = w2;
				this.result = result;
			}
		}
		
		try {
			/*
			 * TESTS - feel free to add more
			 * Format: first time, second time, expected results
			 */
			Tclass[] tests = {
					new Tclass(new WeekTime("Mon", 3, 50), new WeekTime("Tue", 9, 10), true),
					new Tclass(new WeekTime("Fri", 19, 05), new WeekTime("Fri", 18, 10), false),
					new Tclass(new WeekTime("Wed", 0, 05), new WeekTime("Tue", 23, 50), false)
			};
			
			for(Tclass t : tests) {
				assertEquals(t.result, t.w1.isSooner(t.w2));
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed creating of the objects");
		}
	}
	
	/**
	 * Tests the getOverlap method of Planner
	 */
	@Test
	public void testGetOverlap() {
		class Tclass {
			Lesson c1;
			Lesson c2;
			int result;
			
			Tclass(Lesson c1, Lesson c2, int result) {
				this.c1 = c1;
				this.c2 = c2;
				this.result = result;
			}
		}
		
		/*
		 * TESTS - feel free to add more
		 * Format: first time, second time, expected results
		 */
		try {
			Tclass[] tests = {
					new Tclass(new Lesson(new WeekTime("Wed", 10, 00), new WeekTime("Wed", 12, 00), new Course("Hah", 10)),
							new Lesson(new WeekTime("Wed", 14, 00), new WeekTime("Wed", 16, 00), new Course("Hih", 8)),
							0),
					new Tclass(new Lesson(new WeekTime("Fri", 10, 30), new WeekTime("Fri", 18, 20), new Course("Hah", 10)),
							new Lesson(new WeekTime("Fri", 14, 00), new WeekTime("Fri", 15, 45), new Course("Hih", 9)),
							105),
					new Tclass(new Lesson(new WeekTime("Tue", 9, 00), new WeekTime("Tue", 12, 10), new Course("Heh", 10)),
							new Lesson(new WeekTime("Tue", 10, 40), new WeekTime("Tue", 13, 50), new Course("Huh", 9)),
							90),
					new Tclass(new Lesson(new WeekTime("Tue", 9, 00), new WeekTime("Tue", 12, 10), new Course("Heh", 10)),
							new Lesson(new WeekTime("Tue", 10, 40), new WeekTime("Tue", 12, 10), new Course("Huh", 9)),
							90),
					new Tclass(new Lesson(new WeekTime("Fri", 10, 40), new WeekTime("Fri", 13, 50), new Course("Huh", 9)),
							new Lesson(new WeekTime("Fri", 9, 00), new WeekTime("Fri", 12, 10), new Course("Heh", 10)),
							90),	
			};
			
			for (Tclass t : tests) {
				assertEquals(t.result, Planner.getOverlap(t.c1, t.c2));
			}
		} catch (Exception e) {
			e.printStackTrace();
			fail("Failed creating of the objects");
			
		}
	}

}
