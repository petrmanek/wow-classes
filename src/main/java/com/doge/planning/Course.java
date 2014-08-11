package com.doge.planning;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents each course and holds its classes
 * @author salmelu
 */
public class Course {

	private String m_name;
	private int m_priority;
	private List<Lesson> m_lessons;
	
	public Course(String name, int priority, List<Lesson> classes) {
		m_name = name;
		m_priority = priority;
		m_lessons = classes;
	}
	
	public Course(String name, int priority) {
		this(name, priority, new ArrayList<Lesson>());
	}
	
	public String getName() {
		return m_name;
	}
	
	public void setName(String name) {
		m_name = name;
	}
	
	public int getPriority() {
		return m_priority;
	}
	
	public void setPriority(int priority) {
		m_priority = priority;
	}
	
	public List<Lesson> getClasses() {
		return m_lessons;
	}
	
	public void setClasses(List<Lesson> lessons) {
		m_lessons = lessons;
	}
	
	public void addClass(Lesson c) {
		m_lessons.add(c);
	}
	
	public void addClasses(List<Lesson> c) {
		m_lessons.addAll(c);
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(100);
		String lf = System.getProperty("line.separator");
		sb.append("Course: " + m_name);
		sb.append(", priority: " + m_priority);
		sb.append(lf);
		for(Lesson c : m_lessons) {
			sb.append(c).append(lf);
		}
		return sb.toString();
	}

}
