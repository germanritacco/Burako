package ar.edu.unlu.poo.burako.serializacion;

import java.io.*;
import java.util.ArrayList;

public class Serializador {
    private String fileName;

    public Serializador(String fileName) {
        super();
        this.fileName = fileName;
    }

    public boolean writeOneObject(Object obj) {
        boolean respuesta = false;
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName));
            oos.writeObject(obj);
            oos.close();
            respuesta = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    public boolean addOneObject(Object obj) {
        boolean respuesta = false;
        try {
            AddableObjectOutputStream oos = new AddableObjectOutputStream(new FileOutputStream(fileName, true));
            oos.writeObject(obj);
            oos.close();
            respuesta = true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    public Object readFirstObject() {
        Object respuesta = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(fileName));
            respuesta = ois.readObject();
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return respuesta;
    }

    public Object[] readObjects() {
        Object[] respuesta;
        ArrayList<Object> listOfObject = new ArrayList<>();
        File file = new File(fileName);
        // Verificar si el archivo existe antes de intentar leerlo
        if (!file.exists()) {
            System.out.println("El archivo no existe: " + fileName);
            return null;
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            while (true) {
                try {
                    Object r = ois.readObject();
                    listOfObject.add(r);
                } catch (EOFException e) {
                    break;  // Fin del archivo, se rompe el bucle
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Archivo no encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error de entrada/salida: " + e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Clase no encontrada: " + e.getMessage());
        }
        return listOfObject.isEmpty() ? null : listOfObject.toArray();
    }

}
