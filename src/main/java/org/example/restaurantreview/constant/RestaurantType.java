package org.example.restaurantreview.constant;

public enum RestaurantType {

    KEBABCI("Kebabçi"),
    ESNAF_LOKANTASI("Esnaf Lokantası"),
    PIZZACI("Pizzacı"),
    LAHMACUN_PIDE("Lahmacun Pide"),
    DONERCI("Dönerci");

    private String type;

    RestaurantType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
