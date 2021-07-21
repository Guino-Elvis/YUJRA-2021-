package pe.edu.upeu.app;

import java.io.Console;

import pe.edu.upeu.dao.CategoriaDao;
import pe.edu.upeu.dao.ClienteSansung;
import pe.edu.upeu.dao.ColorDeCelularSansung;
import pe.edu.upeu.dao.ModeloDeSansung;
import pe.edu.upeu.dao.ProductoDao;
import pe.edu.upeu.dao.UsuarioDao;
import pe.edu.upeu.dao.VentaDao;
import pe.edu.upeu.gui.MainGUI;
import pe.edu.upeu.modelo.CategoriaTO;
import pe.edu.upeu.modelo.ProductoTO;
import pe.edu.upeu.util.LeerTeclado;
import pe.edu.upeu.util.UtilsX;
import org.fusesource.jansi.Ansi;
import org.fusesource.jansi.AnsiConsole;
import static org.fusesource.jansi.Ansi.*;
import static org.fusesource.jansi.Ansi.Color.*;
/**
 * Hello world!
 *
 */
public class App {

    public static void registrarCategoria(CategoriaTO categ) {
        System.out.println("------------Registro de Categoria de Productos---------");
        System.out.println("IDCateg:"+categ.getIdCateg()+"\tNombre:"+categ.getNombre());
    }

    public static void menuMain(){
        String mensaje="Seleccion el algoritmo que desea Ejecutar:"+
        "\n1=Registrar Categoria"+
        "\n5=Reportar Categoria "+
        "\n6=Registrar Productos"+
        "\n7=Reportar Producto"+
        "\n8=Realizar Venta"+
        "\n9=Reporte de Venta en Rango de Fechas"+
        "\n10=Registrar Usuario"+
        "\n11=Registrar cliente"+
        "\n12=Registrar color"+
        "\n13=Registrar modelo de celular "+
        "\n0=Salir del programa";
        LeerTeclado lt=new LeerTeclado(); 
        UtilsX ut=new UtilsX();
        CategoriaDao daoC;       
        UsuarioDao daoUso;
        ProductoDao proDao;
        VentaDao venDao;
        ClienteSansung cliTO;
        ColorDeCelularSansung colTO;
        ModeloDeSansung modTO;
        int opcion=0;
        opcion=lt.leer(0, mensaje);
        do{            
            switch(opcion){
                case 1:                
                daoC=new CategoriaDao(); daoC.crearCategoria(); 
                ut.clearConsole(); break;
                case 5: 
                ut.clearConsole();
                daoC=new CategoriaDao(); daoC.reporteCategoria(); break;    
                case 6: proDao=new ProductoDao(); proDao.crearProducto(); break;
                case 7: proDao=new ProductoDao(); proDao.reporteProducto(); break;
                case 8: venDao=new VentaDao(); venDao.registroVentaGeneral(); break;
                case 9: venDao=new VentaDao(); venDao.reporteVentasRangoFecha(); break;
                case 10: daoUso=new UsuarioDao(); daoUso.crearNuevoUsuario(); break;
                case 11: cliTO=new ClienteSansung(); cliTO.RegistrarCliente(); break;
                case 12: colTO=new ColorDeCelularSansung(); colTO.registrarColor(); break;
                case 13: modTO=new ModeloDeSansung(); modTO.registrarModelo(); break;
                case 15: break;
                default: System.out.println("La opcion que eligio no exuiste!");
                break;
            }
            if(opcion!=0){
                if(lt.leer("", "\nDesea seguir probando SI=S/NO=N:").toUpperCase().charAt(0)=='S'){
                    opcion=lt.leer(0, mensaje);
                }else{
                    opcion=0;
                }                
            }
            
        }while(opcion!=0);        
    }

    public static void validarAcceso() {
        LeerTeclado lt=new LeerTeclado(); 
        Console  constx= System.console();
        String usuario=lt.leer("", "Ingrese su usuario:");
        System.out.println("Ingrese su clave:");
        char[] clave=constx.readPassword();
        UsuarioDao usuDao=new UsuarioDao();
        if(usuDao.login(usuario, clave)){
            menuMain(); 
        }else{
            System.out.println("Intente Nuevamente!!");
            validarAcceso();
        }
    }


    public static void main( String[] args ){
        AnsiConsole.systemInstall();
        Ansi colorX=new Ansi();
        System.out.println(colorX.bgBrightGreen().fgBlue().a("***************Ingreso al Sistema***********").reset());
        //AnsiConsole.systemInstall();
        //System.out.println(colorX.render("@|red Hello|@ gggg @|green World|@") );
        validarAcceso();       
        //menuMain(); 
        //new MainGUI();
    }
}
