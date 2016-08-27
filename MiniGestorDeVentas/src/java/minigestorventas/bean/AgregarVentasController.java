package minigestorventas.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import minigestorventas.entities.Clientes;
import minigestorventas.entities.Productos;
import minigestorventas.entities.Vendedores;
import minigestorventas.sessions.ClientesFacade;
import minigestorventas.sessions.ProductosFacade;
import minigestorventas.sessions.VendedoresFacade;

@Named("agregarVentasController")
@SessionScoped
public class AgregarVentasController implements Serializable {

    private String nroVenta;
    private String fechaVenta;
    private Connection con = null;
    private List<Clientes> clientesList = new ArrayList<Clientes>(); //comboClientes
    private List<Vendedores> vendedoresList = new ArrayList<Vendedores>(); //comboVendedores
    private List<Productos> productosList = new ArrayList<Productos>(); //comboProductos

    private Integer idCliente;
    private Vendedores vendedor;
    private Productos producto;
    private int cantidad;

    @EJB
    private ClientesFacade clientesFacade = new ClientesFacade();
    @EJB
    private VendedoresFacade vendedoresFacade = new VendedoresFacade();
    @EJB
    private ProductosFacade productosFacade = new ProductosFacade();

    @PostConstruct
    void initialiseSession() {
        con = DataConnect.getConnection();
        this.cargarVista();
    }

    public void cargarVista() {

        int nuevaSeq = obtenerNroVenta();
        if (nuevaSeq < 10) {
            this.nroVenta = "000" + String.valueOf(nuevaSeq);
        } else if (nuevaSeq < 100) {
            this.nroVenta = "0" + String.valueOf(nuevaSeq);
        } else {
            this.nroVenta = String.valueOf(nuevaSeq);
        }

        Date date = Calendar.getInstance().getTime();
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        String today = formatter.format(date);
        fechaVenta = today;

        this.clientesList = clientesFacade.findAll();
        this.vendedoresList = vendedoresFacade.findAll();
        this.productosList = productosFacade.findAll();
    }

    public int obtenerNroVenta() {
        int ultimoValor = 0;
        try {
            PreparedStatement ps
                    = con.prepareStatement("SELECT last_value FROM ventas_id_seq");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BigDecimal uv = rs.getBigDecimal("last_value");
                ultimoValor = uv.toBigInteger().intValue();
            }
        } catch (SQLException ex) {
            System.out.println("Error al obtener secuencia -->" + ex.getMessage());
        }
        return ultimoValor;
    }
    
    public void agregarProducto() {
//        int i = this.listaDetalle.size() + 1;
//        OrdenTrabajoDet otd = new OrdenTrabajoDet(BigDecimal.valueOf(i), "");
//        this.listaDetalle.add(otd);
    }

    public String getNroVenta() {
        return nroVenta;
    }

    public void setNroVenta(String nroVenta) {
        this.nroVenta = nroVenta;
    }

    public String getFechaVenta() {
        return fechaVenta;
    }

    public void setFechaVenta(String fechaVenta) {
        this.fechaVenta = fechaVenta;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public List<Clientes> getClientesList() {
        return clientesList;
    }

    public void setClientesList(List<Clientes> clientesList) {
        this.clientesList = clientesList;
    }

    public List<Vendedores> getVendedoresList() {
        return vendedoresList;
    }

    public void setVendedoresList(List<Vendedores> vendedoresList) {
        this.vendedoresList = vendedoresList;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public Vendedores getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedores vendedor) {
        this.vendedor = vendedor;
    }

    public List<Productos> getProductosList() {
        return productosList;
    }

    public void setProductosList(List<Productos> productosList) {
        this.productosList = productosList;
    }

    public Productos getProducto() {
        return producto;
    }

    public void setProducto(Productos producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

}
