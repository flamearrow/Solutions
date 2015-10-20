package countPrimes;

import java.util.HashMap;
import java.util.Map;

public class UniqueWordAbbreviation {

	public static void main(String[] args) {
		String[] dictionary = { "eersynoiyqkqubhdd", "ylz",
				"yldongowlrnsafafcgmz", "rxcjc", "dvgdtnknareecongydc",
				"ixiwz", "erjfyctsla", "xovvrnbbvivbsuhb",
				"bpslbpzbwphhwvhtcr", "wptflnqkvekpkt", "hwoiniqvfe", "dgidjd",
				"ecgsghxuesvqmhxe", "kfgppbbvwfyp", "qntzitqjuazrqwz",
				"yjbycoyngfrreiyibdsk", "nguqbtdistlgicmjfrs", "yjqrqibgp",
				"avulamdverhdpkpqjtae", "cbwvtowh", "yhgvjnvo",
				"rihywmuspvzvp", "rbkpetmovkwj", "jtsehilffmfkicusup",
				"ficdolmdtvk", "qwldknj", "hseogl", "pdyxrfdxekk",
				"xnrliooqnsqfzwgd", "utnumabyrkasiizsjx", "urygh", "odj",
				"nttcedxgpkjqzwfb", "gxs", "rkizfdlfmm", "pjlz", "fvjm",
				"edkozzeuhxp", "pbjcmakwqkdm", "ppuhqdpaokitaowrzkfh",
				"yimgbxllumgkbqadjjqf", "sqsytssfjbaldz", "llkw", "aliw",
				"hagvoxjnuuhsafkmxww", "hahndehh", "fqmrjyuogqpxyv",
				"gnzbcrikf", "auopjpwsepqwmend", "xfgsbfafytrrkyevtz",
				"acrtfozvjdvg", "hspkwabiheogyk", "uvlcpqien", "eaamufqeal",
				"wsvuyeysox", "oywhf", "kasdlmnj", "fjpryefc", "ftdqq",
				"ftfqzqqig", "dloh", "tleszaz", "yajyoyaxmmos",
				"zbobgedfdpacbkzmxt", "lmcj", "dtjonkbwsg",
				"xeiqjxvsfjdfddclnso", "gpeutivfqwzfyrtax", "wjoo",
				"pptzwdcynnfpnirfkfo", "wsudzgwuouof", "ykjmbtoafrjjsehckh",
				"oqhamskusmqofrsgkqfv", "yifmkodzvk", "vmufzdavpwjagmlcv",
				"dtamuegujvtzdxui", "nxdyotptdjdhst", "rsthatscf",
				"kdpwhmjtnkunabzaxv", "ggzytma", "sdypdz", "xncvkmcddpkhkoalh",
				"qnjndoizypqqqxewgla", "czc", "ojhzmxxceltwzg",
				"hmwvynbzpuebokl", "weuuontazzovia", "ohwqtugyirw", "lrtftghr",
				"fngstcishaseslmb", "athpsapnlyx", "tcdnqc", "fjfyvg",
				"fneurgd", "xddiwjfbshgfbbejmpe", "ynscraxwlwsqhsioe",
				"eaderhxrlwrjpp", "wpnlrfxgnbfpuuiggsvo", "ogqmzw", "xai",
				"fdtbvhaosybjczyfcsdx", "abbcbqhcuoiaggs", "qtdwhsqqjt",
				"dqdvabloavvjhunafwhw", "gcpqevfuos", "hipvttjbniv", "acheeyf",
				"seqrnvez", "hszzzvbvmhjg", "kkwpshwuvsrbjqm", "jqxo",
				"sukagbkkrvbquzkfsj", "axbrmcroycbyykkdhl", "zrtshq",
				"cgwssttvz", "nbwccbisxtkccgmkmped", "ivplojduvs",
				"wmblfkhtnj", "jeoodscttkmjrszzjgh", "qmadddn", "ssdauwepilwi",
				"wghuntzaltedkacttafj", "rxojnfrleq", "qzkuejnvhncjzc",
				"cromyllbcleqipqaitzd", "yjdzifptqtcmrfyjrfj", "svinvs", "uftn" };
		ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
		System.out.println(vwa.isUnique("vlfdxuv"));

	}
}

class ValidWordAbbr {
	Map<String, String> dict;
	private static final String DUMMY = "---";

	public ValidWordAbbr(String[] dictionary) {
		dict = new HashMap<>();
		for (String word : dictionary) {
			String key = getKey(word);
			if (dict.containsKey(key)) {
				dict.put(key, DUMMY);
			} else {
				dict.put(key, word);
			}
		}
		for (Map.Entry<String, String> e : dict.entrySet()) {
			System.out.println(e.getKey() + "->" + e.getValue() + "\n");
		}
	}

	String getKey(String word) {
		char head = word.charAt(0);
		char tail = word.charAt(word.length() - 1);
		String mid = "";
		if (word.length() >= 3) {
			mid = "" + (word.length() - 2);
		}
		return head + mid + tail;
	}

	public boolean isUnique(String word) {
		String key = getKey(word);
		return !dict.containsKey(key) || dict.get(key).equals(word);
	}
}