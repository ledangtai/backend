package com.abc.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.entity.Binhluan;
import com.abc.entity.Sanpham;
import com.abc.repository.BinhluanRepository;

@RestController
public class BinhluanController {
	@Autowired
	BinhluanRepository repo;
	
	@GetMapping("/binhluan")
	public List<Binhluan> getListBL(){
		return repo.findAll();
	}
	
	@PutMapping("/binhluan")
	public ResponseEntity<String> updateBinhluan(@Validated @RequestBody Binhluan binhluan) {

		try {
			List<Binhluan> listBl = repo.findAll();
			for(Binhluan bl : listBl) {
				if(bl.getId() == binhluan.getId()) {
					repo.save(binhluan);
					return new ResponseEntity<String>("Successed" , HttpStatus.OK);
				}
			}
			return new ResponseEntity<String>("failed !!!" , HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("failed !!!" , HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("/binhluan")
	public ResponseEntity<String> insertBinhluan(@Validated @RequestBody Binhluan bl) {
		try {
			repo.save(bl);
			return new ResponseEntity<String>("successed !!!" , HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("failed !!!" , HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/binhluan/{id}")
	public ResponseEntity<String> deleteIdBinhluan(@PathVariable("id") int id) {
		try {
			repo.deleteById(id);
			return new ResponseEntity<String>("successed !!!" , HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("failed !!!" , HttpStatus.BAD_REQUEST);
	}
}
