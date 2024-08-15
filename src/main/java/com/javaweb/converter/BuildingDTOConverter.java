package com.javaweb.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.javaweb.model.BuildingDTO;
import com.javaweb.repository.DistrictRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.repository.entity.RentAreaEntity;

@Component
public class BuildingDTOConverter {
	@Autowired
	private DistrictRepository districtRepository; 
	@Autowired
	private RentAreaRepository rentarearepository;
	@Autowired
	private ModelMapper modelMapper;
	
	public BuildingDTO toBuildingDTO(BuildingEntity item) {
//		BuildingDTO building= new BuildingDTO();
		BuildingDTO building = modelMapper.map(item, BuildingDTO.class);
		DistrictEntity districtEntity = districtRepository.findNameById(item.getDistrictid());
		building.setAddress(item.getStreet() + "," + item.getWard() + "," + districtEntity.getName());
		List<RentAreaEntity> rentArea = rentarearepository.getValueByBuildingId(item.getId());
	    String aeaResult = rentArea.stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
//		building.setRentArea(aeaResult);
//		building.setName(item.getName());
//		building.setBrokerageFee(item.getBrokeragefee());
//		building.setEmptyArea(item.getEmptyarea());
//		building.setFloorArea(item.getFloorarea());
//		building.setManagerName(item.getManagername());
//		building.setManagerPhoneNumber(item.getManagerphonenumber());
//		
		return building;
	}
}
