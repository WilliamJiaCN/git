package com.hivescm.tms.utils;

import java.io.*;

/**
 * @Description:
 * @Author: MaGuoDong
 * @Date: Created in 2018/4/20 13:31
 * @company 蜂网供应链管理（上海）有限公司
 */
public class CloneUtil {

    public static Object deepClone(Object t) throws IOException,ClassNotFoundException{//将对象写到流里
        ByteArrayOutputStream bo=new ByteArrayOutputStream();
        ObjectOutputStream oo=new ObjectOutputStream(bo);
        oo.writeObject(t);//从流里读出来
        ByteArrayInputStream bi=new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi=new ObjectInputStream(bi);
        return oi.readObject();
    }
}
