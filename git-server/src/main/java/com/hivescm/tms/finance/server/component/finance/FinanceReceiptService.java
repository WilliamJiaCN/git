package com.hivescm.tms.finance.server.component.finance;

import java.util.List;

import com.hivescm.tms.api.dto.es.finance.FinanceReceiptConfirmDTO;
import com.hivescm.tms.api.dto.es.finance.FinanceReceiptCreateDTO;
import com.hivescm.tms.intranet.gateway.api.dto.boss.BossIBankAccountInfoDto;

/**
 * 财务收款单
 *
 * @author 杨彭伟
 * @date 2017-11-21 15:56
 */
public interface FinanceReceiptService {

    /**
     * 生成收款记录
     * @param financeReceiptCreateDTO
     * @return
     */
    Long saveReceipt(FinanceReceiptCreateDTO financeReceiptCreateDTO);

    /**
     * 现金收款，确认收银
     * @return
     * @param financeReceiptConfirmDTO
     */
    Boolean updateReceiptToConfirm(FinanceReceiptConfirmDTO financeReceiptConfirmDTO);

    /**
     * 查询收款账户列表(当前网点的结算组织的银行账户)
     * @param userSn
     * @return
     */
    List<BossIBankAccountInfoDto> getAccountList(String userSn);
    
    /**
     * 修复线上没有生成货款发放记录的数据-swagger专用接口
     * @param receiptId
     * @return
     */
    public List<Long> resolvePaymentData(Long receiptId);
}
