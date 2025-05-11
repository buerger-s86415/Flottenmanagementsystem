package com.htwdresden.buerger_banse.fms;

//http://localhost:8080/api/ugvs


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.htwdresden.buerger_banse.fms.model.FleetUser;
import com.htwdresden.buerger_banse.fms.model.Position;
import com.htwdresden.buerger_banse.fms.model.PositionExec;
import com.htwdresden.buerger_banse.fms.model.Route;
import com.htwdresden.buerger_banse.fms.model.Tenant;
import com.htwdresden.buerger_banse.fms.model.TrackExecution;
import com.htwdresden.buerger_banse.fms.model.UGV;
import com.htwdresden.buerger_banse.fms.repository.FleetUserRepository;
import com.htwdresden.buerger_banse.fms.repository.PositionExecRepository;
import com.htwdresden.buerger_banse.fms.repository.PositionRepository;
import com.htwdresden.buerger_banse.fms.repository.RouteRepository;
import com.htwdresden.buerger_banse.fms.repository.TenantRepository;
import com.htwdresden.buerger_banse.fms.repository.TrackExecutionRepository;
import com.htwdresden.buerger_banse.fms.repository.UGVRepository;

@SpringBootApplication
public class FmsApplication {

	private static final Logger log = LoggerFactory.getLogger(FmsApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(FmsApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(
			TenantRepository tenantRepo,
			UGVRepository ugvRepo,
			FleetUserRepository userRepo,
			RouteRepository routeRepo,
			PositionRepository positionRepo,
			TrackExecutionRepository trackExecutionRepo,
			PositionExecRepository positionExecRepo) {
		return args -> {

			// === Tenant 1 ===
			Tenant tenant1 = tenantRepo.save(new Tenant(1001));
			FleetUser user1 = userRepo.save(new FleetUser(1, "Kevin Bürger", tenant1));
			UGV ugv1 = ugvRepo.save(new UGV("UGV-A1", "Transporter", 3.5f, 85.0f, tenant1));
			UGV ugv2 = ugvRepo.save(new UGV("UGV-A2", "Transporter2", 3.5f, 34.0f, tenant1));
			Route route1 = routeRepo.save(new Route("R-01", "Innenstadt", tenant1));
			positionRepo.save(new Position(51, 13, route1));
			positionRepo.save(new Position(10, 45, route1));
			PositionExec exec1 = positionExecRepo.save(new PositionExec(51.010101010101f, 13.01010101010f, java.time.LocalDateTime.now()));
			trackExecutionRepo.save(new TrackExecution(route1, user1, ugv1, exec1, tenant1));

			// === Tenant 2 ===
			Tenant tenant2 = tenantRepo.save(new Tenant(1002));
			FleetUser user2 = userRepo.save(new FleetUser(2, "Benjamin Banse", tenant2));
			UGV ugv3 = ugvRepo.save(new UGV("UGV-B1", "Versorger", 4.0f, 65.0f, tenant2));
			Route route2 = routeRepo.save(new Route("R-02", "Campus", tenant2));
			positionRepo.save(new Position(15, 15, route2));
			positionRepo.save(new Position(26, 19, route2));
			PositionExec exec2 = positionExecRepo.save(new PositionExec(51, 13, java.time.LocalDateTime.now()));
			trackExecutionRepo.save(new TrackExecution(route2, user2, ugv2, exec2, tenant2));

			// === Ausgabe ===
			log.info("==== ALL TENANTS ====");
			tenantRepo.findAll().forEach(t ->
				log.info("Tenant: " + t.getTenantID() + " (DB-ID: " + t.getId() + ")")
			);

			log.info("==== ALL UGVs ====");
			ugvRepo.findAll().forEach(u ->
				log.info("UGV: " + u.getUgvId() + " (" + u.getDescription() + "), Akku: " + u.getBatteryLevel() + "%")
			);

			log.info("==== ALL USERS ====");
			userRepo.findAll().forEach(u ->
				log.info("User: " + u.getUserName() + ", userID=" + u.getUserID())
			);

			log.info("==== ALL ROUTES ====");
			routeRepo.findAll().forEach(r ->
				log.info("Route: " + r.getShortName() + " - " + r.getDescription())
			);

			log.info("==== ALL POSITIONS ====");
			positionRepo.findAll().forEach(p ->
				log.info("Position: " + p.getLatitude() + ", " + p.getLongitude() + " (Route: " + p.getRoute().getShortName() + ")")
			);

			log.info("==== ALL EXECUTED POSITIONS ====");
			positionExecRepo.findAll().forEach(p ->
				log.info("Executed Position: " + p.getLatitude() + ", " + p.getLongitude() + " at " + p.getTimestamp())
			);

			log.info("==== ALL TRACK EXECUTIONS ====");
			trackExecutionRepo.findAll().forEach(e ->
				log.info("Track: Tenant=" + e.getTenant().getTenantID() +
						", Route=" + e.getRoute().getShortName() +
						", UGV=" + e.getUgv().getUgvId() +
						", User=" + e.getFleetUser().getUserName() +
						", Position=" + e.getPositionExec().getLatitude() + "/" + e.getPositionExec().getLongitude())
			);
		};
	}
}