package co.dataswitch.utils;

import org.apache.commons.text.StringEscapeUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

public class InputValidation {

	public static String getCleanedString(String input) {
		String cleanedString = Jsoup.clean(StringEscapeUtils.escapeHtml4(input), Whitelist.none());
		return cleanedString;
	}
}
