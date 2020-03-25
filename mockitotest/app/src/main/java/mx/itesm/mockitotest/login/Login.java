package mx.itesm.mockitotest.login;

public interface Login
{
   interface Activity
   {
       void userIsValid();
       String getUsername();
       String getPassword();
       void data_error();
   }

   interface Presenter
   {
     void verifyUser(String user, String password);
     void validUser();
     void data_error();
   }

   interface Model
   {
       void verifyUser(String user, String password);
   }
}

