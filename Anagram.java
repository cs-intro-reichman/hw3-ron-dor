import java.util.Random;

/** Functions for checking if a given string is an anagram. */
public class Anagram {
	public static void main(String args[]) {
		// Tests the isAnagram function.
		System.out.println(isAnagram("silent","listen"));  // true
		System.out.println(isAnagram("William Shakespeare","I am a weakish speller")); // true
		System.out.println(isAnagram("Madam Curie","Radium came")); // true
		System.out.println(isAnagram("Tom Marvolo Riddle","I am Lord Voldemort")); // true

		// Tests the preProcess function.
		System.out.println(preProcess("What? No way!!!"));
		
		// Tests the randomAnagram function.
		System.out.println("silent and " + randomAnagram("silent") + " are anagrams.");
		
		// Performs a stress test of randomAnagram 
		String str = "1234567";
		Boolean pass = true;
		//// 10 can be changed to much larger values, like 1000
		for (int i = 0; i < 10; i++) {
			String randomAnagram = randomAnagram(str);
			System.out.println(randomAnagram);
			pass = pass && isAnagram(str, randomAnagram);
			if (!pass) break;
		}
		System.out.println(pass ? "test passed" : "test Failed");
	}  

	// Returns true if the two given strings are anagrams, false otherwise.
	public static boolean isAnagram(String str1, String str2) {
		str1 = preProcess(str1);
		str2 = preProcess(str2);
		if (str1.length() != str2.length()) {
			return false;
		}
		int strLength = str1.length();
		for (int i = 0; i < strLength; i++) {
			char currentChar = str1.charAt(i);
			int indexInLatter = str2.indexOf(currentChar);
			if (indexInLatter == -1) {
				return false;
			}
			str2 = str2.substring(0, indexInLatter) + str2.substring(indexInLatter + 1);
		}
		return true;
	}
	   
	// Returns a string with no spaces, no special letters, just characters in lower case.
	public static String preProcess(String str) {
		String finalString = "";
		int strLength = str.length();
		for (int i = 0; i < strLength; i++) {
			char currentChar = str.charAt(i);
			if (Character.isLetter(currentChar)) {
				finalString += Character.toLowerCase(currentChar);
			}
		}
		return finalString;
	} 
	   
    // Returns a random anagram for the string received.
    public static String randomAnagram(String str) {
        String finalString = "";
        String inputString = str;
        Random generateRandom = new Random();
        while (!inputString.isEmpty()) {
            int randomIndex = generateRandom.nextInt(inputString.length());
            finalString += inputString.charAt(randomIndex);
            inputString = inputString.substring(0, randomIndex) + inputString.substring(randomIndex + 1);
        }
        return finalString;
    }
}
