package org.example.restaurantreview.constant;

public enum UserRole {
    STANDART("Standart"),
    KIDEMLI("Kıdemli"),
    YONETICI("Yönetici");

    private String roleName;

    UserRole(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
