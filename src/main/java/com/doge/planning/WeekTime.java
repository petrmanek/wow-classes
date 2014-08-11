package com.doge.planning;

/**
 * Custom project class representing a day in the week, hour and minute
 * Class is only package specific
 * Seconds are omitted because preciseness is not needed
 * All the fields are final, they are not supposed to change
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
		
		private final String m_displayName;
		private final int m_order;
		
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
	
	private final DayOfWeek m_day;
	private final int m_hour;
	private final int m_minute;
	
	/**
	 * Constructor
	 * @param day Day of the week
	 * @param hour
	 * @param minute
	 */
	public WeekTime(DayOfWeek day, int hour, int minute) {
		m_day = day;
		m_hour = hour;
		m_minute = minute;
	}
	
	/**
	 * Constructor taking string instead of enum
	 * @param day String of the day, supported: Mon, Monday, Tue, Tuesday, ...
	 * @param hour 
	 * @param minute
	 * @throws Exception If the day is unknown
	 */
	public WeekTime(String day, int hour, int minute) throws Exception {
		switch(day) {
		case "Mon":
		case "Monday":
			m_day = DayOfWeek.MONDAY;
			break;
		case "Tue":
		case "Tuesday":
			m_day = DayOfWeek.TUESDAY;
			break;
		case "Wed":
		case "Wednesday":
			m_day = DayOfWeek.WEDNESDAY;
			break;
		case "Thu":
		case "Thursday":
			m_day = DayOfWeek.THURSDAY;
			break;
		case "Fri":
		case "Friday":
			m_day = DayOfWeek.FRIDAY;
			break;
		case "Sat":
		case "Saturday":
			m_day = DayOfWeek.SATURDAY;
			break;
		case "Sun":
		case "Sunday":
			m_day = DayOfWeek.SUNDAY;
			break;
		default:
			throw new Exception("Unsupported Day");	
		}
		m_hour = hour;
		m_minute = minute;
	}
	
	@Override
	public String toString() {
		return m_day.toString() + " - " + String.valueOf(m_hour) + ":" + String.valueOf(m_minute); 
	}
	
	public DayOfWeek getDay() {
		return m_day;
	}
	
	public int getHour() {
		return m_hour;
	}
	
	public int getMinute() {
		return m_minute;
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
	
	/**
	 * Checks if this WeekTime points to sooner time than the parameter
	 * @param other WeekTime to check
	 * @return true if the other happens later or at the same time, false otherwise
	 */
	public boolean isSooner(WeekTime other) {
		if(other.getDay().m_order > m_day.getOrder()) {
			return true;
		} else if (other.getDay() == m_day && other.getHour() > m_hour) {
			return true;
		} else if (other.getDay() == m_day && other.getHour() == m_hour && other.getMinute() >= m_minute) {
			return true;
		}
		return false;
	}

}
