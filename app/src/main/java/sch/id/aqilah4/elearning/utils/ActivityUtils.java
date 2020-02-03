package sch.id.aqilah4.elearning.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ActivityUtils {
    public static void goToFragment(FragmentManager fragmentManager, Fragment fragment, int framelayout){
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(framelayout, fragment, fragment.getTag());
        fragmentTransaction.commit();
    }
}
