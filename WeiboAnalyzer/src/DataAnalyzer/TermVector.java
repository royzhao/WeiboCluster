package DataAnalyzer;

import java.util.LinkedList;
import java.util.List;

public class TermVector {
	private int _docNum;
	private List<docVector> _docVset;
	
	public List<docVector> getList(){
		return _docVset;
	}
	
	public void clearAll(){
		_docNum = 0;
		_docVset.clear();
	}
	public TermVector(docVector v){
		_docNum = 1;
		_docVset = new LinkedList<docVector>();
		_docVset.add(v);
	}
	public TermVector(){
		_docNum = 0;
		_docVset = new LinkedList<docVector>();
	}
	public void addVector(docVector v){
		_docNum ++;
		_docVset.add(v);
	}
	public void removeVector(docVector v){
		_docVset.remove(v);
	}
	public int getdocNum(){
		return _docNum;
	}


	public static double getDist(docVector aV1, docVector aV2) {
		double a1 = 0;
		double a2 = 0;
		try {
			a1 = aV1.computeLength();
			a2 = aV2.computeLength();
			if(a1 == 0 || a2 == 0){
				throw new Exception();
			}
		}
		catch (Exception e) {
		}

		return 1-mult2Vector(aV1, aV2)/(a1*a2);
	}
	public static double mult2Vector(docVector aV1, docVector aV2){
		int num = aV1.getitemNum();
		double tem= 0 ;
		for(int i =0; i< num; i++ ){
			tem += aV1.getValue()[i] * aV2.getValue()[i];  
		}
		return tem;
	}
}