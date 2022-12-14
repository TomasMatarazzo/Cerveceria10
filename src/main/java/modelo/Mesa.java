package modelo;

import java.io.Serializable;
public class Mesa implements Comparable<Mesa>, Serializable {
    private static int id;
    private int nro;
    private int cantidadPersonas;
    private String estado;
    private Mozo mozo;
    private int cantComandas;
    private double totalComandas;

    public Mesa( int cantidadPersonas) throws Exception {
        this.nro = Mesa.id++;
        this.setCantidadPersonas(cantidadPersonas);
        this.setEstado("libre");
        this.cantComandas=0;
        this.totalComandas=0;
    }

    public int getCantComandas() {
        return cantComandas;
    }

    public void setCantComandas(int cantComandas) {
        this.cantComandas = cantComandas;
    }

    public double getTotalComandas() {
        return totalComandas;
    }

    public void setTotalComandas(double totalComandas) {
        this.totalComandas = totalComandas;
    }

    public int getNro() {
        return nro;
    }

    public void setNro(int nro) {
        this.nro = nro;
    }

    public int getCantidadPersonas() {
        return cantidadPersonas;
    }

    public void setCantidadPersonas(int cantidadPersonas){
            this.cantidadPersonas = cantidadPersonas;
    }

    public String getEstado() {
        return estado;
    }

    /**
     * @param estado sera una cadena que debera ser "libre" u "ocupada"
     * @throws Exception en caso de que estado no sea "libre" u "ocupada" se lanzara una excepcion
     */
    public void setEstado(String estado) throws Exception {
        if (estado.equalsIgnoreCase("libre") || estado.equalsIgnoreCase("ocupada"))
            this.estado = estado;
        else
            throw new Exception();
    }

    public Mozo getMozo() {
        return mozo;
    }

    public void setMozo(Mozo mozo) throws Exception {
        if (mozo.getEstado() == 0)
            this.mozo = mozo;
        else
            throw new Exception();
    }

    @Override
    public String toString() {
        return
                "Nro de Mesa: " + nro +
                "      Estado='" + estado + '\'';
    }
    public void cerrarMesa() {
        this.estado = "libre";

    }

    /**
     * El metodo permitira ordenar el treeset de acuerdo al numero de mesa
     * @param o debera ser distinto de null
     * @return devuelve 1 en caso de que la mesa instancia sea mayor a la recibida por parametro, 0 en caso contrario
     */
    @Override
    public int compareTo(Mesa o) {
        return (this.getNro() > o.getNro()) ? 1 : 0;
    }
}
