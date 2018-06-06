package edLineEditor;

import java.util.List;
import java.util.Scanner;

//一些工具,直接操作history
public class EditTool {
    String lastpara;
    Scanner sc;
    History h;
    FileProcess f;
    private boolean check=false;
    EditTool(Scanner c,History hs,FileProcess ff){
        this.sc=c;
        this.h=hs;
        this.f=ff;
    }
    int getPinpoint(){
        return h.historyOfpin;
    }
    void cover(String content){h.cover(content);}
    void fresh(String content){
           h.fresh(content);
    }//更新最新的文本纪录
  //add
     String getInput(){
        String re="";
        while(sc.hasNextLine()){
            String line=sc.nextLine();
            if(line.equals(".")){
                return re;
            }
            else{
                re=re+line+System.getProperty("line.separator");
            }
        }
        return null;
    }
    String Add(int st,String addContent){
        String re=add(st,addContent);
            int pin=st+addContent.split(System.getProperty("line.separator")).length;
        if(h.getLast().length()==0){
            pin=pin-1;
        }
        h.setPinpoint(pin);
            return re;
    }//增加内容
    String add(int st,String addContent){
        String re="";
        String[] content=h.getLast().split(System.getProperty("line.separator"));
        if(st==0){
            re=addContent+re;
        }
        for(int be=0;be<content.length;be++){
            re=re+content[be]+System.getProperty("line.separator");
            if(be==st-1){
               if(addContent.length()!=0) re=re+addContent;
            }}
try{        for (Object key :h.getmap().keySet()) {
            if (Integer.valueOf(String.valueOf(h.getmap().get(key))) > st) {
                h.getmap().put(key,Integer.valueOf(String.valueOf(h.getmap().get(key)))  + addContent.split(System.getProperty("line.separator")).length);
            }
        }}catch (Exception e){}
            return re;
    }//增加内容的复用方法

    String Delete(int[] pins){
        String re=delete(pins);
        int pin=2*pins[1]-pins[0]+2;
        if(pin>h.getMax()-pins[1]+pins[0]-1){
            pin=h.getMax()-pins[1]+pins[0]-1;
        }
        h.setPinpoint(pin);
        return re;
    }
    String delete(int[] pins){
        String re="";
        String[] content=h.getLast().split(System.getProperty("line.separator"));
        for(int be=0;be<content.length;be++){
            if(be>=pins[0]-1&&be<=pins[1]-1) continue;;
            re=re+content[be]+System.getProperty("line.separator");
        }
        try{        for (Object key :h.getmap().keySet()) {
            if (Integer.valueOf(String.valueOf(h.getmap().get(key))) >= pins[0]&&Integer.valueOf(String.valueOf(h.getmap().get(key)))<=pins[1]) {
                h.getmap().remove(key);}
                else if(Integer.valueOf(String.valueOf(h.getmap().get(key))) > pins[1]){
                h.getmap().put(key,Integer.valueOf(String.valueOf(h.getmap().get(key)))-(pins[1]-pins[0]+1));
            }
            }
        }catch (Exception e){}
        return re;
    }//删除内容的复用方法

    void Print(int[] pins){
        print(pins,true);
        h.setPinpoint(pins[1]);
    }
    String print(int[] pins,boolean b){
        String re="";
        String[] content=h.getLast().split(System.getProperty("line.separator"));
        if(b){
        for(int i=pins[0]-1;i<pins[1];i++){
           System.out.print(content[i]+System.getProperty("line.separator"));}
        }
        else{
        for(int i=pins[0]-1;i<pins[1];i++){
            re=re+content[i]+System.getProperty("line.separator");
        }}
        return re;
    }//打印,但是本身返回打印行的内容,可以用作获取,true为打印,false返回

    void equal(int[] pins){
        System.out.println(pins[0]);
    }

    void quit() throws Exit{
        throw new Exit();
    }//退出的标志
    void check(){
        if(h.checkhere()||check) quit();
        else{
    //        System.out.println("?");
            check=true;
        }
    }

    void filepathset(String para){
        try{
        f.filePathset(para);}catch(Exception e){}
    }
    String filepathcheck(){
        try{
        return f.getFilePath();}catch(Exception e){
            return "";
        }
    }


    void fileWritein(String content,String filepath,boolean b){
        FileProcess.WriteIn(content,filepath,b);
    }//b为true时候为追加内容

    int ParaParse(String para){
        CommandParse c=new CommandParse();
        return c.parseOne(para,h)[0];
    }

    String join(String s){
        return s.replace(System.getProperty("line.separator"),"")+System.getProperty("line.separator");
    }

    void u(){
        h.u();
    }

    void setmark(String mark,int Line){
        h.markin(mark,Line);
    }

    String replace(String content,String para){
        String re="";
        if(para.length()==0){
            para=lastpara;
        }
        else
        this.lastpara=para;
        String[] parameter=para.split("/");

        String[] linescontent=content.split(System.getProperty("line.separator"));
        if(!parameter[3].equals("g")){
        for(int i=0;i<Integer.valueOf(parameter[3]);i++){
            re="";
        for(String item: linescontent){
            re=re+item.replaceFirst(parameter[1],parameter[2])+System.getProperty("line.separator");
        }
            linescontent=re.split(System.getProperty("line.separator"));
        }
        }
        else{
            for(String item: linescontent){
                re=re+item.replaceAll(parameter[1],parameter[2])+System.getProperty("line.separator");
        }}
        return re;
    }

}
