package com.frlprojects.petscroll;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.frlprojects.petscroll.database.PetDbSchema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class PetListFragment extends Fragment {

    private RecyclerView mPetRecyclerView;
    private PetAdapter mAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    private class PetHolder extends RecyclerView.ViewHolder{
        private ImageView mPetImageView;
        private TextView mNameTextView;
        private TextView mScoreTextView;
        private ImageView mRatingImageView;
        private Pet mPet;



        public PetHolder(LayoutInflater inflater, ViewGroup parent ){
            super(inflater.inflate(R.layout.list_item_pet,parent,false));
            mNameTextView = (TextView) itemView.findViewById(R.id.pet_name);
            mScoreTextView = (TextView) itemView.findViewById(R.id.pet_rating);
            mPetImageView = (ImageView) itemView.findViewById(R.id.pet_image);
            mRatingImageView = (ImageView) itemView.findViewById(R.id.rating_button);

        }
        public void bind(Pet pet){
            mPet = pet;
            mNameTextView.setText(mPet.getName());
            mScoreTextView.setText(Integer.toString(mPet.getScore()));
            mPetImageView.setImageResource(mPet.getImage());
            mRatingImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String rating_value = Integer.toString(Integer.parseInt(mScoreTextView.getText().toString())+1);
                    mScoreTextView.setText(rating_value);
                    mPet.setScore(Integer.parseInt(rating_value));
                    PetLab.get(getActivity()).updatePet(mPet);
                }
            });
        }


    }
    private class PetAdapter extends RecyclerView.Adapter<PetHolder>{

        private List<Pet> mPets;

        public PetAdapter(List<Pet> pets){
            mPets = pets;
        }

        @NonNull
        @Override
        public PetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            return new PetHolder(layoutInflater,parent);
        }

        @Override
        public void onBindViewHolder(@NonNull PetHolder holder, int position) {
            Pet pet = mPets.get(position);
            holder.bind(pet);
        }

        @Override
        public int getItemCount() {
            return mPets.size();
        }

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_pet_list,container,false);

        mPetRecyclerView = (RecyclerView) view.findViewById(R.id.pet_recycler_view);
        mPetRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        PetLab petLab = PetLab.get(getActivity());
        List<Pet> pets = petLab.getPets(PetDbSchema.PetTable.Cols.NAME,null);


        mAdapter = new PetAdapter(pets);
        mPetRecyclerView.setAdapter(mAdapter);

        return view;
    }

    //Inyectamos el archivo de layout del menu, en el objeto menu
    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_pet_list,menu);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.top_five_pets){
            Intent intent = new Intent(getActivity(),TopFivePetsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
