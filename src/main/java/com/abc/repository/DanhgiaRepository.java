package com.abc.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.abc.entity.DanhGia_ID;
import com.abc.entity.Danhgia;

public interface DanhgiaRepository extends JpaRepository<Danhgia, DanhGia_ID> {
	@Query(value="select * from danhgia where masp = ?1",nativeQuery = true)
	List<Danhgia> getDGByManhathuoc(String masp);
	
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true,value = "update danhgia set danhgia=?3 where manhathuoc = ?1 and masp =?2 ")
	void updateDanhgia(String manhathuoc,String masp,int danhgia);
}
