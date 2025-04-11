package com.goshell.goshell.product;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    //Add new product
    @PostMapping("")
    public ResponseEntity<String> addProduct(@RequestBody Product product) {
        String id = productService.addProduct(product);
        return ResponseEntity.ok(id);
    }

    //Get all products
    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts(){
        return productService.getAllProducts();
    }

    //Get product by id
    @GetMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@PathVariable String productId){
        Product product = productService.getProductById(productId);

        if (product != null) {
            return product;
        } else {
            throw new RuntimeException("User not found with ID: " + productId);
        }
    }

    //Update product
    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public String updateProduct(@PathVariable String productId, @RequestBody Map<String, Object> updates){
        return productService.updateProduct(productId, updates);
    }

    //Delete product
    @DeleteMapping("/{productId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteProduct(@PathVariable String productId){
        return productService.deleteProduct(productId);
    }
}