package TestCase;

import org.junit.Test;

import DataAnalyzer.dataAnalyzer;

public class AnalyzerTest {

	//case5 分析数据
	@Test
	public void testAnalyzer(){
		dataAnalyzer ss = new dataAnalyzer();
		ss.mainAnalyzer();
	}

}
