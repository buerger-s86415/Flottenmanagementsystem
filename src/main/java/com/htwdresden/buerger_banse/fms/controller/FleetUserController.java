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

import com.htwdresden.buerger_banse.fms.model.FleetUser;
import com.htwdresden.buerger_banse.fms.repository.FleetUserRepository;

@RestController
@RequestMapping("/api/fleetusers")
public class FleetUserController {

    private final FleetUserRepository userRepo;

    public FleetUserController(FleetUserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public List<FleetUser> getAllUsers() {
        List<FleetUser> list = new ArrayList<>();
        userRepo.findAll().forEach(list::add);
        return list;
    }

    @GetMapping("/{id}")
    public FleetUser getUserById(@PathVariable Long id) {
        return userRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("FleetUser mit ID " + id + " nicht gefunden"));
    }

    @PostMapping
    public FleetUser createUser(@RequestBody FleetUser user) {
        return userRepo.save(user);
    }

    @PutMapping("/{id}")
    public FleetUser updateUser(@PathVariable Long id, @RequestBody FleetUser updatedUser) {
        FleetUser user = userRepo.findById(id).orElseThrow();
        user.setUserID(updatedUser.getUserID());
        user.setUserName(updatedUser.getUserName());
        return userRepo.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepo.deleteById(id);
    }
} 
