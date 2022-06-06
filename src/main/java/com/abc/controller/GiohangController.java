package com.abc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abc.entity.Danhmuc;
import com.abc.entity.Giohang;
import com.abc.entity.Giohang_ID;
import com.abc.entity.Nhathuoc;
import com.abc.entity.Sanpham;
import com.abc.entity.Taikhoan;
import com.abc.repository.GiohangRepository;

@RestController
@CrossOrigin
public class GiohangController {
	@Autowired
	GiohangRepository repo;
	
	@GetMapping("/giohang/{manhathuoc}")
	public List<Giohang> getListGH(@PathVariable("manhathuoc") String manhathuoc){
		return repo.getGiohangByMaNhaThuoc(manhathuoc);
	}
	
	@GetMapping("/giohang/{manhathuoc}/{masp}")
	public ResponseEntity<Boolean> changeNum(@PathVariable("manhathuoc") String manhathuoc,@PathVariable("masp") String masp,@RequestParam("soluong") int soluong) {
		List<Giohang> listGH = repo.getGiohangByMaNhaThuoc(manhathuoc);
		for(Giohang gh:listGH) {
			if(gh.getSanpham().getMasp().equalsIgnoreCase(masp)) {
				Giohang giohang = gh;
				giohang.setSoluong(soluong);
				//System.out.println(gh.getKhachhang().getTen());
				try {
					repo.save(giohang);
					return new ResponseEntity<Boolean>(true,HttpStatus.OK);
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				break;
			}
		}
		return new ResponseEntity<Boolean>(false,HttpStatus.BAD_GATEWAY);
	}
	
	
	@PostMapping("/giohang/{manhathuoc}/{masp}")
	public String addCart(@PathVariable("manhathuoc") String manhathuoc, @PathVariable("masp") String masp,@RequestParam("soluong") int soluong) {
		Giohang_ID id = new Giohang_ID(manhathuoc, masp);
		Giohang gh = new Giohang();
		gh.setId(id);
		gh.setSoluong(soluong);
		Sanpham sp = new Sanpham();
		sp.setMasp(masp);
		Nhathuoc nt = new Nhathuoc();
		nt.setManhathuoc(manhathuoc);
		gh.setSanpham(sp);
		gh.setNhathuoc(nt);
		
		List<Giohang> listGH = repo.findAll();
		for(Giohang i:listGH) {
			if(i.getId().getMasp().equalsIgnoreCase(masp) && i.getId().getManhathuoc().equalsIgnoreCase(manhathuoc)) {
				gh.setSoluong(soluong +i.getSoluong());
			}
		}
		try {
			repo.save(gh);
			return "true";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return "false";
	}
	
	@DeleteMapping("/giohang/{manhathuoc}")
	public ResponseEntity<String> deleteGiohangOfNhaThuoc(@PathVariable("manhathuoc") String manhathuoc) {
		try {
			repo.deleteGiohangByMaNhaThuoc(manhathuoc);
			return new ResponseEntity<String>("Successed !!!",HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Failed !!!",HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/giohang/{manhathuoc}/{masp}")
	public ResponseEntity<String> deleteGiohangById(@PathVariable("manhathuoc") String manhathuoc, @PathVariable("masp") String masp) {
		try {
			repo.deleteGiohangByMaNhaThuocAndMasp(manhathuoc, masp);
			return new ResponseEntity<String>("Successed !!!",HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Failed !!!",HttpStatus.BAD_REQUEST);
	}
	@GetMapping("/numcart/{manhathuoc}")
	public int getSLC(@PathVariable("manhathuoc") String manhathuoc) {
		List<Giohang> list = repo.getGiohangByMaNhaThuoc(manhathuoc);
		int soluong = 0 ;
		for(Giohang gh:list) {
			soluong +=gh.getSoluong();
		}
		return soluong;
	}
	@PostMapping("/dathang")
	public ResponseEntity<Object> checkDatHang(@RequestBody Sanpham[] sanpham)
	{
		for (Sanpham i:sanpham)
		{
			System.out.println(repo.checkSanPham(i.getMasp(), i.getSoluong()));
			if (repo.checkSanPham(i.getMasp(), i.getSoluong()).equalsIgnoreCase("FALSE")) return new ResponseEntity<Object>(false,HttpStatus.OK);
		}
		return new ResponseEntity<Object>(true,HttpStatus.OK);
	}
	@GetMapping("/getSoluonggh/{manhathuoc}/{masp}")
	public int checkSoLuong(@PathVariable("manhathuoc") String manhathuoc, @PathVariable("masp") String masp){
	    int sl = repo.getSoLuongSP(manhathuoc,masp);
	    return sl;
	}
}
