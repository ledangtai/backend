package com.abc.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.abc.entity.Nhathuoc;
import com.abc.entity.Nhanvien;

public interface NhanvienRepository extends JpaRepository<Nhanvien, String> {
	@Transactional
	@Modifying
	@Query(value="delete from Nhanvien kh where kh.taikhoan.matk = ?1")
	void deleteNhanvienByMatk(int matk);
	
	
	@Query(value="select kh from Nhanvien kh where kh.taikhoan.username = ?1")
	Nhanvien getNhanvienByUsername(String username);
}
