package DataMining;

//这个是数据获取模块的主程序
public class dataMining {

	public boolean mainDataMining(){
		initDB();
		//初始化微博与人物队列
		if(loginWeibo())
		{
			process();
		}
		else {
			return false;
		}
		return false;
		
	}
	
	//登陆微博并且初始化人物队列
	public boolean loginWeibo(){
		People tmp = null;
		int size = Global.usedIDList.size();
		if(size == 0){
			tmp = new People(Global.uIDString);
		}
		else {
			tmp = new People(Global.usedIDList.get(size -1));
		}
		tmp.getFriends(Global.queue);
		return true;
		
	}
	
	public boolean process(){
		
		People tmp;
		//处理数据，建立倒排索引
		while(Global.queue.getCount() != 0){
			tmp = Global.queue.getOut();
			if(tmp == null)
				return false;
			//数据处理
			if(tmp.getDoc() == false)
				return false;
			tmp.getFriends(Global.queue);
			Global.usedIDList.add(tmp.getID());
			Global.dBer.insertUsedList(tmp.getID());
		}
		return true;
	}
	
	public boolean initDB(){
		Global.dBer.connectDB();
		Global.dBer.initUsedList();
		Global.dBer.initValue();
		return false;
	}
	
}
