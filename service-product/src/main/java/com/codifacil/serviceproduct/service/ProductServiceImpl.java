package com.codifacil.serviceproduct.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.codifacil.serviceproduct.entity.Category;
import com.codifacil.serviceproduct.entity.Product;
import com.codifacil.serviceproduct.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	//@Autowired
	private final ProductRepository productRepository;

	@Override
	public List<Product> listAllProducts() {
		// TODO Auto-generated method stub
		return productRepository.findAll();
	}

	@Override
	public Product getProduct(Long id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).orElse(null);
	}

	@Override
	public Product createProduct(Product product) {
		// TODO Auto-generated method stub
		product.setStatus("CREATED");
		product.setCreateAt(new Date());
		
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		// TODO Auto-generated method stub
		Product productDB = getProduct(product.getId());
		
		if(productDB == null) return null;
		
		//no actualizaremos el producto que nos envian del cliente
		//actualizamos el producto que obtenemos de la BD
		productDB.setName(product.getName());
		productDB.setDescription(product.getDescription());
		productDB.setCategory(product.getCategory());
		//productDB.setStatus(product.getStatus());
		productDB.setPrice(product.getPrice());
		return productRepository.save(productDB);
	}

	@Override
	public Product deleteProduct(Long id) {
		// TODO Auto-generated method stub
		Product productDB = getProduct(id);
		
		if(productDB == null) return null;
		
		productDB.setStatus("DELETED");
		
		return productRepository.save(productDB);
	}

	@Override
	public List<Product> findByCategory(Category category) {
		// TODO Auto-generated method stub
		return productRepository.findByCategory(category);
	}

	@Override
	public Product updateStock(Long id, Double quantity) {
		// TODO Auto-generated method stub
		Product productDB = getProduct(id);
		
		if(productDB == null) return null;
		
		Double stock = productDB.getStock() + quantity;
		productDB.setStock(stock);
		
		return productRepository.save(productDB);
	}

}
