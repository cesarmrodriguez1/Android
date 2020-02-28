package mx.itesm.sqliteexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLInput;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper
{
    //We are supposing this database has two tables. We define their attributes:
    public static final String TABLE_USERS = "users";
    public static final String USERS_ID ="id_user";
    public static final String USERS_NICKNAME ="nickname";
    public static final String USERS_PASSWORD ="password";
    public static final String USERS_DEPARTMENT ="department";

    public static final String TABLE_PRODUCTS = "products";
    public static final String PRODUCTS_ID ="id_product";
    public static final String PRODUCTS_NAME ="name";
    public static final String PRODUCTS_PRICE ="price";

    //We name our database as follows:
    public static final String DATABASE_NAME = "mydatabase.db";

    //For this case, we put the CREATE sentences in Java Strings. We create two tables:

    private static final String CREATE_TABLE_USERS = "CREATE TABLE "+TABLE_USERS+
            "("+USERS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            USERS_NICKNAME+" TEXT NOT NULL, "+
            USERS_PASSWORD+" TEXT NOT NULL, "+
            USERS_DEPARTMENT+" TEXT NOT NULL);";

    private static final String CREATE_TABLE_PRODUCTS = "CREATE TABLE "+TABLE_PRODUCTS+
            "("+PRODUCTS_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
            PRODUCTS_NAME+" TEXT NOT NULL, "+
            PRODUCTS_PRICE+" REAL NOT NULL);";

//In constructor we create officially the database:
    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
      //We create officially both tables. If you had 10 tables, you will execute 10 SQL sentences.
      //Relationships between tables must be created here, with SQL Code.
      db.execSQL(CREATE_TABLE_USERS);
      db.execSQL(CREATE_TABLE_PRODUCTS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
       //For cleaning and updating processes, it is required to restart the database:

        db.execSQL("DROP TABLE IF EXISTS "+TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_PRODUCTS);
        onCreate(db);
    }

    /*Now, it is important to define methods to modify and access the data in the database
    These operations will be useful to manipulate information saved in the database.
    It is recommended to define these methods for each table, or for each view report that must
    be obtained
    */

    //FIRST, we define INSERT sentences:

    public boolean insertUser(User user)
    {
        String nickname, password, department;
        nickname = user.nickname;
        password = user.password;
        department = user.department;

        SQLiteDatabase database = this.getWritableDatabase(); // We obtain an instance of our database
        ContentValues contentValues = new ContentValues(); // We insert the data using one variable of type ContentValues
        contentValues.put("nickname", nickname);
        contentValues.put("password", password);
        contentValues.put("department", department);

        database.insert("users",null,contentValues); //We insert

        return true;
    }

    public boolean insertProduct(Product product)
    {
        String name;
        double price;
        name = product.name;
        price = product.price;

        SQLiteDatabase database = this.getWritableDatabase(); // We obtain an instance of our database
        ContentValues contentValues = new ContentValues(); // We insert the data using one variable of type ContentValues
        contentValues.put("name", name);
        contentValues.put("price", price);

        database.insert("products",null,contentValues); //We insert

        return true;
    }

    //SECOND, we define SELECT sentences:

    public ArrayList<User> getAllUsers()
    {
        // This arraylist defines a personalized list containing objects of type "User"
        // Managing the instance of Table as a Java Class allows to have control of
        // data from table.

        ArrayList<User> users_list = new ArrayList<User>();
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor result = database.rawQuery("SELECT * FROM "+TABLE_USERS, null);
        Log.e(null ,"SELECT * FROM "+TABLE_USERS);

        if(result.moveToFirst()) {
            Log.e(null, "We have data, OHH YEAAAH!!");
            while (result.moveToNext()) {
                User current_user = new User(
                        result.getInt(result.getColumnIndex(USERS_ID)),
                        result.getString(result.getColumnIndex(USERS_NICKNAME)),
                        result.getString(result.getColumnIndex(USERS_PASSWORD)),
                        result.getString(result.getColumnIndex(USERS_DEPARTMENT))
                );
                users_list.add(current_user);
            }
        }
        else
            Log.e(null, "Oh no, no data!!");

        return users_list;
    }

    public ArrayList<Product> getAllProducts()
    {
        // This arraylist defines a personalized list containing objects of type "Product"
        // Managing the instance of Table as a Java Class allows to have control of
        // data from table.

        ArrayList<Product> products_list = new ArrayList<Product>();
        SQLiteDatabase database = this.getReadableDatabase();

        Cursor result = database.rawQuery("SELECT * FROM "+TABLE_PRODUCTS, null);

        result.moveToFirst();

        while(result.moveToNext())
        {
            Product current_product = new Product(
                    result.getInt(result.getColumnIndex(PRODUCTS_ID)),
                    result.getString(result.getColumnIndex(PRODUCTS_NAME)),
                    result.getDouble(result.getColumnIndex(PRODUCTS_PRICE))
            );
            products_list.add(current_product);
        }
        return products_list;
    }

    public User getUserById(int id)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor result = database.rawQuery("SELECT * FROM "+TABLE_USERS+" WHERE "+USERS_ID+" = "+id, null);
        User obtained_user = new User(result.getInt(result.getColumnIndex(USERS_ID)),
                result.getString(result.getColumnIndex(USERS_NICKNAME)),
                result.getString(result.getColumnIndex(USERS_PASSWORD)),
                result.getString(result.getColumnIndex(USERS_DEPARTMENT))
        );
        return obtained_user; //We return the Cursor with all data
    }

    public Product getProductById(int id)
    {
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor result = database.rawQuery("SELECT * FROM "+TABLE_USERS+" WHERE "+USERS_ID+" = "+id, null);
        Product obtained_product = new Product(result.getInt(result.getColumnIndex(PRODUCTS_ID)),
        result.getString(result.getColumnIndex(PRODUCTS_NAME)),
                result.getDouble(result.getColumnIndex(PRODUCTS_PRICE))
        );
        return obtained_product; //We return the Cursor with all data
    }

    //THIRD: We create functions to UPDATE the tables in our database:

    public boolean updateUser(Integer id, String nickname, String password, String department)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nickname", nickname);
        contentValues.put("password", password);
        contentValues.put("department", department);
        database.update(TABLE_USERS, contentValues, "id = ?", new String[] {Integer.toString(id)});
        return true;
    }

    public boolean updateProduct(Integer id, String name, double price)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("price", price);
        database.update(TABLE_PRODUCTS, contentValues, "id = ?", new String[] {Integer.toString(id)});
        return true;
    }
    //Functions to count number of rows for each table. In our case, table primary keys are auto increment.

    public int numRowsUsers()
    {
        SQLiteDatabase database = this.getReadableDatabase();
        int rows = (int) DatabaseUtils.queryNumEntries(database, TABLE_USERS);
        return rows;
    }

    public int numRowsProducts()
    {
        SQLiteDatabase database = this.getReadableDatabase();
        int rows = (int) DatabaseUtils.queryNumEntries(database, TABLE_PRODUCTS);
        return rows;
    }
}
