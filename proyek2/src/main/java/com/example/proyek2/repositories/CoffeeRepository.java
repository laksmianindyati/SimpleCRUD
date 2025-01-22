package com.example.proyek2.repositories;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.proyek2.models.Coffee;


@Repository
public class CoffeeRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;



    public List<Coffee> getAllData(){
        List<Coffee> coffees;

        
        String sql = "SELECT * FROM coffees";

        coffees = jdbcTemplate.query(sql , new RowMapper<Coffee>() {
            @Override
            public Coffee mapRow(ResultSet rs, int rowNum) throws SQLException {
                Coffee myObject = new Coffee(rs.getString("id"), rs.getString("nama"));
                return myObject;
            }
        });
        return coffees;
    }

    public Coffee getCoffeeById(String id){

        Coffee cf;
        String sql = "SELECT * FROM coffees  WHERE id = ?";
        cf = jdbcTemplate.queryForObject(sql,  new RowMapper<Coffee>() {
            @Override
            public Coffee mapRow(ResultSet rs, int rowNum) throws SQLException {
                Coffee myObject = new Coffee(rs.getString("id"), rs.getString("nama"));
                return myObject;
            }
        }, id);
        return cf;
    }

    public void create(Coffee myObject) {
        System.out.println("Test repo"+ myObject.getId() + myObject.getName());
        String sql = "INSERT INTO coffees (id, nama) VALUES (?, ?)";
        jdbcTemplate.update(sql, myObject.getId(), myObject.getName());
    }

    public void update(String id, String name){
        String sql = "UPDATE coffees SET nama = ? WHERE id = ?";
        jdbcTemplate.update(sql, name, id); 
    }

    
    public int delete(String id) {
        String sql = "DELETE FROM coffees WHERE id = ?";
        return jdbcTemplate.update(sql, id); 
    }




}
