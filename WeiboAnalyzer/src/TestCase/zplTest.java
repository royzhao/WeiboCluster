package TestCase;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import weibo4j.Timeline;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.WeiboException;

import DataAnalyzer.dataAnalyzer;
import DataMining.*;

public class zplTest {

	//case1 测试数据获取模块
	@Test
	public void testMining() {
//		dataMining testdata = new dataMining();
//		testdata.mainDataMining();
		
	}
	
	//case2 微博中获取某一个人的全部微博
	@Test
	public void testGetAllDocbyOne(){
/*		Global.dBer.connectDB();
		Timeline tm = new Timeline();
		tm.client.setToken(Global.access_token);
		try {
			//StatusWapper status = tm.getUserTimeline();
			StatusWapper status=tm.getUserTimelineByUid(Global.uIDString);
			int i = 0;
			for(Status s : status.getStatuses()){
				i++;
				String conString ;
				if (s.getRetweetedStatus() == null)
					conString = s.getText();
				else {
					conString = (s.getRetweetedStatus().getText());
				}
				System.out.println("old"+conString);
				Global.dBer.addDoc(conString,i);
				System.out.println("utf:"+Global.dBer.convertCharset(conString));
			}
		} catch (WeiboException e) {
			e.printStackTrace();
		}
		Global.dBer.closeDB();
		*/
	}
	
	//case3 写入excel
	@Test
	public void testSplitDoc(){
		
	}
	
	//case4 建立词典
	@Test
	public void testBuildLexicon(){
		
	}
	
	//case4 建立倒排索引
	@Test
	public void testBuildPosting(){
		
	}
	

	//case5 连接数据库
	@Test
	public void testConSQL(){
		
/*		Global.dBer.connectDB();
		Statement statement = null;
		try {
			statement = Global.dBer.getCon().createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//更新数据库
		//String cmdString = "insert into doc(DocID,DocText) values(1,'"+new_conString+"');";
		String cmdString = "select count(*) from doc";
		//String cmdString = "insert into doc(DocID,DocText) values(1,'dasdad');";
		try {
			//statement.executeUpdate(cmdString);
			ResultSet set = statement.executeQuery(cmdString);
			int a = 0;
			if(set.next())
				a= set.getInt(1);
			System.out.println("a="+a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println(e.getErrorCode());
			//e.printStackTrace();
			
		}
		*/
	}
}
