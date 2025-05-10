# Flottenmanagementsystem für UGVs – Praktikum I267

**Kurs:** Programmierung von Komponentenarchitekturen (I267)  
**Betreuung:** Prof. Dr. Mario Neugebauer, Dipl.-Medieninf. Alexander Wülfing  

## Projektziel

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
