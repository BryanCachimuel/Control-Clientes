package web;

import datos.ClienteDaoJDBC;
import dominio.Cliente;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/*
    Este Servlet va a recuperar el listado de clientes, compartir la información asta un jsp que se va a llamar clientes.jsp
*/

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet{
    
    /* 
        Este método se va a encargar de recuperar el estado de clientes, los objetos de tipo clientes 
        y los va compartir con uno de los alcances disponibles, para este caso se va a utilizar request
        y posteriormenete se va a hacer redirect hacia la página clientes.jsp
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        List<Cliente> clientes = new ClienteDaoJDBC().listar();
        System.out.println("clientes = " + clientes);
        request.setAttribute("clientes", clientes);                               // con el alcance request se extiende el listado de clientes
        request.getRequestDispatcher("clientes.jsp").forward(request, response);  // se envia la información al archivo clientes.jsp
    }
    
}
