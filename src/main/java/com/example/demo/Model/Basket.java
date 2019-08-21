package com.example.demo.Model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "baskets")
public class Basket {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.MERGE)
    private User user;

    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "product_basket",
            joinColumns = @JoinColumn(name = "basket_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private List<Product> products;

    @Column(name = "actuality")
    private String actuality;

    public Basket(long id, User user, List<Product> products, String actuality) {
        this.id = id;
        this.user = user;
        this.products = products;
        this.actuality = actuality;
    }

    public Basket(User user, List<Product> products, String actuality) {
        this.user = user;
        this.products = products;
        this.actuality = actuality;
    }

    public Basket() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getActuality() {
        return actuality;
    }

    public void setActuality(String actuality) {
        this.actuality = actuality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Basket basket = (Basket) o;

        if (getId() != null ? !getId().equals(basket.getId()) : basket.getId() != null)
            return false;
        if (getUser() != null ? !getUser().equals(basket.getUser()) : basket.getUser() != null)
            return false;
        if (getProducts() != null ? !getProducts().equals(basket.getProducts()) : basket.getProducts() != null)
            return false;
        return getActuality() != null ? getActuality().equals(basket.getActuality()) : basket.getActuality() == null;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (getUser() != null ? getUser().hashCode() : 0);
        result = 31 * result + (getProducts() != null ? getProducts().hashCode() : 0);
        result = 31 * result + (getActuality() != null ? getActuality().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Basket{" +
                "id=" + id +
                ", user=" + user +
                ", products=" + products +
                ", actuality='" + actuality + '\'' +
                '}';
    }
}
