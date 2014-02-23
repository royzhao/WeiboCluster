package TestCase;

import static org.junit.Assert.*;

import org.junit.Test;

import DataMining.dataMining;

public class GetWeiboTest {

		//case1 测试数据获取模块
		@Test
		public void testMining() {
			dataMining testdata = new dataMining();
			testdata.mainDataMining();
			
		}

}
