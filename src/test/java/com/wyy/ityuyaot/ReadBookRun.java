package com.wyy.ityuyaot;

import static com.wyy.ityuyaot.StartThread.No;

/**
 * @program: ityuyaot
 * @description: ReadBook  entrance
 * @author: lizhe zeng
 * @create: 2020-09-22 09:39
 **/

public class ReadBookRun implements  Runnable {

    private  ReadBook readBook;

    public ReadBook getReadBook() {
        return readBook;
    }

    public void setReadBook(ReadBook readBook) {
        this.readBook = readBook;
    }

    @Override
    public  void  run(){
        ReadBook readBook=new ReadBook();
        readBook.readBookTimes(No);
    }

}
