package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.DeliveryBox;
import com.example.demo.domain.Ingredient;

@Repository
public class JdbcDeliveryBoxRepository implements DeliveryBoxRepository {

	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcDeliveryBoxRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public Iterable<DeliveryBox> findAll() {
		// TODO Auto-generated method stub
		return jdbc.query("select id, color, size from Delivery_Box", this::mapRowToDeliveryBox);
	}

	@Override
	public DeliveryBox findById(String id) {
		// TODO Auto-generated method stub
		return jdbc.queryForObject(
				"select id, color, size from Delivery_Box where id=?", this::mapRowToDeliveryBox, id);
	}

	private DeliveryBox mapRowToDeliveryBox(ResultSet rs, int rowNum) throws SQLException {
		return new DeliveryBox(
				rs.getString("id"), 
				rs.getString("color"), 
				DeliveryBox.Type.valueOf(rs.getString("size")));
	}
	
}
