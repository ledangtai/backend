package com.abc.controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.apache.naming.java.javaURLContextFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abc.dao.DAO;
import com.abc.entity.CTDH;
import com.abc.entity.CTDH_ID;
import com.abc.entity.Danhmuc;
import com.abc.entity.Donhang;
import com.abc.entity.Giohang;
import com.abc.entity.Nhathuoc;
import com.abc.entity.Sanpham;
import com.abc.model.Dathang;
import com.abc.model.DoanhThu;
import com.abc.repository.CTDHRepository;
import com.abc.repository.DonhangRepositoty;
import com.abc.repository.GiohangRepository;
import com.abc.repository.SanphamRepository;

@RestController
@CrossOrigin
public class DonhangController {
	@Autowired
	DonhangRepositoty repo;
	
	@Autowired
	CTDHRepository ctRepo;
	
	@Autowired
	GiohangRepository ghRepo;
	
	@GetMapping("/doanhthu")
	public ArrayList<DoanhThu> getDoanhThu() {
		return new DAO().getDoanhThu();
	}
	
	@GetMapping("/donhang")
	public List<Donhang> getListDH(){
		return repo.findAll(Sort.by(Sort.Order.desc("ngaydat"),Sort.Order.asc("trangthai")));
	}
	
	@GetMapping("/donhang/{manhathuoc}/{madh}")// ?username làm biến ảo (không trùng với các method get khác) có thể nhập sai trường username :)
	public Optional<Donhang> getIdDonhangByMaNhaThuoc(@PathVariable("madh") String madh) {
		return repo.findById(madh);
	}
	
	@GetMapping("/donhang/{manv}/{madh}")
	public Optional<Donhang> getIdDonhangByManv(@PathVariable("madh") String madh) {
		return repo.findById(madh);
	}
	
	@GetMapping("/donhang/{manhathuoc}")
	public List<Donhang> getDonhangByManhathuoc(@PathVariable("manhathuoc") String mant){
		List<Donhang> list = repo.getDonhangByManhathuoc(mant);
		java.util.Collections.sort(list,new Comparator<Donhang>() {

			@Override
			public int compare(Donhang o1, Donhang o2) {
				int cpTT = o2.getNgaydat().compareTo(o1.getNgaydat());
				if(cpTT != 0) {
					return o2.getNgaydat().compareTo(o1.getNgaydat());
				}
				return o1.getTrangthai()>o2.getTrangthai()?1:-1;
			}
			
		});
		return list;
	}
	
	
	@PutMapping("/donhang")
	public ResponseEntity<String> updateDonhang(@Validated @RequestBody Donhang donhang) {

		try {
			List<Donhang> listDH = repo.findAll();
			for(Donhang dh : listDH) {
				if (dh.getMadh().equalsIgnoreCase(donhang.getMadh())) {
					repo.save(donhang);
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
	
	@PostMapping("/donhang/{manhathuoc}")
	public ResponseEntity<String> insertDonhang(@Validated @RequestBody List<Dathang> listDH,@PathVariable("manhathuoc") String manhathuoc,@RequestParam("thanhtoan") int thanhtoan) {
		try {
			Donhang donhang = new Donhang();
			String madh = "DH" +  System.currentTimeMillis() % 100000000;
			float tongtien = 0;
			for(Dathang dh : listDH) {
				tongtien += dh.getDongia() * dh.getSoluong();
			}
			
			donhang.setMadh(madh);
			//Timestamp tm = new Timestamp(System.currentTimeMillis());
			
			//donhang.setNgaydat(tm);
			donhang.setHinhthucthanhtoan(thanhtoan);
			donhang.setTrangthai(0);
			Nhathuoc nhathuoc = new Nhathuoc();
			nhathuoc.setManhathuoc(manhathuoc);
			donhang.setNhathuoc(nhathuoc);
			donhang.setTongtien(tongtien);
			//save donhang
			try {
				repo.save(donhang);
				
				//save ctdh
				for(Dathang dh : listDH) {
					CTDH ctdh = new CTDH();
					CTDH_ID id = new CTDH_ID(madh,dh.getMasp());
					ctdh.setDonhang(donhang);
					ctdh.setId(id);
					Sanpham sanpham = new Sanpham();
					sanpham.setMasp(dh.getMasp());
					ctdh.setSanpham(sanpham);
					ctdh.setSoluong(dh.getSoluong());
					ctRepo.save(ctdh);
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
				return new ResponseEntity<String>("không thể thêm đơn hàng",HttpStatus.BAD_REQUEST);
			}
			try {
				for(Dathang dh : listDH) {
					ghRepo.deleteGiohangByMaNhaThuocAndMasp(manhathuoc, dh.getMasp());
				}
				return new ResponseEntity<String>("Successed !!!",HttpStatus.OK);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Failed !!!",HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/donhang/{madh}")
	public ResponseEntity<String> deleteIdDonhang(@PathVariable("madh") String madh) {
		List<CTDH> listCT = ctRepo.findAll();
		try {
			
			for(CTDH ct : listCT) {
				if(ct.getId().getMadh().equalsIgnoreCase(madh)) {
					ctRepo.delete(ct);
				}
			}
			repo.deleteById(madh);
			return new ResponseEntity<String>("Successed !!!",HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return new ResponseEntity<String>("Failed !!!",HttpStatus.BAD_REQUEST);
	}
	@PutMapping("/donhang/{madh}")
	public ResponseEntity<Boolean> huyDonhang(@PathVariable("madh") String madh) {
		
		Optional<Donhang> dh = repo.findById(madh);
		
		if(true) {
			try {
				dh.get().setTrangthai(4);
				repo.save(dh.get());
				return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return new ResponseEntity<Boolean>(false,HttpStatus.BAD_REQUEST);
	}
	@GetMapping("/doanhthutrongkhoang/{NGAYBD}/{NGAYKT}")
	public int doanhthutrongkhoang(@PathVariable("NGAYBD") Date NGAYBD , @PathVariable("NGAYKT") Date NGAYKT) {
		int doanhthu = repo.doanhthutrongkhoang(NGAYBD, NGAYKT);
		return doanhthu;
	}
	@GetMapping("/doanhthutrongngay")
	public int doanhthutrongngay() {
		return repo.doanhthutrongngay();
	}
	
}

