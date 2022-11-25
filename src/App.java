import java.io.FileInputStream;
import java.sql.SQLOutput;
import java.util.Properties;

import model.Previsao;
import service.PrevisaoService;

import javax.swing.JOptionPane;
import static java.lang.Integer.parseInt;
import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;

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

        
        //service.obterPrevisoesWeatherMap(
           // WEATHER_MAP_BASEURL, 
           // WEATHER_MAP_APPID, 
            //"Itu",
          //  WEATHER_MAP_UNITS);

        int op;
        String cidade = null;
        String menu =
                "1-Pesquisar a cidade \n2- Histórico de pesquisa\n0-Sair";
        do{
            op = parseInt(showInputDialog(menu));

            switch(op){

                case 1:{
                cidade = JOptionPane.showInputDialog("Digite o nome da cidade");

                    Previsao p = new Previsao(cidade);
                    service.obterPrevisoesWeatherMap(WEATHER_MAP_BASEURL,WEATHER_MAP_APPID,cidade,WEATHER_MAP_UNITS);

                    break;
                }
                  case 2:{
                //mostrar histórico de pesquisa
                      // JOptionPane.showMessageDialog(null, service.armazenarPrevisaoOracleCloud() );
                      Previsao previsao = new Previsao(cidade);
                      JOptionPane.showInputDialog(null,"Armazenado" );
                      service.armazenarPrevisaoOracleCloud(previsao);
                      break;
                  }
                  case 0:{
                      showMessageDialog(null, "Até mais");
                      break;
                  }
                  default:{
                      showMessageDialog(null, "Opção inválida");
                      break;
                  }
              }

          }while (op != 0);


    }
}
