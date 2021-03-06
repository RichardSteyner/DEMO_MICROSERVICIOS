package com.codifacil.serviceproduct;

import java.util.Date;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.codifacil.serviceproduct.entity.Category;
import com.codifacil.serviceproduct.entity.Product;
import com.codifacil.serviceproduct.repository.ProductRepository;

@DataJpaTest
public class ProductRepositoryMockTest {

	@Autowired
	private ProductRepository productRepository;
	
	@Test
	public void whenFindCategory_thenReturnListProduct() {
		Product pro01 = Product.builder()
				.name("azucar")
				.category(Category.builder().id(1L).build())
				.description("")
				.stock(Double.parseDouble("10"))
				.price(Double.parseDouble("1000"))
				.status("Created")
				.createAt(new Date())
				.build();
		
		productRepository.save(pro01);
		
		List<Product> foundsList = productRepository.findByCategory(pro01.getCategory());
		
		Assertions.assertThat(foundsList.size()).isEqualTo(3);
				
	}
	
}
