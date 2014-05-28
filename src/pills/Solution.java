package pills;

// You have n whole pills in a jar, on each day, you take a random pill
// if it's a whole pill, you break it into two pieces, eat one half and drop another half back
// if it's a half pill, you eat it.
// Calculate the possibility of picking up an half pill on day d
public class Solution {
	// starting from state w(hole) and h(alf), the probability we take a half pill on day d
	double halfProb(int w, int h, int d, double[][][] back) {
		if (back[w][h][d] > 0)
			return back[w][h][d];
		if (d == 1) {
			return (double) h / (w + h);
		} else {
			if (w == 0) {
				if (h >= d)
					return 1;
				else
					return 0;
			}
			double p = 0;
			p += (double) w / (w + h) * halfProb(w - 1, h + 1, d - 1, back);
			if (h > 0)
				p += (double) h / (w + h) * halfProb(w, h - 1, d - 1, back);
			back[w][h][d] = p;
			return p;
		}
	}

	public static void main(String[] args) {
		System.out.println(new Solution().halfProb(100, 0, 100,
				new double[100 + 1][100 + 1][200 + 1]));
	}
}
