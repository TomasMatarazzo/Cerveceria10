package modelo;

import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

class FacturaTest {
    PromocionTemporal promocionTemporal;
    ProductoEnPromocion productoEnPromocion;
    Empresa empresa;
    Comanda comanda;
    Mozo mozo;
    Pedido pedido;
    Producto producto;
    Mesa mesa;
    String formaPago;
    Factura factura;

    @Nested
    @DisplayName("test factura ")
    class facturaTest {
        @BeforeEach
        void setUp() throws Exception {
            producto = new Producto("coca", 100, 200, 3);
            promocionTemporal = new PromocionTemporal("2X1", "Efectivo", 10, new Date().getDay(), true, true);
            productoEnPromocion = new ProductoEnPromocion(1, producto, "Domingo", true, false, 10, 20, true);
            pedido = new Pedido(producto, 1);
            mesa = new Mesa(3);
            mozo = new Mozo("Matias", new GregorianCalendar(1990, 12, 12), 0, 0);
            comanda = new Comanda(mesa, mozo);
            comanda.agregarPedido(pedido);
            empresa = new Empresa("Cerveceria", null, null, null, null);
            empresa.altaComanda(mesa, mozo, pedido);
            empresa.getPromocionesProductos().add(productoEnPromocion);
            empresa.getPromocionesTemporales().add(promocionTemporal);
        }

        @AfterEach
        void tearDown() {
        }

        /**
         * metodo GENERAR FACTURA clase Empresa
         * <p>
         * PRECONDICIONES
         * Comanda distinto vacio y null
         * <p>
         * Se quiere generar una factura valida
         */
        @Test
        void generarFactura1() {
            double total, total2 = ((pedido.getCantidad() * producto.getPrecioVenta()) / 2.) * 0.9;

            try {
                total = empresa.generarFactura(comanda);

                assertTrue(total == total2, "ERROR AL CALCULAR TOTAL");
            } catch (Exception e) {
                fail("NO DEBERIA LANZAR EXCEPCION");
            }
        }

        /**
         * Se quiere crear una factura con una mesa Libre
         */
        @Test
        void generarFactura2() throws Exception {
            double total;
            mesa.setEstado("Libre");
            try {
                total = empresa.generarFactura(comanda);
                fail("DEBERIA LANZAR EXCEPCION");
            } catch (Exception e) {
                final String msg = "Imposible crear una Factura sobre una mesa Libre";
                assertEquals(msg, e.getMessage());
            }
        }

        /**
         * metodo CREAR FACTURA clase Empresa
         * <p>
         * PRECONDICIONES
         * Comanda distinto vacio y null
         * <p>
         * Se quiere crear una factura valida
         */
        @Test
        void crearFactura1() {
            formaPago = "efectivo";
            try {
                empresa.crearFactura(mesa, formaPago);
                assertTrue(empresa.getComandas().size() == 0, "ERROR AL BORRAR COMANDA");

            } catch (Exception e) {
                fail("NO DEBERIA LANZAR EXCEPCION");
            }
        }
    }
}