package com.doge.planning;

import java.util.LinkedList;
import java.util.List;

/**
 * Object holding the plan of classes
 * @author salmelu
 */
public class Plan {

	private List<Lesson> m_lessons;
	
	public Plan() {
		m_lessons = new LinkedList<>();
	}
	
	public Plan(List<Lesson> lessons) {
		m_lessons = lessons;
	}
	
	public void addLesson(Lesson c) {
		m_lessons.add(c);
	}
	
	public List<Lesson> getLessons() {
		return m_lessons;
	}
 	
	public void printClasses() {
		for(Lesson l : m_lessons) {
			System.out.println(l.toString());
		}
	}

    public int computeOverlap() {
        int overlap = 0;

        for (Lesson k : m_lessons) {
            for (Lesson l : m_lessons) {
                if (k.equals(l)) continue;

                overlap += Planner.getOverlap(k, l) * k.getCourse().getPriority();
            };
        }

        return overlap;
    }

    public int computeEfficiency() {
        int sum = 0;

        for (Lesson l : m_lessons) {
            sum += l.getDuration() * l.getCourse().getPriority();
        }

        return sum - computeOverlap();
    }

}
