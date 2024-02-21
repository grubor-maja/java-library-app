/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import controller.Controller;

/**
 *
 * @author Maja
 */
public class Main {

    public static void main(String[] args) {
        try {
            Controller.getInstance().openMainForm();

        } catch (Exception e) {
        }

    }
}
