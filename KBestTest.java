public class KBestTest {

	public static void main(String[] args) {
		KBest<String> kBest = new KBestCounter<> (5);
		kBest.count("d");
		kBest.count("v");
		kBest.count("a");
		kBest.count("h");
		kBest.count("m");
		kBest.count("z");
		kBest.count("q");
		kBest.count("t");
		
		System.out.println(kBest.kbest());
	}
}
