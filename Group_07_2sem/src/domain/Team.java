/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author MS
 */
public class Team {

    private int teamID;
    private int fitters;
    private int trucks;

    public Team(int teamID, int fitters, int trucks) {
        this.teamID = teamID;
        this.fitters = fitters;
        this.trucks = trucks;
    }

    public int getTeamID() {
        return teamID;
    }

    public int getFitters() {
        return fitters;
    }

    public int getTrucks() {
        return trucks;
    }

    @Override
    public String toString() {
        return "Team: "+teamID + " Fitters: "+fitters + "Trucks: " +trucks;
    }

}
