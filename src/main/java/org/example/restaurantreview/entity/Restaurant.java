package org.example.restaurantreview.entity;

import jakarta.persistence.*;
import org.example.restaurantreview.constant.RestaurantType;

import java.util.HashSet;
import java.util.Set;
@Entity
@Table( name = "restaurants")
public class Restaurant extends BaseEntity{

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private RestaurantType type;

    @Column(nullable = false)
    private String address;

    @Column
    private String photoUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User owner;

    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Comment> comments;

    @Column
    private double averageRaiting;



    //Getter setter

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RestaurantType getType() {
        return type;
    }

    public void setType(RestaurantType type) {
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public double getAverageRaiting() {
        return averageRaiting;
    }

    public void setAverageRaiting(double averageRaiting) {
        this.averageRaiting = averageRaiting;
    }
}