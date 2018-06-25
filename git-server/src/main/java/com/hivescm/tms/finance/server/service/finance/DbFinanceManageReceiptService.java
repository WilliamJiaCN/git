package com.hivescm.tms.finance.server.service.finance;

import com.hivescm.tms.api.dto.es.finance.request.FinanceDeleteReqDTO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageReceiptDO;
import com.mogujie.trade.tsharding.annotation.ShardingExtensionMethod;
import com.mogujie.trade.tsharding.annotation.parameter.ShardingParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DbFinanceManageReceiptService {

    public Boolean insert(FinanceManageReceiptDO record);

    public int insertSelective(FinanceManageReceiptDO record);

    public int updateByPrimaryKeySelective(FinanceManageReceiptDO record);

    public int updateByPrimaryKey(FinanceManageReceiptDO record);

    public int insertBatch(List<FinanceManageReceiptDO> list);

    public int deleteByCode(FinanceDeleteReqDTO financeDeleteReqDTO);

    public boolean updateByPrimaryKeySelectiveBatch(List<FinanceManageReceiptDO> list);

    boolean deleteByPayway(FinanceDeleteReqDTO financeDeleteReqDTO);
}
