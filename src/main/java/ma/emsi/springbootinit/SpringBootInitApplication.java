package ma.emsi.springbootinit;

import ma.emsi.springbootinit.entities.Product;
import ma.emsi.springbootinit.repositories.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class SpringBootInitApplication implements CommandLineRunner {
	@Autowired
	ProductRepo productRepo;
	public static void main(String[] args) {
		SpringApplication.run(SpringBootInitApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//Insertion des produits
		/*
		for (int i=1; i<=10; i++){
			productRepo.save(
					new Product(null, "PC "+i,
					"PC"+i+"-Ref", 1234,new Date()
							,(float)Math.random()*1000));

		}
		*/
		//Nombre d'enregistrement dans "Product"
		System.out.println(" ------ Nombre total des produits: "
				+productRepo.count()+"------");

		//Lecture de la table "Product"
		Iterable<Product> products = productRepo.findAll();
		for (Product p:products) {
			System.out.println(p.getId()+" - "+ p.getName() + " - " +p.getRef());
		}
		if(productRepo.existsById(11L))
			System.out.println("Le produit 11: "
					+ productRepo.findById(11L).get() );
		else
			System.out.println("Product not found");

		//Récupérer un objet à travers son ID
		//System.out.println("Le produit 5: " + productRepo.findById(5L).get() );

		//Supprimer l'objet 5
		//productRepo.deleteById(5L);

		//Validation de la suppression (Affichage)
		products = productRepo.findAll();
		for (Product p:products) {
			System.out.println(p.getId()+" - "+ p.getName() + " - " +p.getRef());
		}

		//Recherche par nom
		//System.out.println(productRepo.findByName("PC 9"));
	}
}
