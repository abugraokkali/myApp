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

    public static void main(String[] args) {
        Logger logger = LogManager.getLogger(App.class);
        int port = Integer.parseInt(System.getenv("PORT"));
        port(port);
        logger.error("Current port number:" + port);
        
        get("/",(rq,rs)-> "Hello World");
        get("/compute",
        (rq,rs)-> {
            Map<String,String>map= new HashMap<String,String>();
            map.put("result"," ");
            map.put("greater"," ");
            map.put("less", " ");
            return new ModelAndView(map,"compute.mustache");
        },
        new MustacheTemplateEngine()
        );

        post("/compute",
        (req,rs)->{
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

            ArrayList<Integer> greater=new ArrayList<>();
            ArrayList<Integer> less=new ArrayList<>();
            boolean result=App.isGreaterThanOrEqualToAverage(inputList, input2AsInt, greater, less);
            Map<String,String>map=new HashMap<>();
            map.put("greater", greater.toString());
            map.put("less", less.toString());
            if(result==true)
                map.put("result",input2AsInt+" is greater than or equal to average of list.");
            else
                map.put("result",input2AsInt+" is less than the average of list.");
            return new ModelAndView(map,"compute.mustache");
        },
        new MustacheTemplateEngine()
        );
    
    
    }
    /*This method return true when x is greater than or equal to average of elements of arr
        Also it takes two arraylists greater and less.
        Greater array filled with elements of arr that are greater than or equal to x.
        Less array filled with elements of arr that are less than x.     
    */
    public static boolean isGreaterThanOrEqualToAverage(ArrayList<Integer> arr,Integer x,ArrayList<Integer> greater,ArrayList<Integer> less){
        if(arr.size()==0 || arr==null)
            throw new NullPointerException();
        double sum=0;
        for(int i=0;i<arr.size();i++){
            sum+=arr.get(i);
        }
        //finding greater and less elements and storing in arrays.
        double average=(sum/arr.size());
        for(int i=0;i<arr.size();i++){
            if(arr.get(i)<x)
                less.add(arr.get(i));
            else
                greater.add(arr.get(i));
        }
        if(x>=average)
            return true;
        else
            return false;
    } 
    
}
