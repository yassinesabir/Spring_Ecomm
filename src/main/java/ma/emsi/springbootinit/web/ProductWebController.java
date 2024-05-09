package ma.emsi.springbootinit.web;

import lombok.AllArgsConstructor;
import ma.emsi.springbootinit.entities.Product;
import ma.emsi.springbootinit.repositories.ProductRepo;
import ma.emsi.springbootinit.service.ServiceProduct;
import org.springframework.boot.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
public class ProductWebController {
    ServiceProduct serviceProduct;
    ProductRepo productRepo;
    @GetMapping("/index")
    String getProducts(Model model,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(name = "NomProduit",defaultValue = "") String nomProduit){
        Page<Product> productList
                = productRepo.findByNameContains(nomProduit, PageRequest.of(page, 10));
        model.addAttribute("prdcts",productList);
        model.addAttribute("title",
                "Liste des produits");
        model.addAttribute("count",
                productList.getTotalElements());

        model.addAttribute("currentPage"
                ,productList.getNumber());

        int[] listPages
                = new int[productList.getTotalPages()];
        for (int i =0; i<listPages.length; i++)
            listPages[i] = i;
        model.addAttribute("listPages",
                listPages);
        return "products"; //Nom de la page HTML (templates)
    }

    @GetMapping("/delete")
    String deleteProductById(@RequestParam Long id){
        System.out.println("PRDCT TO BE DELETE: " + id);
        serviceProduct.deleteProduct(id);
        return "redirect:/index";
    }


    @GetMapping("/layout")
    public String layout(Model model) {
        model.addAttribute("product", new Product());
        return "layout";
    }

    @PostMapping("/addProduct")
    String addProduct(@ModelAttribute Product product){
        serviceProduct.addProduct(product);
        return "redirect:/index";
    }

    @PostMapping("/edit")
    String editProductByPrice(@RequestParam Float price,
                               @RequestParam Long id){
         serviceProduct.editProductPrice(id, price);
        return "redirect:/index";
    }
}
