package ma.emsi.springbootinit.service;

import ma.emsi.springbootinit.entities.Product;
import ma.emsi.springbootinit.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ServiceProductImp implements ServiceProduct{
    @Autowired
    ProductRepo productRepo;
    @Override
    public Page<Product> getProducts(int page) {
        return productRepo
                .findAll(PageRequest.of(page, 10));
    }
    @Override
    public Product getProduct(Long id) {
        //Verifier si le produit existe
        return productRepo.findById(id).get();
    }

    @Override
    public Product getProduct(String ref) {
        return productRepo.findByRef(ref);
    }

    @Override
    public Product getProductName(String name) {
        return productRepo.findByName(name);
    }

    @Override
    public Product addProduct(Product product) {
        return productRepo.save(product);
    }

    @Override
    public Product editProductPrice(Long id, Float price) {
        Product prctToBeEdited
                = productRepo.findById(id).get();
        if(prctToBeEdited!=null) {
            prctToBeEdited.setPrice(price);
            return productRepo.save(prctToBeEdited);
        }
        return null;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepo.deleteById(id);
    }

    @Override
    public Product deleteProduct(String name) {
        return productRepo.deleteByName(name);
    }

}

