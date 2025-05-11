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

import com.htwdresden.buerger_banse.fms.model.Tenant;
import com.htwdresden.buerger_banse.fms.repository.TenantRepository;

@RestController
@RequestMapping("/api/tenants")
public class TenantController {

    private final TenantRepository tenantRepo;

    public TenantController(TenantRepository tenantRepo) {
        this.tenantRepo = tenantRepo;
    }

    // Alle Tenants abfragen
    @GetMapping
    public List<Tenant> getAllTenants() {
        List<Tenant> list = new ArrayList<>();
        tenantRepo.findAll().forEach(list::add);
        return list;
    }

    // Einzelnen Tenant per ID abfragen
    @GetMapping("/{id}")
    public Tenant getTenantById(@PathVariable Long id) {
        return tenantRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Tenant mit ID " + id + " nicht gefunden"));
    }

    // Neuen Tenant anlegen
    @PostMapping
    public Tenant createTenant(@RequestBody Tenant tenant) {
        return tenantRepo.save(tenant);
    }

    // Tenant aktualisieren
    @PutMapping("/{id}")
    public Tenant updateTenant(@PathVariable Long id, @RequestBody Tenant updatedTenant) {
        Tenant tenant = tenantRepo.findById(id).orElseThrow();
        tenant.setTenantID(updatedTenant.getTenantID());
        return tenantRepo.save(tenant);
    }

    // Tenant löschen
    @DeleteMapping("/{id}")
    public void deleteTenant(@PathVariable Long id) {
        tenantRepo.deleteById(id);
    }
} 
