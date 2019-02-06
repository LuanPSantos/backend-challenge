package com.invillia.acme.model;

import com.invillia.acme.model.constant.OrderStatus;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "`Order`")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    private String address;
    private LocalDateTime confirmationDate;
    @Enumerated(value = EnumType.STRING)
    @NotNull
    private OrderStatus status;
    @ManyToMany
    @NotNull
    @NotEmpty
    private List<OrderItem> items;
    @ManyToOne
    @NotNull
    private Store store;

    public Long getId() {
        return id;
    }

    public Order setId(Long id) {
        this.id = id;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Order setAddress(String address) {
        this.address = address;
        return this;
    }

    public LocalDateTime getConfirmationDate() {
        return confirmationDate;
    }

    public Order setConfirmationDate(LocalDateTime confirmationDate) {
        this.confirmationDate = confirmationDate;
        return this;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public Order setStatus(OrderStatus status) {
        this.status = status;
        return this;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public Order setItems(List<OrderItem> items) {
        this.items = items;
        return this;
    }

    public Store getStore() {
        return store;
    }

    public Order setStore(Store store) {
        this.store = store;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return Objects.equals(getId(), order.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
