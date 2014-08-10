package com.doge.planning;

import java.util.LinkedList;
import java.util.List;

/**
 * Object holding the plan of classes
 * @author salmelu
 */
public class Plan {

	private List<Class> m_classes;
	
	public Plan() {
		m_classes = new LinkedList<>();
	}
	
	public Plan(List<Class> classes) {
		m_classes = classes;
	}
	
	public void addClass(Class c) {
		m_classes.add(c);
	}
	
	public List<Class> getClasses() {
		return m_classes;
	}
 	
	public void printClasses() {
		for(Class c : m_classes) {
			System.out.println(c.toString());
		}
	}

}
