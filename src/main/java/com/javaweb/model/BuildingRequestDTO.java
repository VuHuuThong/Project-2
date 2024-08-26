package com.javaweb.model;

public class BuildingRequestDTO {
	private Integer id;
  private String name;
  private String street;
  private String ward;
   private Integer districtid;
   private Integer rentprice;
   
   
   
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

public String getStreet() {
	return street;
}
public void setStreet(String street) {
	this.street = street;
}
public String getWard() {
	return ward;
}
public void setWard(String ward) {
	this.ward = ward;
}
public Integer getDistrictid() {
	return districtid;
}
public void setDistrictid(Integer districtid) {
	this.districtid = districtid;
}
public Integer getRentprice() {
	return rentprice;
}
public void setRentprice(Integer rentprice) {
	this.rentprice = rentprice;
}
   
   
}
