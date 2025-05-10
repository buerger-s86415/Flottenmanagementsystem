# Flottenmanagementsystem für UGVs (Aufgabenstellung siehe unten)
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
