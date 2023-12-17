package com.ra.repositoty;
import com.ra.dto.reponse.OrdersDTO;
import com.ra.model.Orders;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;


@Repository
public interface IOrderRepository extends JpaRepository<Orders,Integer> {
    @Query("SELECT new com.ra.dto.reponse.OrdersDTO(o.id, o.address, o.phone, o.note, o.created, o.status, u.username, u.email) FROM Orders o JOIN o.user u")
    List<OrdersDTO> getOrdersDTO();
    @Query(value = "update Orders SET status= :status WHERE id=:id")
    @Modifying
    @Transactional
    void updateStatus(@Param("status") Boolean status, @Param("id") Integer id);
    Optional<Orders> findByStatusAndUserId(boolean status, Integer idUser);
}
