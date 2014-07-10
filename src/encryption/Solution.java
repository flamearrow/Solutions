package encryption;

//One classic method for composing secret messages is called a square code.  The spaces are removed from the english text and the characters are written into a square (or rectangle). The width and height of the rectangle have the constraint,
//
//floor(sqrt( len(word) )) <= width, height <= ceil(sqrt( len(word) ))
//
//Among the possible squares, choose the one with the minimum area.
//
//In case of a rectangle, the number of rows will always be smaller than the number of columns. For example, the sentence "if man was meant to stay on the ground god would have given us roots" is 54 characters long, so it is written in the form of a rectangle with 7 rows and 8 columns. Many more rectangles can accomodate these characters; choose the one with minimum area such that: length * width >= len(word)
//
//                ifmanwas
//                meanttos        
//                tayonthe
//                groundgo
//                dwouldha
//                vegivenu
//                sroots
//
//The coded message is obtained by reading the characters in a column, inserting a space, and then moving on to the next column towards the right. For example, the message above is coded as:
//
//imtgdvs fearwer mayoogo anouuio ntnnlvt wttddes aohghn sseoau
//
//You will be given a message in English with no spaces between the words.The maximum message length can be 81 characters. Print the encoded message.
//
//Here are some more examples:
//
//Sample Input:
//
//haveaniceday
//
//Sample Output:
//
//hae and via ecy
//
//Sample Input:
//
//feedthedog    
//
//Sample Output:
//
//fto ehg ee dd
//
//Sample Input:
//
//chillout
//
//Sample Output:
//
//clu hlt io

public class Solution {
	// the idea to create a minimum rectangle is first get a min width, 
	// start from height = width and increment height until the area can hold the entire string 
	static void doPrint(String word) {
		int width = (int) Math.sqrt(word.length());
		while (width * width < word.length()) {
			width++;
		}
		int height = width;

		while (height * width < word.length()) {
			height++;
		}
		char[] chars = word.toCharArray();
		boolean first = true;
		for (int i = 0; i < height; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < width; j++) {
				if ((i + j * height) >= chars.length)
					break;
				sb.append(chars[i + j * height]);
			}
			if (first) {
				System.out.print(sb.toString());
				first = false;
			} else {
				System.out.print(" " + sb.toString());
			}
		}
	}

	public static void main(String[] args) {
		String word = "feedthedog";
		doPrint(word);
	}
}
