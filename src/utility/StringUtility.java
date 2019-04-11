package utility;

import utility.IConstant;

public class StringUtility {

	public static boolean isEmptyOrNull(String inputVal){
		if(inputVal == null || inputVal.trim().equals(IConstant.EMPTY_STRING))return true;
		return false;
	}
	
	public static String napraviFormatPrikaza(String...params){
		StringBuilder prikaz = new StringBuilder();
		if (params != null) {
		  for (String param : params) {
		    System.out.println(param);
		    
		    prikaz.append(param);
			prikaz.append(IConstant.DELIMITER);
		  }
		}
		String str = prikaz.toString().substring(0, prikaz.length()-1);
		return str;
	}
	
	public static int Converter(String str)
	{   
		int convrtr=0;
		if(str==null)
	    	{
				str="0";
	    	}
		else if((str.trim()).equals("null"))
	    	{
				str="0";
	    	}
		else if(str.equals(""))
	    	{
				str="0";
	    	}
		try{
				convrtr=Integer.parseInt(str);
	    	}
		catch(Exception e)
	    	{
	    	}
		return convrtr;
	}
}

