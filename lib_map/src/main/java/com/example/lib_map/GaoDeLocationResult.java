package com.example.lib_map;

/**
 * Created by wys on 2017/7/21.
 */

public class GaoDeLocationResult {

    /**
     * var1.append("latitude=" + this.t + "#");
     * var1.append("longitude=" + this.u + "#");
     * var1.append("province=" + this.d + "#");
     * var1.append("city=" + this.e + "#");
     * var1.append("district=" + this.f + "#");
     * var1.append("cityCode=" + this.g + "#");
     * var1.append("adCode=" + this.h + "#");
     * var1.append("address=" + this.i + "#");
     * var1.append("country=" + this.k + "#");
     * var1.append("road=" + this.l + "#");
     * var1.append("poiName=" + this.j + "#");
     * var1.append("street=" + this.m + "#");
     * var1.append("streetNum=" + this.n + "#");
     * var1.append("aoiName=" + this.w + "#");
     * var1.append("poiid=" + this.a + "#");
     * var1.append("floor=" + this.b + "#");
     * var1.append("errorCode=" + this.p + "#");
     * var1.append("errorInfo=" + this.q + "#");
     * var1.append("locationDetail=" + this.r + "#");
     * var1.append("locationType=" + this.s);
     */

    private double longitude;
    private double latitude;
    private String province;
    private String city;
    private String district;
    private String cityCode;
    private String adCode;
    private String address;
    private String country;
    private String road;
    private String poiName;
    private String street;
    private String streetNum;
    private String aoiName;
    private String poiid;
    private String floor;
    private int errorCode;
    private String errorInfo;
    private String locationDetail;
    private String locationType;

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getAdCode() {
        return adCode;
    }

    public void setAdCode(String adCode) {
        this.adCode = adCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
    }

    public String getAoiName() {
        return aoiName;
    }

    public void setAoiName(String aoiName) {
        this.aoiName = aoiName;
    }

    public String getPoiid() {
        return poiid;
    }

    public void setPoiid(String poiid) {
        this.poiid = poiid;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getLocationDetail() {
        return locationDetail;
    }

    public void setLocationDetail(String locationDetail) {
        this.locationDetail = locationDetail;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }
}
