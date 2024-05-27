package com.xsis.javapos.repositories;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
// import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
// import org.springframework.transaction.annotation.Transactional;

import com.xsis.javapos.models.OrderDetail;
// @Transactional(readOnly = true)
@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Long>{
    @Query(value = "select\r\n" + //
                "\tod.id,\r\n" + //
                "\tod.order_header_id,\r\n" + //
                "\tod.product_id,\r\n" + //
                "\tod.qty,\r\n" + //
                "\tod.price,\r\n" + //
                "\tod.is_deleted,\r\n" + //
                "\tod.create_by,\r\n" + //
                "\tod.create_date,\r\n" + //
                "\tod.update_by,\r\n" + //
                "\tod.update_date \r\n" + //
                "from tbl_t_order_detail od\r\n" + //
                "where od.is_deleted = false and od.order_header_id=:orderHeaderId", nativeQuery = true)
    Optional <List<OrderDetail>> findByOrderHeaderIdNative(@Param("orderHeaderId")long orderHeaderId);
    
    // @Modifying
    // @Transactional
    // @Query(value = "UPDATE Tbl_T_Order_Header oh SET is_delete = TRUE "
    //                 + "WHERE oh.id =: orderHeaderId")
    // public void deleteOrderHeader(@Param("orderHeaderId") long orderHeaderId);
    @Modifying
    @Query(value = "UPDATE Tbl_T_Order_Detail od "
			+ "SET od.is_delete=TRUE "
			+ "		od.update_by=:updateBy, "
			+ "		od.update_date=:updateDate "
			+ "WHERE od.order_header_id=:orderHeaderId "
			+ "RETURNING is_delete"
			, nativeQuery = true)
	public int deleteOrderDetail(
		@Param("orderHeaderId") long orderHeaderId
		, @Param("updateBy") int updateBy
		, @Param("updateDate") LocalDateTime updateDate
	);
}
