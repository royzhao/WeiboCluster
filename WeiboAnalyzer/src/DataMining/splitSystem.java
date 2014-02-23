package DataMining;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.wltea.analyzer.lucene.IKAnalyzer;

public class splitSystem {
	
	public class wordCount{
		public class wordAndCount{
			public int count;
			public String word;
			public wordAndCount(String w,int c){
				count = c;
				word = w;
			}
		}
		
		public List<wordAndCount> wordlist ;
		public wordCount(){
			wordlist = new ArrayList<wordAndCount>();
		}
		public void addValue(String word,int count){
			wordlist.add(new wordAndCount(word, count));
		}
		public void begin_analyzer(Doc doc){
			//构建IK分词器，使用smart分词模式
			Analyzer analyzer = new IKAnalyzer(true);
			StringReader reader = new StringReader(doc.getDocCon());
			List<String> tmpList=new ArrayList<String>();
			//获取Lucene的TokenStream对象
		    TokenStream ts = null;
			try {
				ts = analyzer.tokenStream("myfield", reader);
	 
			    //获取词元文本属性
			    CharTermAttribute term = ts.addAttribute(CharTermAttribute.class);

			    
			    
			    //重置TokenStream（重置StringReader）
				ts.reset(); 
				//迭代获取分词结果,加入到tmplist中待处理
				while (ts.incrementToken()) {
					if(Global.markNolex(term.toString()) == false){
						tmpList.add(term.toString());
					}
				}
				
				//词频统计
				for(String word:tmpList)
				{
					boolean match=false;
					for(int i=0;i<wordlist.size();i++)
					{
						if(word.equals(wordlist.get(i).word))
						{
							wordlist.get(i).count++;
							match=true;
							break;
						}
					}
					if(match==false)
					{
						wordlist.add(new wordAndCount(word,1));
					}
				}
				
				//加入单词词典
				for(wordAndCount w: wordlist){
					Global.dBer.addLexicon(w.word, w.count,doc.getDocID());
				}
				
				//关闭TokenStream（关闭StringReader）
				ts.end();   // Perform end-of-stream operations, e.g. set the final offset.

			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				//释放TokenStream的所有资源
				if(ts != null){
			      try {
					ts.close();
			      } catch (IOException e) {
					e.printStackTrace();
			      }
				}
		    }
		}
		
	}
	
	private Doc _target;

	public void setDoc(String doc){
		for(Doc c:Global.docList){
			if (c.getDocCon().equals(doc)){
				_target = c;
				return;
			}
		}
		_target = null;
	}
	
	public void beginSplit() {
		if (_target == null)
			return ;
		wordCount wCount = new wordCount();
		wCount.begin_analyzer(_target);
	}
}