package com.neuedu.test;

import com.neuedu.utils.BigDecimalUtils;

import java.math.BigDecimal;
import java.util.BitSet;

public class Test {

    public static void main(String[] args){
        //System.out.println(0.05+0.01);
        //System.out.println(1.0-0.42);

        //BigDecimal bigDecimal=new BigDecimal("0.05");
        //BigDecimal bigDecimal1=new BigDecimal("0.01");
        //System.out.println(bigDecimal.add(bigDecimal1));


        System.out.println(BigDecimalUtils.add(0.05,0.01));
        System.out.println(BigDecimalUtils.sub(1.0,0.42));

        System.out.println(BigDecimalUtils.mul(4.015,100.0));
        System.out.println(BigDecimalUtils.div(123.56,100.0));


    }
}
