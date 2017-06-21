package minigestorventas.sessions;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import minigestorventas.entities.Vendedores;

@Stateless
public class VendedoresFacade extends AbstractFacade<Vendedores> {

    @PersistenceContext(unitName = "MiniGestorDeVentasPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VendedoresFacade() {
        super(Vendedores.class);
    }

}
