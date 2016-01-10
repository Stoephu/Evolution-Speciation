package dataHandler;

public class StringReader {
	public StringReader(){
		
	}
	public String readFromString(String sequence,int startPos,int numberChars){
		String string = sequence.substring((startPos-1)*numberChars,(startPos-1)*numberChars+numberChars);
		return string;
	}
	public int translateFromHex(String sequence){
		int number = Integer.valueOf(sequence,16);
		return number;
	}
	public int readHexStringToInt(String sequence,int startPos,int numberChars){
		String string = this.readFromString(sequence, startPos, numberChars);
		int number = this.translateFromHex(string);
		return number;
	}
}
