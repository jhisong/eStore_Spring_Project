<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="<c:url value="/resources/js/controller.js" />"></script>

<div class="container-wrapper">

	<div class="container" ng-app ="cartApp">
		<h2>Product Detail</h2>
		<p class="lead">Here is the detail information of the product!</p>
		<div class="row" ng-controller="cartCtrl">
			<div class="col-md-6">
				<c:set var="imageFilename"
					value="/resources/images/${product.imageName}" />
				<img src="<c:url value="${imageFilename}" />" alt="image"
					style="width: 80%" />
			</div>
			<div class="col-md-6">
				<h3>${product.name }</h3>
				<p> <strong>Description :</strong> ${product.description }</p>
				<p> <strong>Manufacturer :</strong> ${product.manufacturer }</p>
				<p> <strong>Category :</strong> ${product.category }</p>
				<p> <strong>Price :</strong> ${product.price }</p>

				<br />

				<c:if test="${pageContext.request.userPrincipal.name !=null }">
					<p>
						<a href="<c:url value="/products" />" class="btn btn-danger">Back</a>

						<button class="btn btn-warning btn-large"
							ng-click="addToCart('${product.id}')">
							<i class="fa fa-shopping-cart"></i>Order Now
						</button>

						<a href="<c:url value="/cart"/>" class="btn btn-info"> <i
							class="fa fa-eye"></i> View Cart
						</a>
					</p>
				</c:if>

			</div>
		</div>
	</div>
</div>