package com.test.cloudcomputing.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class NumberTool {
	
	private static NumberTool tool;
	
	private NumberTool(){
		
	}
	
	public static NumberTool getInstance(){
		if(tool == null){
			tool = new NumberTool();
		}
		return tool;
	}

	public int getPrice(){
		Random ran = new Random();
		int num = ran.nextInt(1000);
		return num;
	}
	public String getUniqueName(int length, boolean symbol)  
            throws Exception {  
        Random ran = new Random();  
        int num = ran.nextInt(61);  
        String returnString = "";  
        String str = "";  
        for (int i = 0; i < length;) {  
            if (symbol)  
                num = ran.nextInt(70);  
            else  
                num = ran.nextInt(61);  
            str = strArray[num];  
            if (!(returnString.indexOf(str) >= 0)) {  
                returnString += str;  
                i++;  
            }  
        }  
        return returnString;  
    }  
	
    public String getUniqueString(int length, boolean symbol) throws Exception {  
        Random ran = new Random();  
        int num = ran.nextInt(61);  
        Calendar d = Calendar.getInstance();  
        Date nowTime = d.getTime();  
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");  
        String returnString = sf.format(nowTime);  
        String str = "";  
        for (int i = 0; i < length;) {  
            if (symbol)  
                num = ran.nextInt(70);  
            else  
                num = ran.nextInt(61);  
            str = strArray[num];  
            if (!(returnString.indexOf(str) >= 0)) {  
                returnString += str;  
                i++;  
            }  
        }  
        return returnString;  
    }  
  
    /** 
     * 给生成唯一字符串使用 
     */  
    private static String[] strArray = new String[] { "a", "b", "c", "d", "e",  
            "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r",  
            "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E",  
            "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",  
            "S", "T", "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4",  
            "5", "6", "7", "8", "9", "!", "@", "#", "$", "%", "^", "&", "(",  
            ")" };  
}
