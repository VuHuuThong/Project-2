package com.javaweb.repository.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionJDBCUtil;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;
@Repository
public class BuildingRepositoryImpl implements BuildingRepository {
	

	
	public static void joinTable(Map<String, Object> params, List<String> typeCode,StringBuilder sql) {
		
		String staffId =( String)params.get("staffId");
		if(StringUtil.checkString(staffId)) {
			sql.append("INNER JOIN assignmentbuilding ON b.id = assignmentbuilding.buildingid ");
		}
		if(typeCode !=null && typeCode.size()!=0) {
			sql.append("INNER JOIN buildingrenttype ON b.id = buildingrenttype.buildingid ");
			sql.append("INNER JOIN renttype ON renttype.id = buildingrenttype.renttypeid ");
		}
		String rentAreaTo=(String)params.get("areaTo");
		String rentAreaFrom=(String)params.get("areaFrom");
		if(StringUtil.checkString(rentAreaFrom)==true || StringUtil.checkString(rentAreaTo)==true) {
			sql.append("INNER JOIN rentarea ON rentarea.buildingid = b.id ");
		}	
	}
    public static void queryNormal(Map<String, Object> params, StringBuilder where) {
        for(Map.Entry<String, Object> it : params.entrySet()) {
        	if(!it.getKey().equals("staffId") && !it.getKey().equals("typeCode") && !it.getKey().startsWith("area") &&!it.getKey().startsWith("rentPrice")) {
        		String value = it.getValue().toString();
        		if(StringUtil.checkString(value)) {
        			if(NumberUtil.isNumber(value)==true) {
        				where.append(" AND b." + it.getKey() + " = " +value);
        			}
        			else {
        				where.append(" AND b. " + it.getKey() + " LIKE '%" + value + "%' " );
        			}
        		}
        	}
        }
	}
    public static void querySpecial(Map<String,Object> params, List<String> typeCode, StringBuilder where) {
    	String staffId = (String)params.get("staffId");
    	if(StringUtil.checkString(staffId)) {
    		where.append(" AND assignmentbuilding.staffid = " + staffId);
    	}
    	String rentAreaTo=(String)params.get("areaTo");
		String rentAreaFrom =(String)params.get("areaFrom");
		if(StringUtil.checkString(rentAreaFrom)==true || StringUtil.checkString(rentAreaTo)==true) {
			if(StringUtil.checkString(rentAreaFrom)) {
				where.append(" AND rentarea.value >= " +rentAreaFrom);
			}
			if(StringUtil.checkString(rentAreaTo)) {
				where.append(" AND rentarea.value <= " +rentAreaTo); 
			}
		}
		String rentPriceTo=(String)params.get("rentPriceTo");
		String rentPriceFrom =(String)params.get("rentPriceFrom");
		if(StringUtil.checkString(rentPriceFrom)==true || StringUtil.checkString(rentPriceTo)==true) {
			if(StringUtil.checkString(rentPriceFrom)) {
				where.append(" AND b.rentprice >= " +rentPriceFrom);
			}
			if(StringUtil.checkString(rentPriceTo)) {
				where.append(" AND  b.rentprice <= " +rentPriceTo); 
			}
		}
		if(typeCode!=null&& typeCode.size()!=0) {
			List<String> code = new ArrayList<>();
			for(String item : typeCode ) {
				code.add("'"+item + "'");
			}
			where.append(" AND renttype.code IN(" + String.join(",", code) + ")");
		}
    }
	@Override
	public List<BuildingEntity> findAll(Map<String, Object> params , List<String> typeCode ) {

		StringBuilder sql=new StringBuilder("SELECT b.id ,b.name, b.districtid, b.numberofbasement, b.street, b.ward, b.floorarea, b.rentprice, "+
		" b.managername, b.managerphonenumber , b.servicefee, b.brokeragefee FROM building b  ");
		joinTable(params, typeCode, sql);
		StringBuilder where = new StringBuilder("WHERE 1=1 ");
		queryNormal(params, where);
		querySpecial(params, typeCode, where);
		where.append(" GROUP BY b.id ");
		sql.append(where);
		List<BuildingEntity> result= new ArrayList<>();
		try(Connection conn = ConnectionJDBCUtil.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql.toString());
				){
			
			while(rs.next()) {
				BuildingEntity building=new BuildingEntity();
				building.setId(rs.getInt("b.id"));
				building.setName(rs.getString("b.name"));
				building.setStreet(rs.getString("b.street"));
				building.setWard(rs.getString("b.ward"));
				building.setDistrictid(rs.getInt("b.districtid"));
				building.setFloorarea(rs.getInt("b.floorarea"));
				building.setRentprice(rs.getInt("b.rentprice"));
				building.setServicefee(rs.getInt("b.servicefee"));
				building.setBrokeragefee(rs.getFloat("b.brokeragefee"));
				building.setManagername(rs.getString("b.managername"));
				building.setManagerphonenumber(rs.getString("b.managerphonenumber"));
				
				
				result.add(building);
			
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.print("Connected database failed...");
		}
		return result;
	}


}
