package mx.itesm.sqliteexample;

import android.content.Context;
import android.util.Log;
import java.util.ArrayList;

public class DataManager
{
    DBHelper myDBHelper;
    Context context;

    public DataManager(Context context)
    {
        this.context = context;
        this.myDBHelper = new DBHelper(context);
    }

    public boolean initialPopulate()
    {
        if(myDBHelper.numRowsUsers()==0)
        {
            Log.e(null,"No data in database, I will populate");
            //Maybe first time user uses the App. Here we populate the database with some initial
            // data
            User user1 = new User("user1","password","science");
            User user2 = new User("user2", "abc123","science");
            User user3 = new User("user3", "abcxyz","science");
            User user4 = new User("user4", "iloveyou","medicine");
            myDBHelper.insertUser(user1);
            myDBHelper.insertUser(user2);
            myDBHelper.insertUser(user3);
            myDBHelper.insertUser(user4);
            return true;
        }
       else
           return false;
    }

    public ArrayList<User> getUsersToDisplay()
    {
        ArrayList<User> users_list = myDBHelper.getAllUsers();
        Log.e(null,"I obtained user list");
        if(users_list.isEmpty())
            Log.e(null,"LIST IS EMPTY. From getUsersToDisplay()");
        else
            Log.e(null,"LIST IS NOT EMPTY. From getUsersToDisplay()");

        return users_list;
    }

    public void insertUser(String nickname, String password, String department)
    {
        User user_toInsert = new User(nickname, password, department);
        myDBHelper.insertUser(user_toInsert);
    }
    public void insertProduct(String name, double price)
    {
        Product product_toInsert = new Product(name, price);
        myDBHelper.insertProduct(product_toInsert);
    }
}
