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
	
	@Value("${dev.vu}")
	private String data;
	
	@GetMapping(value="/api/building")
	public List<BuildingDTO> getBuilding(@RequestParam Map<String, Object> params ,
			                             @RequestParam(name="typeCode", required=false) List<String> typeCode){
		List<BuildingDTO> result = buildingService.findAll(params,typeCode);
		return result;
	}
	
	@PostMapping(value="/api/building/")
	
	public void creatBuilding(@RequestBody BuildingRequestDTO buildingRequestDTO) {
		 BuildingEntity buildingEntity= new BuildingEntity();
		 buildingEntity.setName(buildingRequestDTO.getName());
		 buildingEntity.setStreet(buildingRequestDTO.getStreet());
		 buildingEntity.setWard(buildingRequestDTO.getWard());
		 DistrictEntity dis= new DistrictEntity();
		 dis.setId(buildingRequestDTO.getDistrictid());
		 buildingEntity.setDistrict(dis);
		 
		 entityManager.persist(buildingEntity);
		 System.out.print("ok");
	}
	
	@PutMapping(value="/api/building/")
	public void updateBuilding(@RequestBody BuildingRequestDTO buildingRequestDTO) {
		 BuildingEntity buildingEntity= new BuildingEntity();
		 buildingEntity.setId(5);
		 buildingEntity.setName(buildingRequestDTO.getName());
		 buildingEntity.setStreet(buildingRequestDTO.getStreet());
		 buildingEntity.setWard(buildingRequestDTO.getWard());
		 DistrictEntity dis= new DistrictEntity();
		 dis.setId(buildingRequestDTO.getDistrictid());
		 buildingEntity.setDistrict(dis);
		 
		 entityManager.merge(buildingEntity);
		 System.out.print("ok");
	}
	
	 @DeleteMapping(value="/api/building/{id}")
	 public void dele(@PathVariable Integer id) {
		 BuildingEntity buildingEntity= entityManager.find(BuildingEntity.class, id);
		 entityManager.remove(buildingEntity);
		
	 }
	

}
