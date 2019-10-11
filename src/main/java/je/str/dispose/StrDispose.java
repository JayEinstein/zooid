package je.str.dispose;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import je.jdbc.sql.MySql;
import je.jdbc.sql.Sql;
import je.project.mapper.StrLibraryMapper;
import je.project.mapper.StrLinkMapper;
import je.project.mapper.StrSceneMapper;
import je.project.pojo.StrLibrary;
import je.project.pojo.StrLink;
import je.project.pojo.StrScene;

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
	
	@Test
	public void test2() {
		ApplicationContext startSpring = MySql.startSpring();
		StrLibraryMapper strLibraryMapper = startSpring.getBean(StrLibraryMapper.class);
		StrLibrary strlib = new StrLibrary();
		strlib.setStr("红豆");
		List<StrLibrary> select = strLibraryMapper.select(strlib);
		System.out.println(select.isEmpty());
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
		
		char prev;
		char item;
		char next;
		
		for (List<Character> sent : section) {
			// TODO 区分复读机
			for(int i = 0;i<sent.size();i++) {
				StringBuilder s = new StringBuilder();
				next = ' ';
				item = ' ';
				prev = ' ';
				for(int j = i;j<sent.size();j++) {
					
					prev = item;
					item = next;
					next = sent.get(j);

					s.append(next);
					
					synchronized (puna) {
						try {
							
							ApplicationContext spring = MySql.startSpring();
							
							// 看看library里面有没有字符
							StrLibraryMapper strLibraryMapper = spring.getBean(StrLibraryMapper.class);
							StrLibrary strlib = new StrLibrary();
							String readStr = s.toString();
							List<StrLibrary> strList = strLibraryMapper.selectStr(readStr);
							if(strList.isEmpty()) { // 为空则 添加字库 library
								strlib.setStr(readStr);
								strlib.setLen(s.length());
								strLibraryMapper.insert(strlib);
							}else {
								strlib = strList.get(strList.size()-1);
							}
							
							// 写入字符场景
							StrSceneMapper strSceneMapper = spring.getBean(StrSceneMapper.class);
							StrScene scene = new StrScene();
							scene.setSl_id(strlib.getSl_id());
							scene.setScn_id("1");// 先固定1
							List<StrScene> scelist = strSceneMapper.select(scene);
							
							if(scelist.isEmpty()) { 
								scene.setAppear(1);
								strSceneMapper.insert(scene);
							} else {
								// 不空就计数
								StrScene strScene = scelist.get(scelist.size()-1);
								strScene.setAppear(strScene.getAppear()+1);
								strSceneMapper.update(strScene);
							}
							
							// 单字链表
							if(item != ' ') { // item不为空
								StrLinkMapper strLinkMapper = spring.getBean(StrLinkMapper.class);
								StrLibrary str_item = new StrLibrary();
								str_item.setStr(String.valueOf(item));
								List<StrLibrary> itemlist = strLibraryMapper.select(str_item);
								str_item = itemlist.get(itemlist.size()-1);
							
								StrLink slnk = new StrLink();
								slnk.setItem_id(str_item.getSl_id());
								if(prev != ' ') {
//									slnk.setPrev_id(prev_id);
								}
//								slnk.setNext_id(next_id);
								
							}
							StrLibrary str_prev = new StrLibrary();
							
							
//							slnk.setItem_id(item_id);
//							strLinkMapper.select(slnk);
							
							
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
