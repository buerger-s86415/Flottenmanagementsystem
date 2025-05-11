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

import com.htwdresden.buerger_banse.fms.model.Position;
import com.htwdresden.buerger_banse.fms.repository.PositionRepository;

@RestController
@RequestMapping("/api/positions")
public class PositionController {

    private final PositionRepository positionRepo;

    public PositionController(PositionRepository positionRepo) {
        this.positionRepo = positionRepo;
    }

    @GetMapping
    public List<Position> getAllPositions() {
        List<Position> list = new ArrayList<>();
        positionRepo.findAll().forEach(list::add);
        return list;
    }

    @GetMapping("/{id}")
    public Position getPositionById(@PathVariable Long id) {
        return positionRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Position mit ID " + id + " nicht gefunden"));
    }

    @PostMapping
    public Position createPosition(@RequestBody Position position) {
        return positionRepo.save(position);
    }

    @PutMapping("/{id}")
    public Position updatePosition(@PathVariable Long id, @RequestBody Position updatedPosition) {
        Position position = positionRepo.findById(id).orElseThrow();
        position.setLatitude(updatedPosition.getLatitude());
        position.setLongitude(updatedPosition.getLongitude());
        return positionRepo.save(position);
    }

    @DeleteMapping("/{id}")
    public void deletePosition(@PathVariable Long id) {
        positionRepo.deleteById(id);
    }
}