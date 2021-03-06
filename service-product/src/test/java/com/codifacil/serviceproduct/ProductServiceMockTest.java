package com.codifacil.serviceproduct;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.codifacil.serviceproduct.entity.Category;
import com.codifacil.serviceproduct.entity.Product;
import com.codifacil.serviceproduct.repository.ProductRepository;
import com.codifacil.serviceproduct.service.ProductService;
import com.codifacil.serviceproduct.service.ProductServiceImpl;

@SpringBootTest
public class ProductServiceMockTest {
	
	@Mock
	private ProductRepository productRepository;
	
	private ProductService productService;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
		productService = new ProductServiceImpl(productRepository);
		
		Product pro01 = Product.builder()
				.id(1L)
				.name("azucar")
				.category(Category.builder().id(1L).build())
				.description("")
				.stock(Double.parseDouble("10"))
				.price(Double.parseDouble("1000"))
				.build();
		
		//tenemos que poner los metodos a llamar y que devolveràn
		Mockito.when(productRepository.findById(1L))
		.thenReturn(Optional.of(pro01));
		
		Mockito.when(productRepository.save(pro01)).thenReturn(pro01);
	}
	
	@Test
	public void whenValidGetId_ThenReturnProduct() {
		Product found = productService.getProduct(1L);
		Assertions.assertThat(found.getName()).isEqualTo("azucar");
	}
	
	@Test
	public void whenValidUpdateStock_ThenReturnNewStock() {
		Product newStock = productService.updateStock(1L, Double.parseDouble("3"));
		Assertions.assertThat(newStock.getStock()).isEqualTo(Double.parseDouble("13"));
	}
	

}
