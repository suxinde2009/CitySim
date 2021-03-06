/*
 * Copyright (c) 2012 Felix Mo. All rights reserved.
 * 
 * CitySim is published under the terms of the MIT License. See the LICENSE file for more information.
 * 
 */

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Write a description of class Street here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Street extends Road
{
    public static final int TYPE_ID = 1;
    public static final int SIZE_WIDTH = 1;
    public static final int SIZE_HEIGHT = 1;
    public static final String NAME = "Street";
    public static final int PRICE = 1;

    public static void buildStreet(Tile tile, int type) {

        Cash.subtract(PRICE);

        Tile up = null, down = null, left = null, right = null;

        Tile[] nearby = Data.tilesMatchingCriteriaTouchingTile(tile, "road = 1");
        for (Tile t : nearby) {
            if (t.position().x < tile.position().x) {
                // LEFT
                left = t;
            }
            else if (t.position().x > tile.position().x) {
                // RIGHT
                right = t;
            }
            else {
                if (t.position().y < tile.position().y) {
                    // UP
                    up = t;
                }
                else if (t.position().y > tile.position().y) {
                    // DOWN
                    down = t;
                }
            }
        }

        if (up != null && left != null) {

            // Straight (v)
            up.setType(up.type() == Tile.POWERLINE_ROAD_V ? Tile.POWERLINE_ROAD_V : Tile.STREET_V);

            // Straight (h)
            left.setType(left.type() == Tile.POWERLINE_ROAD_H ? Tile.POWERLINE_ROAD_H : Tile.STREET_H);

            // Bend
            tile.setType(Tile.STREET_B_TL);
        }
        else if (up != null && right != null) {

            // Straight (v)
            up.setType(up.type() == Tile.POWERLINE_ROAD_V ? Tile.POWERLINE_ROAD_V : Tile.STREET_V);

            // Straight (h)
            right.setType(right.type() == Tile.POWERLINE_ROAD_H ? Tile.POWERLINE_ROAD_H : Tile.STREET_H);

            // Bend
            tile.setType(Tile.STREET_B_TR);
        }
        else if (down != null && left != null) {

            // Straight (v)
            down.setType(down.type() == Tile.POWERLINE_ROAD_V ? Tile.POWERLINE_ROAD_V : Tile.STREET_V);

            // Straight (h)
            left.setType(left.type() == Tile.POWERLINE_ROAD_H ? Tile.POWERLINE_ROAD_H : Tile.STREET_H);

            // Bend
            tile.setType(Tile.STREET_B_BL);
        }
        else if (down != null && right != null) {

            // Straight (v)
            down.setType(down.type() == Tile.POWERLINE_ROAD_V ? Tile.POWERLINE_ROAD_V : Tile.STREET_V);

            // Straight (h)
            right.setType(right.type() == Tile.POWERLINE_ROAD_H ? Tile.POWERLINE_ROAD_H : Tile.STREET_H);

            // Bend
            tile.setType(Tile.STREET_B_BR);
        }
        else if (up != null || down != null) {

            if (up != null) {

                if ((Data.tilesMatchingCriteria("road = 1 AND x = " + (up.position().x-1) + " AND y = " + (up.position().y)).length == 1) && (Data.tilesMatchingCriteria("road = 1 AND x = " + (up.position().x+1) + " AND y = " + (up.position().y)).length == 1)) {
                    // Check for:
                    // 1. a road LEFT of the upper tile
                    // 2. a road RIGHT of the upper tile

                    if (Data.tilesMatchingCriteria("road = 1 AND x = " + (up.position().x) + " AND y = " + (up.position().y-1)).length == 1) {
                        // Check for:
                        // 1. a road ON TOP of the upper tile

                        // 4-way
                        up.setType(Tile.STREET_INTERSECTION);
                    }
                    else {

                        // 3-way (down)
                        up.setType(Tile.STREET_H_D);
                    }
                }
                else if (Data.tilesMatchingCriteria("road = 1 AND y = " + (up.position().y) + " AND x = " + (up.position().x-1)).length == 1) {
                    // Check for a road to the LEFT of the upper tile

                    // Check for a road on TOP of the upper tile
                    if (Data.tilesMatchingCriteria("road = 1 AND y = " + (up.position().y-1) + " AND x = " + (up.position().x)).length == 0) {
                        // Bend
                        up.setType(Tile.STREET_B_BL);
                    }
                    else {
                        // 3 way (left)
                        up.setType(Tile.STREET_V_L);
                    }
                }
                else if (Data.tilesMatchingCriteria("road = 1 AND y = " + (up.position().y) + " AND x = " + (up.position().x+1)).length == 1) {

                    // Check for a road to the RIGHT of the upper tile

                    // Check for a road on TOP of the upper tile
                    if (Data.tilesMatchingCriteria("road = 1 AND y = " + (up.position().y-1) + " AND x = " + (up.position().x)).length == 0) {
                        // Bend
                        up.setType(Tile.STREET_B_BR);
                    }
                    else {
                        // 3 way (right)
                        up.setType(Tile.STREET_V_R);
                    }
                }
                else {

                    // Straight (v)
                    if (up.powerGrid() == 0) {
                        if (up.type() == Tile.BRIDGE_V) {
                            up.setType(Tile.BRIDGE_V);   
                        }
                        else {
                            up.setType(Tile.STREET_V);
                        }
                    }
                }
            }

            if (down != null) {               

                if ((Data.tilesMatchingCriteria("road = 1 AND x = " + (down.position().x-1) + " AND y = " + (down.position().y)).length == 1) && (Data.tilesMatchingCriteria("road = 1 AND x = " + (down.position().x+1) + " AND y = " + (down.position().y)).length == 1)) {
                    // Check for:
                    // 1. a road LEFT of the lower tile
                    // 2. a road RIGHT of the lower tile

                    if (Data.tilesMatchingCriteria("road = 1 AND x = " + (down.position().x) + " AND y = " + (down.position().y+1)).length == 1) {
                        // Check for:
                        // 1. a road BELOW the lower tile

                        // 4-way
                        down.setType(Tile.STREET_INTERSECTION);
                    }
                    else {

                        // 3-way (up)
                        down.setType(Tile.STREET_H_U);
                    }
                }
                else if (Data.tilesMatchingCriteria("road = 1 AND y = " + (down.position().y) + " AND x = " + (down.position().x-1)).length == 1) {
                    // Check for a road to the LEFT of the lower tile

                    // Check for a road BELOW the lower tile
                    if (Data.tilesMatchingCriteria("road = 1 AND y = " + (down.position().y-1) + " AND x = " + (down.position().x)).length == 0) {
                        // Bend
                        down.setType(Tile.STREET_B_TL);
                    }
                    else {
                        // 3 way (left)
                        down.setType(Tile.STREET_V_L);
                    }
                }
                else if (Data.tilesMatchingCriteria("road = 1 AND y = " + (down.position().y) + " AND x = " + (down.position().x+1)).length == 1) {

                    // Check for a road to the RIGHT of the lower tile

                    // Check for a road BELOW the lower tile
                    if (Data.tilesMatchingCriteria("road = 1 AND y = " + (down.position().y-1) + " AND x = " + (down.position().x)).length == 0) {
                        // Bend
                        down.setType(Tile.STREET_B_TR);
                    }
                    else {
                        // 3 way (right)
                        down.setType(Tile.STREET_V_R);
                    }
                }
                else {

                    if (down.powerGrid() == 0) {
                        if (down.type() == Tile.BRIDGE_V) {
                            down.setType(Tile.BRIDGE_V);   
                        }
                        else {
                            down.setType(Tile.STREET_V);
                        }
                    }
                }
            }

            if (tile.type() == Tile.POWERLINE_H || tile.type() == Tile.POWERLINE_ROAD_V) {
                tile.setType(Tile.POWERLINE_ROAD_V);
            }
            else if (tile.type() == Tile.WATER) {
                tile.setType(Tile.BRIDGE_V);
            }
            else {
                // Straight (v)
                tile.setType(Tile.STREET_V);
            }
        }
        else if (left != null || right != null) {

            if (left != null) {
                if ((Data.tilesMatchingCriteria("road = 1 AND x = " + (left.position().x) + " AND y = " + (left.position().y+1)).length == 1) && (Data.tilesMatchingCriteria("road = 1 AND x = " + (left.position().x) + " AND y = " + (left.position().y-1)).length == 1)) {
                    // Check for a road on top and below the left tile

                    if (Data.tilesMatchingCriteria("road = 1 AND x = " + (left.position().x-1) + " AND y = " + (left.position().y)).length == 1) {
                        // Check for a road to the left of the left tile

                        // 4-way
                        left.setType(Tile.STREET_INTERSECTION);
                    }
                    else {
                        // 3-way (right)
                        left.setType(Tile.STREET_V_R);
                    }
                }
                else if ((Data.tilesMatchingCriteria("road = 1 AND x = " + (left.position().x) + " AND y = " + (left.position().y-1)).length == 1) && (Data.tilesMatchingCriteria("road = 1 AND x = " + (left.position().x-1) + " AND y = " + (left.position().y)).length == 1)) {
                    // Check for a road on top of, and left of, the left tile

                    // 3-way (up)
                    left.setType(Tile.STREET_H_U);
                }
                else if ((Data.tilesMatchingCriteria("road = 1 AND x = " + (left.position().x) + " AND y = " + (left.position().y+1)).length == 1) && (Data.tilesMatchingCriteria("road = 1 AND x = " + (left.position().x-1) + " AND y = " + (left.position().y)).length == 1)) {
                    // Check for a road below, and left of, the left tile

                    // 3-way (down)
                    left.setType(Tile.STREET_H_D);
                }
                else if ((Data.tilesMatchingCriteria("road = 1 AND x = " + (left.position().x) + " AND y = " + (left.position().y-1)).length == 1) && (Data.tilesMatchingCriteria("road = 1 AND x = " + (left.position().x) + " AND y = " + (left.position().y+1)).length == 0)) {
                    // Check for a road on top of, and below, the left tile

                    // Bend
                    left.setType(Tile.STREET_B_TR);
                }
                else if ((Data.tilesMatchingCriteria("road = 1 AND y = " + (left.position().y-1) + " AND x = " + left.position().x).length == 0) && (Data.tilesMatchingCriteria("road = 1 AND y = " + (left.position().y+1) + " AND x = " + left.position().x).length == 1)) {
                    // Check for a road on top of, and below, the left tile

                    // Bend
                    left.setType(Tile.STREET_B_BR);
                }
                else {

                    if (left.powerGrid() == 0) {
                        if (left.type() == Tile.BRIDGE_H) {
                            left.setType(Tile.BRIDGE_H);   
                        }
                        else {
                            left.setType(Tile.STREET_H);
                        }
                    }
                }
            }

            if (right != null) {

                if ((Data.tilesMatchingCriteria("road = 1 AND x = " + (right.position().x) + " AND y = " + (right.position().y+1)).length == 1) && (Data.tilesMatchingCriteria("road = 1 AND x = " + (right.position().x) + " AND y = " + (right.position().y-1)).length == 1)) {
                    // Check for a road on top and below the right tile

                    if (Data.tilesMatchingCriteria("road = 1 AND x = " + (right.position().x+1) + " AND y = " + (right.position().y)).length == 1) {
                        // Check for a road to the right of the right tile

                        // 4-way
                        right.setType(Tile.STREET_INTERSECTION);
                    }
                    else {
                        // 3-way (left)
                        right.setType(Tile.STREET_V_L);
                    }
                }
                else if ((Data.tilesMatchingCriteria("road = 1 AND x = " + (right.position().x) + " AND y = " + (right.position().y-1)).length == 1) && (Data.tilesMatchingCriteria("road = 1 AND x = " + (right.position().x+1) + " AND y = " + (right.position().y)).length == 1)) {
                    // Check for a road on top of the right tile

                    // 3-way (up)
                    right.setType(Tile.STREET_H_U);
                }
                else if ((Data.tilesMatchingCriteria("road = 1 AND x = " + (right.position().x) + " AND y = " + (right.position().y+1)).length == 1) && (Data.tilesMatchingCriteria("road = 1 AND x = " + (right.position().x+1) + " AND y = " + (right.position().y)).length == 1)) {
                    // Check for a road below right tile

                    // 3-way (down)
                    right.setType(Tile.STREET_H_D);
                }
                else if ((Data.tilesMatchingCriteria("road = 1 AND x = " + (right.position().x) + " AND y = " + (right.position().y-1)).length == 1) && (Data.tilesMatchingCriteria("road = 1 AND x = " + (right.position().x) + " AND y = " + (right.position().y+1)).length == 0)) {
                    // Check for a road on top of, and below, the right tile

                    // Bend
                    right.setType(Tile.STREET_B_TL);
                }
                else if ((Data.tilesMatchingCriteria("road = 1 AND y = " + (right.position().y-1) + " AND x = " + right.position().x).length == 0) && (Data.tilesMatchingCriteria("road = 1 AND y = " + (right.position().y+1) + " AND x = " + right.position().x).length == 1)) {
                    // Check for a road on top of, and below, the right tile

                    // Bend
                    right.setType(Tile.STREET_B_BL);
                }
                else {

                    if (right.powerGrid() == 0) {
                        if (right.type() == Tile.BRIDGE_H) {
                            right.setType(Tile.BRIDGE_H);   
                        }
                        else {
                            right.setType(Tile.STREET_H);
                        }
                    }
                }
            }

            if (tile.type() == Tile.POWERLINE_V || tile.type() == Tile.POWERLINE_ROAD_H) {
                tile.setType(Tile.POWERLINE_ROAD_H);
            }
            else if (tile.type() == Tile.WATER) {
                tile.setType(Tile.BRIDGE_H);
            }
            else {
                // Straight (h)
                tile.setType(Tile.STREET_H);
            }
        }
        else {

            if (tile.type() == Tile.POWERLINE_V || tile.type() == Tile.POWERLINE_ROAD_H) {
                tile.setType(Tile.POWERLINE_ROAD_H);
            }
            else if (tile.type() == Tile.WATER) {
                tile.setType(Tile.BRIDGE_H);
            }
            else {
                // Straight (h)
                tile.setType(Tile.STREET_H);
            }
        }

        if (left != null) {
            left.setRoad(TYPE_ID);
            Data.updateTile(left);
        }
        if (right != null) {
            right.setRoad(TYPE_ID);
            Data.updateTile(right);
        }
        if (down != null) {
            down.setRoad(TYPE_ID);
            Data.updateTile(down);
        }
        if (up != null) {
            up.setRoad(TYPE_ID);
            Data.updateTile(up);
        }

        tile.setRoad(TYPE_ID);
        addToCount(1);

        Road.updateTile(tile);
    }

    public static int count() {
        return ((Integer)Data.roadStats().get(Data.ROADSTATS_STREETCOUNT)).intValue();
    }

    public static void addToCount(int more) {
        HashMap roadStats = Data.roadStats();
        roadStats.put(Data.ROADSTATS_STREETCOUNT, new Integer(count()+more));
        Data.updateRoadStats(roadStats);
    }

    public static void subtractFromCount(int less) {
        HashMap roadStats = Data.roadStats();
        roadStats.put(Data.ROADSTATS_STREETCOUNT, new Integer(count()-less));
        Data.updateRoadStats(roadStats);
    }
}