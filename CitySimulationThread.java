/*
 * Copyright (c) 2012 Felix Mo. All rights reserved.
 * 
 * CitySim is published under the terms of the MIT License. See the LICENSE file for more information.
 * 
 */

import java.util.HashMap;
import java.awt.Point;

/**
 * Write a description of class CitySimulationThread here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CitySimulationThread extends CSThread
{
    public CitySimulationThread() {
        super("CitySimulationThread");
    }

    public void run() {
        CSLogger.sharedLogger().fine("Began simulating the city...");
        long startTime = System.currentTimeMillis();

        CommercialZone[] cZones = Data.commercialZones();
        IndustrialZone[] iZones = Data.industrialZones();

        // Commercial zones
        for (CommercialZone zone : cZones) {
            new CommercialZoneSimulationThread(zone).start();
        }

        // Industrial zones
        for (IndustrialZone zone : iZones) {
            new IndustrialZoneSimulationThread(zone).start();
        }

        // Residential zones
        for (ResidentialZone zone : Data.residentialZones()) {
            new ResidentialZoneSimulationThread(zone).start();
        }

        // Do census to determine population
        int population = 0;
        for (ResidentialZone zone : Data.residentialZones()) {
            population += zone.allocation();
        }
        Population.set(population);

        Employment.simulate();
        
        new IssuesSimulationThread().start();
        
        CSLogger.sharedLogger().fine("Finished simulating the city (" + (System.currentTimeMillis() - startTime) + " ms)");
    }
}
