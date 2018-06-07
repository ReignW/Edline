package edLineEditor;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandParse {
    //用于处理命令地址(秃头)
    char commandbe;//命令
    String adressGroup;//地址
    String para;//参数
    private static final String GREP = "(^([.]|[$]|[-+]?[0-9]+(,[0-9]+)?|[,]|[;]|[/].+[/]|[?].+[?]|'[a-z])+)";

    public CommandParse parseCommand(String command) {
        int Pin=0;//匹配字符串最后一个位置的指针
        CommandParse c = new CommandParse();
        Pattern p = Pattern.compile(GREP);
        Matcher matcher = p.matcher(command);
        if (matcher.find()) {
            adressGroup=matcher.group();
            Pin = matcher.end();
        }

        //解析单字符指令
        if(command.length()!=1){
        commandbe=command.charAt(Pin);//地址
        Pin++;

        //解析参数
        if (Pin <= command.length()) {
            para=command.substring(Pin).trim();//所有起始和结尾的空格都被删除
        }}
        else commandbe=command.charAt(0);
        return c;//打包了命令的全部内容
    }


     int[] parseAddress(String addressGroup,History h) {
        if(addressGroup==null||addressGroup.length() == 0){
            return new int[]{-111,-111};
        }
        //空地址
        boolean isFound = false;
        int[] lineNum=new int[2];
        if (addressGroup.matches("^[/].+[/]$")) {
            String temp = addressGroup.substring(1, addressGroup.length() - 1);
            String[] content=h.getLast().split(System.getProperty("line.separator"));
            for (int i = h.getPinpoint(); i < content.length; i++) {
                if (content[i].contains(temp)) {
                    lineNum[0] = i + 1;
                    lineNum[1] = lineNum[0];
                    isFound = true;
                    break;
                }
            }
            for(int i=0;i<h.getPinpoint();i++){
                if (content[i].contains(temp)) {
                    lineNum[0] = i + 1;
                    lineNum[1] = lineNum[0];
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                System.out.println("?");
            }//搜索
        } else if (addressGroup.matches("^[?].+[?]$")) {
            String temp = addressGroup.substring(1, addressGroup.length() - 1);
            String[] content=h.getLast().split(System.getProperty("line.separator"));
            for (int i = h.getPinpoint()- 2; i >= 0; i--) {
                if (content[i].contains(temp)) {
                    lineNum[0] = i + 1;
                    lineNum[1] = lineNum[0];
                    isFound = true;
                    break;
                }
            }
            for(int i=h.getMax();i>h.getPinpoint()-2;i--){
                if (content[i].contains(temp)) {
                    lineNum[0] = i + 1;
                    lineNum[1] = lineNum[0];
                    isFound = true;
                    break;
                }
            }
            if (!isFound) {
                System.out.println("?");
            }
        } else if (addressGroup.matches("^['][a-z]$")) {
            Integer line = h.getMarkLine(addressGroup.charAt(1) + "");
            if (line != null) {
                isFound = true;
                lineNum[0] = line;
                lineNum[1] = lineNum[0];
            } else {
            //    System.out.println("?");
            }
        }

        if (isFound) {
            return lineNum;
        }

        //计算一些特别的地址,需要检验
        if (addressGroup.matches("^.+[,].+$")) {
            String[] parts = addressGroup.split(",");
            lineNum[0] = parseOne(parts[0],h)[0];
            lineNum[1] = parseOne((parts[1]),h)[0];
        } else {
            lineNum = parseOne(addressGroup,h);
        }

        if (lineNum[0] < 1 || lineNum[1] > h.getMax()|| lineNum[0] > lineNum[1]) {
            lineNum[0] = -111;
            lineNum[1] = -111;
        }//检验是否合理

        return lineNum;

    }



    int[] parseOne(String addressGroup,History h){
        int currentLine = h.getPinpoint();//调用历史指针
        int maxLine = h.getMax();
        if (addressGroup.matches("^[.]$")) {
            return new int[]{currentLine, currentLine};
        } else if (addressGroup.matches("^[$]$")) {
            return new int[]{maxLine, maxLine};
        } else if (addressGroup.matches("^[;]$")) {
            return new int[]{currentLine, maxLine};
        } else if (addressGroup.matches("^[,]$")) {
            return new int[]{1, maxLine};
        } else if (addressGroup.matches("^[0-9]+$")) {
            return new int[]{Integer.valueOf(addressGroup), Integer.valueOf(addressGroup)};
        } else if (addressGroup.matches("^[0-9]+[-+0-9]+$")) {
            int flag = 1;
            if (addressGroup.contains("-")) flag = -1;
            int temp = Integer.valueOf(addressGroup.split("^[-+]$")[0]) + Integer.valueOf(addressGroup.split("^[-+]$")[1]) * flag;
            return new int[]{temp, temp};
        } else if (addressGroup.matches("^[.]?[-+0-9]+$")) {
            if (addressGroup.charAt(0) == '.') {
                addressGroup = addressGroup.substring(1);
            }
            return new int[]{currentLine + Integer.valueOf(addressGroup), currentLine + Integer.valueOf(addressGroup)};
        } else if (addressGroup.matches("^[$][-+0-9]+$")) {
            return new int[]{maxLine + Integer.valueOf(addressGroup.substring(1)), maxLine + Integer.valueOf(addressGroup.substring(1))};
        } else {
            return new int[]{-111,-1111};//我也不知道这里会发生什么
        }
    }
}//处理地址
