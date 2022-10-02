package com.repositories;

import com.dto.OrderInterface;
import com.entities.OrderEntity;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Integer> {
	@Query(value = "select\n"
			+ "        od.id ,\n"
			+ "        od.order_address as orderAddress,\n"
			+ "        od.order_date as orderDate,\n"
			+ "        od.received_date as receivedDate,\n"
			+ "        od.phone,\n"
			+ "        od.status,\n"
			+ "        od.users_id as userName,\n"
			+ "        od.cart_id as cart,\n"
			+ "        od.note  \n"
			+ "    from\n"
			+ "        orders od \n"
			+ "    inner join\n"
			+ "        users u \n"
			+ "            on od.users_id = u.id \n"
			+ "    where\n"
			+ "        u.name like concat('%', :name, '%') \n"
			+ "        and od.phone like concat('%', :phone, '%') \n"
			+ "    ORDER BY\n"
			+ "        od.id OFFSET :pageIndex ROWS FETCH NEXT :pageSize ROWS ONLY", nativeQuery = true)
	List<OrderInterface> findByOrder(@Param("name" ) String name, @Param("phone" ) String phone, @Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize);
	
	@Query(value = "select count(*)  "
			+ "from orders od inner join users u on od.users_id = u.id where u.name like concat('%', :name, '%') "
			+ "and od.phone like concat('%', :phone, '%')", nativeQuery = true)
	int totalCountByOrder(@Param("name" ) String name, @Param("phone" ) String phone);
	
}
