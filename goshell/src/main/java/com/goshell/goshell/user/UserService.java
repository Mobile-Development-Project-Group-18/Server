package com.goshell.goshell.user;

import com.google.cloud.firestore.Firestore;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    private Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }

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

}