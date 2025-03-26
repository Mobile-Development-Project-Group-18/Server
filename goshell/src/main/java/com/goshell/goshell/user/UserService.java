package com.goshell.goshell.user;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.goshell.goshell.product.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }

    //Register
    public String addUser(User user) {
        Firestore db = getFirestore();

        try {
            if (user.getId() != null && !user.getId().isEmpty()) {
                DocumentReference docRef = db.collection("users").document(user.getId());
                DocumentSnapshot document = docRef.get().get();

                if (document.exists()) {
                    return ("User already exists" );
                }
            }

            db.collection("users").document(user.getId()).set(user).get();
            return user.getId();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return "Error adding user: " + e.getMessage();
        }
    }

    //Get user's information by id
    public User getUserById(String documentId) {
        Firestore db = getFirestore();
        DocumentReference docRef = db.collection("users").document(documentId);

        try {
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();

            if (document.exists()) {
                return document.toObject(User.class); // Convert Firestore data to User object
            } else {
                return null; // User not found
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Get user's sell product
    public List<Product> getUserProduct(String userId) {
        Firestore db = getFirestore();
        List<Product> products = new ArrayList<>();
        try {
            CollectionReference productsRef = db.collection("products");
            Query query = productsRef.whereEqualTo("seller", userId);
            ApiFuture<QuerySnapshot> querySnapshot = query.get();

            for (QueryDocumentSnapshot document : querySnapshot.get().getDocuments()) {
                Product product = document.toObject(Product.class);
                products.add(product);
            }

            return products;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

}