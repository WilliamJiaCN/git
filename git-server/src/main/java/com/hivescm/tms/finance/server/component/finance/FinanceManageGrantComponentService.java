package com.hivescm.tms.finance.server.component.finance;

import com.hivescm.tms.api.dto.es.finance.request.FinanceManageGrantCommonReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManageGrantConfirmReqDTO;
import com.hivescm.tms.api.dto.es.finance.request.FinanceManagePayCancelReqDTO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageCashSerialDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsGrantDO;
import com.hivescm.tms.finance.server.dao.entity.finance.FinanceManageGoodsRecycleDO;


/*
 * 货款管理-货款发放Service接口
 * 
 * @author wenxiong.jia
 * @date 2018/5/7
 */
public interface FinanceManageGrantComponentService {

    /**
     * 货款发放确认
     *
     * @param financeManageGrantConfirmReqDto
     * @return
     */
    Boolean grantConfirm(FinanceManageGrantConfirmReqDTO financeManageGrantConfirmReqDto);

    /**
     * 货款发放取消
     *
     * @param financeManageGoodsGrantDo
     * @param financeManageCashSerialDo
     * @param newFinanceManageCashSerialDo
     * @param financeManagePayCancelReqDto
     * @return
     */
    Boolean cancelGrantPay(FinanceManageGoodsGrantDO financeManageGoodsGrantDo, FinanceManageCashSerialDO financeManageCashSerialDo, FinanceManageCashSerialDO newFinanceManageCashSerialDo, FinanceManageGoodsRecycleDO financeManageGoodsRecycleDO, FinanceManagePayCancelReqDTO financeManagePayCancelReqDto);

    /**
     * 货款手续费收款取消
     *
     * @param financeManageGoodsGrantDo
     * @param financeManageCashSerialDo
     * @param newFinanceManageCashSerialDo
     * @param financeManagePayCancelReqDto
     * @return
     */
    Boolean cancelGoodsFeeReceipt(FinanceManageGoodsGrantDO financeManageGoodsGrantDo, FinanceManageCashSerialDO financeManageCashSerialDo, FinanceManageCashSerialDO newFinanceManageCashSerialDo, FinanceManagePayCancelReqDTO financeManagePayCancelReqDto);

    /**
     * 进账确认
     *
     * @param financeManageGrantCommonReqDto
     * @return
     */
    Boolean incomeConfirm(FinanceManageGrantCommonReqDTO financeManageGrantCommonReqDto);
}
