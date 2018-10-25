package kr.ac.hansung.cse.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hansung.cse.model.Cart;
import kr.ac.hansung.cse.model.CartItem;

@Repository
@Transactional
public class CartItemDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void addCartItem(CartItem cartItem) {
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(cartItem);
		session.flush();
	}
	
	public void removeCartItem(CartItem cartItem) {
		Session session = sessionFactory.getCurrentSession();
		System.out.println("call removeCartItem.");
		session.delete(cartItem);
		session.flush();
	}
	
	public void removeAllCartItem(Cart cart) {
		List<CartItem> cartItems = cart.getCartItems();
		//eager로 줬기 때문에 읽어올 수 있다. 그게 아니면 저 위에서 cartItem을 따로 얻어와야함
		
		for(CartItem item : cartItems) {
			removeCartItem(item);
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public CartItem getCartItemByProductId(int cartId,int productId) {
		Session session = sessionFactory.getCurrentSession();
		TypedQuery<CartItem> query = session.createQuery("from CartItem where cart.id=? and product.id=?");
		query.setParameter(0, cartId);
		query.setParameter(1, productId);
		
		return (CartItem) query.getSingleResult();
	}
}
