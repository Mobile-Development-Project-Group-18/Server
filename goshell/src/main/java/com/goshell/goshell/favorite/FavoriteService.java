package com.goshell.goshell.favorite;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.goshell.goshell.product.Product;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.Collections;

@Service
public class FavoriteService {

    private Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }

    public String addFavorite(Favorite favorite){
        Firestore db = getFirestore();

        try {
            // Check if the favorite already exists based on userId and productId
            ApiFuture<QuerySnapshot> future = db.collection("favorites")
                    .whereEqualTo("userId", favorite.getUserId())
                    .whereEqualTo("productId", favorite.getProductId())
                    .get();

            QuerySnapshot querySnapshot = future.get();
            if (!querySnapshot.isEmpty()) {
                return "Favorite already exists!";
            }

            // Generate new favorite ID and add to Firestore
            DocumentReference docRef = db.collection("favorites").document();
            String favoriteId = docRef.getId();
            favorite.setFavoriteId(favoriteId);
            docRef.set(favorite).get(); // Store favorite in Firestore

            return favoriteId; // Return the generated ID
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return "Error adding favorite: " + e.getMessage();
        }
    }

    public String deleteFavorite(String favoriteId){
        Firestore db = getFirestore();
        DocumentReference docRef = db.collection("favorites").document(favoriteId);

        try{
            ApiFuture<WriteResult> writeResult = docRef.delete();
            return "Favortie deleted at: " + writeResult.get().getUpdateTime();
        }catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return "Error deleting user";
        }
    }

    public List<Favorite> getFavoriteByUserId(String userId){
        Firestore db = getFirestore();
        List<Favorite> favorites = new ArrayList<>();
        try{
            CollectionReference favoriteRef = db.collection("favorites");
            Query query = favoriteRef.whereEqualTo("userId", userId);
            ApiFuture<QuerySnapshot> querySnapshotApiFuture = query.get();

            for (QueryDocumentSnapshot document : querySnapshotApiFuture.get().getDocuments()){
                Favorite favorite = document.toObject(Favorite.class);
                favorites.add(favorite);
            }

            return favorites;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }

    }


}
