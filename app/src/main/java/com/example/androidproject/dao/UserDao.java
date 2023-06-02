package com.example.androidproject.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.androidproject.entity.User;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    List<User> getAll();
//
//    @Query("SELECT * FROM user WHERE uid IN (:userIds)")
//    List<User> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM user WHERE email = :email AND " +
            "password_hash = :passwordHash LIMIT 1")
    User login(String email, String passwordHash);

    @Query("SELECT * FROM user WHERE email = :email  LIMIT 1")
    User checkEmail(String email);

    @Insert
    void signUp(User user);

    @Delete
    void delete(User user);


}
