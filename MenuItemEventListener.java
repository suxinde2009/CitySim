/*
 * Copyright (c) 2012 Felix Mo. All rights reserved.
 * 
 * CitySim is published under the terms of the MIT License. See the LICENSE file for more information.
 * 
 */

import com.google.common.eventbus.Subscribe;

/**
 * MenuItemEventListener
 * CitySim
 * v0.1
 * 
 * Created by Felix Mo on 05-01-2012
 * 
 * Listens for menu item events and acts accordingly
 * 
 */

public class MenuItemEventListener extends CSEventListener
{

    @Subscribe
    public void listen(MenuItemEvent event) {
        CSLogger.sharedLogger().trace("\"" + event.message() + "\" was selected.");

        // * ZONING *
        if (event.message().equals(ResidentialZone.NAME)) {
            Map.getInstance().selection().setSelectionMode(true);
            Map.getInstance().selection().setUnacceptedTypes( new int[]{ Tile.WATER } );
            Map.getInstance().selection().setUnacceptedZones( new int[] { ResidentialZone.TYPE_ID, CommercialZone.TYPE_ID, IndustrialZone.TYPE_ID });
            Map.getInstance().selection().setSize(ResidentialZone.SIZE_WIDTH, ResidentialZone.SIZE_HEIGHT);
            Zone.setPendingOp(ResidentialZone.TYPE_ID);
            City.getInstance().setHint(new Hint("Select the areas you wish to zone as residential. Press 'ESC' when done."));
        }
        else if (event.message().equals(IndustrialZone.NAME)) {
            Map.getInstance().selection().setSelectionMode(true);
            Map.getInstance().selection().setAcceptedTypes( new int[]{ Tile.GROUND } );
            Map.getInstance().selection().setUnacceptedZones( new int[] { ResidentialZone.TYPE_ID, CommercialZone.TYPE_ID, IndustrialZone.TYPE_ID });
            Map.getInstance().selection().setSize(IndustrialZone.SIZE_WIDTH, IndustrialZone.SIZE_HEIGHT);
            Zone.setPendingOp(IndustrialZone.TYPE_ID);
            City.getInstance().setHint(new Hint("Select the areas you wish to zone as industrial. Press 'ESC' when done."));
        }
        else if (event.message().equals(CommercialZone.NAME)) {
            Map.getInstance().selection().setSelectionMode(true);
            Map.getInstance().selection().setAcceptedTypes( new int[]{ Tile.GROUND } );
            Map.getInstance().selection().setUnacceptedZones( new int[] { ResidentialZone.TYPE_ID, CommercialZone.TYPE_ID, IndustrialZone.TYPE_ID });
            Map.getInstance().selection().setSize(CommercialZone.SIZE_WIDTH, CommercialZone.SIZE_HEIGHT);
            Zone.setPendingOp(CommercialZone.TYPE_ID);
            City.getInstance().setHint(new Hint("Select the areas you wish to zone as commercial. Press 'ESC' when done."));
        }
        // * TRANSPORTATION *
        else if (event.message().equals(Street.NAME)) {
            Map.getInstance().selection().setSelectionMode(true);
            Map.getInstance().selection().setAcceptedTypes( new int[]{ Tile.GROUND } );
            Map.getInstance().selection().setUnacceptedZones( new int[] { ResidentialZone.TYPE_ID, CommercialZone.TYPE_ID, IndustrialZone.TYPE_ID });
            Map.getInstance().selection().setSize(Street.SIZE_WIDTH, Street.SIZE_HEIGHT);    
            Road.setPendingOp(Street.TYPE_ID);
            City.getInstance().setTileSelector(new TileSelector(Street.MARKERS));
        }
        // * POWER *
        else if (event.message().equals(PowerLine.NAME)) {
            Map.getInstance().selection().setSelectionMode(true);
            Map.getInstance().selection().setAcceptedTypes( new int[]{ Tile.GROUND } );
            Map.getInstance().selection().setUnacceptedZones( new int[] { ResidentialZone.TYPE_ID, CommercialZone.TYPE_ID, IndustrialZone.TYPE_ID });
            Map.getInstance().selection().setSize(PowerLine.SIZE_WIDTH, PowerLine.SIZE_HEIGHT);    
            PowerGrid.setPendingOp(PowerLine.TYPE_ID);
            City.getInstance().setTileSelector(new TileSelector(PowerLine.MARKERS));
        }
        else if (event.message().equals(CoalPowerPlant.NAME)) {
            Map.getInstance().selection().setSelectionMode(true);
            Map.getInstance().selection().setAcceptedTypes( new int[]{ Tile.GROUND } );
            Map.getInstance().selection().setUnacceptedZones( new int[] { ResidentialZone.TYPE_ID, CommercialZone.TYPE_ID, IndustrialZone.TYPE_ID });
            Map.getInstance().selection().setSize(CoalPowerPlant.SIZE_WIDTH, CoalPowerPlant.SIZE_HEIGHT);
            PowerGrid.setPendingOp(CoalPowerPlant.TYPE_ID);
        }
        else if (event.message().equals(NuclearPowerPlant.NAME)) {
            Map.getInstance().selection().setSelectionMode(true);
            Map.getInstance().selection().setAcceptedTypes( new int[]{ Tile.GROUND } );
            Map.getInstance().selection().setUnacceptedZones( new int[] { ResidentialZone.TYPE_ID, CommercialZone.TYPE_ID, IndustrialZone.TYPE_ID });
            Map.getInstance().selection().setSize(NuclearPowerPlant.SIZE_WIDTH, NuclearPowerPlant.SIZE_HEIGHT);
            PowerGrid.setPendingOp(NuclearPowerPlant.TYPE_ID);
        }
        // * TOOLS *
        else if (event.message().equals(Bulldozer.NAME)) {
            Map.getInstance().selection().setSelectionMode(true);
            Map.getInstance().selection().setUnacceptedTypes( new int[]{ Tile.WATER } );
            Map.getInstance().selection().setSize(Bulldozer.SIZE_WIDTH, Bulldozer.SIZE_HEIGHT);
            Tool.setPendingOp(Bulldozer.TYPE_ID);
            City.getInstance().setHint(new Hint("Select the areas you wish to bulldoze. Press 'ESC' when done."));
        }
    }
}