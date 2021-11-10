package com.spring.mvc.anonymous;

import org.junit.jupiter.api.Test;

public class AnonymousTest {

    @Test
    void test1() {
        Car sonata = new Sonata();
        sonata.run();

        //익명 클래스
        //new Car() class Ferrari {
        // };
         Car ferrari  = new Car()  {

            @Override
            public void run() {
                System.out.println("페라리가 달립니다.");
            }
        };
        ferrari.run();

        Car avante = () -> System.out.println("아반떼가 달립니다.");
        avante.run();

        Dealer pororo = new Dealer();
        pororo.getCar(ferrari);
        pororo.getCar(() -> System.out.println("투싼이 달립니다."));


    }//

    @Test
    void test2() {

        Calculator addCal = new Calculator() {
            @Override
            public double operate(int n1, int n2) {
                return n1 + n2;
            }
        };

        Calculator multiCal = new Calculator() {
            @Override
            public double operate(int n1, int n2) {
                return n1 * n2;
            }
        };

        /*Calculator addCal2 = (n1,n2) -> {
            return n1 + n2;
        };*/

        Calculator addCal2 = (n1, n2) ->  n1 + n2;
        Calculator multiCal2 = (n1, n2) -> {
            System.out.println("메롱메롱!");
            return  n1 * n2;
        };

        System.out.println(addCal2.operate(100,200));
        System.out.println(addCal2.operate(10,1));
        System.out.println(multiCal2.operate(11,11));

    }//



}//end class
