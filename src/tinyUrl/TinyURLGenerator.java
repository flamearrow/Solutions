package tinyUrl;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class TinyURLGenerator {
	public static String base = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ+/";
	// in real world, generate curIndex from a MySQL ticket service
	static int curIndex = 1;
	Map<Integer, String> _backMap;

	private static int TINYURL_LENGTH = 6;

	public TinyURLGenerator() {
		_backMap = new HashMap<Integer, String>();
	}

	public String getTinyURL(String url) {
		_backMap.put(curIndex, url);
		String ret = to64Base(curIndex);
		curIndex++;
		return ret;
	}

	private String to64Base(int curIndex) {
		StringBuilder sb = new StringBuilder();
		while (curIndex > 0) {
			sb.insert(0, base.charAt(curIndex % 64));
			curIndex /= 64;
		}
		// make sure each tiny url is of length 5
		if (sb.length() < TINYURL_LENGTH) {
			int padding = TINYURL_LENGTH - sb.length();
			for (int i = 0; i < padding; i++) {
				sb.insert(0, '0');
			}
		}
		return sb.toString();
	}

	public String getOriginalURL(String tinyURL) {
		int index = 0;
		for (int i = 0; i < TINYURL_LENGTH; i++) {
			index += Math.pow(64, TINYURL_LENGTH - 1 - i)
					* base.indexOf(tinyURL.charAt(i));
		}
		return _backMap.get(index);
	}

	public static void main(String[] args) {
		TinyURLGenerator g = new TinyURLGenerator();
		RandomStringGenerator sg = new RandomStringGenerator(50);
		for (int i = 0; i < 1000000; i++) {
			String originalURL = sg.nextString();
			String tinyURL = g.getTinyURL(originalURL);
			String decodedURL = g.getOriginalURL(tinyURL);
			System.out.println("originalURL: " + originalURL + " - tinyURL:"
					+ tinyURL + " - decodedURL:" + decodedURL);
			if (!originalURL.equals(decodedURL))
				throw new RuntimeException("urls don't match!");
		}
		//		String[] inputs = { "mlgb", "bglm", "wawafewafeawggfaewfadgadgfd",
		//				"feawfaewfgsgrgfawef", "github.com/flamearrow" };
		//		String[] tinyUrls = new String[inputs.length];
		//		for (int i = 0; i < inputs.length; i++) {
		//			tinyUrls[i] = g.getTinyURL(inputs[i]);
		//			System.out.println("originalURL: " + inputs[i] + " - tinyURL:"
		//					+ tinyUrls[i]);
		//		}
		//
		//		for (int i = 0; i < inputs.length; i++) {
		//			String decodedURL = g.getOriginalURL(tinyUrls[i]);
		//			System.out.println("originalURL: " + inputs[i] + " - tinyURL:"
		//					+ tinyUrls[i] + " - decodedURL:" + decodedURL);
		//		}
	}
}

class RandomStringGenerator {
	Random rand;

	private int _size;

	public RandomStringGenerator(int size) {
		rand = new Random();
		_size = size;
	}

	String nextString() {
		StringBuilder sb = new StringBuilder();
		int size = rand.nextInt(_size);
		for (int i = 0; i < size; i++) {
			sb.append(TinyURLGenerator.base.charAt(rand.nextInt(64)));
		}
		return sb.toString();
	}
}
