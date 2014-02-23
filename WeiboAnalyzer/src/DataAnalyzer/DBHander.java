package DataAnalyzer;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHander {
	private int docCount ;
	private int lexCount ;
	private Connection conn;
	
	/**
	 * 数据库处理者
	 */
	public DBHander(){
		docCount = 0;
		lexCount = 0;
		conn = null;
	}
	public String convertCharset(String old){
		try {
			String strUtf = new String(old.getBytes(),"UTF-8");
			String strgbString = new String(old.getBytes(),"gb2312");
			System.out.println("utf:"+strUtf);
			System.out.println("gb:"+strgbString);
			return new String(old.getBytes(),"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public Connection getCon(){
		return conn;
	}
	public boolean connectDB(){
		String driver = "com.mysql.jdbc.Driver";

		// URL指向要访问的数据库名scutcs

		String url = "jdbc:mysql://127.0.0.1:3306/zplweibo";

		// MySQL配置时的用户名

		String user = "root";

		// Java连接MySQL配置时的密码

		String password = "123456";

		try {

			// 加载驱动程序
	
			Class.forName(driver);
	
			// 连续数据库
	
			conn = DriverManager.getConnection(url, user, password);
	
			
			if(!conn.isClosed()){
				System.out.println("Succeeded connecting to the Database!");
				getWeidu();
				return true;
			}
			
		}
		catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		}
		return false;
	}
	public int getDocCount(){
		return docCount;
	}
	public boolean closeDB(){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
	
	public void getWeidu(){
		String cmdString = "select * from lexcion";
		Statement ste;
		try {
			Global.weidu = 0;
			ste = conn.createStatement();
			ResultSet resultSet = ste.executeQuery(cmdString);
			while(resultSet.next()){
				Global.weidu ++;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		lexCount = Global.weidu;
	}
	public double getIDf(int lexID){
		double idf = 0;
		String idfString;
		String cmdString = "select IDF from lexcion where lexiconID="+lexID+";";
		Statement ste;
		try {
			ste = conn.createStatement();
			ResultSet resultSet = ste.executeQuery(cmdString);
			if(resultSet.next()){
				idfString = resultSet.getString("IDF");
				idf =Double.parseDouble(idfString);
			}

			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return idf;
	}
	public boolean zeroVector(double[] s){
		for(int i =0; i< lexCount;i++){
			if(s[i] != 0.0){
				return false;
			}
		}
		return true;
	}
	public docVector buildDocVector(int docID){
		String cmdString = "select * from postingitem where docID="+ docID+";";
		ResultSet resultSet = null;
		docVector doc = new docVector(docID);
		double[] value = new double[lexCount];
		int lexID = 0;
		int tf;
		double idf;
		for(int i =0; i<lexCount;i++){
			value[i]= 0; 
		}
		try {

			Statement statement = conn.createStatement();
			resultSet = statement.executeQuery(cmdString);
			while (resultSet.next()) {
				lexID = resultSet.getInt("lexiconID");
				tf = resultSet.getInt("tf");
				idf = getIDf(lexID);
				
				value[lexID -1] = tf*idf;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block

			e.printStackTrace();
			System.out.println("lex ID:"+lexID);
		}
		if(zeroVector(value) == false){
			doc.setitemNum(lexCount);
			doc.setValue(value);
		}
		else {
			return null;
		}
		
		return doc;
	}
	
	public TermVector buildTermVector(){
		TermVector vector = new TermVector();
		docVector tmp = null;
		Global.docNum = 0;
		String cmdString = "select docID from doc";
		Statement ste;
		try {
			ste = conn.createStatement();
			ResultSet resultSet = ste.executeQuery(cmdString);
			while(resultSet.next()){
				int docID = resultSet.getInt("docID");
				tmp = buildDocVector(docID);
				if(tmp != null){
					vector.addVector(tmp);
					Global.docNum ++;
					System.out.println("docnum"+Global.docNum);
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return vector;
	}
	
	public String getDoc(int docID){
		String cmdString = "select docText from doc where docid="+docID+";";
		Statement ste;
		String temp = null;
		try {
			ste = conn.createStatement();
			ResultSet resultSet = ste.executeQuery(cmdString);
			while(resultSet.next()){
				temp = resultSet.getString("docText");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return temp;
	}
}
