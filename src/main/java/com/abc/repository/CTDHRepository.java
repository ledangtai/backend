package com.abc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.abc.entity.CTDH;
import com.abc.entity.CTDH_ID;

public interface CTDHRepository extends JpaRepository<CTDH, CTDH_ID>{
	
	@Query(nativeQuery = true, value = "select * from ctdh where madh =?1")
	List<CTDH> getCTDHByMadh(String madh);

	@Query(nativeQuery = true, value = "select * from ctdh where madh =?1")
	List<CTDH> postCTDHByMadh(String madh);
	
	@Query(nativeQuery = true, value = "delete from ctdh where madh=?1")
	List<CTDH> deleteCTDHByDonhang(String madh);
	
}
