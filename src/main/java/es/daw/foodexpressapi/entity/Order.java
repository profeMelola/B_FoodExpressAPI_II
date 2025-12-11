package es.daw.foodexpressapi.entity;

import es.daw.foodexpressapi.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="orders")
//@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="order_date", nullable = false)
    //private LocalDateTime orderdate;
    private LocalDateTime orderDate;

    @Column(nullable = false, length = 30)
    private String status;


    // --------------- ASOCIACIÓN CON USER -------------
    @ManyToOne(optional = false)
    @JoinColumn(name="user_id",nullable = false)
    private User user;

    // ------------- ASOCIACIÓN CON RESTAURANTES ----------------


    @ManyToOne(optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    // FALTA LA RELACIÓN ORDER_DETAILS...
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    private List<OrderDetail> orderDetails = new ArrayList<>();

}
