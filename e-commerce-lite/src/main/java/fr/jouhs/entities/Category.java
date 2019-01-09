package fr.jouhs.entities;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data @AllArgsConstructor @NoArgsConstructor
public class Category {
	@Id
	private String id; // souvent le id en mongodb est de type String
	private String name;
	
	@DBRef
	private Collection<Product> products = new ArrayList<Product>(); // Au moment de la creation de la catégorie, la liste est vide

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + "]";
	}
	
	
}
