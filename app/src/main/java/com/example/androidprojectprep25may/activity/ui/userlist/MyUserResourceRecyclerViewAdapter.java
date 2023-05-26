package com.example.androidprojectprep25may.activity.ui.userlist;

import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androidprojectprep25may.apiresources.UserResource;
import com.example.androidprojectprep25may.databinding.FragmentUserResourceBinding;
import com.example.androidprojectprep25may.helper.Functions;
import com.example.androidprojectprep25may.helper.api.DownloadImageFromInternet;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link UserResource}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyUserResourceRecyclerViewAdapter extends RecyclerView.Adapter<MyUserResourceRecyclerViewAdapter.ViewHolder> {

    private final List<UserResource> mValues;

    public MyUserResourceRecyclerViewAdapter(List<UserResource> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentUserResourceBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdOutput.setText(mValues.get(position).getId().toString());
//        holder.mEmailOutput.setText(mValues.get(position).getEmail());
        holder.mFirstNameOutput.setText(mValues.get(position).getFirstName());
        holder.mLastNameOutput.setText(mValues.get(position).getLastName());
        new DownloadImageFromInternet(holder.mPhotoOutput,holder.itemView.getContext())
                .execute(mValues.get(position).getAvatar());

//        holder.mPhotoOutput
//                .setImageURI(Uri.parse(mValues.get(position).getAvatar()));
//                .setImageDrawable(
//                        Functions.LoadImageFromWebOperations(
//                                mValues.get(position).getAvatar())
//                );
        System.out.println(mValues.get(position).getAvatar());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView mIdOutput;
        public final TextView mEmailOutput;
        public final TextView mFirstNameOutput;
        public final TextView mLastNameOutput;
        public final ImageView mPhotoOutput;

        public UserResource mItem;

        public ViewHolder(FragmentUserResourceBinding binding) {
            super(binding.getRoot());
            mIdOutput = binding.idOutput;
            mEmailOutput = binding.emailOutput;
            mFirstNameOutput = binding.firstNameOutput;
            mLastNameOutput = binding.lastNameOutput;
            mPhotoOutput = binding.photoOutput;
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mEmailOutput.getText() + "'";
        }
    }
}