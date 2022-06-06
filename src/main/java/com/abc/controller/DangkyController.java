package com.abc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.abc.entity.Nhathuoc;
import com.abc.entity.Quyen;
import com.abc.entity.Taikhoan;
import com.abc.model.Dangky;
import com.abc.repository.NhathuocRepository;
import com.abc.repository.TaikhoanRepository;

@RestController
@CrossOrigin
public class DangkyController {

	@Autowired 
	NhathuocRepository nhaThuocRepo;
	
	@Autowired
	TaikhoanRepository taiKhoanRepo;
	
	
	@GetMapping("/IsUserExits/{username}&{email}")
	public ResponseEntity<String> checkUserExit(@PathVariable("username") String username, @PathVariable("email") String email){
		if (taiKhoanRepo.findByUsername(username) != null) return new ResponseEntity<String>("Tên Đăng Kí đã tồn tại",HttpStatus.OK);
		if (nhaThuocRepo.findNhaThuocBy(email) != null) return new ResponseEntity<String>("Email đã tồn tại",HttpStatus.OK);
		return new ResponseEntity<String>("",HttpStatus.OK);
	}

	
	@PostMapping("/register")
	public ResponseEntity<String> register(@Validated @RequestBody Dangky dangky){
		try {
			Nhathuoc nt = new Nhathuoc();
			Taikhoan tk = new Taikhoan();
			
			nt.setTennhathuoc(dangky.getTennhathuoc());
			nt.setSdt(dangky.getSdt());
			nt.setEmail(dangky.getEmail());
			nt.setDiachi(dangky.getDiachi());
			String MaNhaThuoc = "NT" +  System.currentTimeMillis() % 10000000;
			nt.setManhathuoc(MaNhaThuoc);
			
			
			tk.setUsername(dangky.getUsername());
			tk.setPassword(dangky.getPassword());
			tk.setQuyen(new Quyen(2));
			nt.setTaikhoan(tk);
			
			for(Taikhoan t : taiKhoanRepo.findAll()) {
				if(t.getUsername().equalsIgnoreCase(tk.getUsername())) {
					return new ResponseEntity<String>("failed !!!",HttpStatus.BAD_REQUEST);
				}
			}
			taiKhoanRepo.save(tk);
			nhaThuocRepo.save(nt);
			
			return new ResponseEntity<String>("successed !!!",HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("failed !!!",HttpStatus.BAD_REQUEST);
	}
	
	
	@GetMapping("/quyen/{username}")
	public int getQuyen(@PathVariable("username") String username) {
		Taikhoan tk = taiKhoanRepo.findByUsername(username);
		return tk.getQuyen().getMaquyen();
	}
}
