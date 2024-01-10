package com.ubl.busschedule.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.service.spi.ServiceException;
import org.springframework.stereotype.Service;

import com.ubl.busschedule.domain.Bus;
import com.ubl.busschedule.repository.BusEntity;
import com.ubl.busschedule.repository.BusEntityRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BusServiceImpl  implements BusService {

    private final BusEntityRepository busEntityRepository;

    private Bus map(BusEntity entity) {
        final Bus bus = new Bus();
        bus.setCode(entity.getCode());
        bus.setBusName(entity.getBusName());
        bus.setBusRoute(entity.getBusRoute());
        bus.setBusSchedule(entity.getBusSchedule());
        return bus;
    }

    private BusEntity map(Bus bus) {
        final BusEntity entity = new BusEntity();
        entity.setCode(bus.getCode());
        entity.setBusName(bus.getBusName());
        entity.setBusRoute(bus.getBusRoute());
        entity.setBusSchedule(bus.getBusSchedule());
        return entity;

    }

    @Override
    public List<Bus> getBuss() {
        final List<BusEntity> entities = busEntityRepository.findAll();
        return entities.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Bus> findBusByCode(String code) {
        Optional<BusEntity> optionalBusEntity = busEntityRepository.findByCode(code);
        if (optionalBusEntity.isPresent()) {
            return Optional.of(this.map(optionalBusEntity.get()));
        } else {
            return Optional.empty();

        }
    }

    @Override
    public void save(Bus bus) {
        busEntityRepository.save(this.map(bus));
    }

    @Override
    public void delete(String code) {
       Optional<BusEntity> optionalEntity = busEntityRepository.findByCode(code);
        if (optionalEntity.isPresent()) {
            busEntityRepository.delete(optionalEntity.get());
        } else {
            throw new ServiceException("bus with code {0} not found" + code);
        }
    }

    @Override
    public void update(Bus bus) {
    
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
    
}
