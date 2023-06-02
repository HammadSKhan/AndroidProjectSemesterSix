package com.example.androidproject.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.androidproject.entity.User;
import com.example.androidproject.dao.UserDao;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserDao userDao();
}
