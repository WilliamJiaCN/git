package com.hivescm.tms.test.mockframe;

import com.hivescm.tms.mockframe.Enums.NameTypeEnum;
import com.hivescm.tms.mockframe.Enums.SexEnum;
import com.hivescm.tms.mockframe.annotation.NameRule;
import lombok.Data;
import lombok.ToString;

/**
 * 男人，人的子类
 *
 * @author LiuQiang
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/9/5
 */
@ToString
@Data
public class Male extends Person{

    @NameRule(nameType = NameTypeEnum.CN_NAME,sex = SexEnum.SEX_FEMALE)
    private String wifeName;

}
