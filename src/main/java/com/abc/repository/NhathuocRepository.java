package com.abc.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.abc.entity.Nhathuoc;

public interface NhathuocRepository extends JpaRepository<Nhathuoc, String>{

	@Query(value="select nt from Nhathuoc nt where nt.taikhoan.username = ?1")
	Nhathuoc getNhathuocByUsername(String username);
	@Transactional
	@Modifying
	@Query(value="delete from Nhathuoc nt where nt.taikhoan.matk = ?1")
	void deleteNhathuocByMatk(int matk);
	@Query(value = "SELECT * FROM Nhathuoc WHERE EMAIL = ?1", nativeQuery = true)
	Nhathuoc findNhaThuocBy(String email);
}
