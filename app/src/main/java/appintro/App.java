/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package appintro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import spark.ModelAndView;
import spark.template.mustache.MustacheTemplateEngine;
import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.port;


public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        /*Logger logger = LogManager.getLogger(App.class);
        int port = Integer.parseInt(System.getenv("PORT"));
        port(port);
        logger.error("Current port number:" + port);*/

        System.out.println(new App().getGreeting());
       
        
        get("/",(rq,rs)-> "Hello World");
        get("/compute",
        (rq,rs)-> {
            Map<String,String>map= new HashMap<String,String>();
            map.put("result","not computed yet");
            return new ModelAndView(map,"compute.mustache");
        },
        new MustacheTemplateEngine()
        );

        post("/compute",
        (req,rs)->{
            //System.out.println(req.queryParams("input1"));
            //System.out.println(req.queryParams("input2"));
            String input1 =req.queryParams("input1");
            java.util.Scanner sc1=new java.util.Scanner(input1);
            sc1.useDelimiter("[;\r\n]+");
            java.util.ArrayList<Integer>inputList=new java.util.ArrayList<>();
            while(sc1.hasNext()){
                int value = Integer.parseInt(sc1.next().replaceAll("\\s",""));
                inputList.add(value);
            }
            sc1.close();
            System.out.println(inputList);

            String input2 = req.queryParams("input2").replaceAll("\\s","");
            int input2AsInt = Integer.parseInt(input2);

            //boolean result = App.search(inputList, input2AsInt);
            ArrayList<Integer> greater=new ArrayList<>();
            ArrayList<Integer> less=new ArrayList<>();
            boolean result=App.isAboveTheAverage(inputList, input2AsInt, greater, less);
            Map<String,Boolean>map=new HashMap<>();
            map.put("result",result);
            return new ModelAndView(map,"compute.mustache");
        },
        new MustacheTemplateEngine()
        );
    
    
    }
    public static boolean isAboveTheAverage(ArrayList<Integer> arr,Integer x,ArrayList<Integer> greater,ArrayList<Integer> less){
        double sum=0;
        for(int i=0;i<arr.size();i++){
            sum+=arr.get(i);
        }
        double average=(sum/arr.size());
        for(int i=0;i<arr.size();i++){
            if(arr.get(i)<average)
                less.add(arr.get(i));
            else
                greater.add(arr.get(i));
        }
        for(int i=0;i<greater.size();i++){
           greater.get(i);
        }
        for(int i=0;i<less.size();i++){
            less.get(i);
         }
        if(x>=average)
            return true;
        else
            return false;
    } 
    
}
