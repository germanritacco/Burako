package ar.edu.unlu.poo.burako.juego;

public class Ficha {

    protected ColorFicha color;

    protected Integer numeroFicha;

    protected Integer valorFicha;

    public Ficha(ColorFicha color, Integer numeroFicha) {
        this.color = color;
        valorFicha = Valor(numeroFicha);
        this.numeroFicha = numeroFicha;
    }

    public Ficha() {
    }

    public Integer Valor(Integer ficha) {
        Integer ValorFinal = 0;
        switch (ficha) {
            case 1 -> ValorFinal = 15;
            case 2 -> ValorFinal = 20;
            case 3, 4, 5, 6, 7 -> ValorFinal = 5;
            case 8, 9, 10, 11, 12, 13 -> ValorFinal = 10;
        }
        return ValorFinal;
    }

}
