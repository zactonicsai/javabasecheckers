/*
 * INTENTIONALLY BAD STYLE — DEMONSTRATION ONLY.
 * See SqlInjectionExample.java header for context.
 *
 * Triggers Checkstyle and PMD violations:
 *   - bad class/method/variable naming
 *   - long lines
 *   - missing javadoc on public API
 *   - magic numbers
 *   - public mutable static fields
 *   - System.out.println instead of a logger
 */
package com.zactonics.demo.badexamples;

import java.util.ArrayList;
import java.util.List;

public class bad_style_example {

public static int MAX_RETRIES = 5;

public static List<String> STUFF = new ArrayList<>();

public List<Integer> doStuff(int x){
List<Integer> RESULT=new ArrayList<>();
for(int i=0;i<x;i++){
if(i%2==0){RESULT.add(i*86400);}else{RESULT.add(i*3600);}
}
System.out.println("Did stuff with x = " + x + " and got a result list of size " + RESULT.size() + " which is hopefully the right size");
return RESULT;
}

public String GREETING(String n){return "Hello, "+n+"!";}

public int Calculate_total(int a,int b,int c,int d,int e,int f,int g,int h,int i,int j){return a+b+c+d+e+f+g+h+i+j;}
}
