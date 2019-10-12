package je.str.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import je.str.dispose.StrDispose;

public class InputStr {

	public static void main(String[] args) throws Exception {
		List<String> files = getFiles("E:\\github\\GPT-src\\THUCTC\\THUCNews");
		for (String file : files) {
			readBook(file);
		}
	}
	
	public static void readBook(String file) throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
      BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(file)), "UTF-8"));
		String lineTxt = null;
		while ((lineTxt = br.readLine()) != null) {
			
			StrDispose sd = new StrDispose();
			sd.entrance(lineTxt);
		}
		br.close();
	}
	
    public static List<String> getFiles(String path) {
        List<String> files = new ArrayList<String>();
        File file = new File(path);
        File[] tempList = file.listFiles();

        for (int i = 0; i < tempList.length; i++) {
            if (tempList[i].isFile()) {
                files.add(tempList[i].toString());
                //文件名，不包含路径
                //String fileName = tempList[i].getName();
            }
            if (tempList[i].isDirectory()) {
                //这里就不递归了，
            }
        }
        return files;
    }
}
