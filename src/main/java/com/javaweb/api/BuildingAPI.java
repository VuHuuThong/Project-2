package com.javaweb.api;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.javaweb.model.BuildingDTO;
import com.javaweb.model.BuildingRequestDTO;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.entity.BuildingEntity;
import com.javaweb.repository.entity.DistrictEntity;
import com.javaweb.service.BuildingService;


@RestController
@PropertySource("classpath:application.properties")
@Transactional
public class BuildingAPI {

	@PersistenceContext
    private EntityManager entityManager;
	@Autowired
	private BuildingService buildingService;
	@Autowired
    private BuildingRepository buildingRepository;
	@Value("${dev.vu}")
	private String data;
	
	@GetMapping(value="/api/building")
	public List<BuildingDTO> getBuilding(@RequestParam Map<String, Object> params ,
			                             @RequestParam(name="typeCode", required=false) List<String> typeCode){
		
		
		List<BuildingDTO> result = buildingService.findAll(params,typeCode);
		return result;
	}
	
	@GetMapping(value="/api/building/{id}")
	public BuildingDTO getBuildingById(@PathVariable Integer id) {
		BuildingDTO result= buildingService.findById(id);
		return result;
		
	}

	@GetMapping(value="/api/building/")
	public List<BuildingDTO> getBuildingByname(@RequestParam(value="name") String name) {
		List<BuildingDTO> result= buildingService.findByNameContaining(name);
		return result;
	}
	@GetMapping(value="/api/building/{name}/{street}")
	public BuildingDTO getBuildingBynameandstreet(@PathVariable String name, @PathVariable String street) {
		BuildingDTO result= new BuildingDTO();
		List<BuildingEntity> building = buildingRepository.findByNameContainingAndStreet(name, street);
		return result;
	}	
//	@PutMapping(value="/api/building/")
//	public void putBuilding(@RequestBody BuildingRequestDTO buildingDTO) {
//		buildingService.putData(buildingDTO);
//		System.out.print("ok");
//	}
//
//	@PutMapping(value="/api/building/")
//	public void updatedBuilding(@RequestBody BuildingRequestDTO buildingRequestDTO) {
//		BuildingEntity builEntity =buildingRepository.findById(buildingRequestDTO.getId()).get() ;
//		builEntity.setName(buildingRequestDTO.getName());
//		builEntity.setStreet(buildingRequestDTO.getStreet());
//		builEntity.setWard(buildingRequestDTO.getWard());
//		DistrictEntity dis = new DistrictEntity();
//		dis.setId(buildingRequestDTO.getDistrictid());
//		builEntity.setDistrict(dis);
//		buildingRepository.save(builEntity);
//		System.out.print("ok");
//	}
//	
//	@DeleteMapping(value="/api/building/{id}")
//	public void deleteById(@PathVariable Integer id) {
//		buildingRepository.deleteById(id);
//	}
//	@DeleteMapping(value="/api/building/{ids}")
//	public void deleteByIds(@PathVariable Integer[] ids) {
//		buildingRepository.deleteByIdIn(ids);
//	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	

}
