package je.str.dispose;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import je.jdbc.sql.MySql;
import je.project.mapper.CharRepetMapper;
import je.project.mapper.SntRepetMapper;
import je.project.mapper.StrLibraryMapper;
import je.project.mapper.StrLinkMapper;
import je.project.mapper.StrSceneMapper;
import je.project.pojo.CharRepet;
import je.project.pojo.SntRepet;
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
	private static String puna = "。？！，、；：「」『』‘’“”（）〔〕【】—…–．《》〈〉  ";
	private String scn_id = "1";
	
	@Test
	public void test() {
		entrance("爷爷年轻的时候可是超强的哦。");
		
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
		
		ApplicationContext spring = MySql.startSpring();
		for (List<Character> sent : section) {
			String outPuna = outPuna(sent);
			StrSceneMapper ssMapper = spring.getBean(StrSceneMapper.class);
			StrScene sscn = ssMapper.selectAlibrary(outPuna);
			
			if(sscn != null) {
				LinkNode node = new LinkNode(null,null,null); 
				for(int i = 0;i<sent.size();i++) {
					// 取单字
					StrLibraryMapper strLibraryMapper = spring.getBean(StrLibraryMapper.class);
					StrLibrary strlib = strLibraryMapper.selectStr(String.valueOf(sent.get(i)));
					node.putNode(strlib.getSl_id());
					if(node.item != null) {
						entryLinkRepet(spring, node);
						if(i == sent.size()-1) {
							node.putNode(null);
							entryLinkRepet(spring, node);
						}
					}
					StringBuilder sb = new StringBuilder();
					for(int j = i;j<sent.size();j++) {
						// 写记忆
						sb.append(sent.get(j));
						StrSceneMapper strSceneMapper = spring.getBean(StrSceneMapper.class);
						StrScene sscen = strSceneMapper.selectAlibrary(sb.toString());
						entryVocRepet(spring, sscen);
					}
				}
			}else {
				LinkNode node = new LinkNode(null, null, null);
				for(int i = 0;i<sent.size();i++) {
					StrLibrary slib = entryLibrary(spring,String.valueOf(sent.get(i)));
					node.putNode(slib.getSl_id());
					if(node.item != null) {
						entryLink(spring, node);
						if(i == sent.size()-1) {
							node.putNode(null);
							entryLink(spring, node);
						}
					}
					
					StringBuilder sb = new StringBuilder();
					for(int j = i;j<sent.size();j++) {
						sb.append(sent.get(j));
						StrLibrary strlib = entryLibrary(spring,sb.toString());
						entryVoc(spring, strlib);
					}
				}
			}
		}
	}
		
	
	/**
	 * 写字库
	 * @param spring
	 * @param s
	 * @return
	 */
	public StrLibrary entryLibrary(ApplicationContext spring, String s) {
		// ## 添字库
		// 看看library里面有没有字符
		StrLibraryMapper strLibraryMapper = spring.getBean(StrLibraryMapper.class);
		StrLibrary strlib = new StrLibrary();
		String readStr = s.toString();
		strlib.setStr(readStr);
		List<StrLibrary> strList = strLibraryMapper.select(strlib);
		if(strList.isEmpty()) { // 为空则 添加字库 library
			strlib.setLen(s.length());
			strLibraryMapper.insert(strlib);
		}else {
			strlib = strList.get(strList.size()-1);
		}
		return strlib;
	}
	
	/**
	 * 写经验
	 * @param spring
	 * @param strlib
	 */
	public void entryVoc(ApplicationContext spring,	StrLibrary strlib) {
		// ## 记词汇
		// 写入字符场景
		StrSceneMapper strSceneMapper = spring.getBean(StrSceneMapper.class);
		StrScene scene = new StrScene();
		scene.setSl_id(strlib.getSl_id());
		scene.setScn_id(scn_id);// 先固定1
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
	}
	
	/**
	 * 复读机，传入StrScene的主键进行新增或者更新
	 * @param spring
	 * @param sscn
	 */
	public void entryVocRepet(ApplicationContext spring, StrScene sscn) {
		SntRepetMapper sntRepetMapper = spring.getBean(SntRepetMapper.class);
		SntRepet sr = new SntRepet();
		sr.setSs_id(sscn.getSs_id());
		List<SntRepet> srL = sntRepetMapper.select(sr);
		if(srL.isEmpty()) {
			sr.setAppear(1);
			sntRepetMapper.insert(sr);
		}else {
			sr = srL.get(srL.size()-1);
			sr.setAppear(sr.getAppear()+1);
		}
	}
	
	/**
	 * 拼字链
	 * @param spring
	 * @param node
	 */
	public void entryLink(ApplicationContext spring, LinkNode node) {
		// ## 加字链
		
		if(node.item != null) {
			StrLink itemNode = new StrLink();
			itemNode.setItem_id(node.item);
			itemNode.setPrev_id(node.prev);
			itemNode.setNext_id(node.next);
		
			StrLinkMapper strLinkMapper = spring.getBean(StrLinkMapper.class);
			List<StrLink> linklist = strLinkMapper.select(itemNode);
			if(linklist.isEmpty()) {
				itemNode.setScn_id(scn_id);
				itemNode.setAppear(1);
				strLinkMapper.insert(itemNode);
			}else {
				StrLink strLink = linklist.get(linklist.size()-1);
				strLink.setAppear(strLink.getAppear()+1);
				strLinkMapper.update(strLink);
			}
			
		}
	
	}
	
	public void entryLinkRepet(ApplicationContext spring, LinkNode node) {
		StrLink slk = new StrLink();
		slk.setItem_id(node.item);
		slk.setPrev_id(node.prev);
		slk.setNext_id(node.next);
		
		StrLinkMapper strLinkMapper = spring.getBean(StrLinkMapper.class);
		List<StrLink> slinkL = strLinkMapper.select(slk);
		slk = slinkL.get(slinkL.size()-1);
		
		CharRepetMapper charRepetMapper = spring.getBean(CharRepetMapper.class);
		CharRepet cr = new CharRepet();
		cr.setSlnk_id(slk.getSlnk_id());
		List<CharRepet> crL = charRepetMapper.select(cr);
		if(crL.isEmpty()) {
			cr.setAppear(1);
			charRepetMapper.insert(cr);
		}else {
			cr = crL.get(crL.size()-1);
			cr.setAppear(cr.getAppear()+1);
			charRepetMapper.update(cr);
		}
	}
	
	private static class LinkNode{
		String item;
		String next;
		String prev;
		
		LinkNode(String prev, String item, String next){
			this.prev = prev;
			this.item = item;
			this.next = next;
		}
		
		public void putNode(String node){
			this.prev = this.item;
			this.item = this.next;
			this.next = node;
		}
	}
	
	public String outPuna(List<Character> list) {
		StringBuffer sb = new StringBuffer();
		for (Character c : list) {
			if(!isChinesePunctuation(c)) {
				sb.append(c);
			}
		}
		return sb.toString();
	}
	
	public String noutPuna(List<Character> list) {
		StringBuffer sb = new StringBuffer();
		for (Character c : list) {
			sb.append(c);
		}
		return sb.toString();
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
