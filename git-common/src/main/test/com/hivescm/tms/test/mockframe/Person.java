package com.hivescm.tms.test.mockframe;

import com.hivescm.tms.mockframe.Enums.NameTypeEnum;
import com.hivescm.tms.mockframe.annotation.*;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * description
 *
 * @author LiuQiang
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/9/4
 */
@ToString
@Data
public class Person {
    @NameRule(nameType = NameTypeEnum.CN_NAME)
    private String name;

    @AgeRule(random = false,value = 20)
    private Integer age;
    @DefaultRule("2.0")
    private BigDecimal money;
    @DefaultRule("2.00")
    private Double moneyDouble;
    @DefaultRule("{wifeName:'myname'}")
    private Male male;
    @DefaultRule("1.00")
    private Float aFloat;
    @TelphoneRule
    private String telPhnoe;
    @IdCardRule(firstSixNum="131127",age = 20)
    private String idCard;
    @DefaultRule("M")
    private char bok;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public Double getMoneyDouble() {
        return moneyDouble;
    }

    public void setMoneyDouble(Double moneyDouble) {
        this.moneyDouble = moneyDouble;
    }

    public Male getMale() {
        return male;
    }

    public void setMale(Male male) {
        this.male = male;
    }

    public Float getaFloat() {
        return aFloat;
    }

    public void setaFloat(Float aFloat) {
        this.aFloat = aFloat;
    }

    public String getTelPhnoe() {
        return telPhnoe;
    }

    public void setTelPhnoe(String telPhnoe) {
        this.telPhnoe = telPhnoe;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
