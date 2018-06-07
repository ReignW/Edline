package edLineEditor;

import java.util.ArrayList;
import java.util.HashMap;

public class History {
    private HashMap<String, Integer> marktable=new HashMap<>();
    private boolean check=true;//记录着最后一次是否存了文本
    ArrayList<String> history=new ArrayList<String>();//文版编辑的历史纪录
    int historyOfpin;//指针记录
    void fresh(String content){
        if(!this.getLast().equals(content)){//P等对文本不改动的指令，不需要纪录历史

            history.add(History.tirming(content.trim()));
            check=false;
            if(history.size()==1){
            check=true;
        }

        }
    }//更新最新的文本纪录
    void cover(String content){
        history.remove(history.size()-1);
        history.add(content);
    }

    String getLast(){
        try{return history.get(history.size()-1);}
        catch(Exception e){
            return "";
        }
    }//得到当前文本

    int getMax(){
       return history.get(history.size()-1).split(System.getProperty("line.separator")).length;
    }//得到当前文本的最大长度
    int getPinpoint(){
        return historyOfpin;
    }
    void setPinpoint(int s){
        historyOfpin=s;
    }//文本指针
    int getMarkLine(String ma){
        return marktable.get(ma);
    }//找到相印行
    void markin(String mark,int linenum){
        marktable.put(mark,linenum);
    }


    void bufferfresh(){
        String relic=history.get(history.size()-1);
        history.clear();
        history.add(relic);
        check=true;
    }//写入文件之后清空记录,记录的第一个总是被写入的最后一个
    boolean checkhere(){
        return check;
    }

    void u(){if(history.size()!=1){
    history.remove(history.size()-1);
    historyOfpin=history.get(history.size()-1).split(System.getProperty("line.separator")).length;
    }
    }
    HashMap getmap(){return marktable;}


    static String tirming(String s){
        if(s.startsWith(System.getProperty("line.separator")))
            s=s.replaceFirst(System.getProperty("line.separator"),"");
        if(s.endsWith(System.getProperty("line.separator")))
            s.substring(0,s.length()-1);

        return s;
    }
}
