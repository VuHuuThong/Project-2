package com.javaweb.api;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.customexception.FieldRequiredException;
import com.javaweb.model.BuildingDTO;
import com.javaweb.model.ErrorResponse;
import com.javaweb.service.BuildingService;


@RestController
public class BuildingAPI {
//	@RequestMapping(value="/api/building/", method=RequestMethod.GET)
//	public void getBuilding1(@RequestParam(value="name", required=false) String nameBuilding,
//			                 @RequestParam(value="numberOfBasement", required=false) Integer numberOfBasement,
//			                 @RequestParam(value="ward" , required=false) String ward) {
//		System.out.print(nameBuilding+ " " + numberOfBasement + " " + ward);
//
//	}
//	
//	@GetMapping(value="/api/building/")
//	public void getbuilding2(@RequestParam Map<String, String> param) {
//		System.out.print(param);
//	}
//	
//	@GetMapping("/api/building/")
//	public void getbuilding3(@RequestBody Map<String, String> param) {
//		System.out.print(param);
//	}
//	
//	@PostMapping(value="/api/building/")
//	public void getBuilding4(@RequestParam Map<String,String> parmas) {
//		System.out.print(parmas);
//	}
	
//	@PostMapping(value="/api/building/")
//	public void getBuilding5(@RequestBody Map<String,String> parmas) {
//		System.out.print(parmas);
//	}
//	
//	@GetMapping(value="/api/building")
//	public void getBuilding6(@RequestBody BuildingDTO buildingDTO) {
//		System.out.print("ok");
//	}
	
//	@PostMapping(value="/api/building/")
//	public void getBuilding7(@RequestBody BuildingDTO building) {
//		System.out.print("ok");
//	}
	
	//@DeleteMapping(value="/api/building/{id}/{name}")
//  public void deleteBuilding(@PathVariable Integer id,
//		                     @PathVariable String name) {
//	  System.out.print("Đã xóa tòa nhà có id"+ id + "roi");
//  }
	
//	@GetMapping(value="/api/building/")
//	public BuildingDTO getBuilding8(@RequestParam(value="name" , required=false) String nameBuilding,
//			                        @RequestParam(value="numberOfBasement" , required=false) Integer numberOfBasement,
//			                        @RequestParam(value="ward " , required=false) String ward, 
//			                        @RequestParam(value="street" , required=false) String street) {
//		//xu ly xong db roi
//		BuildingDTO buildingDTO = new BuildingDTO();
//		buildingDTO.setName(nameBuilding);
//		buildingDTO.setNumberOfBasement(numberOfBasement);
//		buildingDTO.setWard(ward);
//		buildingDTO.setStreet(street);
//		
//		
//		return buildingDTO;
//	}
	
//	@GetMapping(value="/api/building/")
//	public Object getBuilding9(@RequestParam(value="name" , required=false) String nameBuilding,
//			                        @RequestParam(value="numberOfBasement" , required=false) Integer numberOfBasement,
//			                        @RequestParam(value="ward " , required=false) String ward, 
//			                        @RequestParam(value="street" , required=false) String street) {
//		//xu ly xong db roi
//		BuildingDTO buildingDTO = new BuildingDTO();
//		buildingDTO.setName(nameBuilding);
//		buildingDTO.setNumberOfBasement(numberOfBasement);
//		buildingDTO.setWard(ward);
//		buildingDTO.setStreet(street);
//		
//		
//		return buildingDTO;
//	}
	
