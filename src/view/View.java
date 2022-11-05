package view;

import controller.RegistrationSystem;

public class View {

    private static View single_instance = null;

    private RegistrationSystem controller;

    private View(){
        this.controller = RegistrationSystem.getInstance();
    }

    public static View getInstance(){
        if(single_instance == null)
            single_instance = new View();
        return  single_instance;
    }
}
