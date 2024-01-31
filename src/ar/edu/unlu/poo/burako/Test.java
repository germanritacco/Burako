package ar.edu.unlu.poo.burako;

public class Test {
    public static void main(String[] args) {
        AppServidor.main(new String[]{"127.0.0.1", "8888"});
        AppCliente.main(new String[]{"127.0.0.1", "9999", "127.0.0.1", "8888", "-3", "0"});
        AppCliente.main(new String[]{"127.0.0.1", "9998", "127.0.0.1", "8888", "725", "0"});
    }
}

