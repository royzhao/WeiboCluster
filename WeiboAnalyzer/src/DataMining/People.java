package DataMining;


import weibo4j.Friendships;
import weibo4j.Timeline;
import weibo4j.model.Status;
import weibo4j.model.StatusWapper;
import weibo4j.model.User;
import weibo4j.model.UserWapper;
import weibo4j.model.WeiboException;

public class People {
	/**
	 * 人物
	 */
	private String _uID;
	
	private Friendships fm;
	private Timeline tm;
	public People(String ID){
		this._uID = ID;
	}
	public String getID(){
		return _uID;
	}
	
	//得到朋友列表，并加入人物队列
	public boolean getFriends(PeopleQueue queue) {
		fm = new Friendships();
		fm.client.setToken(Global.access_token);
		try {
			UserWapper users = fm.getFriendsByID(_uID);
			for(User u : users.getUsers()){
				int flag = 0;
				for(String t:Global.usedIDList){
					if(t.equals(u.getId()))
					{
						flag = 1;
					}
				}
				if(flag == 0)
				{
					//朋友入队
					People tmpPeople = new People(u.getId());
					if(queue.putIn(tmpPeople) == false){
						//记录断点
						throw new WeiboException("putinQueue Error!");
					}
				}
				
			}
		} catch (WeiboException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return true;
	}

	public boolean getDoc() {
		tm = new Timeline();
		tm.client.setToken(Global.access_token);
		Global.usedIDList.add(_uID);
		try {
			//StatusWapper status = tm.getUserTimeline();
			StatusWapper status=tm.getUserTimelineByUid(_uID);
			for(Status s : status.getStatuses()){
				String conString ;
				if (s.getRetweetedStatus() == null)
					conString = s.getText();
				else {
					conString = (s.getRetweetedStatus().getText());
				}
				if(Global.markNoMean(conString) == false){
					//加入文档词典
					if(Global.dBer.addDoc(s) == false)
						throw new WeiboException("addDoc wrong");
					//进行分词
					System.out.println("doc:"+conString);
					if (s.getRetweetedStatus() == null)
						Global.split.setDoc(s.getText());
					else {
						Global.split.setDoc(s.getRetweetedStatus().getText());
					}
					Global.split.beginSplit();
				}
			}
		} catch (WeiboException e) {
			e.printStackTrace();
			System.out.println("Error:"+e.getMessage());
			return false;
		}
		return true;
	}
}