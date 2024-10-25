<!-- Modal -->
<div class="modal fade" id="agregarClienteModal" tabindex="-1" aria-labelledby="agregarClienteModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header bg-info text-white">
                <h5 class="modal-title fs-5" id="exampleModalLabel">Agregar Cliente</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>

            <form action="${pageContext.request.contextPath}/ServletControlador?accion=insertar" method="POST" class="was-validated" autocomplete="off">
                <div class="modal-body">
                    <div class="form-group">
                        <label for="nombre">Nombre</label>
                        <input type="text" class="form-control" name="nombre" required/>
                    </div>
                    <div class="form-group mt-3">
                        <label for="apellido">Apellido</label>
                        <input type="text" class="form-control" name="apellido" required/>
                    </div>
                    <div class="form-group mt-3">
                        <label for="email">Email</label>
                        <input type="email" class="form-control" name="email" required/>
                    </div>
                    <div class="form-group mt-3">
                        <label for="telefono">Teléfono</label>
                        <input type="tel" class="form-control" name="telefono" required/>
                    </div>
                    <div class="form-group mt-3 mb-3">
                        <label for="saldo">Saldo</label>
                        <input type="number" step="0.01" class="form-control" name="saldo" required/>
                        <!--<input type="number" step="any" class="form-control" name="saldo"/>-->
                    </div>
                </div>

                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
                    <button type="submit" class="btn btn-primary">Guardar</button>
                </div>
            </form>     
        </div>
    </div>
</div>
