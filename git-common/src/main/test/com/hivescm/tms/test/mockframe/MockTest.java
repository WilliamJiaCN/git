package com.hivescm.tms.test.mockframe;

import com.hivescm.tms.common.address.BDReq;

import com.hivescm.tms.mockframe.IMock;
import com.hivescm.tms.mockframe.engine.mockdb.beans.WhereParam;
import lombok.Data;
import lombok.extern.java.Log;

import java.util.List;
import java.util.Vector;

/**
 * description
 *
 * @author LiuQiang
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/9/4
 */
@Log
@Data
public class MockTest {


    public static void main(String[] args) throws Exception{

        Person bdReq = IMock.newInstance(Person.class);
        System.out.println(bdReq.toString());
        IMock.saveObject(bdReq);
        Person bdReq1 = IMock.queryOnlyById(Person.class,0);
        System.out.println(bdReq1);

        Vector<Person> vector = IMock.queryFullTable(Person.class);
        System.out.println(vector);

        List<Person> list = IMock.queryObjectsByParam(Person.class,new WhereParam("age",20),new WhereParam("bok",'M'),
                new WhereParam("aFloat",new Float(1.00)));
        list.stream().forEach((item)->{
            System.out.println(item);
        });


        List<Person> people = IMock.newInstances(Person.class,5);
        System.out.println(people);

    }


}
