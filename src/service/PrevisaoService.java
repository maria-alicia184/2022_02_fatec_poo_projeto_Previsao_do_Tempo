package service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse.BodyHandlers;

import org.json.JSONArray;
import org.json.JSONObject;

import model.Previsao;

import javax.swing.*;
import java.io.FileInputStream;
import java.util.Properties;

import java.util.Date;
import java.text.SimpleDateFormat;

public class PrevisaoService {
    private HttpClient client = HttpClient.newBuilder().build(); //capaz de enviar requisição
    public void armazenarPrevisaoOracleCloud(Previsao p) throws Exception{
        Properties properties = new Properties(); //lê o arquivo de propriedade p/ link oracle cloud
        properties.load(new FileInputStream("src/App.properties"));
        final String LINK_ORACLE_CLOUD =
                    properties.getProperty("LINK_ORACLE_CLOUD");
                    
        JSONObject pJSON = new JSONObject();
        pJSON.put("cidade", p.getCidade());
        HttpRequest req = HttpRequest.newBuilder().
        POST(BodyPublishers.ofString(pJSON.toString())).
        uri(URI.create(LINK_ORACLE_CLOUD)).
        header("Content-Type", "application/json").
        build();
        System.out.println(pJSON);

    }
    public void obterPrevisoesWeatherMap(
        String url,
        String appid,
        String cidade,
        String units
    ) throws Exception{
        url = String.format("%s?q=%s&appid=%s&units=%s",
        url,
        cidade,
        appid,
        units
        );
        //1. Construir um objeto que representa a requisição
        HttpRequest req = //representa a requisição
            HttpRequest.newBuilder() 
            .uri(URI.create(url)) 
            .build(); 
        //2. Enviar a requisição ao servidor WeatherMap
        var res = client.send(req, BodyHandlers.ofString());
        //3. Mapeamento JSON -> colecao de objetos Java
        JSONObject raiz = new JSONObject(res.body());
        JSONArray list = raiz.getJSONArray("list");
        for(int i =0; i < list.length(); i++){
            JSONObject previsaoJSON = list.getJSONObject(i);
            JSONObject main = previsaoJSON.getJSONObject("main");
            Double temp_min = main.getDouble("temp_min");
            Double temp_max = main.getDouble("temp_max");
            String dt_txt = previsaoJSON.getString("dt_txt");

            Previsao p = new Previsao(0, temp_min, temp_max, cidade, dt_txt);

            System.out.print("\nCidade: " + p.getCidade()+"\n Temperatura maxima: "+ p.getTemperaturaMaxima() + " Temperatura minima: "
                    + p.getTemperaturaMinima() + " Data: " + dt_txt);

                //Date data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dt_txt);
                //String dataFormatada = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(data);
                //p.setData(dataFormatada);
                //p.Data(dataFormatada);



        }
    }
}
