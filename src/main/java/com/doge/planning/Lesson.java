package com.doge.planning;

/**
 * Represents each class
 * @author salmelu
 */
public class Lesson {

	private WeekTime m_start;
	private WeekTime m_end;
	private Course m_course;
	
	/**
	 * Constructor of the Lesson
	 * @param start Starting time of the class
	 * @param end Ending time of the class
	 * @param course Course which this class applies to
	 */
	public Lesson(WeekTime start, WeekTime end, Course course) {
		m_start = start;
		m_end = end;
		m_course = course;
	}
	
	/**
	 * @return the m_start
	 */
	public WeekTime getStart() {
		return m_start;
	}

	/**
	 * @param m_start the m_start to set
	 */
	public void setStart(WeekTime start) {
		m_start = start;
	}

	/**
	 * @return the m_end
	 */
	public WeekTime getEnd() {
		return m_end;
	}

	/**
	 * @param m_end the m_end to set
	 */
	public void setEnd(WeekTime end) {
		m_end = end;
	}

	/**
	 * @return the m_course
	 */
	public Course getCourse() {
		return m_course;
	}

	/**
	 * @param m_course the m_course to set
	 */
	public void setCourse(Course course) {
		m_course = course;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(50);
		sb.append(m_course.getName());
		sb.append(": ");
		sb.append(m_start.toString());
		sb.append(" - ");
		sb.append(m_end.toString());
		return sb.toString();
	}

    public int getDuration() {
        return WeekTime.getLength(m_start, m_end);
    }
}
