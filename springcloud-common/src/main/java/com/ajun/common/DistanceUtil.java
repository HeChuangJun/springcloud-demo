package com.ajun.common;

/**
 * 距离工具类
 */
public class DistanceUtil {

    private static double EARTH_RADIUS = 6378.137D;
    /**
     * 根据2点经纬度计算两者之间的距离
     * 数据库中计算两点的距离
     * case when ac.lat is null then 0 else abs(sqrt(pow((?-ac.lat), 2)+pow((?-ac.lng), 2))) end as pointdistance
     * @param lat1 第一点的纬度
     * @param lng1 第一点的经度
     * @param lat2 第二点的纬度
     * @param lng2 第二点的经度
     * @return 返回的距离，单位m
     **/
    public static Double getDistance(Double lat1, Double lng1, Double lat2, Double lng2) {
        if(lat1 == null || lng1 == null || lat2 == null || lng2 == null){
            return null;
        }
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2.0D * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2.0D), 2.0D) +
        Math.cos(radLat1) * Math.cos(radLat2) *
        Math.pow(Math.sin(b / 2.0D), 2.0D)));
        s *= EARTH_RADIUS;
        s = Math.round(s * 10000.0D) / 10000.0D;
        return s;
    }

    private static double rad(double d) {
        return d * Math.PI / 180.0D;
    }
}
