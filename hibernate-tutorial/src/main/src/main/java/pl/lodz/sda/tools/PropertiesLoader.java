package pl.lodz.sda.tools;

import pl.lodz.sda.environment.DB;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    public static String PATH = "connection.properties"; //plik wczytujemy
    Properties prop = new Properties(); // properties to taka mapa z klucz wartosc

    //zaczytujemy plik
    public void init() throws IOException {
        //zaczytywanie strumienia z classLoadera
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(PATH);

        // jezeli zaczytalismy, tzn ze znalezlismy plik
        if (inputStream != null) {
            try {
                // load zaczytuje jako strumien i wczytuje do obiektu properties
                prop.load(inputStream);
            } catch (IOException e) {
            } finally {
                //zamykamy strumien
                inputStream.close();
            }
        } else {
            throw new FileNotFoundException("property file '" + PATH + "' not found in the classpath");
        }
    }

    //wyciagmay wartosc properties po kluczu
    private String getProperty(String key) {
        return prop.getProperty(key);
    }

    //zmapujmy te wartosc na enum DB
    public DB getDb(){
        return  DB.valueOf(getProperty("db.engine"));
    }

}
