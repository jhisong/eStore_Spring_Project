package kr.ac.hansung.cse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.ac.hansung.cse.dao.ProductDAO;
import kr.ac.hansung.cse.model.Product;

@Service
public class ProductService {

	@Autowired
	private ProductDAO productDAO; 
	
	public Product getProductsById(int id) {
		return  productDAO.getProductById(id);
	}
	
	public List<Product> getProducts() {
		
		return productDAO.getProducts();
	}

	public void addProduct(Product product) {
		
		productDAO.addProduct(product);
		
	}

	public void deleteProduct(Product product) {
		productDAO.deleteProduct(product);
	}

	public void updateProduct(Product product) {
		productDAO.updateProduct(product);
	}

}
