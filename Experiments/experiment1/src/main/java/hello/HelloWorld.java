package hello;

import org.joda.time.LocalTime;

public class HelloWorld {
    public static void main(String[] args) {
		Localtime curr = new LocalTime();
		System.out.println("The time is: " + curr);
		
        Greeter greeter = new Greeter();
        System.out.println(greeter.sayHello());
    }
}