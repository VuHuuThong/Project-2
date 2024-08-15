package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.DistrictRepository;

import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.utils.ConnectionJDBCUtil;
@Repository
public class DistrictRepositoryImpl implements DistrictRepository {
	
	
	@Override
	public DistrictEntity findNameById(Integer id) {
		String sql= "SELECT d.name FROM district d WHERE d.id = "+ id + ";";
		DistrictEntity dis = new DistrictEntity();
		try(Connection conn = ConnectionJDBCUtil.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)
				){
			
			while(rs.next()) {
			
			dis.setName(rs.getString("name"));
			
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.print("Connected database failed...");
		}
		return dis;
	}

}
