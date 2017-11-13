package com.becheer.donation.model;

/*
* 阿里返回的IP地址实体
* Creator : xiaokepu
* Date : 2017-11-13
*/
public class AliIpDetail {
    //国家
    private String country;

    //区域
    private String area;

    //省
    private String region;

    //市
    private String city;

    //网络服务商
    private String isp;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getIsp() {
        return isp;
    }

    public void setIsp(String isp) {
        this.isp = isp;
    }
}
