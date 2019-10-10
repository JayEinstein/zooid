package je.word;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.druid.pool.DruidDataSource;

import je.jdbc.sql.MySql;
import je.jdbc.sql.Sql;
import je.str.dispose.Sollar;

public class CutWord {
	
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("spring2.xml");
		Sollar bean = ctx.getBean(Sollar.class);
		System.out.println(bean.high(10));
	}

	public void cut(String s) {
		
		// 根据标点符号进行细切 ，。！
		
		// 过滤掉括号 （），括号内容在切为一个句子
		
		
	}
	
	
	/**
	 * 梯子塔 计算行数，行数等于 1+2+3+...+(n-1)+n = n*(n+1)/2
	 * @param n 
	 * @return
	 */
	public int hangshu(int n) {
		return n*(n+1)/2;
	}
	
	/**
	 * 梯子塔 砖数，等于各个行数之和 1+3+6+10+15+...+(n-1)*n/2+n*(n+1)/2 = n*(n+1)/4+n*(n+1)*(2*n+1)/12
	 * @param n 
	 * @return
	 */
	public int zongshu(int n) {
		return n*(n+1)/4+n*(n+1)*(2*n+1)/12;
	}
	
	public void 生成词汇(String s){
		char[] carr = s.toCharArray();
		int len = carr.length;
		
		
		
	}
	
	@Test
	public void ceshi() {
		List<Integer> list = new ArrayList<Integer>();
		for(int i = 1;i<=100;i++) {
			list.add(tizi(i));
		}
		for(int i = 0;i<list.size();i++) {
			if(i != 0 && i%10 == 0) {
				System.out.println();				
			}
			System.out.print(list.get(i) + "\t");		
		}
		
		System.out.println(zongshu(44));
	}
	
	public int tizi(int n) {
		int zishu = 0;
		for(int a = 1;a<=n;a++) {
			for(int i = a;i<=n;i++) {
				for(int j = a;j<=i;j++) {
//					System.out.print("*");
					zishu++;
				}
//				System.out.println();
			}
		}
		return zishu;
	}
	
	@Test
	public void 梯子塔() {
		int n = 100;
		int 字符数 = 0;
		int 行数 = 0; // (1+n)*(n/2)
		for(int a = 1;a<=n;a++) {
			for(int i = a;i<=n;i++) {
				for(int j = a;j<=i;j++) {
					System.out.print("* ");
					字符数++;
				}
				行数++;
				System.out.println();
			}
		}
		System.out.println(字符数);
		System.out.println(行数);
		
	}
	
	
	public void a() {
		String str1 = "T恤;https://blog.csdn.net/vpqtxzmzezeqjj9977";//中文汉字
		String str2 = "；https://blog.csdn.net/vpqtxzmzezeqjj9977";//中文分号
		String str3 = "https://blog.csdn.net/vpqtxzmzezeqjj9977";//英文
		int flage = 0;
		for (char c : str2.toCharArray()) {  
		         if (c >= 0x4E00 &&  c <= 0x9FA5){
		          flage = 1;
		          System.out.println("发现中文字符："+c);
		             break; //有一个中文字符就返回  
		         }
		    }
		if(flage!=1){
		    System.out.println("恭喜，没有发现中文字符！");
		}
	}
	
	
	private static final boolean isChinese(char c) {  
	       Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);  
	       if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS  
	               || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  
	               || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A  
	               || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION  
	               || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION  
	               || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {  
	           return true;  
	       }  
	       return false;  
	}
	
}
