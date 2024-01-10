package com.ubl.busschedule.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusEntityRepository extends JpaRepository<BusEntity, Long> {

    Optional<BusEntity> findByCode(final String code);
}
