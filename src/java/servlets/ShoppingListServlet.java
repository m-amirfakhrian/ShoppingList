package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.User;

/**
 *
 * @author Majid
 */
public class ShoppingListServlet extends HttpServlet {

    //int brLines = 3;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String action = request.getParameter("action");
        if (action != null && action.equals("logout")) {
            session.invalidate();
            session = request.getSession();
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }

        //String username = request.getParameter("username");
       // session.setAttribute("username", username);
        User user;
        if (session.getAttribute("user") != null) {
            user = (User) session.getAttribute("user");

            request.setAttribute("username", user.getUsername());

            ArrayList<String> itemsList = (ArrayList<String>) session.getAttribute("itemsList");

            request.setAttribute("itemsList", itemsList);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        } else {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ArrayList<String> itemsList;
        

        HttpSession session = request.getSession();
        //session.setAttribute("brLines",brLines);
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "register": {
                    String username = request.getParameter("username");
                    User user = new User(username);
                    itemsList = new ArrayList<>();
                    session.setAttribute("user", user);
                    session.setAttribute("itemsList", itemsList);
                    break;
                }
                case "add": {
                    itemsList = (ArrayList) session.getAttribute("itemsList");
                    if (itemsList == null) {
                        itemsList = new ArrayList<>();
                        String itemNew = request.getParameter("itemNew");
                        itemsList.add(itemNew);
                        session.setAttribute("itemsList", itemsList);

                    } else {
                        String itemNew = request.getParameter("itemNew");
                        itemsList.add(itemNew);
                        session.setAttribute("itemsList", itemsList);
                    }
                    break;
                }
                case "delete": {
                    itemsList = (ArrayList) session.getAttribute("itemsList");
                    String itemDelete = request.getParameter("itemDelete");
                    itemsList.remove(itemDelete);
                    session.setAttribute("itemsList", itemsList);
                    break;
                }
            }
            getServletContext().getRequestDispatcher("/WEB-INF/shoppingList.jsp").forward(request, response);
            return;
        }
    }
}
