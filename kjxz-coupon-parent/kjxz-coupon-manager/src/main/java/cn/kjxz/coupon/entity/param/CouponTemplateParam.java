package cn.kjxz.coupon.entity.param;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel("接收优惠券模板参数")
public class CouponTemplateParam {

    @ApiModelProperty(name = "cCategory", dataType = "string", required = true,  value= "优惠券类型：立减。。。")
    @NotBlank(message = "分类不能为null")//参数校验
    private String cCategory;
    @ApiModelProperty(name = "cExporeType", dataType = "String", required = true, value = "优惠券过期类型")
    private String cExpireType;
    @ApiModelProperty(name = "cExpireDay", dataType = "String", required = true, value = "优惠劵过期时间")
    private Integer cExpireDay;
    @ApiModelProperty(name = "cMerchantId", dataType = "String", required = true, value = "商家id:指定在当前商户及商户的子分店使用,可以为null，就是平台发的")
    private String cMerchantId;
    @ApiModelProperty(name = "cMerchantName", dataType = "String", required = true, value = "商家名称")
    private String cMerchantName;

    @ApiModelProperty(name = "cDesc", dataType = "String", required = true, value = "对优惠卷简单描述")
    private String cDesc;
    @ApiModelProperty(name = "cProductLine", dataType = "String", required = true, value = "产品线id 可以为null")
    private String cProductLine;
    @ApiModelProperty(name = "cLimiter", dataType = "Integer", required = true, value = "每个人领的数量")
    private Integer cLimiter;
    @ApiModelProperty(name = "cCount", dataType = "Integer", required = true, value = "这种优惠券的数量")
    private Integer cCount;
    @ApiModelProperty(name = "cAmount", dataType = "Double", required = true, value = "金额0<amount<1就是折扣券")
    private double cAmount;
    @ApiModelProperty(name = "cAmountLimit", dataType = "Double", required = true, value = "金额使用限制")
    private double cAmountLimit;
    @ApiModelProperty(name = "cConsumeStatus", dataType = "String", required = true, value = "1.同类不可使用 2.不能与所有进行使用 3.都可以使用")
    private String cConsumeStatus;

    public String getcCategory() {
        return cCategory;
    }

    public void setcCategory(String cCategory) {
        this.cCategory = cCategory;
    }

    public String getcExpireType() {
        return cExpireType;
    }

    public void setcExpireType(String cExpireType) {
        this.cExpireType = cExpireType;
    }

    public Integer getcExpireDay() {
        return cExpireDay;
    }

    public void setcExpireDay(Integer cExpireDay) {
        this.cExpireDay = cExpireDay;
    }

    public String getcMerchantId() {
        return cMerchantId;
    }

    public void setcMerchantId(String cMerchantId) {
        this.cMerchantId = cMerchantId;
    }

    public String getcMerchantName() {
        return cMerchantName;
    }

    public void setcMerchantName(String cMerchantName) {
        this.cMerchantName = cMerchantName;
    }

    public String getcDesc() {
        return cDesc;
    }

    public void setcDesc(String cDesc) {
        this.cDesc = cDesc;
    }

    public String getcProductLine() {
        return cProductLine;
    }

    public void setcProductLine(String cProductLine) {
        this.cProductLine = cProductLine;
    }

    public Integer getcLimiter() {
        return cLimiter;
    }

    public void setcLimiter(Integer cLimiter) {
        this.cLimiter = cLimiter;
    }

    public Integer getcCount() {
        return cCount;
    }

    public void setcCount(Integer cCount) {
        this.cCount = cCount;
    }

    public double getcAmount() {
        return cAmount;
    }

    public void setcAmount(double cAmount) {
        this.cAmount = cAmount;
    }

    public double getcAmountLimit() {
        return cAmountLimit;
    }

    public void setcAmountLimit(double cAmountLimit) {
        this.cAmountLimit = cAmountLimit;
    }

    public String getcConsumeStatus() {
        return cConsumeStatus;
    }

    public void setcConsumeStatus(String cConsumeStatus) {
        this.cConsumeStatus = cConsumeStatus;
    }
}
