package Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

import Tools.GetMapValue;
import Tools.Tool;
import com.HD.toShr.KqRecord;
import com.alibaba.fastjson.JSONObject;
import com.HD.toShr.KqRecordTwo;

import com.kingdee.shr.api.SHRClient;

import com.method.Timer.TimerManager1;
import com.method.Timer.TimerManager2;
import exceptions.TimerSettingError;
import ga.ArtemisPostTest;
import ga.ArtemisPostTestImpl;
import ga.Json2HkList;
import ga.getXml.GetTimer;
import ga.getXml.XmlHandler;
import ga.pojo.Data;
import ga.pojo.MakeTimeGetHk;
import ga.pojo.NewMap;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.xml.sax.SAXException;
import com.kingdee.shr.api.OSFWSClient;
import javax.xml.parsers.ParserConfigurationException;
/**
 * @program: ityuyao
 * @description: OCJP Test
 * @author: lizhe zeng
 * @create: 2020-09-23 09:36
 **/

public class TestPass {
    public static void main(String[] args) {
        TestPass appTest=new TestPass();
//        appTest.insertSHR();
//        获取数据的测试
        appTest.GetHK();
    }

    /*
     * 测试获取海康数据
     * */
    public  void  GetHK(){
            String result=null;
        ArtemisPostTest artemisPostTest = new ArtemisPostTestImpl();
//            artemisPostTest.getEquApi();
        try {
//                获取海康的原始数据

//                测试实际传入数据
            //   artemisPostTest.callPostStringApi();
            result= artemisPostTest.callPostStringApiOne("2020-12-28","徐坤");
            System.out.println("新的Json数据"+result);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimerSettingError timerSettingError) {
            timerSettingError.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
