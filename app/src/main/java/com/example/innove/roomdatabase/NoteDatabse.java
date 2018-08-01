package com.example.innove.roomdatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

/**
 * Created by abhisheksharma on 18-May-2018.
 */
@Database(entities = {Note.class}, version = 1)
@TypeConverters({DateRoomConverter.class})
public abstract class NoteDatabse extends RoomDatabase {

    public abstract NoteDAO getNoteDao();

    private static NoteDatabse noteDB;

    public static NoteDatabse getInstance(Context context) {
        if (null == noteDB) {
            noteDB = buildDatabaseInstance(context);
        }
        return noteDB;
    }

    private static NoteDatabse buildDatabaseInstance(Context context) {
        return Room.databaseBuilder(context, NoteDatabse.class, Constants.DB_NAME).allowMainThreadQueries().build();
    }

    public void cleanUp(){
        noteDB = null;
    }
}
