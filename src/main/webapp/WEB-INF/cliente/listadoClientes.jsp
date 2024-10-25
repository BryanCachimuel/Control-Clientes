<!-- Como que se quiere que la información que se obtiene de la base de datos sea privada se va a 
 obtener desde aquí la información ya que todo archivo o directorio aquí es privado y no se ve desde
el navegador desde donde se extrae la información respectiva
-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>  <!-- libreria para dar formato a un número -->
<fmt:setLocale value="es_EC"/>                                    <!-- localidad para el formato del número -->

<section id="clientes">
    <div class="container">
        <div class="row">
            <div class="col-md-9">
                <div class="card">
                    <div class="card-header">
                        <h4>Listado de Clientes</h4>
                    </div>
                    <table class="table table-striped">
                        <thead class="thead-dark">
                            <tr>
                                <th>Id</th>
                                <th>Nombre</th>
                                <th>Saldo</th>
                                <th></th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Iteramos cada elemento de la lista de los clientes -->
                            <c:forEach var="cliente" items="${clientes}">
                                <tr>
                                    <td>${cliente.idCliente}</td>
                                    <td>${cliente.nombre}  ${cliente.apellido}</td>
                                    <td> <fmt:formatNumber value="${cliente.saldo}" type="currency"/>  </td> <!-- type="currency" -> formato moneda -->
                                    <td>
                                        <a href="${pageContext.request.contextPath}/ServletControlador?accion=editar&idCliente=${cliente.idCliente}" class="btn btn-info"><i class="fa-solid fa-pen-to-square"></i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
           
            
        </div>
    </div>
</section>