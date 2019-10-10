package je.str.dispose;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import je.jdbc.sql.MySql;
import je.jdbc.sql.Sql;

/**
 * 
 * 切割，把长长的一串分成独立的小个体
 * 1.中文根据标点符号切割
 * 2.（）要单独处理一下
 * 
 * 判断有无标点，有，切割
 * 
 * 切割规则，需要定义一下
 * 
 * @author JayEinstein
 *
 */
public class StrDispose {
	private static String puna = "。？！，、；：「」『』‘’“”（）〔〕【】—…–．《》〈〉";
	
	@Test
	public void test() {
		entrance("红豆生南国，春来发几枝。随风潜入夜，花落知多少。");
	}

	/**
	 * 
	 * 
	 * 切割，把长长的一串分成独立的小个体
	 * 1.中文根据标点符号切割
	 * 2.（）要单独处理一下
	 * 
	 */
	public void entrance(String str) {
		
		char[] carr = str.toCharArray();
		
		List<List<Character>> section = new ArrayList<List<Character>>();
		
		List<Character> sentence = new ArrayList<Character>();
		for(int i = 0;i<carr.length;i++) {
			sentence.add(carr[i]);
			if(isChinesePunctuation(carr[i]) || i == carr.length-1) {
				section.add(sentence);
				if(i != carr.length-1) {
					sentence = new ArrayList<Character>();
				}
			}
		}
		
		char item;
		char prve;
		
		for (List<Character> sent : section) {
			
			for(int i = 0;i<sent.size();i++) {
				String s = "";
				item = ' ';
				for(int j = i;j<sent.size();j++) {
					
					prve = item;
					item = sent.get(j);

					s = s + item;
					
					synchronized (puna) {
						try {
							MySql.start();
							// 看看数据库里面有没有 字符
							String sql1 = "select id from str_library where name = '" + s +"'";
							
							// 没有就 insert
							String sql2 = "insert into str_library () values()";
							
							// 有就更新 update appear
							Sql.executeUpdate(sql2);
							
							// 如果单字符前一个不为空，则把对应关系加到 str_after
							if(prve != ' ') {
								String sql3 = "SELECT sa.sa_id FROM str_after sa " + 
										"WHERE sa.sl_id = (select sl_id from str_library where str = '?') " + 
										"and sa.slsa_id = (select sl_id from str_library where str = '?');";
								
								
								
							}
							
							
						} catch (Exception e) {
							e.printStackTrace();
						}
						
					}
				}
			}
			
		}
		
		
	}
	

	
	public void cut(List<List<Character>> section) {
		for (List<Character> sentence : section) {
			
		}
	}
	
	private static final boolean isChinesePunctuation(char c) {
		char[] punlist = puna.toCharArray();
		for (char d : punlist) {
			if(d == c) {
				return true;
			}
		}
		return false;
	}
	
	class Node{
		char item;
		char prev;
		
        Node(char element) {
            this.item = element;
            this.prev = item;
        }
	}
	
}
