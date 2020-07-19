package com.wyy.ityuyao;

/**
 * @program: ityuyao
 * @description: 多线程测试
 * @author: lizhe zeng
 * @create: 2020-07-13 23:07
 **/

public class StartThread  extends Thread{
/*
* 线程开辟的对象
* */
   static int countNo=10;
    public void run(){
        for(int i=0;i<countNo;i++){
            System.out.println("Listening");
        }
    }
    public static void main(String[] args) {
//        int county=10;
        StartThread startThread=new StartThread();
       startThread.start();
        // 单线程
       // startThread.run();
        for(int i=0;i<countNo;i++){
            System.out.println("看书o");
        }
    }

}
