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
public class ServletControlador extends HttpServlet {

    /* 
        Este método se va a encargar de recuperar el estado de clientes, los objetos de tipo clientes 
        y los va compartir con uno de los alcances disponibles, para este caso se va a utilizar request
        y posteriormenete se va a hacer redirect hacia la página clientes.jsp
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // this.accionDefault(request, response);  //se manda a llamar al método accionDefault
        String accion = request.getParameter("accion");  
        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarCliente(request, response);
                    break;
                case "eliminar":
                    this.eliminarCliente(request,response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {    // en caso de que la acción sea igual a null
            this.accionDefault(request, response);
        }
    }

    /* 
        Se corto el fragmento de código anterior por que estos scripts van a ser utilizados en otros métodos y para 
        que no haya redundancia se crea mejor otro método
     */
    private void accionDefault(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientes = new ClienteDaoJDBC().listar();
        System.out.println("clientes = " + clientes);
        HttpSession sesion = request.getSession();                                   // comoel alcance request no funciona se debe utilizar el sesion para que estos valores que se envia se puedan utilizar de forma mas eficiente
        sesion.setAttribute("clientes", clientes);                                  // con el alcance request se extiende el listado de clientes
        sesion.setAttribute("totalClientes", clientes.size());                     // este dato se va a mandar hacia las vistas para obtener el total de clientes registrados
        sesion.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));     // este dato se va a mandar hacia las vistas para obtener el saldoTotal de todos los clientes
        // request.getRequestDispatcher("clientes.jsp").forward(request, response);   se envia la información al archivo clientes.jsp // cuando se produce la acción de insertar la información se queda ahí y en caso de que se recarge la página la información se vuelve a insertar en la base de datos y produce un doble registro(para mas informacion ver el curso Academia java - De cero a experto con Ejercicios, Sección 66 Servlets y JSP´s: Proyecto Final Control Clientes, video número 254 minuto 17)
        response.sendRedirect("clientes.jsp");
    }

    /* método para calcular el saldo total de todos los clientes registrados */
    private double calcularSaldoTotal(List<Cliente> clientes) {
        double saldoTotal = 0;
        for (Cliente cliente : clientes) {  // itera el saldo de cadacliente y lo suma y total final se almacena en la variable saldoTotal
            saldoTotal += cliente.getSaldo();
        }
        return saldoTotal;
    }
    
    private void editarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));      // recuperamos e idCliente de la tabla donde estan todos los registros de clientes
        Cliente cliente = new ClienteDaoJDBC().encontrar(new Cliente(idCliente));
        request.setAttribute("cliente", cliente);
        String jspEditar = "/WEB-INF/paginas/cliente/editarCliente.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }
    
    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));    // recuperamos los valores del formulario editarCliente
        Cliente cliente = new Cliente(idCliente);                               // creamos el objeto de cliente
        
        int registroEliminado = new ClienteDaoJDBC().eliminar(cliente);         // eliminamos el objeto en la base de datos
        System.out.println("Registro Eliminado: " + registroEliminado);
        
        this.accionDefault(request, response);                                  // redirigimos hacia la acción por default
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String accion = request.getParameter("accion");   // para obtener el parámetro obtenido desde los formularios de inserción y actualización
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarCliente(request, response);
                    break;
                case "modificar":
                    this.modificarCliente(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {    // en caso de que la acción sea igual a null
            this.accionDefault(request, response);
        }
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // recuperamos los valores del formulario agregarCliente
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldo = 0;                                   // en caso de que no se envie ningun valor se ponga 0 por defecto
        String saldoString = request.getParameter("saldo");

        if (saldoString != null & !"".equals(saldoString)) {
            saldo = Double.parseDouble(saldoString);
        }

        Cliente cliente = new Cliente(nombre, apellido, email, telefono, saldo);   // creamos el objeto de cliente (modelo)

        int registrosInsertados = new ClienteDaoJDBC().insertar(cliente);   // insertamos el nuevo objeto en la base de datos
        System.out.println("Registros Insertados: " + registrosInsertados); // la variable no era necesario definirla pero es para ver por consola los registros insertados

        this.accionDefault(request, response);  // redirigimos hacia la acción por default
    }
    
    private void modificarCliente(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        int idCliente = Integer.parseInt(request.getParameter("idCliente")); // recuperamos los valores del formulario editarCliente
        String nombre = request.getParameter("nombre");         
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldo = 0;                                   // en caso de que no se envie ningun valor se ponga 0 por defecto
        String saldoString = request.getParameter("saldo");
        
        if (saldoString != null & !"".equals(saldoString)) {
            saldo = Double.parseDouble(saldoString);
        }
        Cliente cliente = new Cliente(idCliente, nombre, apellido, email, telefono, saldo);  // se crea el objeto de cliente (modelo)
        
        int registroModificado = new ClienteDaoJDBC().actualizar(cliente);
        System.out.println("Registro Modificado: " +registroModificado);
        
        this.accionDefault(request, response);  // redirigimos hacia la acción por default
    }
}
