package DataMining;


public class PeopleQueue {
	/**
	 * 人物队列
	 */
	private People[] _peopleList;
	private int _head;
	private int _rear;
	private static int MAXQUEUELEN = 100000;
	private static int B_Y	= 1;
	private static int B_N	= 0;
	private int count;
	private People breakPoint;
	
	//判断是否需要设置断点
	private int flag;
	
	public void needBreak(){
		flag = B_Y;
	}
	public boolean isBreak(){
		if (flag == B_Y)
			return true;
		return false;
	}
	public int getCount(){
		return count;
	}
	public People getBreak(){
		return breakPoint;
	}
	public void setBreak(People tmp){
		breakPoint = tmp;
	}
	public PeopleQueue(){
		_peopleList = new People[MAXQUEUELEN];
		_head = 0;
		_rear = 0;
		count = 0;
		breakPoint = null;
		flag = B_N;
	}
	public int getMaxQueueLen(){
		return MAXQUEUELEN;
	}
	public boolean putIn(People aInfo) {
		for(String s:Global.usedIDList){
			if (s.equals(aInfo.getID()))
				return true;
		}
		if (count != MAXQUEUELEN)
		{
			count ++;
			_peopleList[_rear] = aInfo;
			_rear = (_rear+1)%MAXQUEUELEN;
		}
		else {
			return false;
		}
		return true;
	}

	public People getOut() {
		People temPeople;
		if(count != 0)
		{
			count --;
			temPeople = _peopleList[_head] ;
			_head = (_head+1)%MAXQUEUELEN;
		}
		else {
			return null;
		}
		return temPeople;
	}
}