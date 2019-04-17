import java.util.ArrayList;
import java.util.Arrays;

public class CountryNameGenerator {
	/*
	 * Havenmoor Byfay Bushwyn Whitewater Merriborough Bluebeech Greencrest Seapond Southton Aellake Westtown Freyhedge Janness Oldnesse Icedell Goldford Southmount Aldpine Northcliff Oldriver
	 * Shadowsea Wheatville Clearwater Icenesse Pineedge
	 */
	private static ArrayList<String> availableCountries = new ArrayList<String>(
			Arrays.asList("Havenmoor", "Byfay", "Bushwyn", "Whitewater", "Merriborough", "Bluebeech", "Greencrest", "Seapond", "Southton", "Aellake", "Westtown", "Freyhedge", "Janness", "Oldnesse",
					"Icedell", "Goldford", "Southmount", "Aldpine", "Northcliff", "Oldriver", "Shadowsea", "Wheatville", "Clearwater", "Icenesse", "Pineedge"));

	public static String getRandomCountryName() {

		int i = (int) (Math.random() * availableCountries.size());
		String name = availableCountries.get(i);
		return name;
	}

}
