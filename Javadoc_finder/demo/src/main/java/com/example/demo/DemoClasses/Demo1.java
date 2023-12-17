package com.example.demo.DemoClasses;
import com.example.demo.CustomAnnotations.ClassDocumentation;
import com.example.demo.CustomAnnotations.MethodDocumentation;
/**
 * Demo1 class to check the annotations
 *
 * @ClassDocumentation "This is a class A"
 */
@ClassDocumentation
public class Demo1 {
    /**
            * This is a method Demo1M1 inside class Demo1
     *
             * @MethodDocumentation "Demo1M1 inside class Demo1"
            */
    @MethodDocumentation
    public void Demo1M1() {
        System.out.println("Demo1M2");
    }
    /**
     * This is a method Demo1M2 inside class Demo1
     *
     * @MethodDocumentation "Demo1M2 inside class Demo1"
     */
    @MethodDocumentation
    public void Demo1M2() {
        System.out.println("Demo1M2");
    }/**
     * This is a method Demo1M3 inside class Demo1
     *
     * @MethodDocumentation "Demo1M3 inside class Demo1"
     */
    @MethodDocumentation
    public void Demo1M3() {
        System.out.println("Demo1M3");
    }
    public void Demo1M4()
    {
        System.out.println("Demo1M4");
    }



}
