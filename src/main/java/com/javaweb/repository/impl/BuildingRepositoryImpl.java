package com.javaweb.repository.impl;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.utils.ConnectionJDBCUtil;
import com.javaweb.utils.NumberUtil;
import com.javaweb.utils.StringUtil;
@Repository

public class BuildingRepositoryImpl implements BuildingRepository {
	@PersistenceContext
    private EntityManager entityManager;
	
	public static void joinTable(BuildingSearchBuilder buildingSearchBuilder,StringBuilder sql) {
		
		Integer staffId =buildingSearchBuilder.getStaffId();
		if(staffId!=null) {
			sql.append("INNER JOIN assignmentbuilding ab ON b.id = ab.buildingid ");
		}
		List<String> typeCode = buildingSearchBuilder.getTypeCode();
		if(typeCode !=null && typeCode.size()!=0) {
			sql.append("INNER JOIN buildingrenttype ON b.id = buildingrenttype.buildingid ");
			sql.append("INNER JOIN renttype ON renttype.id = buildingrenttype.renttypeid ");
		}
//		String rentAreaTo=(String)params.get("areaTo");
//		String rentAreaFrom=(String)params.get("areaFrom");
//		if(StringUtil.checkString(rentAreaFrom)==true || StringUtil.checkString(rentAreaTo)==true) {
//			sql.append("INNER JOIN rentarea ON rentarea.buildingid = b.id ");
//		}	
	}
    public static void queryNormal(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
//        for(Map.Entry<String, Object> it : params.entrySet()) {
//        	if(!it.getKey().equals("staffId") && !it.getKey().equals("typeCode") && !it.getKey().startsWith("area") &&!it.getKey().startsWith("rentPrice")) {
//        		String value = it.getValue().toString();
//        		if(StringUtil.checkString(value)) {
//        			if(NumberUtil.isNumber(value)==true) {
//        				where.append(" AND b." + it.getKey() + " = " +value);
//        			}
//        			else {
//        				where.append(" AND b. " + it.getKey() + " LIKE '%" + value + "%' " );
//        			}
//        		}
//        	}
//        }
    	try {
    		Field[] fields = BuildingSearchBuilder.class.getDeclaredFields();
    		for(Field item: fields) {
    			item.setAccessible(true);// cấp phát quyền truy cập của item vào các fields
    			String fieldName = item.getName();
    			if(!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area") &&!fieldName.startsWith("rentPrice")) {
    				Object value = item.get(buildingSearchBuilder);
    				if(value!=null) {
            			if(item.getType().getName().equals("java.lang.Integer")|| item.getType().getName().equals("java.lang.Long")) {
            				where.append(" AND b." + fieldName + " = " +value);
            			}
            			else if(item.getType().getName().equals("java.lang.String")) {
            				where.append(" AND b. " + fieldName + " LIKE '%" + value + "%' " );
            			}
            		}
    			}
    		}
    	}catch(Exception ex){
    		ex.printStackTrace();
    	}
	}
    public static void querySpecial(BuildingSearchBuilder buildingSearchBuilder, StringBuilder where) {
    	Integer staffId =buildingSearchBuilder.getStaffId();
    	if(staffId!=null) {
    		where.append(" AND ab.staffid = " + staffId);
    	}
    	Integer rentAreaTo=buildingSearchBuilder.getAreaTo();
		Integer rentAreaFrom =buildingSearchBuilder.getAreaFrom();
		if(rentAreaFrom != null || rentAreaTo != null) {
			where.append(" AND EXITS (SELECT * FROM rentarea r WHERE b.id = r.buildingid ");
			if(rentAreaFrom != null) {
				where.append(" AND r.value >= " +rentAreaFrom);
			}
			if(rentAreaTo != null) {
				where.append(" AND r.value <= " +rentAreaTo); 
			}
			where.append(") ");
			
		}
		Integer rentPriceTo=buildingSearchBuilder.getRentPriceTo();
		Integer rentPriceFrom =buildingSearchBuilder.getRentPriceFrom();
		if(rentPriceTo != null || rentPriceFrom != null) {
			if(rentPriceFrom != null) {
				where.append(" AND b.rentprice >= " +rentPriceFrom);
			}
			if(rentPriceTo != null) {
				where.append(" AND  b.rentprice <= " +rentPriceTo); 
			}
		}
//		//java7
//		if(typeCode!=null&& typeCode.size()!=0) {
//			List<String> code = new ArrayList<>();
//			for(String item : typeCode ) {
//				code.add("'"+item + "'");
//			}
//			where.append(" AND renttype.code IN(" + String.join(",", code) + ")");
//		}
//		//java8
		List<String> typeCode = buildingSearchBuilder.getTypeCode();
		if(typeCode !=null&& typeCode.size()!=0) {
			where.append(" AND(");
			String sql = typeCode.stream().map(it -> "renttype.code Like" + "'%" + it + "%'").collect(Collectors.joining("OR"));
			where.append(sql);
			where.append(" ) ");
		}
    }
	@Override
	public List<BuildingEntity> findAll(BuildingSearchBuilder buildingSearchBuilder ) {

		StringBuilder sql=new StringBuilder("SELECT b.* FROM building b  ");
		joinTable(buildingSearchBuilder, sql);
		StringBuilder where = new StringBuilder("WHERE 1=1 ");
		queryNormal(buildingSearchBuilder, where);
		querySpecial(buildingSearchBuilder, where);
		where.append(" GROUP BY b.id ");
		sql.append(where);
		Query query= entityManager.createNativeQuery(sql.toString(),BuildingEntity.class);
		return query.getResultList();
	}
	
	
	
	


}
