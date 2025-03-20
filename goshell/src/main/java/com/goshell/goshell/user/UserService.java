package com.goshell.goshell.user;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
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
            // Use the getter for 'id' (user.id())
            db.collection("users").document(user.getId()).set(user).get();
            return "User added successfully";
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
}