package com.frlprojects.petscroll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.os.Bundle;

public class PetListActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() { return new PetListFragment(); }
    }
