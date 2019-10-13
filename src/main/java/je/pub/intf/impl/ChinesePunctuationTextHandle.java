package je.pub.intf.impl;

import java.util.ArrayList;
import java.util.List;

import je.pub.intf.TextHandle;

public class ChinesePunctuationTextHandle implements TextHandle {
	private static String puna = "。？！，、；：「」『』‘’“”（）〔〕【】—…–．《》〈〉  　";

	@Override
	public List<List<Character>> handle(String text) {
		char[] carr = text.toCharArray();
		List<Character> sentence = new ArrayList<Character>();
		List<List<Character>> section = new ArrayList<List<Character>>();
		for(int i = 0;i<carr.length;i++) {
			sentence.add(carr[i]);
			if(isChinesePunctuation(carr[i]) || i == carr.length-1) {
				section.add(sentence);
				if(i != carr.length-1) {
					sentence = new ArrayList<Character>();
				}
			}
		}
		return section;
	}
	
	@Override
	public int handleSize() {
		return 7;
	}
	
	public static final boolean isChinesePunctuation(char c) {
		char[] punlist = puna.toCharArray();
		for (char d : punlist) {
			if(d == c) {
				return true;
			}
		}
		return false;
	}


}
