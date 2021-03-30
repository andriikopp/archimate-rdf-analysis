package ua.khpi.apparchi.util;

public final class RecommendationUtil {

	private RecommendationUtil() {
	}

	public static String provideRecommendation(String pattern) {
		String format = "{"
				+ "'Development' : '%s', "
				+ "'Cost' : '%s', "
				+ "'Flexible' : '%s', "
				+ "'Reliability' : '%s', "
				+ "'Extension' : '%s', "
				+ "'Robust' : '%s'"
				+ "}";
		
		switch (pattern) {
		case "Sequential":
			return format.formatted("easy", "inexpensive", "yes", "moderate", "easy", "no");
		case "Ring":
			return format.formatted("difficult", "moderate", "no", "high", "easy", "no");
		case "Radial":
			return format.formatted("easy", "expensive", "yes", "high", "easy", "yes");
		case "Tree":
			return format.formatted("easy", "moderate", "yes", "high", "easy", "no");
		case "Mesh":
			return format.formatted("difficult", "expensive", "no", "moderate", "difficult", "yes");
		}
		
		return null;
	}
}
