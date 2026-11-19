package murach.cart;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import murach.business.Cart;
import murach.business.Product;

@WebServlet(urlPatterns = {"/products", "/cart"})
public class CarServlet extends HttpServlet {
    private static final List<Product> PRODUCTS = List.of(
        new Product("8601", "86 (the band) - True Life Songs and Pictures", "14.95"),
        new Product("pf01", "Paddlefoot - The first CD", "12.95"),
        new Product("pf02", "Paddlefoot - The second CD", "14.95"),
        new Product("jr01", "Joe Rut - Genuine Wood Grained Finish", "14.95")
    );

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("/products".equals(request.getServletPath())) {
            request.setAttribute("products", PRODUCTS);
            request.getRequestDispatcher("/WEB-INF/products.jsp").forward(request, response);
            return;
        }
        HttpSession session = request.getSession();
        if (session.getAttribute("cart") == null) { session.setAttribute("cart", new Cart()); }
        request.setAttribute("error", session.getAttribute("cartError"));
        session.removeAttribute("cartError");
        String view = "checkout".equals(request.getParameter("action")) ? "checkout" : "cart";
        request.getRequestDispatcher("/WEB-INF/" + view + ".jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if (action == null) { action = "add"; }
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute("cart");
        if (cart == null) { cart = new Cart(); session.setAttribute("cart", cart); }
        String destination = "/cart";
        try {
            switch (action) {
                case "shop": destination = "/products"; break;
                case "checkout": destination = "/cart?action=checkout"; break;
                case "cancel": session.removeAttribute("cart"); destination = "/products"; break;
                case "add": case "cart": case "update": case "remove":
                    String code = request.getParameter("productCode");
                    Product product = PRODUCTS.stream().filter(p -> p.getCode().equals(code)).findFirst()
                            .orElseThrow(() -> new IllegalArgumentException("Unknown product."));
                    if ("remove".equals(action)) { cart.removeItem(code); }
                    else if ("update".equals(action)) {
                        String quantity = request.getParameter("quantity");
                        if (quantity == null || !quantity.matches("[0-9]{1,3}")) {
                            throw new IllegalArgumentException("Enter a whole quantity from 0 to 999.");
                        }
                        cart.updateItem(code, Integer.parseInt(quantity));
                    } else { cart.addItem(product); }
                    break;
                default: response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Unknown action."); return;
            }
        } catch (IllegalArgumentException ex) {
            session.setAttribute("cartError", ex.getMessage());
        }
        response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + destination));
    }
}
