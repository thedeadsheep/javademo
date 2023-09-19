package com.example.demomoingay.helloWorld;

import com.example.demomoingay.dbConnector.mySQLConnection;
import com.example.demomoingay.models.Actor;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;

@RestController
public class helloworld {
    @GetMapping("/hello-world")
    public String sayHello(){
        return "Halu-Wow";
    }
    @GetMapping("/getAllActor")
    public String getAllActor(){

        ArrayList<Actor> actorList = new ArrayList<>();
        mySQLConnection sqlConnect = new mySQLConnection();
        Connection conn = sqlConnect.connect();

        String query = "select actor_id, first_name, last_name from actor";
        Statement stm = null ;
        try{
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()){
                Actor a = new Actor(rs.getString("actor_id"),rs.getString("first_name"), rs.getString("last_name"));
                actorList.add(a);
            }
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
        String json = new Gson().toJson(actorList);
        return json;
    }
    @GetMapping("/getActorById")
    public String getActorById(@RequestParam("id") String actorId){

        ArrayList<Actor> actorList = new ArrayList<>();
        mySQLConnection sqlConnection = new mySQLConnection();
        Connection conn = sqlConnection.connect();

        String query = "select actor_id, first_name, last_name from actor where actor_id=" +actorId;
        Statement stm = null;
        try{
            stm = conn.createStatement();
            ResultSet rs = stm.executeQuery(query);
            while (rs.next()){
                Actor a = new Actor(rs.getString("actor_id"),rs.getString("first_name"), rs.getString("last_name"));
                actorList.add(a);
            }
            conn.close();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        String json = new Gson().toJson(actorList);
        return json;
    }
    @PutMapping("/updateActorById")
    public String updateActorById(@RequestParam("id") String actorId, @RequestBody Actor actor) throws SQLException {
        String response = actorId + " " + actor.toString();
        mySQLConnection sqlConnection = new mySQLConnection();
        Connection conn = sqlConnection.connect();

        String query = "update actor set first_name = ?, last_name = ? where actor_id = ? ";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1,actor.getFirst_name());
        preparedStatement.setString(2, actor.getLast_name());
        preparedStatement.setString(3, actorId);
        try{
            Integer r = preparedStatement.executeUpdate();
            response = r.toString();

        }catch (SQLException e){
            e.printStackTrace();

            response = "Có cái gì đó lỗi rồi Homie";
        }
        conn.close();
        return response;
    }
    @PostMapping("/addNewActor")
    public String addNewActor(@RequestBody Actor actor) throws SQLException{
        String response ;
        mySQLConnection sqlConnection = new mySQLConnection();
        Connection conn = sqlConnection.connect();
        String query = "insert into actor (actor_id, first_name, last_name) values (?, ?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        preparedStatement.setString(1, actor.getActor_id());
        preparedStatement.setString(2, actor.getFirst_name());
        preparedStatement.setString(3, actor.getLast_name());
        try{
            Integer r= preparedStatement.executeUpdate();
            response = r.toString();
        }catch (SQLException e){
            e.printStackTrace();
            response = "Có cái gì đó lỗi rồi Homie";
        }
        conn.close();
        return response;
    }
    @DeleteMapping("/deleteActorById")
    public String deleteActorById(@RequestParam("id") String actorId) throws SQLException {
        String response = "Cái gì đó lỗi rồi Homie";
        mySQLConnection sqlConnection=new mySQLConnection();
        Connection conn = sqlConnection.connect();
        String query = "delete from actor where actor_id = "+ actorId;
        Statement stm = null;
        try{
            stm = conn.createStatement();
            Integer r = stm.executeUpdate(query);
            response = r.toString();
        }catch(SQLException e){
            e.printStackTrace();

        }
        if (conn != null){
            conn.close();
        }
        return response;
    }
}
