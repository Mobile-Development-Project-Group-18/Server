package com.goshell.goshell.product;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

@Service
public class ProductService {

    private Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }

    //Add new product
    public String addProduct(Product product){
        Firestore db = getFirestore();

        try{
            DocumentReference docRef = db.collection("products").document();
            String productId = docRef.getId();
            product.setId(productId);
            docRef.set(product).get();
            return product.getId();
        } catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
            return "Error adding product" + e.getMessage();
        }
    }

    //Get all products
    public List<Product> getAllProducts(){
        Firestore db = getFirestore();

        List<Product> productsList = new ArrayList<>();
        CollectionReference productRef = db.collection("products");

        try{
            ApiFuture<QuerySnapshot> future = productRef.get();
            List<QueryDocumentSnapshot> documents = future.get().getDocuments();

            for(QueryDocumentSnapshot document : documents){
                Product product = document.toObject(Product.class);
                product.setId(document.getId());
                productsList.add(product);
            }
        }catch (InterruptedException | ExecutionException e){
            e.printStackTrace();
        }

        return productsList;
    }

    //Get product by id
    public Product getProductById(String productId){
        Firestore db = getFirestore();
        DocumentReference docRef = db.collection("products").document(productId);

        try{
            ApiFuture<DocumentSnapshot> future = docRef.get();
            DocumentSnapshot document = future.get();

            if(document.exists()){
                return document.toObject(Product.class);
            }else {
                return null;
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return null;
        }
    }

    //Update product
    public String updateProduct(String productId, Map<String, Object> updates) {
        Firestore db = getFirestore();
        DocumentReference docRef = db.collection("products").document(productId);

        try {
            ApiFuture<WriteResult> writeResult = docRef.update(updates);
            return "Product updated at: " + writeResult.get().getUpdateTime();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return "Error updating product";
        }
    }

    //Delete product
    public String deleteProduct(String productId){
        Firestore db = getFirestore();
        DocumentReference docRef = db.collection("products").document(productId);

        try {
            ApiFuture<WriteResult> writeResult = docRef.delete();
            return "Product deleted at: " + writeResult.get().getUpdateTime();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return "Error deleting user";
        }
    }


}