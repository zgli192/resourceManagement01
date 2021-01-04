package com.wyy.ityuyaot.service.Impl;

import Tools.PojoTrunMap;
import com.HD.toShr.KqRecord;
import com.baomidou.mybatisplus.plugins.Page;
import com.wyy.ityuyaot.entity.Assessment;
import com.wyy.ityuyaot.entity.HkList;
import ga.Json2HkList;
import ga.JsonUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static net.sf.json.JSONObject.fromObject;

/**
 * @program: ityuyao
 * @description: 将Json转换为Hklist
 * @author: lizhe zeng
 * @create: 2021-01-01 23:24
 **/
@Service
public class Json2HkListImpl {
    private static final Logger LOGGER = Logger.getLogger(Json2HkListImpl.class);

    public static Page<HkList> getHkList(String jsonStr) throws ParserConfigurationException, SAXException, IOException {
        //获取data 数组
        String dataJson= Json2HkListImpl.getHkListdata(jsonStr);
        Page<HkList> HkListss=new Page<>();
        List<HkList> hkLists =new ArrayList<>();

        //将jsonStr转为JSON对象
        JSONObject obj = fromObject(dataJson);
        //获取json对象的值    json格式为key-value形式
        JSONArray array = obj.getJSONArray("list");

        //change3: 这个list不用，就不要去new
        //  ArrayList list = new ArrayList();
        for (int i = 0; i < array.size(); i++) {
            // 之前的位置：HkList hkList=new HkList();，打卡失败的数据，不需要放到Hklist里面去。
            JSONObject asset = array.getJSONObject(i);
            try {
                    //Chang1：从上面移动下来，在jobNo为空的时候，是不需要创建对象的
                    HkList hkList=new HkList();

                    hkList.setCardNo(asset.getString("jobNo").trim());
                    hkList.setEventId(asset.getString("eventTime").substring(0, 10));
                    hkList.setEventName(asset.getString("eventTime").substring(11, 19));
                    hkList.setPersonName(asset.getString("personName"));
                    hkList.setDevName(asset.getString("devName").trim());
                    // 这个导致的了没有传入hkLists 中
                   // hkList.setOrgName(asset.getString("orgName".substring(10)));
                    //根据配置做字段转换
                hkLists.add(hkList);
            }catch(Exception e){
                LOGGER.info("打卡失败的是数据"+array.get(i));
            }
        }

        if (!hkLists.isEmpty()) {
            HkListss.setRecords(hkLists);
        }else {
            LOGGER.info("没有获取数据");
        }
        //return pageInfo;
        return  HkListss;

    }
    private  static  String getHkListdata (String result){
        StringBuilder sb= new StringBuilder();
        JsonUtils jsonUtils = new JsonUtils();
        String[] getvalue = jsonUtils.getvalue(result, "/data");
        for (String value : getvalue) {
            sb.append(value);
        }
        return sb.toString();
    }
}
