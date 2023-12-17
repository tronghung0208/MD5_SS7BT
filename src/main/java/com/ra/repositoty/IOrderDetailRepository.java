package com.ra.repositoty;

import com.ra.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrderDetail,Integer> {

    List<OrderDetail> findAllByOrdersId(Integer idOrder);

    Optional<OrderDetail> findByOrdersIdAndProductId(Integer idOrder, Integer productId);
}
