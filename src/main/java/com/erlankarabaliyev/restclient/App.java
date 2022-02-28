package com.erlankarabaliyev.restclient;

import com.erlankarabaliyev.restclient.configuration.MyConfig;
import com.erlankarabaliyev.restclient.entity.Employee;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext annContext
                 = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = annContext.getBean("communication", Communication.class);

//        Employee employee = communication.getEmployee(1);
//        System.out.println(employee);

        communication.saveEmployee(new Employee("Alihan", "N", "IT", 1200));

        List<Employee> allEmployees = communication.getAllEmployees();

        for (Employee emp : allEmployees) {
            System.out.println(emp);
        }
    }
}
