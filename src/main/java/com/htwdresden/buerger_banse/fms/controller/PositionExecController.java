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

import com.htwdresden.buerger_banse.fms.model.PositionExec;
import com.htwdresden.buerger_banse.fms.repository.PositionExecRepository;

@RestController
@RequestMapping("/api/positionexecs")
public class PositionExecController {

    private final PositionExecRepository positionExecRepo;

    public PositionExecController(PositionExecRepository positionExecRepo) {
        this.positionExecRepo = positionExecRepo;
    }

    @GetMapping
    public List<PositionExec> getAllExecs() {
        List<PositionExec> list = new ArrayList<>();
        positionExecRepo.findAll().forEach(list::add);
        return list;
    }

    @GetMapping("/{id}")
    public PositionExec getExecById(@PathVariable Long id) {
        return positionExecRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("PositionExec mit ID " + id + " nicht gefunden"));
    }

    @PostMapping
    public PositionExec createExec(@RequestBody PositionExec exec) {
        return positionExecRepo.save(exec);
    }

    @PutMapping("/{id}")
    public PositionExec updateExec(@PathVariable Long id, @RequestBody PositionExec updatedExec) {
        PositionExec exec = positionExecRepo.findById(id).orElseThrow();
        exec.setLatitude(updatedExec.getLatitude());
        exec.setLongitude(updatedExec.getLongitude());
        exec.setTimestamp(updatedExec.getTimestamp());
        return positionExecRepo.save(exec);
    }

    @DeleteMapping("/{id}")
    public void deleteExec(@PathVariable Long id) {
        positionExecRepo.deleteById(id);
    }
}
