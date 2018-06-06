package edLineEditor;

import javax.activation.CommandMap;
import java.util.ArrayDeque;

public class Dealer {

    public static void engine(String command,History h,EditTool e){
            CommandParse parse=new CommandParse();
            parse.parseCommand(command);
            Command c=Dealer.getCommand(parse,h,e);
            c.run();
    }//执行
    public static Command getCommand(CommandParse p,History h,EditTool e){
        Command c=null;
        int[] pins=p.parseAddress(p.adressGroup,h);
        String para=p.para;
        switch (p.commandbe){
            case('a'):c=new CommandA(pins,e);break;
            case('i'):c=new CommandI(pins,e);break;
            case('d'):c=new CommandD(pins,e);break;
            case('c'):c=new CommandC(pins,e);break;
            case('p'):c=new CommandP(pins,e);break;
            case('='):c=new CommandE(pins,e);break;
            case('z'):c=new CommandZ(pins,e,para);break;
            case('q'):c=new Commandqq(e);break;
            case('Q'):c=new CommandQ(e);break;
            case('f'):c=new CommandF(e,para);break;
            case('w'):c=new Commandww(pins,e,para);break;
            case('W'):c=new CommandW(pins,e,para);break;
            case('m'):c=new CommandM(pins,e,para);break;
            case('t'):c=new CommandT(pins,e,para);break;
            case('j'):c=new CommandJ(pins,e);break;
            case('u'):c=new CommandU(e);break;
            case('k'):c=new CommandK(pins,e,para);break;
            case('s'):c=new CommandS(pins,e,para);break;
        }
        return c;
    }

}
