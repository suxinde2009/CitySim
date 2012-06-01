/*
 * Copyright (c) 2012 Felix Mo. All rights reserved.
 * 
 * CitySim is published under the terms of the MIT License. See the LICENSE file for more information.
 * 
 */

import java.util.HashMap;
import java.util.ArrayList;

/**
 * Write a description of class FireStation here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FireStation extends ProtectionZone
{
    public static final int TYPE_ID = 6;
    public static final int[] MARKERS = { 800, 801, 802, 803, 804, 805, 806, 807, 808 };
    public static final int SIZE_WIDTH = 3;
    public static final int SIZE_HEIGHT = 3;
    public static final String NAME = "Fire station";

    public FireStation(HashMap properties) {
        super(properties);
    }

    public static void build(ArrayList<ArrayList<Tile>> selectedTiles) {
        
        int width = selectedTiles.size();
        int height = ((ArrayList)selectedTiles.get(0)).size();

        int k = 0;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Tile tile = selectedTiles.get(j).get(i);
                tile.setType(FireStation.MARKERS[k]);
                k++;
            }
        }

        CSLogger.sharedLogger().info("Did build fire station on " + (width*height) + " tiles.");

        Zone.updateTiles(selectedTiles);
    }
}