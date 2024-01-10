package com.ubl.busschedule.service;

import java.util.List;
import java.util.Optional;
import com.ubl.busschedule.domain.Bus;

public interface BusService {
    
    List<Bus> getBuss();

    Optional<Bus> findBusByCode(final String code);

    void save(Bus bus);

    void delete(final String code);

    void update(final Bus bus);

}
