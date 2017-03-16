package encryption;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class Encrypt {

	private static List<Integer> numbersToDecrypt;
	private static char[][] arr = new char[26][26];
	private static List<String> encryptList;
	private static char[] arrLower = new char[26];
	private static char[] arrCapitals = new char[26];
	private static char[] arrCharacters = new char[33];
	private static String str;

	private static int cezarKey;

	private static String getLowerKey;
	private static StringBuilder sbLower = new StringBuilder();

	private static String getCapitalsKey;
	private static StringBuilder sbCapitals = new StringBuilder();

	public static String getNumbersToDecrypt() {

		StringBuilder sb = new StringBuilder();

		numbersToDecrypt.stream().forEach(e -> {
			sb.append(e);
		});
		return sb.toString();
	}

	public static String encrypt(String s) throws IOException {

		StringBuilder encryptedWord = new StringBuilder();

		numbersToDecrypt = new LinkedList<>();

		encryptList = new LinkedList<>();

		arrLower = getArrLower();

		arrCapitals = getArrCapitals();

		// ================properties==================

		Properties prop = new Properties();
		String propFile = "keys.properties";

		InputStream inputStream = new FileInputStream(propFile);
		prop.load(inputStream);

		getLowerKey = prop.getProperty("lowercaseKey");
		getCapitalsKey = prop.getProperty("capitalsKey");
		cezarKey = Integer.parseInt(prop.getProperty("cezar"));

		// ================properties==================

		if (cezarKey <= 0) {
			cezarKey = 28;
		}
		arrCharacters = getCharArr();

		arr = createArray();

		for (int i = 0; i < getLowerKey.length(); ++i) {
			if (!sbLower.toString().contains(String.valueOf(getLowerKey.charAt(i)))
					&& String.valueOf(getLowerKey.charAt(i)).matches("[a-z]")) {

				sbLower.append(getLowerKey.charAt(i));

			}
		}

		for (int i = 0; i < getCapitalsKey.length(); ++i) {
			if (!sbCapitals.toString().contains(String.valueOf(getCapitalsKey.charAt(i)))) {

				if (String.valueOf(getCapitalsKey.charAt(i)).matches("[A-Z]")) {
					sbCapitals.append(getCapitalsKey.charAt(i));
				}
			}
		}

		for (int i = 0; i < s.length(); i++) {

			str = String.valueOf(s.charAt(i));

			if (str.matches("[A-Z]")) {

				encryptedWord.append(capital(str));

				numbersToDecrypt.add(1);

			} else if (str.matches("[a-z]")) {

				encryptedWord.append(lowercase(str));
				numbersToDecrypt.add(2);

			} else if (str.matches("[0-9]")) {

				encryptedWord.append(number(str));
				numbersToDecrypt.add(3);

			} else if (str.matches(".")) {

				encryptedWord.append(cezar(str));
				numbersToDecrypt.add(4);

			}

		}
		return encryptedWord.toString();

	}

	private static String capital(String charToDecrypt) {

		String s = "";
		String[] arrString = new String[2];
		int lastUsedIndexOfKey = -1;
		String firstEl = "";
		int lastIdxOfList = 0;

		if (numbersToDecrypt.contains(1)) {

			lastIdxOfList = encryptList.size() - 1;
			while (!s.matches("[A-Z]") && !encryptList.isEmpty()) {

				firstEl = encryptList.get(lastIdxOfList);

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

		int idxOfWordToEncrypt = -1;

		for (int i = 0; i < arrCapitals.length; i++) {

			if (charToDecrypt.matches(String.valueOf(arrCapitals[i]))) {

				idxOfWordToEncrypt = i;

			}

		}

		encryptList.add(charToDecrypt + "-" + (lastUsedIndexOfKey));

		return String.valueOf(arr[idxEncrypt][idxOfWordToEncrypt]).toUpperCase();
	}

	// ============================================================

	private static String lowercase(String charTodecrypt) {

		int lastIdxOfList;
		String s = "";
		String[] arrString = new String[2];
		int lastUsedIndexOfKey = -1;
		String first = "";

		if (numbersToDecrypt.contains(2)) {
			while (!s.matches("[a-z]") && !encryptList.isEmpty()) {

				lastIdxOfList = encryptList.size() - 1;
				first = encryptList.get(lastIdxOfList);
				arrString = first.split("-");
				s = arrString[0];

				if (s.matches("[a-z]")) {

					lastUsedIndexOfKey = Integer.parseInt(arrString[1]);

				} else {

					lastIdxOfList--;

				}
			}

		}

		int idxEncrypt = -1;

		if (lastUsedIndexOfKey >= 0) {

			if (lastUsedIndexOfKey < sbLower.length() - 1) {

				lastUsedIndexOfKey += 1;

				for (int i = 0; i < arrLower.length; i++) {

					if (String.valueOf(sbLower.charAt(lastUsedIndexOfKey)).matches(String.valueOf(arrLower[i]))) {

						idxEncrypt = i;

					}

				}

			} else {

				lastUsedIndexOfKey = 0;
				for (int i = 0; i < arrLower.length; i++) {

					if (String.valueOf(sbLower.charAt(lastUsedIndexOfKey)).matches(String.valueOf(arrLower[i]))) {

						idxEncrypt = i;

					}

				}

			}

		} else {

			lastUsedIndexOfKey = 0;
			for (int i = 0; i < arrLower.length; i++) {

				if (String.valueOf(sbLower.charAt(lastUsedIndexOfKey)).matches(String.valueOf(arrLower[i]))) {

					idxEncrypt = i;

				}

			}

		}

		int idxOfWord = -1;
		for (int i = 0; i < arrLower.length; i++) {

			if (String.valueOf(charTodecrypt).matches(String.valueOf(arrLower[i]))) {

				idxOfWord = i;

			}

		}

		encryptList.add(charTodecrypt + "-" + (lastUsedIndexOfKey));

		return String.valueOf(arr[idxEncrypt][idxOfWord]);
	}

	private static String number(String number) {

		int i = Integer.parseInt(number);
		i = 10 - i;

		return String.valueOf(i);
	}

	// ==============================================================

	private static String cezar(String c) {

		int idx = -1;

		for (int i = 0; i < arrCharacters.length; i++) {

			if (String.valueOf(arrCharacters[i]).matches(String.valueOf(c))) {
				idx = i;
			}
		}

		idx += cezarKey;

		if (idx > arrCharacters.length) {

			idx -= arrCharacters.length;

		}

		return String.valueOf(arrCharacters[idx]);
	}

	// ===============

	private static char[][] createArray() {

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

	private static char[] getArrLower() {

		char c = 'a';
		for (int i = 0; i < arrLower.length; i++) {

			arrLower[i] = c;
			c++;
		}

		return arrLower;
	}

	private static char[] getArrCapitals() {

		char c = 'A';
		for (int i = 0; i < arrCapitals.length; i++) {

			arrCapitals[i] = c;
			c++;
		}

		return arrCapitals;
	}

	private static char[] getCharArr() {

		char c = ' ';

		for (int i = 0; i < arrCharacters.length; i++) {

			arrCharacters[i] = c;
			c++;
		}

		return arrCharacters;
	}

}
