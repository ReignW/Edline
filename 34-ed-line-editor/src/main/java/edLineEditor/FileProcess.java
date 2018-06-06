package edLineEditor;

import java.io.*;
//这个类未经测试
public class FileProcess {
    String filePath="";
    String fileContent="";
    FileProcess(){

    }
    FileProcess(String file){
        filePath=file;
        File f=new File(filePath);
        if(!f.exists()){
            try{f.createNewFile();}catch(Exception e){}
        }
        fileContent=this.FileRead();

    }//ed file的构造方法
    String getContent(){
        return fileContent;
    }
    String getFilePath(){return filePath;}

public void filePathset(String name){
       this.filePath=name;
}
    String FileRead(){
        String charset = "utf-8";
        File file = new File(filePath);
        long fileByteLength = file.length();
        byte[] content = new byte[(int)fileByteLength];
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            fileInputStream.read(content);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String str = null;
        try {
            str = new String(content,charset);
          //  System.out.println(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    return str;
    }//本方法读取ed file的文件内容

    void WriteIn(String w){
        try
        {
            // 创建文件对象
            File fileText = new File(filePath);
            // 向文件写入对象写入信息
            FileWriter fileWriter = new FileWriter(fileText,false);

            // 写文件
            fileWriter.write(w);
            // 关闭
            fileWriter.close();
        }
        catch (IOException e)
        {
            //
            e.printStackTrace();
        }
    }


 static void WriteIn(String w,String filepath,boolean b){//追加写文件的方法，需要参数,适用于w,W
        try
        {
            // 创建文件对象
            File fileText = new File(filepath);
            // 向文件写入对象写入信息
            FileWriter fileWriter = new FileWriter(fileText,b);

            // 写文件
            fileWriter.write(w.trim());
            // 关闭
            fileWriter.close();
        }
        catch (IOException e)
        {
            //
            e.printStackTrace();
        }

    }
}
