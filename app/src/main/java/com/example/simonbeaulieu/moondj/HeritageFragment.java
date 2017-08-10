package com.example.simonbeaulieu.moondj;

import android.app.Fragment;
import android.app.FragmentTransaction;

/**
 * Created by simon.beaulieu on 07/08/2017.
 */

public class HeritageFragment extends Fragment {
    public static void instanciate(Fragment fragment, FragmentTransaction FT, boolean toCommit,boolean toAddToStack,int View_id,String fragmentTag){
        FT.replace(View_id,fragment,fragmentTag);
        if(toAddToStack){
            FT.addToBackStack(fragmentTag);
        }
        if(toCommit)
            FT.commit();
    }
}
