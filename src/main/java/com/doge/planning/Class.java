package com.doge.planning;

import com.doge.planning.Course;
import com.doge.planning.WeekTime;

/**
 * Represents each class
 * @author salmelu
 */
public class Class {

	private WeekTime m_start;
	private WeekTime m_end;
	private Course m_course;
	
	/**
	 * Constructor of the Class
	 * @param start Starting time of the class
	 * @param end Ending time of the class
	 * @param course Course which this class applies to
	 */
	public Class(WeekTime start, WeekTime end, Course course) {
		m_start = start;
		m_end = end;
		m_course = course;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(m_course.getName());
		sb.append(": ");
		sb.append(m_start.toString());
		sb.append(" - ");
		sb.append(m_end.toString());
		return sb.toString();
	}

}
