package com.tightcoupled.flockbuddy;

import java.util.Date;

/**
 * Model of flock record in DB.
 * Created by muditpandya on 25/10/15.
 */
public class FlockObject {

    String flockID;
    String FlockName;
    String flockShepherdID;
    Date flockStartDate, flockEnd;
    int maxDistance, maxDuration;
    int numberOfSheep;



    public void setFlockName(String name)
    {
        FlockName = name;
    }

    public String getFlockName()
    {
        return FlockName;
    }


    public void setMaxDistance(int maxDist)
    {
        maxDistance = maxDist;
    }

    public int getMaxDistance()
    {
        return maxDistance;
    }

    public void setMaxDuration(int maxDur)
    {
        maxDuration = maxDur;
    }

    public int getMaxDuration()
    {
        return maxDuration;
    }

    public void addSheep()
    {
        numberOfSheep++;
    }

    public int getNumberOfSheep()
    {
        return numberOfSheep;
    }

    public void setFlockID(String flockID)
    {
        this.flockID = flockID;
    }

    public String getFlockID()
    {
        return flockID;
    }

}
