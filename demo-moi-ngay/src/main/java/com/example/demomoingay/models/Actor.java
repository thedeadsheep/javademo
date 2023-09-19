package com.example.demomoingay.models;

public class Actor {

    private String actor_id;
    private String first_name;
    private  String last_name;


    public Actor(){
        super();
    }
    public Actor(String actor_id, String first_name, String last_name){
        this.actor_id= actor_id;
        this.first_name = first_name;
        this.last_name = last_name;
    }
    public  Actor (String first_name, String last_name){
        this.first_name = first_name;
        this.last_name = last_name;
    }
    public String getActor_id(){return actor_id;}
    public void setActor_id(String actor_id){this.actor_id = actor_id;}
    public String getFirst_name(){return first_name;}
    public void setFirst_name(String first_name){this.first_name = first_name;}
    public  String getLast_name(){return last_name;}
    public  void setLast_name(String last_name){this.last_name=last_name;}

    @Override
    public String toString(){
        return this.first_name +" "+ this.last_name;
    }
}
