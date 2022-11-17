import java.io.FileInputStream;
import java.util.Properties;

import model.Previsao;
import service.PrevisaoService;

public class App {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/App.properties")); //lê o arquivo propriedades

        final String WEATHER_MAP_BASEURL = 
                    properties.getProperty("WEATHER_MAP_BASEURL");
        final String WEATHER_MAP_APPID = 
                    properties.getProperty("WEATHER_MAP_APPID");
        final String WEATHER_MAP_UNITS =
                    properties.getProperty("WEATHER_MAP_UNITS");

        PrevisaoService service = new PrevisaoService();
    
        Previsao p = new Previsao("Itu");
        service.armazenarPrevisaoOracleCloud(p);
        
        //service.obterPrevisoesWeatherMap(
           // WEATHER_MAP_BASEURL, 
           // WEATHER_MAP_APPID, 
            //"Itu",
          //  WEATHER_MAP_UNITS);
    }
}
