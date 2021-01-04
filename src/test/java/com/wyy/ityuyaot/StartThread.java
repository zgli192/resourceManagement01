package com.wyy.ityuyaot;

/**
 * @program: ityuyaot
 * @description: 多线程测试
 * @author: lizhe zeng
 * @create: 2020-07-13 23:07
 **/

public class StartThread  extends Thread{
    static int No=5;
    public static void main(String[] args) {
//        int county=10;
        StartThread startThread=new StartThread();
        startThread.start();
        // 单线程
        // startThread.run();
        for(int i=0;i<No;i++){
            System.out.println("看书o");
        }
    }
    /*
* 线程开辟的对象
* */

    public void run(){
        for(int i=0;i<No;i++){
            System.out.println("Listening");
        }
    }


}
