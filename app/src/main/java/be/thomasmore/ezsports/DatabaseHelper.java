package be.thomasmore.ezsports;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import be.thomasmore.ezsports.models.Player;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "ezsports";

    // uitgevoerd bij instantiatie van DatabaseHelper
    // -> als database nog niet bestaat, dan creëren (call onCreate)
    // -> als de DATABASE_VERSION hoger is dan de huidige versie,
    //    dan upgraden (call onUpgrade)

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // methode wordt uitgevoerd als de database gecreëerd wordt
    // hierin de tables creëren en opvullen met gegevens
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_PRONOSTIC = "CREATE TABLE pronostic (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "matchId INTEGER," +
                "team1Id INTEGER," +
                "team2Id INTEGER," +
                "team1Name TEXT," +
                "team2Name TEXT, " +
                "prognosticTeam1 INTEGER," +
                "prognosticTeam2 INTEGER)";
        db.execSQL(CREATE_TABLE_PRONOSTIC);

        String CREATE_TABLE_PLAYER = "CREATE TABLE player (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT," +
                "first_name TEXT, " +
                "last_name TEXT, " +
                "hometown TEXT)";

        db.execSQL(CREATE_TABLE_PLAYER);
    }

    // methode wordt uitgevoerd als database geupgrade wordt
    // hierin de vorige tabellen wegdoen en opnieuw creëren
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS prognostic");
        db.execSQL("DROP TABLE IF EXISTS player");

        // Create tables again
        onCreate(db);
    }

    //-------------------------------------------------------------------------------------------------
    //  CRUD Operations
    //-------------------------------------------------------------------------------------------------
    public long insertPlayer(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", player.getName());
        values.put("first_name", player.getFirst_name());
        values.put("last_name", player.getLast_name());
        values.put("hometown", player.getHometown());

        long id = db.insert("player", null, values);

        db.close();
        return id;
    }

}
