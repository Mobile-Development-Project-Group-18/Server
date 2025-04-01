package com.goshell.goshell.favorite;

import jakarta.validation.constraints.NotEmpty;

public class Favorite {
    @NotEmpty
    private String userId;
    @NotEmpty
    private String productId;
    @NotEmpty
    private String favoriteId;

    public Favorite(){}

    public Favorite(String userId, String productId, String favoriteId){
        this.productId = productId;
        this.userId = userId;
        this.favoriteId = favoriteId;
    }

    //Getters
    public String getUserId(){
        return userId;
    }

    public String getProductId(){
        return productId;
    }

    public String getFavoriteId(){
        return favoriteId;
    }

    //Setters
    public void setUserId(String userId){
        this.userId = userId;
    }

    public void setProductId(String productId){
        this.productId = productId;
    }

    public void setFavoriteId(String favoriteId){
        this.favoriteId = favoriteId;
    }
}
