package com.example.androidproject.activity.ui.createuser;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.androidproject.apiresources.CreateUserResource;
import com.example.androidproject.databinding.FragmentCreateUserBinding;
import com.example.androidproject.helper.api.APIClient;
import com.example.androidproject.helper.api.APIInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateUserFragment extends Fragment {

    private FragmentCreateUserBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        SlideshowViewModel slideshowViewModel =
//                new ViewModelProvider(this).get(SlideshowViewModel.class);

        binding = FragmentCreateUserBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        binding.createUserBtn.setOnClickListener(view -> {
            APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

            String name = binding.nameInput.getText().toString();
            String job = binding.jobInput.getText().toString();

            CreateUserResource user = new CreateUserResource(name, job);
            Call<CreateUserResource> call1 = apiInterface.createUser(user);
            call1.enqueue(new Callback<CreateUserResource>() {
                @Override
                public void onResponse(Call<CreateUserResource> call, Response<CreateUserResource> response) {
                    CreateUserResource user1 = response.body();
                    System.out.println(user1);
                    Toast.makeText(getActivity().getApplicationContext(),"User added Successfully!",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<CreateUserResource> call, Throwable t) {
                    call.cancel();
                }
            });
        });

//        final TextView textView = binding.textSlideshow;
//        slideshowViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}