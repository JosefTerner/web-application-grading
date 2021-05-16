package com.github.grading;

import com.github.grading.config.ServerConfig;

import javax.servlet.ServletException;

public class ApplicationStart {

    public static void main(String[] args) {
        try {
            ServerConfig.tomcat().run();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
