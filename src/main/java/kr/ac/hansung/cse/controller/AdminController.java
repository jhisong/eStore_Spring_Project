package kr.ac.hansung.cse.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import kr.ac.hansung.cse.model.Product;
import kr.ac.hansung.cse.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping
	public String adminPage(){
		return "admin";
	}
	
	@RequestMapping("/productInventory")
	public String getProducts(Model model){ // controller => model
		List<Product> products = productService.getProducts();
		
		model.addAttribute("products", products);
		
		return "productInventory";
	}
	
	@RequestMapping(value = "/productInventory/addProduct", method=RequestMethod.GET)
	public String addProduct(Model model){ // controller => model
		Product product = new Product();
		product.setCategory("컴퓨터");
		
		model.addAttribute("product",product);
		
		return "addProduct";
	}
	
	@RequestMapping(value="/productInventory/addProduct", method=RequestMethod.POST)
	public String addProductPost(@Valid Product product, BindingResult result, HttpServletRequest request){ // controller => model
		
		if(result.hasErrors()){
			System.out.println("Form data has some errors");
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError error:errors){
				System.out.print(error.getDefaultMessage());
			}
			
			return "addProduct";
		}
		
		MultipartFile productImage = product.getFile();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + productImage.getOriginalFilename());
		
		if(productImage.isEmpty() == false){
			System.out.println("------------file start---------------");
			System.out.println("name : " + productImage.getName());
			System.out.println("filename : " +  productImage.getOriginalFilename());
			System.out.println("size : " + productImage.getSize());
			System.out.println("savePath : " + savePath);
			System.out.println("------------file end ----------------");
			//FileCopyUtils.copy(product.getFile().getBytes(), new File("C:/Users/Park/dev/workspace2/eStore/src/main/webapp/resources/images/"+product.getFile().getOriginalFilename()));
		}
		
		if(productImage != null && !productImage.isEmpty()){
			try {
				productImage.transferTo(new File(savePath.toString()));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		product.setImageName(productImage.getOriginalFilename());
		
		productService.addProduct(product);
		
		return "redirect:/admin/productInventory";
	}
	
	@RequestMapping(value="/productInventory/deleteProduct/{id}")
	public String deleteProduct(@PathVariable int id, HttpServletRequest request){ // controller => model
		Product product = productService.getProductsById(id);
		
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + product.getImageName());
		
		if(Files.exists(savePath)){
			try {
				Files.delete(savePath);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		productService.deleteProduct(product);
		return "redirect:/admin/productInventory";
	}
	
	@RequestMapping(value="/productInventory/updateProduct/{id}", method=RequestMethod.GET)
	public String updateProduct(@PathVariable int id,Model model){ // controller => model
		
		Product product = productService.getProductsById(id);
		
		model.addAttribute("product",product);
		
		return "updateProduct";
		
	}
	
	@RequestMapping(value="/productInventory/updateProduct", method=RequestMethod.POST)
	public String updateProductPost(@Valid Product product, BindingResult result , HttpServletRequest request){ // controller => model
		
		if(result.hasErrors()){
			System.out.println("Form data has some errors");
			List<ObjectError> errors = result.getAllErrors();
			
			for(ObjectError error:errors){
				System.out.print(error.getDefaultMessage());
			}
			
			return "updateProduct";
		}
		
		MultipartFile productImage = product.getFile();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
		Path savePath = Paths.get(rootDirectory + "\\resources\\images\\" + productImage.getOriginalFilename());
		
		if(productImage.isEmpty() == false){
			System.out.println("------------file start---------------");
			System.out.println("name : " + productImage.getName());
			System.out.println("filename : " +  productImage.getOriginalFilename());
			System.out.println("size : " + productImage.getSize());
			System.out.println("savePath : " + savePath);
			System.out.println("------------file end ----------------");
			//FileCopyUtils.copy(product.getFile().getBytes(), new File("C:/Users/Park/dev/workspace2/eStore/src/main/webapp/resources/images/"+product.getFile().getOriginalFilename()));
		}
		
		if(productImage != null && !productImage.isEmpty()){
			try {
				productImage.transferTo(new File(savePath.toString()));
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		product.setImageName(productImage.getOriginalFilename());
		
		productService.updateProduct(product);
		
		return "redirect:/admin/productInventory";
		
	}
}
