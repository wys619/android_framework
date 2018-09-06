package com.example.lib_map;

import android.content.Context;
import android.util.Log;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;

/**
 * 高德地图定位服务,单次获得
 * Created by WYS on 2016/11/2.
 */

public class GaoDeLocation {

    private AMapLocationClient locationClient = null;
    private Context context;
    private ILocationCallback callback;
    private static GaoDeLocation location;
    private AMapLocationClientOption mOption;
    private static final String TAG = "GaoDeLocation";

    private GaoDeLocation() {

    }

    public static GaoDeLocation getInstance() {
        if (location == null) {
            location = new GaoDeLocation();
        }
        return location;
    }

    public void startOneTimeLocation(Context context, ILocationCallback callback) {
        Log.i(TAG, "startOneTimeLocation()");
        this.context = context.getApplicationContext();
        this.callback = callback;
        initLocation();
        locationClient.startLocation();
    }

    public void stopLocation() {
        Log.i(TAG, "stopLocation()");
        locationClient.stopLocation();
        locationClient.onDestroy();
        locationClient = null;
        context = null;
        location = null;
        callback = null;
        mOption = null;
        System.gc();
    }

    /**
     * 初始化定位
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private void initLocation() {
        if (locationClient == null) {
            locationClient = new AMapLocationClient(context);
            locationClient.setLocationOption(getDefaultOption());
            locationClient.setLocationListener(locationListener);
        }
    }

    /**
     * 默认的定位参数
     *
     * @author hongming.wang
     * @since 2.8.0
     */
    private AMapLocationClientOption getDefaultOption() {
        if (mOption == null) {
            mOption = new AMapLocationClientOption();
            mOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);//可选，设置定位模式，可选的模式有高精度、仅设备、仅网络。默认为高精度模式
            mOption.setGpsFirst(false);             //可选，设置是否gps优先，只在高精度模式下有效。默认关闭
            mOption.setHttpTimeOut(8000);           //可选，设置网络请求超时时间。默认为30秒。在仅设备模式下无效
            mOption.setInterval(2000);              //可选，设置定位间隔。默认为2秒
            mOption.setNeedAddress(true);           //可选，设置是否返回逆地理地址信息。默认是true
            mOption.setOnceLocation(true);          //可选，设置是否单次定位。默认是false
            mOption.setOnceLocationLatest(true);    //可选，设置是否等待wifi刷新，默认为false.如果设置为true,会自动变为单次定位，持续定位时不要使用
            AMapLocationClientOption.setLocationProtocol(AMapLocationClientOption.AMapLocationProtocol.HTTP);//可选， 设置网络请求的协议。可选HTTP或者HTTPS。默认为HTTP
            mOption.setSensorEnable(true);          //可选，设置是否使用传感器。默认是false
        }
        return mOption;
    }


    /**
     * 定位监听
     */
    private AMapLocationListener locationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation loc) {
            Log.i(TAG, "onLocationChanged()");
            if (callback == null) {
                return;
            }
            GaoDeLocationResult result = null;
            if (null != loc) {
                Log.i(TAG, loc.toString());
                result = new GaoDeLocationResult();
                result.setAdCode(loc.getAdCode());
                result.setAddress(loc.getAddress());
                result.setAoiName(loc.getAoiName());
                result.setCity(loc.getCity());
                result.setCityCode(loc.getCityCode());
                result.setCountry(loc.getCountry());
                result.setDistrict(loc.getDistrict());
                result.setErrorCode(loc.getErrorCode());
                result.setFloor(loc.getFloor());
                result.setLocationDetail(loc.getLocationDetail());
                result.setLatitude(loc.getLatitude());
                result.setProvince(loc.getProvince());
                result.setPoiName(loc.getPoiName());
                result.setRoad(loc.getRoad());
                result.setStreet(loc.getStreet());
                result.setStreetNum(loc.getStreetNum());
                result.setLongitude(loc.getLongitude());
                result.setErrorInfo(loc.getErrorInfo());
            } else {
                Log.e(TAG, "定位失败！");
            }
            callback.onLocationResult(result);
            stopLocation();
        }
    };

}
