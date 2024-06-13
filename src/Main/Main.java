package Main;


import DataBaseConnection.OracleConnection;
import Menu.Menu;
import Others.Util;

import java.util.Map;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) {
     Menu menu = new Menu();
     menu.menu();
//       OracleConnection db = new OracleConnection(
//                System.getenv("DB_HOST"),
//                Integer.parseInt(System.getenv("DB_PORT")),
//                System.getenv("DB_NAME"),
//                System.getenv("DB_USR"),
//                System.getenv("DB_PASS"));
//        db = db.connect();
//
//        //Instancias un objeto tipo map, Map<String, Integer>. Se iguala a lo que devuelve la funcion
//        //Ya sea tipo cliente o tipo de lavado
//        Map<String, Integer> x = db.tipoLavado();
//        //Con Util.convertMap(x); lo conviertes a un vector string la llave(Es como un JSON)
//        //Esto para imprimirlo en pantalla y tener las llaves para buscar despues su valor
//        String[] s = Util.convertMap(x);
//
//        //Aqui se obtiene el valor, del cual es el que se guardara en los objetos
//        Integer p = x.get(s[0]);
//
//
//        System.out.println(s[0]);
    }
}