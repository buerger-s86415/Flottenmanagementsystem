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

import com.htwdresden.buerger_banse.fms.model.Route;
import com.htwdresden.buerger_banse.fms.repository.RouteRepository;

@RestController
@RequestMapping("/api/routes")
public class RouteController {

    private final RouteRepository routeRepo;

    public RouteController(RouteRepository routeRepo) {
        this.routeRepo = routeRepo;
    }

    @GetMapping
    public List<Route> getAllRoutes() {
        List<Route> list = new ArrayList<>();
        routeRepo.findAll().forEach(list::add);
        return list;
    }

    @GetMapping("/{id}")
    public Route getRouteById(@PathVariable Long id) {
        return routeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Route mit ID " + id + " nicht gefunden"));
    }

    @PostMapping
    public Route createRoute(@RequestBody Route route) {
        return routeRepo.save(route);
    }

    @PutMapping("/{id}")
    public Route updateRoute(@PathVariable Long id, @RequestBody Route updatedRoute) {
        Route route = routeRepo.findById(id).orElseThrow();
        route.setShortName(updatedRoute.getShortName());
        route.setDescription(updatedRoute.getDescription());
        return routeRepo.save(route);
    }

    @DeleteMapping("/{id}")
    public void deleteRoute(@PathVariable Long id) {
        routeRepo.deleteById(id);
    }
} 
