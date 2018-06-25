package com.hivescm.tms.finance.job.service.lock;

import com.hivescm.tms.finance.job.test.TestBase;
import com.hivescm.tms.finance.server.service.lock.SignLockService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>Title: TestSignLockService</p>
 * <p>Description: </p>
 * <p>Email: syenging@gmail.com</p>
 *
 * @author Jie.Yang
 * @version 1.0
 * @create 2018-05-11-11:34 AM
 */
public class TestSignLockService extends TestBase {

    @Autowired
    private SignLockService signLockService;

    @Test
    public void testLockSign(){
        System.out.println("message input");
    }

}
