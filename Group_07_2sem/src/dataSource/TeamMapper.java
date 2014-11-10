/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataSource;

import domain.Team;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author MS
 */
public class TeamMapper {

    public Team getTeam(int teamID, Connection con) {

        Team t = null;

        String SQLString = "SELECT * "
                + "FROM TEAMS "
                + "WHERE teamID = ?";

        PreparedStatement statement = null;
        try {
            statement = con.prepareStatement(SQLString);
            statement.setInt(1, teamID);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                t = new Team(teamID, rs.getInt(2), rs.getInt(3));
            }

        } catch (SQLException e) {
            System.out.println("Error in TeamMapper - getTeam " + e);
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Error in TeamMapper - getTeam.Finally.Statement.close() " + e);
            }
        }

        return t;
    }

    public ArrayList<Team> getAllTeams(Connection con) {

        ArrayList<Team> arrayTeams = new ArrayList();

        String SQLString = "SELECT * "
                + "FROM Teams ";

        PreparedStatement statment = null;
        try {
            statment = con.prepareStatement(SQLString);

            ResultSet rs = statment.executeQuery();

            while (rs.next()) {
                arrayTeams.add(new Team(rs.getInt(1), rs.getInt(2), rs.getInt(3)));
            }

        } catch (SQLException e) {
            System.out.println("Error in TeamMapper - getAllTeams " + e);
        } finally {

            try {
                statment.close();
            } catch (SQLException e) {
                System.out.println("Error in TeamMapper - getAllOrders.finally " + e);
            }

        }
        return arrayTeams;
    }

}
