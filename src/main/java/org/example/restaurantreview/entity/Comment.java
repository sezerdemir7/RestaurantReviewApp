package org.example.restaurantreview.entity;
import jakarta.persistence.*;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity{



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;

    @Column(nullable = false)
    private int serviceRating;

    @Column(nullable = false)
    private int tasteRating;

    @Column(nullable = false)
    private int priceRating;

    @Column(nullable = false)
    private String commentText;


    //Getter , Setter

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getServiceRating() {
        return serviceRating;
    }

    public void setServiceRating(int serviceRating) {
        this.serviceRating = serviceRating;
    }

    public int getTasteRating() {
        return tasteRating;
    }

    public void setTasteRating(int tasteRating) {
        this.tasteRating = tasteRating;
    }

    public int getPriceRating() {
        return priceRating;
    }

    public void setPriceRating(int priceRating) {
        this.priceRating = priceRating;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }
}