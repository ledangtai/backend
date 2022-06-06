package com.abc.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.abc.entity.Giohang;
import com.abc.entity.Giohang_ID;

public interface GiohangRepository extends JpaRepository<Giohang, Giohang_ID>{
	
	@Query(nativeQuery = true, value = "select * from giohang where manhathuoc = ?1")
	List<Giohang> getGiohangByMaNhaThuoc(String makh);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "delete from giohang where manhathuoc = ?1")
	void deleteGiohangByMaNhaThuoc(String username);
	
	@Transactional
	@Modifying
	@Query(nativeQuery = true, value = "delete from giohang where manhathuoc = ?1 and masp = ?2")
	void deleteGiohangByMaNhaThuocAndMasp(String manhathuoc, String masp);
	
	@Query(value = "EXEC SP_CHECKSANPHAM ?1, ?2", nativeQuery = true)
	String checkSanPham(String MASP, int SOLUONG);

	@Query(value = "EXEC SP_LAYSOLUONG ?1,?2", nativeQuery = true)
	int getSoLuongSP(String manhathuoc, String masp);
}
