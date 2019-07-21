package demo.entity;

import java.io.Serializable;

public class Address implements Serializable {
    private static final long serialVersionUID = -680777855026808602L;

    private String provinceName;
    private String cityName;
    private String countyName;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountyName() {
        return countyName;
    }

    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }
}
