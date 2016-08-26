package minigestorventas.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("agregarVentasController")
@SessionScoped
public class AgregarVentasController implements Serializable {

    private String nroVenta;
    private String fechaVenta;
    private Connection con = null;

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

}
