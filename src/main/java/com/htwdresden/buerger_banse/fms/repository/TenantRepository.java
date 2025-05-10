package com.htwdresden.buerger_banse.fms.repository;

import org.springframework.data.repository.CrudRepository;

import com.htwdresden.buerger_banse.fms.model.Tenant;

public interface TenantRepository extends CrudRepository<Tenant, Long> {
    Tenant findByTenantID(int tenantID);
}
