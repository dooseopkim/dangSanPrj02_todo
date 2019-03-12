package org.edwith.todo.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateFormatChange {
	private static final String IN__DATE = "yyyy-MM-dd HH:mm:ss.S";
	private static final String OUT_DATE = "yyyy.MM.dd";
	
	private static final DateTimeFormatter FORMAT__IN_DATE = DateTimeFormatter.ofPattern(IN__DATE);
	private static final DateTimeFormatter FORMAT__OUT_DATE = DateTimeFormatter.ofPattern(OUT_DATE);
	
	public static String change(String regDate) {
		LocalDateTime localDateTime = LocalDateTime.parse(regDate, FORMAT__IN_DATE);
		return localDateTime.format(FORMAT__OUT_DATE).toString();
	}
}
