package epi.bitToy;

public class NegativeAndComplement {
	// -i and ~(i-1) equates
	static void doStuff() {
		for (int i = 0; i < 100; i++) {
			System.out.print((0 - i) + " - ");
			System.out.println(~(i - 1));
		}
	}

	public static void main(String[] args) {
		doStuff();
	}
}