	// cái này tương tự reponseBody
//	@GetMapping(value="/api/building/")
//	public List<BuildingDTO> getBuilding10(@RequestParam(value="name" , required=false) String nameBuilding,
//			                        @RequestParam(value="numberOfBasement" , required=false) Integer numberOfBasement,
//			                        @RequestParam(value="ward " , required=false) String ward, 
//			                        @RequestParam(value="street" , required=false) String street){
//		List<BuildingDTO> list = new ArrayList<>();
//		BuildingDTO building =new BuildingDTO();
//		building.setName("MB Bank Building");
//		building.setNumberOfBasement(33);
//		building.setStreet("Vo Nguyen Giap");
//		building.setWard("Thanh Xuan");
//		
//		BuildingDTO building2 =new BuildingDTO();
//		building.setName(" TechComBank Building");
//		building.setNumberOfBasement(13);
//		building.setStreet("Vo Thi Sau");
//		building.setWard("Thanh Xuan");
//		
//		list.add(building);
//		list.add(building2);
//		
//		
//		
//		return list;
//	}
	
//	@PostMapping(value="/api/building/")
//	public List<BuildingDTO> getBuilding11(@RequestParam(value="name" , required=false) String nameBuilding,
//			                        @RequestParam(value="numberOfBasement" , required=false) Integer numberOfBasement,
//			                        @RequestParam(value="ward " , required=false) String ward, 
//			                        @RequestParam(value="street" , required=false) String street){
//		List<BuildingDTO> list = new ArrayList<>();
//		BuildingDTO building =new BuildingDTO();
//		building.setName("MB Bank Building");
//		building.setNumberOfBasement(33);
//		building.setStreet("Vo Nguyen Giap");
//		building.setWard("Thanh Xuan");
//		
//		BuildingDTO building2 =new BuildingDTO();
//		building.setName(" TechComBank Building");
//		building.setNumberOfBasement(13);
//		building.setStreet("Vo Thi Sau");
//		building.setWard("Thanh Xuan");
//		
//		list.add(building);
//		list.add(building2);
//		
//		
//		
//		return list;
//	}
	
//	@GetMapping("/api/building/")
//	public Object getBuilding12(@RequestParam(value="name" , required=false) String nameBuilding,
//			                        @RequestParam(value="numberOfBasement" , required=false) Integer numberOfBasement,
//			                        @RequestParam(value="ward " , required=false) String ward, 
//			                        @RequestParam(value="street" , required=false) String street) {
//		// xu li //....
//		try {
//			System.out.print(5/0);
//		}catch(Exception e) {
//			ErrorResponse err =new ErrorResponse();
//			err.setError(e.getMessage());
//			List<String> details =new ArrayList<>();
//			details.add("Khong chia duoc cho so 0");
//			err.setDetial(details);
//			return err;			
//		}
//		
//		List<BuildingDTO> list = new ArrayList<>();
//		BuildingDTO building =new BuildingDTO();
//		building.setName("MB Bank Building");
//		building.setNumberOfBasement(33);
//		building.setStreet("Vo Nguyen Giap");
//		building.setWard("Thanh Xuan");
//		
//		BuildingDTO building2 =new BuildingDTO();
//		building.setName(" TechComBank Building");
//		building.setNumberOfBasement(13);
//		building.setStreet("Vo Thi Sau");
//		building.setWard("Thanh Xuan");
//		
//		list.add(building);
//		list.add(building2);
//		
//		
//		
//		return list;		
//	}
//	
	
	
//	@PostMapping("/api/building/")
//	public Object getBuilding13(@RequestBody BuildingDTO building) {
//		
//			validate(building);
//		
//	
//		
//		return null;
//	}
//	
//	public void validate(BuildingDTO building) {
//		if(building.getName()==null || building.getName().equals("")|| building.getNumberOfBasement()==null) {
//			throw new FieldRequiredException("Name or NumberOfbasement is null");
//		}
//	}
	
	
	
	
	//MVC 
	
//	
//	static final String DB_URL ="jdbc:mysql://localhost:3307/estatebasic";
//	static final String USER="root";
//	static final String PASS="thong123";
//	
//	@GetMapping(value="/api/building/")
//	public List<BuildingDTO>  getBuilding(@RequestParam(name="name") String name) {
//		
//		String sql="SELECT * FROM building WHERE name like '% " + name + "%' ";
//		List<BuildingDTO> result= new ArrayList<>();
//		try(Connection conn = DriverManager.getConnection(DB_URL, USER ,PASS);
//				Statement stmt = conn.createStatement();
//				ResultSet rs = stmt.executeQuery(sql);
//				){
//			
//			while(rs.next()) {
//				BuildingDTO building=new BuildingDTO();
//				building.setName(rs.getString("name"));
//				building.setStreet(rs.getString("street"));
//				building.setWard(rs.getString("ward"));
//				building.setNumberOfBasement(rs.getInt("numberofbasement"));
//				result.add(building);
//			
//			}
//			
//		}catch(SQLException e) {
//			e.printStackTrace();
//			System.out.print("Connected database failed...");
//		}
//		return result;
//	}
	
	@Autowired
	private BuildingService buildingService;
	
	@GetMapping(value="/api/building")
	public List<BuildingDTO> getBuilding(@RequestParam(name="name" , required=false) String name,
			                                                   @RequestParam(name="districtid" , required=false) Long districtid,
			                                                   @RequestParam(name="typeCode" , required=false) List<String>  typeCode){
		List<BuildingDTO> result = buildingService.findAll(name, districtid);
		return result;
	}
	
	

}
