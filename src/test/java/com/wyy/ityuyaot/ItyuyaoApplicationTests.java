package com.wyy.ityuyaot;

//import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ItyuyaoApplicationTests {

    private  CountNo countNo;
    private ReadBookRun readBookRun;

    public void setCountNo(CountNo countNo) {
        this.countNo = countNo;
    }

    public void setReadBookRun(ReadBookRun readBookRun) {
        this.readBookRun = readBookRun;
    }

    public static void main(String[] args) {

        //        实现类对象
        CountNoRun countNoRun =new CountNoRun();
        ReadBookRun readBookRun=new ReadBookRun();
        // 创建代理类对象
        Thread threadOne= new Thread(countNoRun);
        Thread threadTwo= new Thread(readBookRun);
//        threadOne ---countNoRun   事件Listening
        threadTwo.start();
        threadOne.start();



    }

}
