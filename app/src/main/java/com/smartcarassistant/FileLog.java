package com.smartcarassistant;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Aniket on 28-Feb-18.
 */

public class FileLog {
    private static boolean DEBUG=true;
    public static void write(String msg){

        try{
            if(DEBUG) {
                File f=new File("/sdcard/log.txt");
                FileInputStream fis=new FileInputStream(f);
                byte[] b=new byte[(int)f.length()];
                fis.read(b,0,b.length);
                msg=new String(b,"UTF-8")+"\n"+msg;
                FileOutputStream fos = new FileOutputStream(new File("/sdcard/log.txt"));
                fos.write(msg.getBytes(), 0, msg.getBytes().length);
                fos.close();
            }else{

            }
        }catch (Exception e){

        }
    }

    public static void e(Exception e){
        write(e.getMessage());
    }
    public static void e(Throwable t){
        write(t.getMessage());
    }
}
