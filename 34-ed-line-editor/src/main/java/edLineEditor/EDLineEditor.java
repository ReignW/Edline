package edLineEditor;

import java.util.ArrayList;
import java.util.Scanner;

public class EDLineEditor {
	FileProcess f;
	public Scanner sc=new Scanner(System.in);
	History h=new History();
	EditTool e=new EditTool(sc,h,f);
	/**
	 * 接收用户控制台的输入，解析命令，根据命令参数做出相应处理。
	 * 不需要任何提示输入，不要输出任何额外的内容。
	 * 输出换行时，使用System.out.println()。或者换行符使用System.getProperty("line.separator")。
	 * 
	 * 待测方法为public static void main(String[] args)方法。args不传递参数，所有输入通过命令行进行。
	 * 方便手动运行。
	 * 
	 * 说明：可以添加其他类和方法，但不要删除该文件，改动该方法名和参数，不要改动该文件包名和类名
	 */
	public static void main(String[] args) {
		/*	EDLineEditor e=new EDLineEditor();
			e.initialize();
			try{e.doing();}
			catch(Exit exit){
			}//退出程序
		catch(Exception ee){
		}*/
		System.out.println(" ");
		}




  public void initialize(){
	  String[] begin=sc.nextLine().split(" ");
	  if(begin.length==2){
		  f=new FileProcess(begin[1]);}
	  else {
		  f = new FileProcess();
	  }//ed进入编辑器

	  h.fresh(f.getContent());//设置第一次内容
	  //以上是初始化的内容
	  if(f.getContent().length()!=0)
	  h.setPinpoint(f.getContent().split(System.getProperty("line.separator")).length);
	  else h.setPinpoint(1);

  }//初始化的方法

public void doing(){
		while(sc.hasNextLine()){
			Dealer.engine(sc.nextLine(),h,e);
		//	System.out.print(h.getLast()+" "+h.getPinpoint());//检查
		}
}//执行



	}//处理命令，null为不存在该参数或者地址





/*String a="asdasd";
		System.out.println(a.substring(a.length()-1,a.length()));
		试验substring*/