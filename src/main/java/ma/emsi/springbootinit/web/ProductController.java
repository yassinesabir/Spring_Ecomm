package ma.emsi.springbootinit.web;

import ma.emsi.springbootinit.entities.Product;
import ma.emsi.springbootinit.service.ServiceProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    ServiceProduct serviceProduct;

    public ProductController(ServiceProduct serviceProduct) {
        this.serviceProduct = serviceProduct;
    }

    @GetMapping("/products")
    Page<Product> getProducts(){
        return serviceProduct.getProducts(1);
    }

    //@HTTPTypeRequest + Path
    //method: call for services

    @GetMapping("/product/{id}")
    Product getProduct(@PathVariable Long id){
        return serviceProduct.getProduct(id);
    }

    //domaine.com/product?id=7
    @GetMapping("/product")
    Product getProduct2(@RequestParam Long id){
        return serviceProduct.getProduct(id);
    }

    @GetMapping("/product/ref/{ref}")
    Product getProduct(@PathVariable String ref){
        return serviceProduct.getProduct(ref);
    }

    @GetMapping("/product/name/{name}")
    Product getProductName(@PathVariable String name){
        return serviceProduct.getProduct(name);
    }

    @PostMapping("/product")
    Product addProduct(@RequestBody Product product){
        return serviceProduct.addProduct(product);
    }

    @PutMapping("/product")
    Product editProductByPrice(@RequestParam Float price,
                               @RequestParam Long id){
        return serviceProduct.editProductPrice(id, price);
    }

    @DeleteMapping("/product/{id}")
    void deleteProductById(@PathVariable Long id){
        serviceProduct.deleteProduct(id);
    }

    @DeleteMapping("/product/{name}")
    Product deleteProductByName(@PathVariable String name){
        return serviceProduct.deleteProduct(name);
    }
}
