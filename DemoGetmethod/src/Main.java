//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.


import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws  IOException {
        FileReader file=new FileReader("src/ApiDirectory.txt");

        BufferedReader br=new BufferedReader(file);

        String str;
        while((str=br.readLine())!=null){
            System.out.println(str);
        }
        br.close();
    }
        Class myClass = Class.forName("Main");
        Main obj=new Main();
        Method method=null;
        try {
             method = myClass.getDeclaredMethod("printNum",int.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        try{
            method.invoke(obj,90);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }
    public static void printNum(int num){
        System.out.println(num);
    }


}

