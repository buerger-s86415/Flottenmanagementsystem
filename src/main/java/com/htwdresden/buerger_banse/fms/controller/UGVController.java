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

import com.htwdresden.buerger_banse.fms.model.UGV;
import com.htwdresden.buerger_banse.fms.repository.UGVRepository;

@RestController
@RequestMapping("/api/ugvs")
public class UGVController {

    private final UGVRepository ugvRepo;

    public UGVController(UGVRepository ugvRepo) {
        this.ugvRepo = ugvRepo;
    }

    // Alle UGVs abfragen
    @GetMapping
    public List<UGV> getAllUGVs() {
        List<UGV> list = new ArrayList<>();
        ugvRepo.findAll().forEach(list::add);
        return list;
    }


    // Einzelnen UGV per ID abfragen
    @GetMapping("/{id}")
    public UGV getUGVById(@PathVariable Long id) {
        return ugvRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("UGV mit ID " + id + " nicht gefunden"));
    }

    // Neuen UGV anlegen
    @PostMapping
    public UGV createUGV(@RequestBody UGV ugv) {
        return ugvRepo.save(ugv);
    }

    // UGV aktualisieren
    @PutMapping("/{id}")
    public UGV updateUGV(@PathVariable Long id, @RequestBody UGV updatedUGV) {
        UGV ugv = ugvRepo.findById(id).orElseThrow();
        ugv.setUgvId(updatedUGV.getUgvId());
        ugv.setDescription(updatedUGV.getDescription());
        ugv.setBatteryLevel(updatedUGV.getBatteryLevel());
        ugv.setMaxSpeed(updatedUGV.getMaxSpeed());
        return ugvRepo.save(ugv);
    }

    // UGV löschen
    @DeleteMapping("/{id}")
    public void deleteUGV(@PathVariable Long id) {
        ugvRepo.deleteById(id);
    }
}
