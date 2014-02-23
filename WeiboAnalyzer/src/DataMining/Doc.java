package DataMining;

public class Doc {
	protected int DocID;
	protected String DocCon;
	
	public void setDocID(int num){
		DocID = num;
	}
	public void setCon(String con){
		DocCon = con;
	}
	public int getDocID(){
		return DocID;
	}
	public String getDocCon(){
		return DocCon;
	}
}
