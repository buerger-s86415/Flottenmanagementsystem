# Flottenmanagementsystem für UGVs (Aufgabenstellung siehe unten)

## REST-API Endpunktübersicht

| Entität         | Methode | URL                           | Aktion                   |
|------------------|---------|-------------------------------|---------------------------|
| UGV              | GET     | /api/ugvs                     | Alle anzeigen             |
| UGV              | GET     | /api/ugvs/{id}                | Eintrag per ID            |
| UGV              | POST    | /api/ugvs                     | Neuen Eintrag erstellen   |
| UGV              | PUT     | /api/ugvs/{id}                | Eintrag aktualisieren     |
| UGV              | DELETE  | /api/ugvs/{id}                | Eintrag löschen           |
| Tenant           | GET     | /api/tenants                  | Alle anzeigen             |
| Tenant           | GET     | /api/tenants/{id}             | Eintrag per ID            |
| Tenant           | POST    | /api/tenants                  | Neuen Eintrag erstellen   |
| Tenant           | PUT     | /api/tenants/{id}             | Eintrag aktualisieren     |
| Tenant           | DELETE  | /api/tenants/{id}             | Eintrag löschen           |
| FleetUser        | GET     | /api/fleetusers               | Alle anzeigen             |
| FleetUser        | GET     | /api/fleetusers/{id}          | Eintrag per ID            |
| FleetUser        | POST    | /api/fleetusers               | Neuen Eintrag erstellen   |
| FleetUser        | PUT     | /api/fleetusers/{id}          | Eintrag aktualisieren     |
| FleetUser        | DELETE  | /api/fleetusers/{id}          | Eintrag löschen           |
| Route            | GET     | /api/routes                   | Alle anzeigen             |
| Route            | GET     | /api/routes/{id}              | Eintrag per ID            |
| Route            | POST    | /api/routes                   | Neuen Eintrag erstellen   |
| Route            | PUT     | /api/routes/{id}              | Eintrag aktualisieren     |
| Route            | DELETE  | /api/routes/{id}              | Eintrag löschen           |
| Position         | GET     | /api/positions                | Alle anzeigen             |
| Position         | GET     | /api/positions/{id}           | Eintrag per ID            |
| Position         | POST    | /api/positions                | Neuen Eintrag erstellen   |
| Position         | PUT     | /api/positions/{id}           | Eintrag aktualisieren     |
| Position         | DELETE  | /api/positions/{id}           | Eintrag löschen           |
| PositionExec     | GET     | /api/positionexecs            | Alle anzeigen             |
| PositionExec     | GET     | /api/positionexecs/{id}       | Eintrag per ID            |
| PositionExec     | POST    | /api/positionexecs            | Neuen Eintrag erstellen   |
| PositionExec     | PUT     | /api/positionexecs/{id}       | Eintrag aktualisieren     |
| PositionExec     | DELETE  | /api/positionexecs/{id}       | Eintrag löschen           |
| TrackExecution   | GET     | /api/trackexecutions          | Alle anzeigen             |
| TrackExecution   | GET     | /api/trackexecutions/{id}     | Eintrag per ID            |
| TrackExecution   | POST    | /api/trackexecutions          | Neuen Eintrag erstellen   |
| TrackExecution   | PUT     | /api/trackexecutions/{id}     | Eintrag aktualisieren     |
| TrackExecution   | DELETE  | /api/trackexecutions/{id}     | Eintrag löschen           |

## REST API Anleitung

# 📡 REST API Anleitung

Dieses Projekt bietet eine voll funktionsfähige REST-API zur Verwaltung von UGV-Flotten. Die API basiert auf Spring Boot und verwendet standardisierte HTTP-Methoden.

---

## ✅ Voraussetzungen

* Java 17 oder 21+
* Maven
* Optional: Postman (oder curl) zum Testen
* Projekt starten mit:

```bash
./mvnw spring-boot:run
```

---

## 📂 Basis-URL (bei lokalem Start)

```
http://localhost:8080
```

---

## 🚀 Beispiel-Endpunkte (für UGV)

| Methode  | Pfad             | Beschreibung                  |
| -------- | ---------------- | ----------------------------- |
| `GET`    | `/api/ugvs`      | Alle UGVs abrufen             |
| `GET`    | `/api/ugvs/{id}` | Einzelnen UGV abrufen         |
| `POST`   | `/api/ugvs`      | Neuen UGV speichern           |
| `PUT`    | `/api/ugvs/{id}` | Bestehenden UGV aktualisieren |
| `DELETE` | `/api/ugvs/{id}` | UGV löschen                   |

---

## 🧪 Beispiel: Neuen UGV erstellen (mit Postman oder curl)

### 🔗 URL

```http
POST http://localhost:8080/api/ugvs
```

### 📦 JSON-Body

```json
{
  "ugvId": "UGV-X9",
  "description": "Wartungseinheit",
  "maxSpeed": 3.5,
  "batteryLevel": 90.0
}
```

---

## 🔄 Gleiches Prinzip für alle anderen Entitäten

Ersetze einfach `ugvs` durch:

* `tenants`
* `fleetusers`
* `routes`
* `positions`
* `positionexecs`
* `trackexecutions`

Beispiel:

```http
GET http://localhost:8080/api/fleetusers
```

---

## 🛠 Fehlerbehandlung

* Wenn ein Objekt mit gegebener ID nicht existiert, gibt die API `404 Not Found` zurück.
* JSON muss gültig und vollständig sein – sonst `400 Bad Request`

---

## 🧰 Entwicklerhinweis

