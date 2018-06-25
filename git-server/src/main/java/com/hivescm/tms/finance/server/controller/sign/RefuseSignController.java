package com.hivescm.tms.finance.server.controller.sign;


import com.hivescm.common.domain.DataResult;
import com.hivescm.common.domain.PagedList;
import com.hivescm.common.exception.SystemException;
import com.hivescm.tms.api.dto.es.sign.SignRefuseEsDTO;
import com.hivescm.tms.api.dto.es.sign.component.TmsGoodsDetailsEsDTO;
import com.hivescm.tms.api.dto.es.sign.request.RefuseForSignReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.RefuseSignNoticeReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.RefuseSignQueryReqDTO;
import com.hivescm.tms.api.dto.es.sign.request.SignForDetailsReqDTO;
import com.hivescm.tms.api.dto.es.sign.response.RefuseSignResultRespDTO;
import com.hivescm.tms.constants.ExceptionCodeConstants;
import com.hivescm.tms.finance.server.component.sign.RefuseSignService;
import com.hivescm.tms.finance.server.interfaces.sign.RefuseSignControllerDoc;
import com.hivescm.tms.finance.server.service.lock.SignLockService;
import com.hivescm.tms.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 拒收单
 *
 * @author 杨彭伟
 * @since 2017/11/8 11:17
 */
@RestController
@RequestMapping("/sign/refuse")
public class RefuseSignController implements RefuseSignControllerDoc {

    @Autowired
    private RefuseSignService refuseSignService;

    @Autowired
    private SignLockService signLockService;
    //生成拒收单 新方法

    /**
     * 拒收
     *
     * 重复提交 唯一性验证 非幂等
     * 如果失败 报错误码
     *
     * 不区分  全拒签 部分签 全签
     *
     * 方案确定
     * 第一种，若操作用户操作全部签收提交后，然后提交过程中，再次点击返回，在修改这个部分签收，那这块需要三个系统联动，且从开发角度涉及修改的比较多，就是说白了，工作量大
     * 第二种方案，由TMS系统的APP自行控制，即提交了全部签收了，就不能反悔了，即使点击返回 并且再次修改部分签收，点击提交的时候也会验证之前做过的操作，这个改动最少的。
     * 目前按照第二种方案实行，请熟知
     *
     * @see SignForController#insertSign(SignForDetailsReqDTO)
     *
     * {@link SignForController#insertSign(SignForDetailsReqDTO)}
     * @param refuseForSignReqDTO
     * @return
     */
    @Override
    @PostMapping("/save")
//    @Validated(uniqueKey = IdempotencyConstants.BIZ_TMS_SIGN_TYPE_RECOMMIT,uniqueValue = "refuseForSignReqDTO.waybillId")
	public DataResult<Integer> saveRefuseSign(@RequestBody RefuseForSignReqDTO refuseForSignReqDTO) {
		DataResult<Integer> result = new DataResult<Integer>();

        Integer count = 0;
		    //获取重复提交分布式锁
        Boolean lock = signLockService.signRecommitLock(refuseForSignReqDTO.getWaybillId());

        if (!lock) {
            return DataResult.faild(ExceptionCodeConstants.ERROR_ADD_SIGN, "请勿重复提交");
        }


        try {
            count = refuseSignService.insertRefuseSignNew(refuseForSignReqDTO);
            result = DataResult.success(count, Integer.class);
		} catch (SystemException e) {
			e.printStackTrace();
            try {
                signLockService.signRecommitUnLock(refuseForSignReqDTO.getWaybillId());
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            result = DataResult.faild(e.getErrorCode(), e.getErrorReason());
		}

		return result;
	}

    
    @Override
    @PostMapping("/notice")
    public DataResult<Boolean> noticeRefuseSign(@RequestBody RefuseSignNoticeReqDTO refuseForSignReqDTO) {
        String customerOrderCode = refuseForSignReqDTO.getCustomerOrderCode();
        if (!StringUtil.isNumber(customerOrderCode)) {
            return DataResult.faild(ExceptionCodeConstants.ERROR_REFUSE_SIGN, "订单编号类型错误");
        }
        DataResult<Boolean> result = refuseSignService.updateRefuseSign(refuseForSignReqDTO);

        if (result.isSuccess()){
            //释放重复提交分布式锁
            try {
                signLockService.signRecommitUnLock(refuseForSignReqDTO.getRefuseSignWaybillId());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    @Override
    @GetMapping("/get/{refuseId}")
    public DataResult<RefuseSignResultRespDTO> getRefuseSignAmount(@PathVariable("refuseId") Long refuseId) {
        return refuseSignService.getRefuseSignAmount(refuseId);
    }

    @Override
    @PostMapping("/list")
    public DataResult<PagedList<SignRefuseEsDTO>> getRefuseSignList(@RequestBody RefuseSignQueryReqDTO refuseSignQueryReqDTO) {
    	List<SignRefuseEsDTO> list = refuseSignService.getRefuseSignList(refuseSignQueryReqDTO);
		long count = refuseSignService.getCount(refuseSignQueryReqDTO);
		PagedList<SignRefuseEsDTO> page = new PagedList<SignRefuseEsDTO>();
		page.setTotalCount((int) count);
		page.setItems(list);
		page.setCurrentPage(refuseSignQueryReqDTO.getCurrentPage());
		page.setPageSize(refuseSignQueryReqDTO.getPageSize());
		//
		DataResult<PagedList<SignRefuseEsDTO>> rs = new DataResult();
		rs.setResult(page);
    	return rs;
    }

    @Override
    @GetMapping("/goods/{refuseId}")
    public DataResult<List<TmsGoodsDetailsEsDTO>> getRefuseSignGoodsDetails(
            @PathVariable("refuseId") Long refuseId) {
        return refuseSignService.getRefuseSignGoodsDetails(refuseId);
    }

    /**
     *　该接口给oms提供，没有传递 companyId
     *  2018-06-09 运单改造需要传递companyId，待确认
     * @param waybillCode
     * @param companyId
     * @return
     */
    @Override
    @GetMapping("/cancel/{companyId}/{waybillCode}")
    public DataResult<Boolean> cancelRefuseSign(
            @PathVariable("waybillCode") String waybillCode,
            @PathVariable("companyId") @Deprecated Long companyId) {
        return refuseSignService.updateRefuseSignToCancel(waybillCode, companyId);
    }
//    @Deprecated
//    @Override
//    @GetMapping("/cancel/{companyId}/{orderCode}")
//    public DataResult<Boolean> cancelRefuseSign(
//            @PathVariable("orderCode") String orderCode,
//            @PathVariable("companyId") Long companyId) {
//
//
//        throw new SystemException(ExceptionCodeConstants.ERROR_REFUSE_SIGN, "接口已过时");
//    }

    @GetMapping("/test")
    public DataResult test() {
        return refuseSignService.test();
    }
}
