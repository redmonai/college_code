import org.junit.Test;
import static org.junit.Assert.*;

public class Check_Triangle_Test {

	Check_Triangle check =  new Check_Triangle();
    @Test
    public void checkTriangleSameTest(){
        double[] arr = {15,15,15};
        assertTrue(arr.toString()+" is a triangle",Check_Triangle.form_triangle(arr));
    }

    @Test
    public void checkTriangleTest(){
        double[] arr = {3, 4, 6};
        assertTrue(arr.toString()+" is a triangle",Check_Triangle.form_triangle(arr));
    }

    @Test
    public void checkTriangleFalseTest(){
        double[] arr = {25,5,5};
        assertFalse(arr.toString()+" should not be a triangle",Check_Triangle.form_triangle(arr));
    }

    @Test
    public void kindTriangleFalseTest(){
        double[] arr = {25,5,5};
        assertEquals(arr.toString() + " should not be a triangle", 0, Check_Triangle.kind_triangle(arr));
    }

    @Test
    public void kindTrianglObtuseTest(){
        double[] arr = {3,4,6};
        assertEquals(arr.toString()+" should be obtuse",3, Check_Triangle.kind_triangle(arr));
    }

    @Test
     public void kindTriangleRightAngledTest(){
        double[] arr = {3,4,5};
        assertEquals(arr.toString()+" should be right-angled",1, Check_Triangle.kind_triangle(arr));
    }

    @Test
     public void kindTriangleAcuteTest(){
        double[] arr = {6,6,6};
        assertEquals(arr.toString()+" should be acute",2,Check_Triangle.kind_triangle(arr));
    }


}