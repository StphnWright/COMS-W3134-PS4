import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

public class KBestCounter<T extends Comparable<? super T>> implements KBest<T> {

	private final PriorityQueue<T> minQueue = new PriorityQueue<>();
	private final int k;

	public KBestCounter(int k) {
		this.k = k;
	}

	@Override
	public void count(T x) {
		minQueue.add(x);
		if (minQueue.size() > k) {
			minQueue.remove();
		}
	}

	@Override
	public List<T> kbest() {
		List<T> topK = new ArrayList<>();
		Comparator<T> comparator = (t1, t2) -> t2.compareTo(t1);
		PriorityQueue<T> maxQueue = new PriorityQueue<>(comparator);
		for (Iterator<T> i = minQueue.iterator(); i.hasNext();) {
			maxQueue.add(i.next());
		}
		while (! maxQueue.isEmpty()) {
			topK.add(maxQueue.remove());
		}
		return topK;
	}
}
