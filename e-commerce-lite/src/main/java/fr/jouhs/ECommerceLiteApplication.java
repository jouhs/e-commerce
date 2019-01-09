package fr.jouhs;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fr.jouhs.dao.CategoryRepository;
import fr.jouhs.dao.ProductRepository;
import fr.jouhs.entities.Category;
import fr.jouhs.entities.Product;


@SpringBootApplication
public class ECommerceLiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceLiteApplication.class, args);
	}
	
	@Bean
	CommandLineRunner start(CategoryRepository categoryRepository, ProductRepository productRepository) {
	    AtomicInteger counterCat = new AtomicInteger(1); 
	    AtomicInteger counterPro = new AtomicInteger(1); 

		return args ->{
			productRepository.deleteAll();
			categoryRepository.deleteAll();
			
			Stream.of("Ordinateurs", "Imprimantes").forEach(c ->{
				categoryRepository.save(new Category("C"+counterCat.getAndIncrement(), c, new ArrayList<Product>()));
			});
			categoryRepository.findAll().forEach(System.out::println);
			
			Category c1 = categoryRepository.findById("C1").get();
			Stream.of("P1", "P2", "P3", "P4").forEach(name ->{
				Product p = productRepository.save(new Product("P"+counterPro.getAndIncrement(), name, Math.random()*1000, c1));
				c1.getProducts().add(p);
				categoryRepository.save(c1);
			});
			
			Category c2 = categoryRepository.findById("C2").get();
			Stream.of("P5", "P6", "P7").forEach(name ->{
				Product p = productRepository.save(new Product("P"+counterPro.getAndIncrement(), name, Math.random()*1000, c2));
				c2.getProducts().add(p);
				categoryRepository.save(c2);
			});
			productRepository.findAll().forEach(p->System.out.println(p.toString()));
		
		};
	}

}

