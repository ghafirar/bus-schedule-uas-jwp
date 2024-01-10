package com.ubl.busschedule.repository;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bus")
@Getter
@Setter
public class BusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code", nullable = false)
    private String code;

    @Column(name = "bus_name", nullable = false)
    private String busName;

    @Column(name = "bus_route")
    private String busRoute;

    @Column(name = "bus_schedule")
    private Date busSchedule;

}
