package edLineEditor;

import java.util.ArrayList;

public interface Command {
    void run();
}


class CommandA implements Command{
    int[] Pins;
    EditTool edit;
CommandA(int[] pins,EditTool e){
    this.Pins=pins;
    this.edit=e;
    }
    public void run(){
        String result=edit.Add(Pins[0], edit.getInput());
        edit.fresh(result);
    }
}


class CommandI implements  Command{
    int[] Pins;
    EditTool edit;
    CommandI(int[] pins,EditTool e){
        this.Pins=pins;
        this.edit=e;
    }
    public void run(){
        String result=edit.Add(Pins[0]-1,edit.getInput());
        edit.fresh(result);
    }
}


class CommandD implements  Command{
    int[] Pins;
    EditTool edit;
    CommandD(int[] pins,EditTool e){
        this.Pins=pins;
        this.edit=e;
    }
    public void run(){
        String result=edit.Delete(Pins);
        edit.fresh(result);
    }
}


class CommandC implements Command{
    int[] Pins;
    EditTool edit;
    CommandC(int[] pins,EditTool e){
        this.Pins=pins;
        this.edit=e;
    }
    public void run(){
        String result=edit.Delete(Pins);
        edit.fresh(result);
      result=edit.Add(edit.getPinpoint(),edit.getInput());
        edit.cover(result);
    }
}


class CommandP implements  Command{
    int[] Pins;
    EditTool edit;
    CommandP(int[] pins,EditTool e){
        this.Pins=pins;
        this.edit=e;
    }
    public void run(){
        edit.Print(Pins);
    }
}


class CommandE implements  Command{
    int[] Pins;
    EditTool edit;
    CommandE(int[] pins,EditTool e){
        this.Pins=pins;
        this.edit=e;
    }
    public void run(){
        edit.equal(Pins);
    }
}


class CommandZ implements  Command{
    int[] Pins;
    EditTool edit;
    String para;
    int parameter;
    CommandZ(int[] pins,EditTool e,String p){
        this.Pins=pins;
        this.edit=e;
        para=p;
    }
    public void run(){
        String recover=edit.print(Pins,false);
        String result=edit.Delete(Pins);
        edit.fresh(result);
        parameter=result.split(System.getProperty("line.separator")).length;
        if(para.length()==0){
            parameter=result.split(System.getProperty("line.separator")).length;
        }
        else if(Pins[1]+Integer.valueOf(para)<parameter){
            parameter=Pins[1]+Integer.valueOf(para);
        }
        result=edit.Add(parameter,recover);
        edit.fresh(result);
    }
}


class Commandqq implements  Command{
    EditTool edit;
    Commandqq(EditTool e){
        this.edit=e;
    }
    public void run(){
        edit.check();
    }
}


class CommandQ implements  Command{
    EditTool edit;
    CommandQ(EditTool e){
        this.edit=e;
    }
    public void run(){
        edit.quit();
    }
}


class CommandF implements  Command{
    String filepath;
    EditTool e;
    CommandF(EditTool de,String para){
        this.filepath=para;
        this.e=de;
    }
    public void run(){
        if(filepath.length()==0){
           if(e.filepathcheck().length()==0)
            System.out.println("?");
           else
            System.out.println(e.filepathcheck());
        }
        else{
            e.filepathset(filepath.trim());
        }
    }
}


class Commandw implements  Command {
    int[] pins;
    String filepath;
    EditTool edit;

    Commandw(int[] Pins, EditTool e, String para) {
        this.pins = Pins;
        this.edit = e;
        this.filepath = para;
    }

    public void run() {
        if (filepath.length() == 0) {
            if (edit.filepathcheck().length() == 0){
                System.out.println("?");
            return ;}
            else
                filepath=edit.filepathcheck();
        }
        edit.fileWritein(edit.print(pins, false), filepath, false);
    }
}


class CommandW implements  Command{
    int[] pins;
    String filepath;
    EditTool edit;
    CommandW(int[] Pins,EditTool e,String para){
        this.pins=Pins;
        this.edit=e;
        this.filepath=para;
    }
    public void run(){
        if (filepath.length() == 0) {
            if (edit.filepathcheck().length() == 0){
                System.out.println("?");
            return ;}
            else
                filepath=edit.filepathcheck();
        }
        edit.fileWritein(edit.print(pins,false),filepath,true);
    }
}


class CommandT implements  Command{
    int[] Pins;
    String para;
    EditTool edit;
    CommandT(int[] p,EditTool e,String pa){
        this.Pins=p;
        this.edit=e;
        this.para=pa;
    }
    public void run() {
        String content=edit.print(Pins,false);
        int pin=edit.ParaParse(para);
        String result=edit.Add(pin,content);
        edit.fresh(result);
    }
}


class CommandM implements  Command{
    int[] Pins;
    String para;
    EditTool edit;
    CommandM(int[] p,EditTool e,String pa){
        this.Pins=p;
        this.edit=e;
        this.para=pa;
    }
    public void run() {
        String content=edit.print(Pins,false);
        int pin=edit.ParaParse(para);
        String result=edit.Add(pin,content);
        edit.fresh(result);
        result=edit.Delete(Pins);
        edit.cover(result);
    }
}


class CommandJ implements  Command{
    int[] Pins;
    EditTool edit;
    CommandJ(int[] p,EditTool e){
        this.edit=e;
        this.Pins=p;
    }
    public void run(){
        String replace=edit.join(edit.print(Pins,false));
        String result=edit.Delete(Pins);
        edit.fresh(result);
        result=edit.Add(Pins[0]-1,replace);
        edit.cover(result.trim());
    }
}


class CommandU implements  Command{
    EditTool edit;
    CommandU(EditTool e){
        this.edit=e;
    }
    public void run(){
        edit.u();
    }
}


class CommandK implements  Command{
    int[] Pins;
    EditTool edit;
    String mark;
    CommandK(int[] p,EditTool e,String m){
        this.Pins=p;
        this.edit=e;
        this.mark=m;
    }
    public void run(){
        edit.setmark(mark,Pins[0]);
    }
}


class CommandS implements  Command{
    int[] pins;
    EditTool edit;
    String para;
    CommandS(int[] p,EditTool e,String s){
        this.pins=p;
        this.edit=e;
        this.para=s;
    }
    public void run(){
        String content=edit.print(pins,false);
        String result=edit.replace(content,para);
        content=result;
        edit.fresh(result.trim());
        result=edit.Delete(pins);
        edit.cover(result.trim());
        result=edit.Add(pins[0],content);
        edit.cover(result);
    }
}