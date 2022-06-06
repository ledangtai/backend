package com.abc.controller;

import java.util.List;
import java.util.Optional;

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

import com.abc.entity.DanhGia_ID;
import com.abc.entity.Danhgia;
import com.abc.model.ResponseDanhGia;
import com.abc.repository.DanhgiaRepository;

@RestController
@CrossOrigin
public class DanhgiaController {

	@Autowired
	DanhgiaRepository repo;
	
	
	@GetMapping("/danhgia/{manhathuoc}/{masp}")
	public int getDanhGiaByNT(@PathVariable("manhathuoc") String mant, @PathVariable("masp") String masp) {
		DanhGia_ID id = new DanhGia_ID();
		id.setManhathuoc(mant);
		id.setMasp(masp);
		
		List<Danhgia> danhgias = repo.findAll();
		for (Danhgia danhgia:danhgias)
		{
			if (danhgia.getId().getManhathuoc().equalsIgnoreCase(mant) && danhgia.getId().getMasp().equalsIgnoreCase(masp))
			{
				return danhgia.getDanhgia();
			}
		}
		return 0;
		
		
	}
	
	@GetMapping("/danhgia/{masp}")
	public ResponseDanhGia getAllDanhGia(@PathVariable("masp") String masp){
		ResponseDanhGia dg = new ResponseDanhGia();
		
		List<Danhgia> listDG = repo.getDGByManhathuoc(masp);
		
		float one=0,two=0,three=0,four=0,five=0;
		
		for(Danhgia d : listDG) {
			if(d.getDanhgia() == 1)
				one++;
			else if(d.getDanhgia() == 2)
				two++;
			else if(d.getDanhgia() == 3)
				three++;
			else if(d.getDanhgia() == 4)
				four++;
			else if(d.getDanhgia() ==5)
				five++;
		}
		int soluong = (int) (one + two + three + four +five);
		if(soluong != 0) {
			one = (one/soluong) *100;
			two = (two/soluong) *100;
			three = (three/soluong) *100;
			four = (four/soluong) *100;
			five = (five/soluong) *100;
		}
		dg.setOne(one);
		dg.setTwo(two);
		dg.setThree(three);
		dg.setFour(four);
		dg.setFive(five);
		dg.setSoluong(soluong);
		
		
		return dg;
	}
	
	@PostMapping("/danhgia")
	public ResponseEntity<Boolean> insertDanhgia(@Validated @RequestBody Danhgia danhgia){
		
		List<Danhgia> listDG = repo.findAll();
		for(Danhgia dg :listDG) {
			if(dg.getNhathuoc().getManhathuoc().equalsIgnoreCase(danhgia.getNhathuoc().getManhathuoc()) && dg.getSanpham().getMasp().equalsIgnoreCase(danhgia.getSanpham().getMasp())){
				try {
					repo.updateDanhgia(danhgia.getNhathuoc().getManhathuoc(), danhgia.getSanpham().getMasp(), danhgia.getDanhgia());
					return new ResponseEntity<Boolean>(true,HttpStatus.OK);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
					return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
				}
			}
		}
		try {
			repo.save(danhgia);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
		}
		
	}
}
