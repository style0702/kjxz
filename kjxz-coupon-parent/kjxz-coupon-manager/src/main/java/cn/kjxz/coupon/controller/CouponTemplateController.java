package cn.kjxz.coupon.controller;


import cn.kjxz.coupon.controller.validator.BindingResultUtil;
import cn.kjxz.coupon.entity.param.CouponTemplateParam;
import cn.kjxz.coupon.service.CouponTemplateService;
import cn.kjxz.global.common.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Api(value = "优惠劵",description ="优惠劵模板接口" )
@RestController
public class CouponTemplateController {
    @Autowired
    private CouponTemplateService couponTemplateService;

    @PostMapping("couponTemplate")
    @ApiOperation("增加优惠劵模板")
    public ResultResponse resultResponse(
            @ApiParam(value = "优惠劵logo",name = "multipartFile",required = true) MultipartFile multipartFile,
            @ApiParam("模板接收实体类") @Validated  CouponTemplateParam couponTemplateParam, BindingResult bindingResult){
        BindingResultUtil.checkBindingResult(bindingResult);
        //todo 操作人员
        couponTemplateService.add(couponTemplateParam);
        return ResultResponse.success();
    }

}
