package com.htwdresden.buerger_banse.fms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.htwdresden.buerger_banse.fms.model.Position;

public interface PositionRepository extends CrudRepository<Position, Long> {

    // Alle Positionen einer bestimmten Route
    List<Position> findByRoute_Id(Long routeId);
}
