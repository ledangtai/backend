package com.abc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.abc.entity.CTDH;
import com.abc.entity.CTDH_ID;
import com.abc.entity.Donhang;
import com.abc.entity.Sanpham;
import com.abc.repository.CTDHRepository;
import com.abc.repository.DonhangRepositoty;

@RestController
@CrossOrigin
public class CTDHController {

	@Autowired
	CTDHRepository repo;
	
	@Autowired
	DonhangRepositoty dhRepo;

	@GetMapping("/ctdh/{madh}")
	public List<CTDH> getListCTDH(){
		return repo.findAll();
	}

	
	@GetMapping("/ctdh/{manhathuoc}/{masp}")
	public ResponseEntity<Boolean> getCTDH(@PathVariable("manhathuoc") String manhathuoc,@PathVariable("masp") String masp) {
		List<Donhang> listDH = dhRepo.getDonhangByManhathuoc(manhathuoc);
		for(Donhang dh : listDH) {
			for(CTDH ct : dh.getListCTDH()) {
				if(ct.getSanpham().getMasp().equalsIgnoreCase(masp)) {
					return new ResponseEntity<Boolean>(true,HttpStatus.OK);
				}
			}
		}
		return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("ctdh/{madh}")
	public ResponseEntity<String> deleteCTDH(@PathVariable("madh") String madh) {
		try {
			repo.deleteCTDHByDonhang(madh);
			
			return new ResponseEntity<String>("successed !!!" , HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("failed !!!" , HttpStatus.BAD_REQUEST);
	}
}
