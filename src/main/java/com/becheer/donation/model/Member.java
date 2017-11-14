package com.becheer.donation.model;

/*
* Member 用户实体类
* Creator : xiaokepu
* Date : 2017-09-14
*/

import java.util.Date;

public class Member {
    private long id;

    private String memberName;

    private String organizationType;

    private String organizationCode;

    private String address;

    private long donatedAmount;

    private long donateContractAmount;

    private long noContractAmount;

    private String password;

    private String mobile;

    private String contactTel;

    private String avatorImg;

    private String summary;

    private String project;

    private String idCardFrontImg;

    private String idCardBackImg;

    private String license;

    private int validation;

    private Date birthday;

    private String idCard;

    private int sex;

    private String loginIp;

    private String loginTime;

    //角色 0个人 1公司或社会组织
    private int role;

    //会员状态 0 已禁用 1未实名认证 2已实名认证
    private int enable;

    private Date createTime;

    private long createBy;

    private Date updateTime;

    private long updateBy;

    private String remark;

    //身份证
    private String id_card_before;

    private String id_card_after;

    public String getId_card_before() {
        return id_card_before;
    }

    public void setId_card_before(String id_card_before) {
        this.id_card_before = id_card_before;
    }

    public String getId_card_after() {
        return id_card_after;
    }

    public void setId_card_after(String id_card_after) {
        this.id_card_after = id_card_after;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    public String getOrganizationCode() {
        return organizationCode;
    }

    public void setOrganizationCode(String organizationCode) {
        this.organizationCode = organizationCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getDonatedAmount() {
        return donatedAmount;
    }

    public void setDonatedAmount(long donatedAmount) {
        this.donatedAmount = donatedAmount;
    }

    public long getDonateContractAmount() {
        return donateContractAmount;
    }

    public void setDonateContractAmount(long donateContractAmount) {
        this.donateContractAmount = donateContractAmount;
    }

    public long getNoContractAmount() {
        return noContractAmount;
    }

    public void setNoContractAmount(long noContractAmount) {
        this.noContractAmount = noContractAmount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getAvatorImg() {
        return avatorImg;
    }

    public void setAvatorImg(String avatorImg) {
        this.avatorImg = avatorImg;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getIdCardFrontImg() {
        return idCardFrontImg;
    }

    public void setIdCardFrontImg(String idCardFrontImg) {
        this.idCardFrontImg = idCardFrontImg;
    }

    public String getIdCardBackImg() {
        return idCardBackImg;
    }

    public void setIdCardBackImg(String idCardBackImg) {
        this.idCardBackImg = idCardBackImg;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public int getValidation() {
        return validation;
    }

    public void setValidation(int validation) {
        this.validation = validation;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getIdCard() {
//        String result="";
//        for(int i=1;i<13;i++){
//            result=result+"*";
//        }
//        String str1 = idCard.substring(0,2);
//        String str2 = idCard.substring(16,18);
//        result=str1+result+str2;
//        return result;
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public String getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(String loginTime) {
        this.loginTime = loginTime;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getEnable() {
        return enable;
    }

    public void setEnable(int enable) {
        this.enable = enable;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(long createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public long getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(long updateBy) {
        this.updateBy = updateBy;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
