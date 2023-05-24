package com.example.androidprojectprep25may.fragment.auth;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.androidprojectprep25may.R;
import com.example.androidprojectprep25may.dao.UserDao;
import com.example.androidprojectprep25may.db.AppDatabase;
import com.example.androidprojectprep25may.entity.User;
import com.example.androidprojectprep25may.helper.Constants;

import java.util.zip.Inflater;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String email;
    private String password;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            email = getArguments().getString(ARG_PARAM1);
            password = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);


        Button button = view.findViewById(R.id.loginBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String email = ((EditText) view.findViewById(R.id.emailLogin)).getText().toString();
                String password = ((EditText) view.findViewById(R.id.passwordLogin)).getText().toString();
                Log.d("BUTTONS", "User tapped the loginButton ");

                //Database instance
                AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(),
                        AppDatabase.class, Constants.dataBase).allowMainThreadQueries().build();

                UserDao userDao = db.userDao();

                User loginData = userDao.login(email, password);

                if (loginData != null) {
                    System.out.println("Successfully Logged in!!");
                } else {
                    System.out.println("User does not exist!");
                }
            }
        });

        Button button2 = view.findViewById(R.id.forgotPasswordBtn);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(),
                        AppDatabase.class, Constants.dataBase).allowMainThreadQueries().build();

                UserDao userDao = db.userDao();
                System.out.println(userDao.getAll());
            }
        });

        Button button3 = view.findViewById(R.id.signupBtn);
        button3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                getParentFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentAuthContainer, SignupFragment.class, null)
                        .commit();

            }
        });

        return view;
    }


}