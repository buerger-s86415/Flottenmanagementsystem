package com.htwdresden.buerger_banse.fms.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.htwdresden.buerger_banse.fms.model.PositionExec;

public interface PositionExecRepository extends CrudRepository<PositionExec, Long> {

    // Optional: alle Positionen nach Zeit sortieren (falls du später Zeitstempel nutzt)
    List<PositionExec> findAllByOrderByTimestampAsc();
}
