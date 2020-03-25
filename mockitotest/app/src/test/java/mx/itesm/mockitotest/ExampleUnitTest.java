package mx.itesm.mockitotest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import mx.itesm.mockitotest.login.LoginModel;
import mx.itesm.mockitotest.login.LoginPresenter;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest
{
     @Mock
     private LoginPresenter testPresenter;

     private LoginModel testModel;

     @Before
    public void setUp() throws Exception
     {
         testModel=new LoginModel(testPresenter);
     }

     @Test
    public void isSuccessUserAndPassword()
     {
         testModel.verifyUser("android", "12345");
         verify(testPresenter).validUser();
     }

     @Test
    public void isNotSuccessUserAndPassword()
     {
         testModel.verifyUser("and", "123");
         verify(testPresenter).data_error();
     }
}