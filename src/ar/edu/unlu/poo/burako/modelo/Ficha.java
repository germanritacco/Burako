package ar.edu.unlu.poo.burako.modelo;

import java.io.Serializable;

public class Ficha implements Serializable {

    private ColorFicha color;

    protected Integer numeroFicha;

    protected Integer valorFicha;

    public ColorFicha getColor() {
        return color;
    }

    public Integer getNumeroFicha() {
        return numeroFicha;
    }

    public Integer getValorFicha() {
        return valorFicha;
    }

    public Ficha(ColorFicha color, Integer numeroFicha) {
        this.color = color;
        valorFicha = Valor(numeroFicha);
        this.numeroFicha = numeroFicha;
    }

    public Ficha() {
    }

    public Integer Valor(Integer ficha) {
        int ValorFinal = 0;
        switch (ficha) {
            case 1 -> ValorFinal = 15;
            case 2 -> ValorFinal = 20;
            case 3, 4, 5, 6, 7 -> ValorFinal = 5;
            case 8, 9, 10, 11, 12, 13 -> ValorFinal = 10;
        }
        return ValorFinal;
    }

    @Override
    public String toString() {
        return numeroFicha.toString() + " " + color;
    }

}
