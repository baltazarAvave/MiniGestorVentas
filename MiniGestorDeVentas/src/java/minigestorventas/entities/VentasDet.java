/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minigestorventas.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author expsee
 */
@Entity
@Table(name = "ventas_det")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VentasDet.findAll", query = "SELECT v FROM VentasDet v"),
    @NamedQuery(name = "VentasDet.findByCantidad", query = "SELECT v FROM VentasDet v WHERE v.cantidad = :cantidad"),
    @NamedQuery(name = "VentasDet.findByTotalProducto", query = "SELECT v FROM VentasDet v WHERE v.totalProducto = :totalProducto"),
    @NamedQuery(name = "VentasDet.findById", query = "SELECT v FROM VentasDet v WHERE v.id = :id")})
public class VentasDet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "cantidad")
    private Integer cantidad;
    @Column(name = "total_producto")
    private Integer totalProducto;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_ventas", referencedColumnName = "id")
    @ManyToOne
    private Ventas idVentas;
    @JoinColumn(name = "id_producto", referencedColumnName = "id")
    @ManyToOne
    private Productos idProducto;

    public VentasDet() {
    }

    public VentasDet(Integer id) {
        this.id = id;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getTotalProducto() {
        return totalProducto;
    }

    public void setTotalProducto(Integer totalProducto) {
        this.totalProducto = totalProducto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Ventas getIdVentas() {
        return idVentas;
    }

    public void setIdVentas(Ventas idVentas) {
        this.idVentas = idVentas;
    }

    public Productos getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Productos idProducto) {
        this.idProducto = idProducto;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof VentasDet)) {
            return false;
        }
        VentasDet other = (VentasDet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "minigestorventas.entities.VentasDet[ id=" + id + " ]";
    }
    
}
