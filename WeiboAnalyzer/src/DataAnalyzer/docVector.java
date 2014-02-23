package DataAnalyzer;

public class docVector {
	private int _itemNum;
	private int docID;
	private int _nearestCluster;
	private double _nearestDist;
	private double _clusterAssignment;
	private double[] _itemValue;
	private double[] dist;
	private int _k;
	
	public String getWeiboCon(){
		return Global.dBer.getDoc(docID);
	}
	public docVector(int _docID){
		this.docID = _docID;
		_k = Global._k;
		_itemNum = Global.weidu;
		_nearestCluster = -1;
		_nearestDist = Global.MAX;
		_clusterAssignment =0;
		_itemValue = new double[_itemNum];
		dist = new double[_k];
		
	}
	public void setAeqN(){
		_nearestDist = _clusterAssignment;
	}
	public void setclusterAssignment(double s){
		_clusterAssignment = s;
	}
	public int getnearestCluster(){
		return _nearestCluster;
	}
	public void setnearestCluster(){
		int cluster=0;
		for(int i=0; i<_k;i++){
			if(_nearestDist >= dist[i]){
				_nearestDist = dist[i];
				cluster = i;
			}
		}
		_nearestCluster = cluster;
	}
	public void setDist(int i,double dis){
		dist[i]= dis; 
	}
	public double getDist(int i){
		return dist[i];
	}

	public int getitemNum(){
		return _itemNum;
	}
	public void setitemNum(int num){
		_itemNum = num;
	}
	public void setValue(double[] value){
		_itemValue = value;
	}
	public double[] getValue(){
		return _itemValue;
	}
	public int getID(){
		return docID;
	}
	public double computeLength(){
		double s = 0;
		s = Math.sqrt(TermVector.mult2Vector(this, this));
			
		return s;
	}
	public boolean isEqAssig(){
		if (_clusterAssignment == _nearestDist)
			return true;
		return false;
	}
}