package encryption;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class Decrypt {

	private static List<Integer> numbersToDecrypt;
	private static char[][] arr = new char[26][26];
	private static List<String> decryptList;
	private static char[] arrLower = new char[26];
	private static char[] arrCapitals = new char[26];
	private static char[] arrCharacters = new char[33];
	private static String str;
	private static String numbers;

	private static int cezarKey;

	private static String getLowerKey;
	private static StringBuilder sbLower = new StringBuilder();

	private static String getCapitalsKey;
	private static StringBuilder sbCapitals = new StringBuilder();

	public static String decrypt(String wordToDecrypt, String decryptingNumbers) throws IOException {

		StringBuilder decryptedWord = new StringBuilder();

		numbersToDecrypt = new LinkedList<>();

		decryptList = new LinkedList<>();

		arrLower = getArrLower();

		arrCapitals = getArrCapitals();

		Properties prop = new Properties();
		String propFile = "keys.properties";

		InputStream inputStream = new FileInputStream(propFile);
		prop.load(inputStream);

		getLowerKey = prop.getProperty("lowercaseKey");
		getCapitalsKey = prop.getProperty("capitalsKey");
		cezarKey = Integer.parseInt(prop.getProperty("cezar"));

		if (cezarKey <= 0) {
			cezarKey = 28;
		}
		arrCharacters = getCharArr();

		arr = createArray();

		for (int i = 0; i < getLowerKey.length(); ++i) {
			if (!sbLower.toString().contains(String.valueOf(getLowerKey.charAt(i)))
					&& String.valueOf(getLowerKey.charAt(i)).matches("[a-z]")) {// sprawdzic

				sbLower.append(getLowerKey.charAt(i));

			}
		}

		sbLower = new StringBuilder(encryptLowerKey(sbLower.toString()));

		for (int i = 0; i < getCapitalsKey.length(); ++i) {
			if (!sbCapitals.toString().contains(String.valueOf(getCapitalsKey.charAt(i)))
					&& String.valueOf(getCapitalsKey.charAt(i)).matches("[A-Z]")) {
				sbCapitals.append(getCapitalsKey.charAt(i));
			}
		}

		sbCapitals = new StringBuilder(encryptCapitalKey(sbCapitals.toString()));

		for (int i = 0; i < wordToDecrypt.length(); i++) {

			str = String.valueOf(wordToDecrypt.charAt(i));
			numbers = String.valueOf(decryptingNumbers.charAt(i));

			if (String.valueOf(decryptingNumbers.charAt(i)).matches("1")) {

				decryptedWord.append(capital(str));

				numbersToDecrypt.add(1);

			} else if (String.valueOf(decryptingNumbers.charAt(i)).matches("2")) {

				decryptedWord.append(lowercase(str));
				numbersToDecrypt.add(2);

			} else if (String.valueOf(decryptingNumbers.charAt(i)).matches("3")) {

				decryptedWord.append(number(str));
				numbersToDecrypt.add(3);

			} else if (String.valueOf(decryptingNumbers.charAt(i)).matches("4")) {

				decryptedWord.append(decryptCezar(str));
				numbersToDecrypt.add(4);

			}

		}

		return decryptedWord.toString();

	}

	private static String capital(String charToDecrypt) {

		String s = "";
		String[] arrString = new String[2];
		int lastUsedIndexOfKey = -1;
		String firstEl = "";
		int lastIdxOfList = 0;

		if (numbersToDecrypt.contains(1)) {

			lastIdxOfList = decryptList.size() - 1;
			while (!s.matches("[A-Z]") && !decryptList.isEmpty()) {

				firstEl = decryptList.get(lastIdxOfList);
				arrString = firstEl.split("-");
				s = arrString[0];

				if (s.matches("[A-Z]")) {
					lastUsedIndexOfKey = Integer.parseInt(arrString[1]);

				} else {

					lastIdxOfList--;

				}

			}

		}
		if (lastUsedIndexOfKey > sbCapitals.length()) {
			lastUsedIndexOfKey = 0;
		}

		int idxEncrypt = -1;
		if (lastUsedIndexOfKey >= 0) {

			if (lastUsedIndexOfKey < sbCapitals.length() - 1) {

				lastUsedIndexOfKey += 1;

				for (int i = 0; i < arrCapitals.length; i++) {

					if (String.valueOf(sbCapitals.charAt(lastUsedIndexOfKey)).matches(String.valueOf(arrCapitals[i]))) {

						idxEncrypt = i;

					}
				}

			} else {

				lastUsedIndexOfKey = 0;
				for (int i = 0; i < arrCapitals.length; i++) {

					if (String.valueOf(sbCapitals.charAt(lastUsedIndexOfKey)).matches(String.valueOf(arrCapitals[i]))) {

						idxEncrypt = i;

					}
				}

			}

		} else {

			lastUsedIndexOfKey = 0;
			for (int i = 0; i < arrCapitals.length; i++) {

				if (String.valueOf(sbCapitals.charAt(lastUsedIndexOfKey)).matches(String.valueOf(arrCapitals[i]))) {

					idxEncrypt = i;

				}

			}

		}

		int idxOfWordToDecrypt = -1;

		for (int i = 0; i < arrCapitals.length; i++) {

			if (charToDecrypt.matches(String.valueOf(arrCapitals[i]))) {

				idxOfWordToDecrypt = i;

			}

		}

		decryptList.add(charToDecrypt + "-" + (lastUsedIndexOfKey));

		return String.valueOf(arr[idxEncrypt][idxOfWordToDecrypt]).toUpperCase();
	}

	// ============================================================

	private static String lowercase(String charToDecrypt) {

		int lastIdxOfList;
		String s = "";
		String[] arrString = new String[2];
		int lastUsedIndexOfKey = -1;
		String first = "";

		if (numbersToDecrypt.contains(2)) {
			while (!s.matches("[a-z]") && !decryptList.isEmpty()) {

				lastIdxOfList = decryptList.size() - 1;
				first = decryptList.get(lastIdxOfList);
				arrString = first.split("-");
				s = arrString[0];

				if (s.matches("[a-z]")) {

					lastUsedIndexOfKey = Integer.parseInt(arrString[1]);

				} else {

					lastIdxOfList--;

				}
			}

		}

		int idxDecrypt = -1;
		if (lastUsedIndexOfKey >= 0) {

			if (lastUsedIndexOfKey < sbLower.length() - 1) {

				lastUsedIndexOfKey += 1;

				for (int i = 0; i < arrLower.length; i++) {

					if (String.valueOf(sbLower.charAt(lastUsedIndexOfKey)).matches(String.valueOf(arrLower[i]))) {

						idxDecrypt = i;

					}

				}

			} else {

				lastUsedIndexOfKey = 0;
				for (int i = 0; i < arrLower.length; i++) {

					if (String.valueOf(sbLower.charAt(lastUsedIndexOfKey)).matches(String.valueOf(arrLower[i]))) {

						idxDecrypt = i;

					}

				}

			}

		} else {

			lastUsedIndexOfKey = 0;
			for (int i = 0; i < arrLower.length; i++) {

				if (String.valueOf(sbLower.charAt(lastUsedIndexOfKey)).matches(String.valueOf(arrLower[i]))) {

					idxDecrypt = i;

				}

			}

		}

		int idxOfWord = -1;

		for (int i = 0; i < arrLower.length; i++) {

			if (String.valueOf(charToDecrypt).matches(String.valueOf(arrLower[i]))) {

				idxOfWord = i;

			}

		}

		decryptList.add(charToDecrypt + "-" + (lastUsedIndexOfKey));

		return String.valueOf(arr[idxDecrypt][idxOfWord]);
	}

	private static String number(String number) {

		int i = Integer.parseInt(number);
		i = 10 - i;

		return String.valueOf(i);
	}

	// ==============================================================

	public static String decryptCezar(String c) {

		int idx = -1;

		for (int i = 0; i < arrCharacters.length; i++) {

			if (String.valueOf(arrCharacters[i]).matches(String.valueOf(c))) {
				idx = i;

			}
		}

		idx -= cezarKey;

		if (idx > arrCharacters.length) {

			idx += arrCharacters.length;

		}

		return String.valueOf(arrCharacters[idx]);
	}

	public static String decryptNumber(String number) {

		int i = Integer.parseInt(number);
		i = 10 - i;

		return String.valueOf(i);
	}

	public static char[] getArrLower() {

		char c = 'a';
		for (int i = 0; i < arrLower.length; i++) {

			arrLower[i] = c;
			c++;
		}

		return arrLower;
	}

	public static char[] getArrCapitals() {

		char c = 'A';
		for (int i = 0; i < arrCapitals.length; i++) {

			arrCapitals[i] = c;
			c++;
		}

		return arrCapitals;
	}

	public static char[] getCharArr() {

		char c = ' ';

		for (int i = 0; i < arrCharacters.length; i++) {

			arrCharacters[i] = c;
			c++;
		}

		return arrCharacters;
	}

	public static char[][] createArray() {

		char[][] arr = new char[26][26];

		char character = 'a';

		for (int i = 0; i < arr.length; i++) {

			if (i > 0) {

				character = arr[i - 1][0];
			}

			for (int j = 0; j < arr.length; j++) {

				if (character >= 'z') {

					character = 'a';

				} else if (i != 0 || j != 0) {

					character++;
				}

				arr[i][j] = character;

			}

		}

		return arr;

	}

	public static String encryptCapitalKey(String keyCapital) {
		int k2 = -1;

		StringBuilder sbKey = new StringBuilder();

		for (int j = 0; j < keyCapital.length(); j++) {

			for (int i = 0; i < arrCapitals.length; i++) {

				if (String.valueOf(keyCapital.charAt(j)).matches(String.valueOf(arrCapitals[i]))) {

					k2 = (26 - i) % 26;
					sbKey.append(arrCapitals[k2]);

				}

			}

		}
		return sbKey.toString();
	}

	public static String encryptLowerKey(String keyLower) {
		int k2 = -1;

		StringBuilder sbKey = new StringBuilder();

		for (int j = 0; j < keyLower.length(); j++) {

			for (int i = 0; i < arrLower.length; i++) {

				if (String.valueOf(keyLower.charAt(j)).matches(String.valueOf(arrLower[i]))) {

					k2 = (26 - i) % 26;
					sbKey.append(arrLower[k2]);

				}

			}

		}
		return sbKey.toString();
	}
}
