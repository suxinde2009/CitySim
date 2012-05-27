/*
 * Copyright (c) 2012 Felix Mo. All rights reserved.
 * 
 * CitySim is published under the terms of the MIT License. See the LICENSE file for more information.
 * 
 */

/**
 * Write a description of class DrawMinimapThread here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinimapDrawThread extends CSThread
{

    private static int count = 0;
    
    public MinimapDrawThread() {
        super("MinimapDrawThread#" + (count+=1));
    }

    public void run() {
        Minimap.getInstance().draw();
    }
}
