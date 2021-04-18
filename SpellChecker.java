import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpellChecker implements SpellCheckerInterface {

	private final Set<String> dictionary = new HashSet<>();

	public SpellChecker(String filename) throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String word;

			while ((word = reader.readLine()) != null) {
				dictionary.add(word.toLowerCase());
			}
		}
	}

	@Override
	public List<String> getIncorrectWords(String filename) {
		List<String> incorrectWords = new ArrayList<>();
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String line;

			while ((line = reader.readLine()) != null) {
				line = line.toLowerCase().replaceAll("[^a-z0-9 ]", "");
				String[] words = line.split(" +"); // interprets multiple spaces as delimiter and splits string

				for (String word : words) {
					if (!word.isEmpty() && !dictionary.contains(word)) {
						incorrectWords.add(word);
					}
				}
			}
			return incorrectWords;

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Set<String> getSuggestions(String word) {
		Set<String> suggestions = new HashSet<>();

		// Add Character
		for (char c = 'a'; c <= 'z'; c++) {
			for (int i = 0; i < word.length(); i++) {
				String word2 = word.substring(0, i) + c + word.substring(i);
				if (dictionary.contains(word2)) {
					suggestions.add(word2);
				}
			}
		}

		// Remove Character
		if (word.length() > 0) {
			for (int i = 0; i < word.length(); i++) {
				String word2 = word.substring(0, i) + word.substring(i + 1);
				if (dictionary.contains(word2)) {
					suggestions.add(word2);
				}
			}
		}

		// Swap Character
		if (word.length() > 1) {
			for (int i = 0; i < word.length() - 1; i++) {
				String word2 = word.substring(0, i) + word.charAt(i + 1) + word.charAt(i) + word.substring(i + 2);
				if (dictionary.contains(word2)) {
					suggestions.add(word2);
				}
			}
		}

		return suggestions;
	}
}
