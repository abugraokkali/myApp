package appintro;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;


class AppTest {
    @Test
    public void testGreaterThanAverage(){
        ArrayList<Integer> arr=new ArrayList<>(Arrays.asList(1,3,5,7,9));
        assertTrue(App.isGreaterThanOrEqualToAverage(arr, 6,new ArrayList<Integer>(),new ArrayList<Integer>()));
    }
    @Test
    public void testLessThanAverage(){
        ArrayList<Integer> arr=new ArrayList<>(Arrays.asList(1,3,5,7,9));
        assertFalse(App.isGreaterThanOrEqualToAverage(arr, 4,new ArrayList<Integer>(),new ArrayList<Integer>()));

    }
    @Test
    public void testEqualToAverage(){
        ArrayList<Integer> arr=new ArrayList<>(Arrays.asList(1,3,5,7,9));
        assertTrue(App.isGreaterThanOrEqualToAverage(arr, 5,new ArrayList<Integer>(),new ArrayList<Integer>()));
    }
    @Test
    public void testEmptyInputList(){
        ArrayList<Integer> arr=new ArrayList<>();
        ArrayList<Integer> greater=new ArrayList<>();
        ArrayList<Integer> less=new ArrayList<>();
        assertThrows(NullPointerException.class,
        ()->{
            App.isGreaterThanOrEqualToAverage(arr, 4, greater, less);
        }
        );
        
    }
    @Test
    public void testLessArray(){
        ArrayList<Integer> arr=new ArrayList<>(Arrays.asList(-1,3,7,5,-9,11));
        ArrayList<Integer> greater=new ArrayList<>();
        ArrayList<Integer> less=new ArrayList<>();
        App.isGreaterThanOrEqualToAverage(arr, 4, greater, less);

        int[]less_arr = new int[less.size()];
        for(int i=0;i<less.size();i++)
            less_arr[i]=less.get(i);
        assertArrayEquals(less_arr,new int[]{-1,3,-9});
    }
    @Test
    public void testGreaterArray(){
        ArrayList<Integer> arr=new ArrayList<>(Arrays.asList(-1,3,7,5,-9,11));
        ArrayList<Integer> greater=new ArrayList<>();
        ArrayList<Integer> less=new ArrayList<>();
        App.isGreaterThanOrEqualToAverage(arr, 4, greater, less);

        int[]greater_arr = new int[greater.size()];
        for(int i=0;i<less.size();i++)
            greater_arr[i]=greater.get(i);
        assertArrayEquals(greater_arr,new int[]{7,5,11});
    }

    

}
