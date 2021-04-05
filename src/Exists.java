
import java.util.Scanner;
import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQExpression;
import javax.xml.xquery.XQResultSequence;
import net.xqj.exist.ExistXQDataSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author elena
 */
public class Exists {

    private XQConnection createConnection() {

        XQConnection conexion = null;
        try {
            XQDataSource recurso = new ExistXQDataSource();
            recurso.setProperty("serverName", "localhost");
            recurso.setProperty("port", "8081");
  

            //Configurados puerto y servidor , creamos conexi�n.
            conexion = recurso.getConnection("admin", "");
        } catch (XQException e) {
            e.printStackTrace();
        }
        return conexion;
    }

    public static void main(String[] args) {
        
        Exists exist = new Exists();
        XQConnection connection = exist.createConnection();

        if (connection == null) {
            throw new IllegalArgumentException("Fallo al conectar con eXist. Los datos de conexión no son válidos");
        }

        String consulta = "for $libro in /bib/libro return $libro/titulo";

        try {
            XQExpression query = connection.createExpression();
            XQResultSequence result = query.executeQuery(consulta);

            System.out.println("******LIBROS*******\n");

            while (result.next()) {
                System.out.println(result.getItemAsString(null));
            }

        } catch (XQException e) {
            e.printStackTrace();
        }
    }

}
