import java.io.IOException;
import java.util.List;
import java.util.Set;

public class SpellCheckerTest {

	public static void main(String[] args) throws IOException {
		SpellChecker checker = new SpellChecker("words.txt");
		List<String> incorrectWords = checker.getIncorrectWords("test.txt");
		System.out.println(incorrectWords);

		for (String word : incorrectWords) {
			Set<String> suggestions = checker.getSuggestions(word);
			if (!suggestions.isEmpty()) {
				System.out.println("Suggestions for " + word + ": " + suggestions);
			}
		}
	}
}
