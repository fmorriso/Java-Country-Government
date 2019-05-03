import java.util.ArrayList;
import java.util.Arrays;

// generate a random country name from a finite set of available countries
public class CountryNameGenerator {

	private static ArrayList<String> availableCountries = new ArrayList<String>(
			Arrays.asList("Havenmoor", "Byfay", "Bushwyn", "Whitewater", "Merriborough", "Bluebeech", "Greencrest", "Seapond", "Southton", "Aellake", "Westtown", "Freyhedge", "Janness", "Oldnesse",
					"Icedell", "Goldford", "Southmount", "Aldpine", "Northcliff", "Oldriver", "Shadowsea", "Wheatville", "Clearwater", "Icenesse", "Pineedge"));

	public static String getRandomCountryName() {

		int i = (int) (Math.random() * availableCountries.size());
		return availableCountries.get(i);
		
	}

}
