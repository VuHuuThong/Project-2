package com.javaweb.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaweb.builder.BuildingSearchBuilder;
import com.javaweb.converter.BuildingDTOConverter;
import com.javaweb.converter.BuildingSearchBuilderConverter;
import com.javaweb.model.BuildingDTO;
import com.javaweb.model.BuildingRequestDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.BuildingService;
@Service
public class BuildingServiceImpl implements BuildingService {
	
	@Autowired
      private BuildingRepository buildingRepository;
	@Autowired
	private BuildingDTOConverter buildingDTOConverter;
	@Autowired
	private BuildingSearchBuilderConverter buildingSearchConverter;
	
	@Override
	public List<BuildingDTO> findAll(Map<String,Object> params, List<String> typeCode) {
		
		// TODO Auto-generated method stub
		BuildingSearchBuilder buildingSearchBuilder =buildingSearchConverter.toBuildingSearchBuilder(params, typeCode);
		List<BuildingEntity> buildingEntities=buildingRepository.findAll(buildingSearchBuilder);
		List<BuildingDTO>  result = new ArrayList<BuildingDTO>();
		for(BuildingEntity item: buildingEntities) {
			BuildingDTO building = buildingDTOConverter.toBuildingDTO(item);
			
			result.add(building);
		}
		return result;
	}

	@Override
	public BuildingDTO findById(Integer id) {
		
		BuildingEntity buildingen = buildingRepository.findById(id).get();
		BuildingDTO result = buildingDTOConverter.toBuildingDTO(buildingen);
		return result;
	}

	@Override
	public List<BuildingDTO> findByNameContaining(String name) {
		List<BuildingDTO> result= new ArrayList<>();
		List<BuildingEntity> bui = buildingRepository.findByNameContaining(name);
		for(BuildingEntity items: bui) {
			BuildingDTO buildingDTO = buildingDTOConverter.toBuildingDTO(items);
			result.add(buildingDTO);
		}
		return result;
	}
//
//	@Override
//	public void putData(BuildingRequestDTO buildingDTO) {
//			 BuildingEntity builEntity = new BuildingEntity();
//		        builEntity.setId(5); 
//		        builEntity.setName(buildingDTO.getName());
//		        builEntity.setStreet(buildingDTO.getStreet());
//		        builEntity.setWard(buildingDTO.getWard());
//
//		        DistrictEntity dis = new DistrictEntity();
//		        dis.setId(buildingDTO.getDistrictid());
//		        builEntity.setDistrict(dis);
//
//	    buildingRepository.save(builEntity);
//		}
	}
	

