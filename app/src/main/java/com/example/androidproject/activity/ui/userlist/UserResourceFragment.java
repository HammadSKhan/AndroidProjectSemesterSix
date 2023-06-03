package com.example.androidproject.activity.ui.userlist;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.androidproject.R;
import com.example.androidproject.apiresources.UserListResource;
import com.example.androidproject.helper.api.APIClient;
import com.example.androidproject.helper.api.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A fragment representing a list of Items.
 */
public class UserResourceFragment extends Fragment {

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public UserResourceFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static UserResourceFragment newInstance(int columnCount) {
        UserResourceFragment fragment = new UserResourceFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_list_resource, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);
            Call<UserListResource> call1 = apiInterface.doGetListResources();
            try {
                call1.enqueue(new Callback<UserListResource>() {
                    @Override
                    public void onResponse(Call<UserListResource> call, Response<UserListResource> response) {
                        UserListResource listUser = response.body();
                        recyclerView.setAdapter(new MyUserResourceRecyclerViewAdapter((listUser.getData())));
                        System.out.println(listUser.getData().toString());
//                        Toast.makeText(getActivity().getApplicationContext(),"User added Successfully!",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<UserListResource> call, Throwable t) {
                        call.cancel();
                    }
                });

            } catch (Exception e) {
                Toast.makeText(getActivity().getApplicationContext(), "Api call unsuccessful", Toast.LENGTH_SHORT).show();
                throw new RuntimeException(e);
            }

        }
        return view;
    }
}