package com.doge.planning;

import java.util.ArrayList;
import java.util.List;

import com.doge.planning.Class;

/**
 * Represents each course and holds its classes
 * @author salmelu
 */
public class Course {

	private String m_name;
	private int m_priority;
	private List<Class> m_classes;
	
	public Course(String name, int priority, List<Class> classes) {
		m_priority = priority;
		m_classes = classes;
	}
	
	public Course(String name, int priority) {
		this(name, priority, new ArrayList<>());	
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
	
	public List<Class> getClasses() {
		return m_classes;
	}
	
	public void setClasses(List<Class> classes) {
		m_classes = classes;
	}
	
	public void addClass(Class c) {
		m_classes.add(c);
	}
	
	public void addClasses(List<Class> c) {
		m_classes.addAll(c);
	}

}
