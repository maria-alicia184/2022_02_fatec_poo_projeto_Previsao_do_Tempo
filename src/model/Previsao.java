package model;

import java.beans.ConstructorProperties;

import javax.management.ConstructorParameters;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@ToString

public class Previsao {
    private final int codigo;
    @Getter
    private final Double temperaturaMinima;
    @Getter
    private final Double temperaturaMaxima;
    @Getter
    private final String cidade;

    private final String data;

    public Previsao(String cidade){
        this(0, 0.0, 0.0, cidade, null);//construtor para cidade
    }


}
