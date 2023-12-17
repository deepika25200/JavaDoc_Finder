package com.example.demo.DemoClasses;

import com.example.demo.CustomAnnotations.MethodDocumentation;

public class Demo2 {
    /**
     * This is a method Demo2M1 inside class Demo2
     *
     *  "Demo1M1 inside class Demo2"
     */
    public void Demo2M1() {
        System.out.println("Demo2M2");
    }
    /**
     * This is a method Demo2M2 inside class Demo2
     *
     * @MethodDocumentation "Demo1M2 inside class Demo2"
     */
    @MethodDocumentation
    public void Demo2M2() {
        System.out.println("Demo2M2");
    }/**
     * This is a method Demo2M3 inside class Demo2
     *
     * @MethodDocumentation "Demo2M3 inside class Demo2"
     */
    @MethodDocumentation
    public void Demo2M3() {
        System.out.println("Demo2M3");
    }
    public void Demo2M4()
    {
        System.out.println("Demo2M4");
    }

}
