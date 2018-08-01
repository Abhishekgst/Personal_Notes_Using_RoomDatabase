package com.example.innove.roomdatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

/**
 * Created by abhisheksharma on 18-May-2018.
 */
@Dao
public interface NoteDAO {
    @Query("SELECT * FROM  " + Constants.TABLE_NAME_NOTE)
    List<Note> getNotes();


    /*
    * Insert the object in database
    * @param note, object to be inserted
    */
    @Insert
    long insert(Note note);

    /*
    * update the object in database
    * @param note, object to be updated
    */
    @Update
    void update(Note repos);

    /*
    * delete the object from database
    * @param note, object to be deleted
    */
    @Delete
    void delete(Note note);

    /*
    * delete list of objects from database
    * @param note, array of objects to be deleted
    */
    @Delete
    void delete(Note... note);      // Note... is varargs, here note is an array


}
