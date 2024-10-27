<section id="actions" class="py-4 mb-4 bg-light">
    <div class="container">
        <div class="row">
            <div class="col-md-4">
                <a href="index.jsp" class="btn btn-primary btn-block">
                    <i class="fa-solid fa-circle-chevron-left"></i> Regresar al Inicio
                </a>
            </div>
            <div class="col-md-4">
                <button type="submit" class="btn btn-success btn-block">
                    <i class="fa-solid fa-user-check"></i> Actualizar Cliente
                </button>
            </div>
            <div class="col-md-4">
                <a href="${pageContext.request.contextPath}/ServletControlador?accion=eliminar&idCliente=${cliente.idCliente}" class="btn btn-danger btn-block">
                    <i class="fa-solid fa-user-xmark"></i> Eliminar Cliente
                </a>
            </div>
        </div>
    </div>
</section>