package com.abc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.dao.DAO;
import com.abc.entity.Nhathuoc;
import com.abc.entity.Taikhoan;
import com.abc.model.Dangky;
import com.abc.model.DoanhThu;
import com.abc.model.Matkhau;
import com.abc.repository.NhathuocRepository;
import com.abc.repository.TaikhoanRepository;

@RestController
@CrossOrigin
public class NhathuocController {
	
	@Autowired
	NhathuocRepository repo;
	
	@Autowired
	TaikhoanRepository tkRepo;
	
	@GetMapping("/matkhau")
	public ArrayList<Matkhau> getMatkhau() {
		return new DAO().getMatkhau();
	}
	@GetMapping("/IsEmailExits/{email}")
	public ResponseEntity<String> checkEmailExit(@PathVariable("email") String email){
		if (repo.findNhaThuocBy(email) != null) return new ResponseEntity<String>("Email đã tồn tại",HttpStatus.OK);
		return new ResponseEntity<String>("",HttpStatus.OK);
	}

	@GetMapping("/nhathuoc/{username}")
	public Nhathuoc getNhaThuoc(@PathVariable("username") String username) {
		return repo.getNhathuocByUsername(username); 
	}
	
	@GetMapping("/nhathuoc")
	public List<Nhathuoc> getListNT(){
		return repo.findAll();
	}
	
	@PutMapping("/nhathuoc")
	public ResponseEntity<Boolean> updateNhaThuoc(@Validated @RequestBody Dangky dk){
		Nhathuoc nt = repo.getNhathuocByUsername(dk.getUsername());
		Taikhoan tk = tkRepo.findByUsername(dk.getUsername());
		
		tk.setUsername(dk.getUsername());
		tk.setPassword(dk.getPassword());
		
		nt.setTennhathuoc(dk.getTennhathuoc());
		nt.setDiachi(dk.getDiachi());
		nt.setEmail(nt.getEmail());
		nt.setSdt(dk.getSdt());
		nt.setTaikhoan(tk);
		
		try {
			tkRepo.save(tk);
			repo.save(nt);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}
		return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
		
	}
	
	@DeleteMapping("/nhathuoc/{matk}")
	public ResponseEntity<Boolean> deleteNT(@PathVariable("matk") int matk){
		try {
			repo.deleteNhathuocByMatk(matk);
			tkRepo.deleteById(matk);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
			
		}
	}
}
