package com.newgo.infrastructure;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class ServidorTomcat {

    private static final int PORTA_WEB = 8080;
    private static final String LOCAL_DIR_WEB_APP = "src/main/webapp/";
    private static final String CLASSES_WEB_INF = "/WEB-INF/classes";

    public void iniciar() {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(PORTA_WEB);

        System.out.println("Configurando aplicação com base no diretório: " + new File("./" + LOCAL_DIR_WEB_APP).getAbsolutePath() + "...");

        StandardContext contexto = (StandardContext) tomcat.addWebapp("", new File(LOCAL_DIR_WEB_APP).getAbsolutePath());
        File classesAdicionaisWebInf = new File("target/classes");
        WebResourceRoot recursos = new StandardRoot(contexto);
        recursos.addPreResources(new DirResourceSet(recursos, CLASSES_WEB_INF,
                classesAdicionaisWebInf.getAbsolutePath(), "/"));
        contexto.setResources(recursos);
        tomcat.enableNaming();
        tomcat.getConnector();

        try {
            System.out.println("Iniciando o Tomcat...");
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
        tomcat.getServer().await();
    }
}
