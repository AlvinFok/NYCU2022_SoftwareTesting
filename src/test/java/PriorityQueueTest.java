package test.java;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.NoSuchElementException;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.*;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;


public class PriorityQueueTest {
    static Stream<Arguments> streamProvider(){
        return Stream.of(
                Arguments.of(new int[]{1,3,2,4}, new int[]{1,2,3,4}),
                Arguments.of(new int[]{4,3,2,1}, new int[]{1,2,3,4}),
                Arguments.of(new int[]{-1,-2,-3,-4}, new int[]{-4,-3,-2,-1}),
                Arguments.of(new int[]{-1,3,-2,4}, new int[]{-2,-1,3,4}),
                Arguments.of(new int[]{1,2,3,4}, new int[]{1,2,3,4})
        );
    }

    @ParameterizedTest(name = "#{index} - Test with Argument={0},{1}")
    @MethodSource("streamProvider")
    public void PriorityQueue_RunTest(int[] random_array, int[] correct_array){
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        int index = 0;
        Integer s;
        int[] result = new int[random_array.length];
        for(int i = 0; i < random_array.length; i++){
            test.add(random_array[i]);
        }
        for(int i = 0; i < random_array.length; i++){
            result[i] = test.poll();
        }
        assertArrayEquals(result, correct_array);
    }

    @Test
    public void IllegalArgumentException(){
        class MyComparator implements Comparator<Integer>{
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        }

        assertThrows(IllegalArgumentException.class, ()->{
            PriorityQueue<Integer> test = new PriorityQueue<Integer>(0, new MyComparator());
        });

    }

    @Test
    public void NullPointerException(){
        PriorityQueue<Integer> test = new PriorityQueue<Integer>();
        assertThrows(NullPointerException.class, ()->{
            test.add(null);
        });

    }

    @Test
    public void ClassCastException(){
        PriorityQueue<Integer[]> test = new PriorityQueue<Integer[]>();
        assertThrows(ClassCastException.class, ()->{
            test.add(new Integer[]{1,2});
        });


    }
}
