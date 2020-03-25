package mx.itesm.test_basics;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    TestValidate myTest;

    @Before
    public void init()
    {
       myTest = new TestValidate(2,2);
    }


    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void test_add()
    {
        assertEquals(4, myTest.add());
    }
}