package DataAnalyzer;

public class zplCluster {
	private docVector _mean;
	private int clusterNum;
	private TermVector _vector;

	
	public zplCluster(){
		_mean = new docVector(-1);
		_vector = null;
		clusterNum = 0;
	}
	public int getNum(){
		return clusterNum;
	}
	public boolean addDoc(docVector v){
		if(v !=null){
			_vector.addVector(v);
			clusterNum ++;
			return true;
		}
		return false;
			
	}
	public docVector getMean(){
		return _mean;
	}
	public TermVector getTerm(){
		return _vector;
	}
	public void UpdateMean() {
		
		int weidu = Global.weidu;
		double[] value = new double[weidu];
		for(int i = 0; i< weidu;i++){
			double tmp = 0;
			for(docVector v:_vector.getList()){
				tmp += v.getValue()[i];
			}
			value[i]= tmp/(double)weidu;
		}
		_mean.setValue(value);
		try{
			if(_mean.computeLength() ==0)
				throw new Exception();
			
		}catch (Exception e) {
			System.out.println("list len:"+_vector.getList().size());
			if(_vector.getList().size() == 0){
				System.out.println("+++++");
			}
		}
		
		
	}


	public boolean setCluster(TermVector _v) {
		if(_v != null){
			_vector = _v;
			System.out.println("list cluster len:"+_v.getList().size());
			UpdateMean();
			return true;
		}
		return false;

	}
}