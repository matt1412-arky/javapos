package com.xsis.javapos.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.xsis.javapos.models.OrderHeader;

@Repository
public interface OrderHeaderRepository extends JpaRepository<OrderHeader, Long> {
    @Query(value =
			"UPDATE Tbl_T_Order_Header oh "
			+ "	SET oh.is_delete=TRUE, "
			+ "		oh.update_by=:updateBy, "
			+ "		oh.update_date=NOW() "
			+ "WHERE oh.id=:orderHeaderId"
			, nativeQuery = true)
	public void deleteOrderHeader(
		@Param("orderHeaderId") long orderHeaderId
		, @Param("updateBy") int updateBy
	);
}
