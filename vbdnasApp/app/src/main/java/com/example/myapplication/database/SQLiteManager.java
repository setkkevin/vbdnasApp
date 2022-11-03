package com.example.myapplication.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.myapplication.models.Contacts;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public  class SQLiteManager<DateFormate> extends SQLiteOpenHelper {

    private static SQLiteManager sqLiteManager;
    private static final String DATABASE_NAME="ContactsDB";
    private static final int DATABASE_VERSION=1;
    private static final String TABLE_NAME="Contacts";
    private static final String COUNTER="Counter";

    private static final String ID_FIELD="id";
    private static final String TITLE_FIELD="title";
    private static final String DESC_FIELD="desc";
    private static final String DELETED_FIELD="deleted";

    private static final DateFormat dateFormat = new  SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

    public SQLiteManager(Context context) {

        super(context, DATABASE_NAME,null, DATABASE_VERSION);
    }
    public static SQLiteManager instanceOfDataBase(Context context){

        if(sqLiteManager == null)
            sqLiteManager = new SQLiteManager(context);


         return sqLiteManager;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        StringBuilder sql;
         sql = new StringBuilder()
                 .append("CREATE TABLE")
                 .append(TABLE_NAME)
                 .append("(")
                 .append(COUNTER)
                 .append("INTEGER PRIMARY KEY AUTOINCREMENT")
                 .append(ID_FIELD)
                 .append("INT, ")
                 .append(TITLE_FIELD)
                 .append("TEXT, ")
                 .append(DESC_FIELD)
                 .append("TEXT, ")
                 .append(DELETED_FIELD)
                 .append("TEXT)");
         sqLiteDatabase.execSQL(sql.toString());

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
    public void addContactsToDatabase(Contacts contacts){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues =new ContentValues();
        contentValues.put(ID_FIELD,contacts.getId());
        contentValues.put(TITLE_FIELD,contacts.getName());
        contentValues.put(DESC_FIELD,contacts.getNumber());
        contentValues.put(DELETED_FIELD,getStringFromDate(contacts.getDeleted()));
      sqLiteDatabase.insert(TABLE_NAME,null, contentValues);
    }
    public void populateNoteListArray() {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null))
        {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id = result.getInt(1);
                    String name = result.getString(2);
                    String number = result.getString(3);
                    String stringDeleted = result.getString(4);
                    Date deleted = getDateFromString(stringDeleted);
                    Contacts contacts = new Contacts(id, name, number, deleted);
                    Contacts.contactsArrayList.add(contacts);
                }
            }
        }
    }
    public void updateContactsInDB(Contacts contacts){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put(ID_FIELD,contacts.getId());
        contentValues.put(TITLE_FIELD,contacts.getName());
        contentValues.put(DESC_FIELD,contacts.getNumber());
        contentValues.put(DELETED_FIELD,getStringFromDate(contacts.getDeleted()));
        sqLiteDatabase.update(TABLE_NAME,contentValues,ID_FIELD+"=?",new String[]{String.valueOf(contacts.getId())});
    }
    private String getStringFromDate(Date date){
       if(date == null)
           return  null;
       return dateFormat.format(date);
    }
    private Date getDateFromString(String string){

        try {
            return dateFormat.parse(string);
        } catch (ParseException | NullPointerException e) {
            return  null;
        }
    }

}
