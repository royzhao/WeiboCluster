package DataAnalyzer;

public class dataAnalyzer {
	public void mainAnalyzer(){
		Global.dBer.connectDB();
		zplKMeans kMeans;
		
		kMeans = new zplKMeans(20);
		kMeans.readVectorSet();
		kMeans.initRandom();
		kMeans.start();
		kMeans.showAll();
		Global.dBer.closeDB();
	}
}
