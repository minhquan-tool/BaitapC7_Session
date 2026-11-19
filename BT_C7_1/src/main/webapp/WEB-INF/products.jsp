<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!doctype html>
<html lang="en">
<head><meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1"><title>CD list</title><link rel="stylesheet" href="${pageContext.request.contextPath}/styles/main.css"></head>
<body><main>
<h1>CD list</h1>
<div class="table-scroll"><table>
<thead><tr><th scope="col">Description</th><th scope="col" class="right">Price</th><th scope="col"><span class="visually-hidden">Action</span></th></tr></thead>
<tbody><c:forEach var="product" items="${products}"><tr>
<td><c:out value="${product.description}"/></td><td class="right">${product.priceCurrencyFormat}</td>
<td><form action="${pageContext.request.contextPath}/cart" method="post">
<input type="hidden" name="action" value="add"><input type="hidden" name="productCode" value="${product.code}"><button type="submit">Add To Cart</button>
</form></td></tr></c:forEach></tbody>
</table></div>
<p><a href="${pageContext.request.contextPath}/cart">View cart</a></p>
</main></body></html>
