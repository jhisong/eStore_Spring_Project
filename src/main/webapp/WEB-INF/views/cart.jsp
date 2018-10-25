<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script src="<c:url value="/resources/js/controller.js" />"></script>


<div class="container-wrapper">
	<div class="container">
		<div class="jumbotron">
			<div class="container">
				<h2>Cart</h2>
				<p>All the selected products in your shopping cart</p>
			</div>
		</div>
		
		<section class="container" ng-app="cartApp">
			<div ng-controller="cartCtrl" ng-init="initCartId('${cartId }')">
				<a class="btn btn-warning pull-left" ng-click="clearCart()" style="color:white;">
					<i class="fa fa-trash"></i> Clear Cart
				</a>
				<br/>
				
				<table class="table table-hover">
					<tr>
						<th>Product</th>
						<th>Unit Price</th>
						<th>Quantity</th>
						<th>Total Price</th>
						<th>Action</th>
						<th></th>
					</tr>
					
					<tr ng-repeat="item in cart.cartItems">
						<td>{{item.product.name}}</td>
						<td>{{item.product.price}}</td>
						<td>{{item.quantity}}</td>
						<td>{{item.totalPrice}}</td>
						<td colspan="2"><div class="container">
							<a class="col-md-4 btn btn-danger" style="color:white;" ng-click="removeFromCart(item.product.id)">
							<i class="fa fa-times"></i>&nbsp;remove</a>
							
							<a class="col-md-4 btn btn-danger" style="color:white;" ng-click="plusToCart(item.product.id)">
							<i class="fa fa-plus"></i>&nbsp;plus</a>
							
							<a class="col-md-4 btn btn-danger" style="color:white;" ng-click="minusToCart(item.product.id)">
							<i class="fa fa-minus"></i>&nbsp;minus</a>
							</div>
						</td>
					</tr>
					
					
					<tr>
						<td></td>
						<td></td>
						<td>Grand Total</td>
						<td>{{calGrandTotal()}}</td>
						<td></td>
						<td></td>
					</tr>
				</table>
				
				<a class="btn btn-info" href="<c:url value="/products"/>" class="btn btn-default">Continue Shopping</a>
				
			</div>
		</section>
		
	</div>
</div>