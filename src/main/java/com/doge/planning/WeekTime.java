package com.doge.planning;

/**
 * Custom project class representing a day in the week, hour and minute
 * Class is only package specific
 * Seconds are omitted because preciseness is not needed
 * @author salmelu
 */
class WeekTime {

	/**
	 * Enum used for the days of the week
	 * Overrides toString method for simpler usage
	 * @author salmelu
	 */
	protected enum DayOfWeek {
		MONDAY("Monday", 1),
		TUESDAY("Tuesday", 2),
		WEDNESDAY("Wednesday", 3),
		THURSDAY("Thursday", 4),
		FRIDAY("Friday", 5),
		SATURDAY("Saturday", 6),
		SUNDAY("Sunday", 7);
		
		private String m_displayName;
		private int m_order;
		
		DayOfWeek(String displayName, int order) {
			m_displayName = displayName;
			m_order = order;
		}
		
		@Override
		public String toString() {
			return m_displayName;
		}
		
		public int getOrder() {
			return m_order;
		}
	}
	
	private DayOfWeek m_day;
	private int m_hour;
	private int m_minute;
	
	public WeekTime(DayOfWeek day, int hour, int minute) {
		m_day = day;
		m_hour = hour;
		m_minute = minute;
	}
	
	@Override
	public String toString() {
		return m_day.toString() + " - " + String.valueOf(m_hour) + ":" + String.valueOf(m_minute); 
	}
	
	/**
	 * Counts the length of the period
	 * @param start 
	 * @param end
	 * @return number of minutes between start and end
	 */
	public static int getLength(WeekTime start, WeekTime end) {
		int minutes = (start.m_day.getOrder() - end.m_day.getOrder()) * 24 * 60;
		
		if(start.m_hour > end.m_hour) {
			minutes -= (start.m_hour - end.m_hour) * 60;
		} else {
			minutes += (end.m_hour - start.m_hour) * 60;
		}
		
		if(start.m_minute > end.m_minute) {
			minutes -= start.m_minute - end.m_minute;
		} else { 
			minutes += end.m_minute - start.m_minute;
		}
		return minutes;
	}

}
