<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>Checkout</title><link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css"></head>
<body><main><h1>Checkout</h1>
<c:choose><c:when test="${empty cart.items}"><p>Your cart is empty.</p></c:when><c:otherwise>
<div class="table-scroll"><table><thead><tr><th scope="col">Quantity</th><th scope="col">Description</th><th scope="col" class="right">Price</th><th scope="col" class="right">Amount</th></tr></thead>
<tbody><c:forEach var="item" items="${cart.items}"><tr><td>${item.quantity}</td><td><c:out value="${item.product.description}"/></td><td class="right">${item.product.priceCurrencyFormat}</td><td class="right">${item.totalCurrencyFormat}</td></tr></c:forEach></tbody>
<tfoot><tr><th scope="row" colspan="3" class="right">Total:</th><td class="right"><strong>${cart.totalAmountCurrencyFormat}</strong></td></tr></tfoot>
</table></div><p>This is an order summary. No payment has been made.</p>
</c:otherwise></c:choose>
<p class="actions"><a class="button" href="${pageContext.request.contextPath}/products">Continue Shopping</a><a class="button" href="${pageContext.request.contextPath}/cart">Back to cart</a></p>
<form action="${pageContext.request.contextPath}/cart" method="post"><input type="hidden" name="action" value="cancel"><button type="submit">Clear cart and return to menu</button></form>
</main></body></html>
