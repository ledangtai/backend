package com.abc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.entity.Taikhoan;
import com.abc.model.Login;
import com.abc.repository.TaikhoanRepository;

@RestController
@CrossOrigin
public class TaikhoanController {
	
	@Autowired
	TaikhoanRepository repo;
	
	@PostMapping("/login")
	public ResponseEntity<Taikhoan> dangnhap(@Validated @RequestBody Login login){
		Taikhoan tk = new Taikhoan();
		System.out.println(login.getUsername());
		tk = repo.findByUsername(login.getUsername());
		
		System.out.println(tk.getUsername());
		if(tk.getPassword().equalsIgnoreCase(login.getPassword()))
			return new ResponseEntity<Taikhoan>(tk,HttpStatus.OK);
		else return new ResponseEntity<Taikhoan>(new Taikhoan(),HttpStatus.BAD_REQUEST);
	}
	
}
