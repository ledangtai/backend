package com.abc.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.abc.entity.Donhang;
import com.abc.model.DoanhThu;

public interface DonhangRepositoty extends JpaRepository<Donhang, String>{
	
	@Query(nativeQuery = true, value= "select * from donhang where manhathuoc=?1")
	List<Donhang> getDonhangByManhathuoc(String mant);
	
	@Query(nativeQuery = true, value= "select * from donhang where manv=?1")
	List<Donhang> getDonhangByManv(String manv);
	
	@Query(nativeQuery = true, value= "EXEC SP_DOANHTHUTRONGNGAY")
	int doanhthutrongngay();
	
	@Query(value = "EXEC SP_TONGDOANHTHU ?1, ?2", nativeQuery = true)
	int doanhthutrongkhoang(Date NGAYBD, Date NGAYKT);
	
}
