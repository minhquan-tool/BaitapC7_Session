<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Your cart</title><link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css"></head>
<body><main><h1>Your cart</h1>
<c:if test="${not empty error}"><p role="alert" class="error"><c:out value="${error}"/></p></c:if>
<c:choose><c:when test="${empty cart.items}"><p>Your cart is empty.</p></c:when><c:otherwise>
<div class="table-scroll"><table>
<thead><tr><th scope="col">Quantity</th><th scope="col">Description</th><th scope="col" class="right">Price</th><th scope="col" class="right">Amount</th><th scope="col"><span class="visually-hidden">Action</span></th></tr></thead>
<tbody><c:forEach var="item" items="${cart.items}"><tr>
<td><form class="quantity-form" action="${pageContext.request.contextPath}/cart" method="post">
<input type="hidden" name="action" value="update"><input type="hidden" name="productCode" value="${item.product.code}">
<label class="visually-hidden" for="qty-${item.product.code}">Quantity for <c:out value="${item.product.description}"/></label>
<input id="qty-${item.product.code}" type="number" name="quantity" min="0" max="999" step="1" required value="${item.quantity}"><button type="submit">Update</button>
</form></td><td><c:out value="${item.product.description}"/></td><td class="right">${item.product.priceCurrencyFormat}</td><td class="right">${item.totalCurrencyFormat}</td>
<td><form action="${pageContext.request.contextPath}/cart" method="post"><input type="hidden" name="action" value="remove"><input type="hidden" name="productCode" value="${item.product.code}"><button type="submit">Remove Item</button></form></td>
</tr></c:forEach></tbody></table></div>
<p><strong>To change the quantity,</strong> enter the new quantity and click on the Update button.</p>
</c:otherwise></c:choose>
<p class="actions"><a class="button" href="${pageContext.request.contextPath}/products">Continue Shopping</a>
<c:if test="${not empty cart.items}"><a class="button" href="${pageContext.request.contextPath}/cart?action=checkout">Checkout</a></c:if></p>
</main></body></html>
