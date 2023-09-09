package com.newgo;

import com.newgo.infrastructure.ServidorTomcat;

public class Main {

    public static void main(String[] args) {
        ServidorTomcat servidorTomcat = new ServidorTomcat();
        servidorTomcat.iniciar();
    }

}
