package TestCase;
import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStreamReader;  


import jxl.Workbook;  
import jxl.write.Label;  
import jxl.write.WritableSheet;  
import jxl.write.WritableWorkbook;  
import jxl.write.WriteException;  
import jxl.write.biff.RowsExceededException;  



public class testExcel {

public static void readFileByLines(String fileName) throws IOException, RowsExceededException, WriteException{  

//打开文件  
WritableWorkbook book = Workbook.createWorkbook(new File("D://data3.xls"));  
//生成名为“第一页”的工作表，参数0表示这是第一页  
WritableSheet sheet = book.createSheet("第一页", 0);  
//读入txt中的内容  
File file = new File(fileName);  
FileInputStream fis = new FileInputStream(file);  
InputStreamReader isr = new InputStreamReader(fis,"Unicode");  
BufferedReader reader = null;  
try{  
reader = new BufferedReader(isr);  
String tempString = null;  
//一次读入一行，直到读入null为文件结束  
int i = 0;  
while((tempString = reader.readLine())!= null){  
System.out.println(tempString);  
String[] str = tempString.split(",");  
//Label[] label = null;  
for(int j = 0;j < str.length;j++){  
//在Label对象的构造子中指名单元格位置是第j列第i行(j,i)以及单元格内容为str[j]  
Label label = new Label(j,i,str[j]);  
//将定义好的单元格添加到工作表中  
sheet.addCell(label);  
}  
i++;  
}   
//写入数据并关闭文件  
book.write();  
try {  
book.close();  
} catch (WriteException e) {  
// TODO Auto-generated catch block  
e.printStackTrace();  
}  
reader.close();  
}catch(IOException e){  
e.printStackTrace();  
} finally{  
if(reader!= null){  
reader.close();  
}  
}  
}  
public static void main(String[] args) throws RowsExceededException, WriteException{  
try {  
readFileByLines("D://data.txt");  
} catch (IOException e) {  
// TODO Auto-generated catch block  
e.printStackTrace();  
}  
}  
}
