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

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SignupFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignupFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SignupFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SigninFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignupFragment newInstance(String param1, String param2) {
        SignupFragment fragment = new SignupFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_signup, container, false);


        Button button = view.findViewById(R.id.submitBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String firstName = ((EditText) view.findViewById(R.id.firstName)).getText().toString();
                String lastName = ((EditText) view.findViewById(R.id.lastNameInput)).getText().toString();
                String email = ((EditText) view.findViewById(R.id.emailSignup)).getText().toString();
                String password = ((EditText) view.findViewById(R.id.passwordSignup)).getText().toString();
                Log.d("BUTTONS", "User tapped the loginButton ");

                //Database instance
                AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(),
                        AppDatabase.class, Constants.dataBase).allowMainThreadQueries().build();

                UserDao userDao = db.userDao();
                //Checking if email already exists
                User checkUserEmail = userDao.checkEmail(email);

                if (checkUserEmail != null) {
                    System.out.println("User already exists");
                } else {
                    userDao.signUp(new User(firstName, lastName, email, password, "https://placehold.co/100"));
                }
            }
        });

        Button button1 = view.findViewById(R.id.backBtn);
        button1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getParentFragmentManager().beginTransaction()
                        .setReorderingAllowed(true)
                        .replace(R.id.fragmentAuthContainer, LoginFragment.class, null)
                        .commit();
//                AppDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(),
//                        AppDatabase.class, "Main-database").allowMainThreadQueries().build();
//
//                UserDao userDao = db.userDao();
//                System.out.println(userDao.getAll());
            }
        });


        return view;
    }
}