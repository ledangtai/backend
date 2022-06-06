package com.abc.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.entity.Danhmuc;
import com.abc.repository.DanhmucRepository;

@RestController
@CrossOrigin
public class DanhmucController {

	@Autowired
	DanhmucRepository repo;
	
	@GetMapping("/danhmuc")
	public List<Danhmuc> getListDM(){
		return repo.findAll();
	}
	
	@PostMapping("/danhmuc")
	public ResponseEntity<String> insertDM(@Validated @RequestBody Danhmuc danhmuc){
		try {
			repo.save(danhmuc);
			return new ResponseEntity<String>("Successed !!!",HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Failed !!!",HttpStatus.BAD_REQUEST);
	}
	@DeleteMapping("/danhmuc/{madm}")
	public ResponseEntity<String> deleteDM(@PathVariable("madm") String madm){
		try {
			repo.deleteById(madm);
			return new ResponseEntity<String>("Successed !!!",HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Failed !!!",HttpStatus.BAD_REQUEST);
	}
	@PutMapping("/danhmuc")
	public ResponseEntity<String> updateDM(@Validated @RequestBody Danhmuc danhmuc){
		try {
			List<Danhmuc> listDM = repo.findAll();
			for(Danhmuc dm:listDM) {
				if(dm.getMadm().equalsIgnoreCase(danhmuc.getMadm())) {
					repo.save(danhmuc);
					return new ResponseEntity<String>("Successed !!!",HttpStatus.OK);
				}
			}
			return new ResponseEntity<String>("Failed !!!",HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Failed !!!",HttpStatus.BAD_REQUEST);
	}
}
