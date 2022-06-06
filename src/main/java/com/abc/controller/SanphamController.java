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

import com.abc.entity.Sanpham;
import com.abc.repository.SanphamRepository;

@RestController
@CrossOrigin
public class SanphamController {
	
	@Autowired
	SanphamRepository repo;
	
	@GetMapping("/sanpham")
	public List<Sanpham> getListSP(){
		return repo.findAll();
	}

	@PostMapping("/sanpham")
	public ResponseEntity<String> insertSanpham(@Validated @RequestBody Sanpham sanpham){
		try {
			repo.save(sanpham);
			return new ResponseEntity<String>("successed !!!" , HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("failed !!!" , HttpStatus.BAD_REQUEST);
	}
	@DeleteMapping("/sanpham/{masp}")
	public ResponseEntity<String> deleteSanpham(@PathVariable("masp") String masp){
		try {
			repo.deleteById(masp);
			return new ResponseEntity<String>("successed !!!" , HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("failed !!!" , HttpStatus.BAD_REQUEST);
	}
	@PutMapping("/sanpham")
	public ResponseEntity<String> updateSanpham(@Validated @RequestBody Sanpham sanpham){
		try {
			List<Sanpham> listSP = repo.findAll();
			for(Sanpham sp:listSP) {
				if(sp.getMasp().equalsIgnoreCase(sanpham.getMasp())) {
					repo.save(sanpham);
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
	@GetMapping("/sanpham/{masp}")
	public Optional<Sanpham> getSPByID(@PathVariable("masp") String masp) {
		return repo.findById(masp);
	}
	@GetMapping("/sanpham/danhmuc/{madm}")
	public List<Sanpham> getListSPByMadm(@PathVariable("madm") String madm){
		return repo.getSPByMadm(madm);
	}
}
