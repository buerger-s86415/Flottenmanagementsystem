package com.htwdresden.buerger_banse.fms.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.htwdresden.buerger_banse.fms.model.TrackExecution;
import com.htwdresden.buerger_banse.fms.repository.TrackExecutionRepository;

@RestController
@RequestMapping("/api/trackexecutions")
public class TrackExecutionController {

    private final TrackExecutionRepository trackRepo;

    public TrackExecutionController(TrackExecutionRepository trackRepo) {
        this.trackRepo = trackRepo;
    }

    @GetMapping
    public List<TrackExecution> getAllTrackExecutions() {
        List<TrackExecution> list = new ArrayList<>();
        trackRepo.findAll().forEach(list::add);
        return list;
    }

    @GetMapping("/{id}")
    public TrackExecution getTrackExecutionById(@PathVariable Long id) {
        return trackRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("TrackExecution mit ID " + id + " nicht gefunden"));
    }

    @PostMapping
    public TrackExecution createTrackExecution(@RequestBody TrackExecution exec) {
        return trackRepo.save(exec);
    }

    @PutMapping("/{id}")
    public TrackExecution updateTrackExecution(@PathVariable Long id, @RequestBody TrackExecution updatedExec) {
        TrackExecution exec = trackRepo.findById(id).orElseThrow();
        exec.setFleetUser(updatedExec.getFleetUser());
        exec.setRoute(updatedExec.getRoute());
        exec.setTenant(updatedExec.getTenant());
        exec.setUgv(updatedExec.getUgv());
        exec.setPositionExec(updatedExec.getPositionExec());
        return trackRepo.save(exec);
    }

    @DeleteMapping("/{id}")
    public void deleteTrackExecution(@PathVariable Long id) {
        trackRepo.deleteById(id);
    }
}
