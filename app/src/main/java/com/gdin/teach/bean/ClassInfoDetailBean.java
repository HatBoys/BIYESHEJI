package com.gdin.teach.bean;

/**
 * Created by 黄培彦 on 16/3/16.
 * Email: peiyanhuang@yeah.net
 * 类说明:
 */
public class ClassInfoDetailBean {


    /**
     * errNum : 0
     * retMsg : success
     * retData : {"cityName":"北京","provinceName":"北京","cityCode":"101010100","zipCode":"100000","telAreaCode":"010"}
     */

    private int errNum;
    private String retMsg;
    /**
     * cityName : 北京
     * provinceName : 北京
     * cityCode : 101010100
     * zipCode : 100000
     * telAreaCode : 010
     */

    private RetDataEntity retData;

    public int getErrNum() {
        return errNum;
    }

    public void setErrNum(int errNum) {
        this.errNum = errNum;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }

    public RetDataEntity getRetData() {
        return retData;
    }

    public void setRetData(RetDataEntity retData) {
        this.retData = retData;
    }

    public static class RetDataEntity {
        private String cityName;
        private String provinceName;
        private String cityCode;
        private String zipCode;
        private String telAreaCode;

        public String getCityName() {
            return cityName;
        }

        public void setCityName(String cityName) {
            this.cityName = cityName;
        }

        public String getProvinceName() {
            return provinceName;
        }

        public void setProvinceName(String provinceName) {
            this.provinceName = provinceName;
        }

        public String getCityCode() {
            return cityCode;
        }

        public void setCityCode(String cityCode) {
            this.cityCode = cityCode;
        }

        public String getZipCode() {
            return zipCode;
        }

        public void setZipCode(String zipCode) {
            this.zipCode = zipCode;
        }

        public String getTelAreaCode() {
            return telAreaCode;
        }

        public void setTelAreaCode(String telAreaCode) {
            this.telAreaCode = telAreaCode;
        }
    }
}
