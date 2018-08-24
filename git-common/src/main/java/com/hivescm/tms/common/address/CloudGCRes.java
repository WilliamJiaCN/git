package com.hivescm.tms.common.address;


import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * description
 *
 * @author LiuQiang
 * @company 蜂网供应链管理（上海）有限公司
 * @since 2017/8/10
 */
@Data
public class CloudGCRes extends BDRes {

    /**
     * 返回结果
     */
    private CloudGCResult[] result;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SIMPLE_STYLE);
    }


//    public static void main(String[] args) {
//        String jsoStr = "{\"status\":0,\"message\":\"ok\",\"result\":[{\"source\":\"baidu\",\"location\":{\"lat\":39.990862658702798,\"lng\":116.31430419259456},\"bound\":\"39.990517,116.313855;39.991208,116.314753\",\"formatted_address\":\"北京市海淀区\",\"address_components\":{\"province\":\"北京市\",\"city\":\"北京市\",\"district\":\"海淀区\",\"street\":\"\",\"level\":\"购物\"},\"precise\":1.8}]}";
//        Map<String,Object> hashMap = (HashMap) JSONObject.toBean(JSONObject.fromObject(jsoStr), Map.class);
//        List<String> resultList = (List<String>) hashMap.get("result");
//
//        CloudGCRes cloudGCRes = (CloudGCRes) JSONObject.toBean(JSONObject.fromObject(jsoStr),CloudGCRes.class);
//        System.out.println(cloudGCRes);
//
//    }

}