Die REST-Controller befinden sich im Paket:

```text
com.htwdresden.buerger_banse.fms.controller
```

Die Repositories unter:

```text
com.htwdresden.buerger_banse.fms.repository
```

---

Viel Spaß beim Testen und Erweitern deiner REST-API! 💡


## Klassenbeschreibung (warum und was)
## 📦 Datenmodellübersicht

### 🧩 1. `Tenant.java`  
**= Mandant / Betreiber**

- Repräsentiert z. B. ein Unternehmen, eine Abteilung oder Organisation
- Jeder `Tenant` besitzt:
  - eigene Nutzer (`FleetUser`)
  - eigene Fahrzeuge (`UGV`)
  - eigene Routen (`Route`)
  - eigene Ausführungen (`TrackExecution`)

> 💡 Zentrale Verwaltungs-Entität

---

### 👤 2. `FleetUser.java`  
**= Nutzer, der Routen steuert oder plant**

- Gehört genau zu einem `Tenant`
- Hat:
  - eine `userID` (interne ID)
  - einen `userName` (Anzeigename)
- Wird einer `TrackExecution` zugewiesen

> 💡 Beispiel: Max Mustermann, der UGVs fährt

---

### 🚗 3. `UGV.java`  
**= Unbemanntes Fahrzeug (Unmanned Ground Vehicle)**

- Gehört zu einem `Tenant`
- Hat:
  - `ugvId` (z. B. „UGV-A1“)
  - technische Eigenschaften (z. B. `maxSpeed`, `batteryLevel`)
- Wird in `TrackExecution` eingesetzt

> 💡 Ein konkretes, fahrbares System

---

### 🛣️ 4. `Route.java`  
**= Ein Streckenplan mit Positionen**

- Gehört zu einem `Tenant`
- Besteht aus mehreren `Position`-Objekten (Wegpunkte)
- Kann in mehreren `TrackExecution`-Instanzen verwendet werden

> 💡 Z. B. Route durch das Werksgelände

---

### 📍 5. `Position.java`  
**= Ein einzelner geplanter Punkt auf einer Route**

- Enthält:
  - `latitude` + `longitude`
- Gehört genau zu einer `Route`

> 💡 Zielpunkt auf einer Strecke

---

### 🛰️ 6. `PositionExec.java`  
**= Eine tatsächlich angefahrene Position**

- Wird in `TrackExecution` verwendet
- Hat:
  - Position (`latitude`, `longitude`)
  - Zeitpunkt (`timestamp`)
  - optional: Abweichung (z. B. `positionDeviation`)

> 💡 Wird genutzt, um zu prüfen, wo das UGV wirklich war

---

### ▶️ 7. `TrackExecution.java`  
**= Eine konkrete Ausführung einer Route**

- Verbindet:
  - einen `Tenant`
  - eine `Route`
  - ein `UGV`
  - ein `FleetUser`
  - eine `PositionExec`
- Wird z. B. pro Einsatz dokumentiert

> 💡 Quasi ein Logbucheintrag

---

### 🔗 Beziehungen im Überblick:

```text
Tenant
 ├──> FleetUser
 ├──> UGV
 ├──> Route ───> Position
 └──> TrackExecution ───> UGV
                        └──> FleetUser
                        └──> Route
                        └──> PositionExec
```
_____________________________________________________________________________________
## Projektziel (Aufgabenstellung)

Entwicklung eines Flottenmanagementsystems (FMS) für UGVs (Unmanned Guided Vehicles).  
Das System soll ein skalierbares, sicheres Backend bieten, das zentrale Funktionen zur Fahrzeugverfolgung, Routenplanung, Aufgabenverteilung, Kommunikation und Diagnose bereitstellt.

## Hauptanforderungen

### 1. Fahrzeugverfolgung und -verwaltung
- Echtzeit-Tracking aller UGVs
- Verwaltung der Fahrzeugflotte (Hinzufügen, Entfernen, Aufgabensteuerung)

### 2. Routenplanung und Aufgabenzuweisung
- Routenplanung über UI, Mobile App oder Konfigurationsdatei
- Dynamische Aufgabenzuweisung basierend auf:
  - aktueller Position
  - Ladungskapazität
  - Batteriestand

### 3. Kommunikation und Koordination
- REST-basierte Kommunikation zwischen Backend und UGVs
- Zentrale Koordination zur Kollisionsvermeidung

### 4. Leistungsüberwachung und Diagnose
- Sammlung und Analyse von Betriebsdaten (z. B. Batteriestand, Fehlercodes)
- Funktionen zur Fehlerdiagnose und -behebung

### 5. Sicherheit
- Schutz vor unbefugtem Zugriff
- Robuste Authentifizierung und Autorisierung für Benutzer und Admins

### 6. Skalierbarkeit und Flexibilität
- Unterstützung wachsender UGV-Flotten und komplexer Abläufe
- Anpassbar an verschiedene UGV-Typen und Systemanforderungen

### 7. Datenmanagement und Analyse
- Zentrale Datenbank für Fahrzeug- und Betriebsdaten
- Analysefunktionen zur Optimierung des Flottenbetriebs

## Konzept und Datenmodellierung

### Systemkonzeption
- Modellierung des Gesamtsystems mit FMC (http://www.fmc-modeling.org)

### Anforderungsdifferenzierung
- Klare Trennung zwischen funktionalen und nicht-funktionalen Anforderungen

### Datenmodell
- Stufenweise Entwicklung eines Datenmodells, beginnend mit einfachen Anforderungen
