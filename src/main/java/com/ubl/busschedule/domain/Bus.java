package com.ubl.busschedule.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Getter
@Setter
public class Bus {
    private String code;

    @NotBlank(message = "full name is required")
    @Size(min = 3, max = 50)
    private String busName;

     @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Practice Schedule is required")
    private Date busSchedule;

    @NotBlank(message = "full name is required")
    @Size(min = 3, max = 50)
    private String busRoute;
    

    public Bus() {
    }

    public String getBusName() {
        return busName;
    }

    public void setBusName(String busName) {
        this.busName = busName;
    }

    public String getBusRoute() {
        return busRoute;
    }

    public void setBusRoute(String busRoute) {
        this.busRoute = busRoute;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getBusSchedule() {
        return busSchedule;
    }

    public void setBusSchedule(Date busSchedule) {
        this.busSchedule = busSchedule;
    }
}
