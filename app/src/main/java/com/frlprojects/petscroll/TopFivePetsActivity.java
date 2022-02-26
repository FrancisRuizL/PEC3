package com.frlprojects.petscroll;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.os.Bundle;

public class TopFivePetsActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment() { return new TopFivePetsFragment(); }
}
