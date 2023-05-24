package com.example.androidprojectprep25may.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.androidprojectprep25may.entity.User;
import com.example.androidprojectprep25may.dao.UserDao;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
