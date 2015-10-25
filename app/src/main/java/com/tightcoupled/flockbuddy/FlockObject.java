package com.tightcoupled.flockbuddy;

/**
 * Created by muditpandya on 25/10/15.
 */
public class FlockObject {

    String FlockName;
    int maxDistance, maxDuration;
    int numberOfSheep;
    String flockID;


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
