package TestCase;

import static org.junit.Assert.*;

import org.junit.Test;
import DataMining.*;
public class TestFilter {
	String adtext;
	String unadtext;
	MyFilter filter;
	
	public TestFilter() {
		adtext = "[可怜]体毛多的女人伤不起啊[抓狂]手臂、腋下，腿上，各种毛毛各种忧啊[泪] 短袖短裙不敢穿，游泳露毛多尴尬[晕]不过这个夏天姐彻底翻身了，因为偶找到个秘密武器 一抹一擦，毛毛彻底掉光，皮肤变得白嫩润滑，神马脱毛膏，蜡纸跟它比都弱爆了[弱]好东西分享啦>>> http://t.cn/zYseWsE";
		unadtext= "#五四青年节#五一过去五四又来，这样的节日早已经淡忘，尽管自己已不再年轻！";
		filter = new MyFilter();
	}
	@Test
	public void Adtest() {
		assertTrue(filter.isad(adtext));
	}
	@Test
	public void unAdtest(){
		assertFalse(filter.isad(unadtext));
	}

}
