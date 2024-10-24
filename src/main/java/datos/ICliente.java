package datos;

import dominio.Cliente;
import java.util.List;

public interface ICliente {
    
    public List<Cliente> listar();
    public Cliente encontrar(Cliente cliente); 
    public int insertar(Cliente cliente);
    public int actualizar(Cliente cliente);
    public int eliminar(Cliente cliente);
}
