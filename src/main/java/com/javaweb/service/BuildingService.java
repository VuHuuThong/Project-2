package com.javaweb.service;

import java.util.List;
import java.util.Map;

import com.javaweb.model.BuildingDTO;
import com.javaweb.model.BuildingRequestDTO;



public interface BuildingService {
	 List<BuildingDTO> findAll(Map<String, Object> params, List<String> typeCode);
	 BuildingDTO findById(Integer id);
	 List<BuildingDTO> findByNameContaining(String name);
	 //void putData(BuildingRequestDTO buildingDTO);

}
