package com.htwdresden.buerger_banse.fms;

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
			// Tenant anlegen
			Tenant tenant = new Tenant(1001);
			tenantRepo.save(tenant);

			// UGVs anlegen
			UGV ugv = new UGV("UGV-A1", "Transporter", 3.5f, 85.0f, tenant);
			ugvRepo.save(ugv);

			// FleetUser anlegen
			FleetUser user = new FleetUser(1, "Max Mustermann", tenant);
			userRepo.save(user);

			// Route anlegen
			Route route = new Route("R-01", "Teststrecke", tenant);
			routeRepo.save(route);

			// Positionen für Route anlegen
			Position pos1 = new Position(51.0504f, 13.7373f, route);
			Position pos2 = new Position(51.0510f, 13.7400f, route);
			positionRepo.save(pos1);
			positionRepo.save(pos2);

			// PositionExec anlegen
			PositionExec execPos = new PositionExec(51.0504f, 13.7373f, java.time.LocalDateTime.now());
			positionExecRepo.save(execPos);

			// TrackExecution anlegen
			TrackExecution exec = new TrackExecution(route, user, ugv, execPos, tenant);
			trackExecutionRepo.save(exec);

			// Ausgabe aller Objekte
			log.info("==== TENANTS ====");
			tenantRepo.findAll().forEach(t ->
				log.info("Tenant ID: " + t.getTenantID() + " (DB-ID: " + t.getId() + ")")
			);

			log.info("==== FLEET USERS ====");
			userRepo.findAll().forEach(u ->
				log.info("User: " + u.getUserName() + " (userID=" + u.getUserID() + ", Tenant=" + u.getTenant().getTenantID() + ")")
			);

			log.info("==== UGVs ====");
			ugvRepo.findAll().forEach(u ->
				log.info("UGV: " + u.getUgvId() + " (" + u.getDescription() + "), Akku: " + u.getBatteryLevel() + "%, Tenant: " + u.getTenant().getTenantID())
			);

			log.info("==== ROUTES ====");
			routeRepo.findAll().forEach(r ->
				log.info("Route: " + r.getShortName() + " - " + r.getDescription() + ", Tenant: " + r.getTenant().getTenantID())
			);

			log.info("==== POSITIONS ====");
			positionRepo.findAll().forEach(p ->
				log.info("Position: " + p.getLatitude() + ", " + p.getLongitude() + ", Route: " + p.getRoute().getShortName())
			);

			log.info("==== POSITION EXECUTIONS ====");
			positionExecRepo.findAll().forEach(p ->
				log.info("Exec Position: " + p.getLatitude() + ", " + p.getLongitude() + ", Zeit: " + p.getTimestamp())
			);

			log.info("==== TRACK EXECUTIONS ====");
			trackExecutionRepo.findAll().forEach(e -> {
				log.info("TrackExec: Route=" + e.getRoute().getShortName() +
						 ", UGV=" + e.getUgv().getUgvId() +
						 ", User=" + e.getFleetUser().getUserName() +
						 ", Pos=" + e.getPositionExec().getLatitude() + "/" + e.getPositionExec().getLongitude() +
						 ", Tenant=" + e.getTenant().getTenantID());
			});
		};
	}
}
