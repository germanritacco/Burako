package ar.edu.unlu.poo.burako.modelo;

public interface Observable {

    void notificarObservadores();

    void addObserver(Observer observador);

}
