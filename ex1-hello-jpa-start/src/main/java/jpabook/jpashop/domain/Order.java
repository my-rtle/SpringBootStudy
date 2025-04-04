package jpabook.jpashop.domain;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ORDERS")
public class Order {

    @Id @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;
    private LocalDateTime orderDate;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order")
    List<OrderItem> orderItems = new ArrayList<>();

    void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
    public Long getId() { return id;}
    public void setId(Long id){this.id = id;}

    public LocalDateTime getOrderDate() { return orderDate;}
    public void setOrderDate(LocalDateTime orderDate){this.orderDate = orderDate;}
    public OrderStatus getStatus() { return status;}
    public void setStatus(OrderStatus status){this.status = status;}

    public Member getMember() { return member;}
    public void setMember(Member member){
        this.member = member;
    }

    public List<OrderItem> getOrderItems(){return orderItems;}

}
