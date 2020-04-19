package br.com.caelum.ingresso.converter;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;

public class YearMonthConverter implements Converter<String, YearMonth> {
	@Override
	public YearMonth convert(String text) {
		if (text.matches("^[0-9]{2}\\/[0-9]{2}$")) {
			return YearMonth.parse(text, DateTimeFormatter.ofPattern("MM/yy")); }
		
		
		return YearMonth.parse(text, DateTimeFormatter.ofPattern("MM/yyyy"));
	}
}