package prueba;

import controladores.ControladorLogin;
import modelo.*;
import persistencia.IPersistencia;
import persistencia.PersistenciaBIN;
import vistas.FormPassword;
import vistas.VistaLogin;

import java.io.IOException;
import java.util.Calendar;
import java.util.TreeSet;

public class Main {

    public Main() {
    }

    public static void main(String[] args) {
        Empresa cerveceria=null;

        try {
            IPersistencia persistencia=new PersistenciaBIN();

            try {
                persistencia.abrirInput("Cerveceria.bin");
                System.out.println("Archivo abierto");
                cerveceria=(Empresa) persistencia.leer();
                persistencia.cerrarInput();
            }catch (IOException e){
                System.out.println(e.getMessage());
            }

            TreeSet<Mozo> mozos = cerveceria.getMozos();
            TreeSet<Producto> productos = cerveceria.getProductos();
            TreeSet<Operario> operarios = cerveceria.getOperarios();
            TreeSet<Mesa> mesas=cerveceria.getMesas();

            operarios.add(new Operario("SUAREZ","IGNACIO","123",true));

            System.out.println(operarios.toString());

            VistaLogin vista = new VistaLogin();
            Empresa empresa = new Empresa("CerveceriaProgra",mozos,mesas,productos,operarios);

            System.out.printf(empresa.getOperarios().first().toString());
            FormPassword form = new FormPassword();
            ControladorLogin controlador = new ControladorLogin(vista,empresa,form);
            vista.ejecutar();


            try {
                persistencia.abrirOutput("Cerveceria.bin");
                System.out.println("Crea archivo escritura");
                System.out.println(empresa);
                persistencia.escribir(empresa);
                System.out.println("Empresa grabado exitosamente");
                persistencia.cerrarOutput();
                System.out.println("Archivo cerrado");
            }
            catch (IOException e){
                System.out.println(e.getLocalizedMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
