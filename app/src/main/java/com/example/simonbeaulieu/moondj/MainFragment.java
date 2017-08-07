package com.example.simonbeaulieu.moondj;

import android.app.Fragment;
import android.app.FragmentTransaction;

/**
 * Created by simon.beaulieu on 07/08/2017.
 */

public class MainFragment extends Fragment {
    public static void instanciate(Fragment fragment, FragmentTransaction FT, boolean toCommit,boolean toAddToStack,int View_id,String backstackMessage){
        FT.replace(View_id,fragment);
        if(toAddToStack)
            FT.addToBackStack(backstackMessage);
        if(toCommit)
            FT.replace(View_id,fragment);
    }
    public static void instanciate(Fragment fragment, FragmentTransaction FT, boolean toCommit,boolean toAddToStack,int View_id){
        FT.replace(View_id,fragment);
        if(toAddToStack)
            FT.addToBackStack("step added");
        if(toCommit)
            System.out.println("COUCOUZOBISOU");
            FT.replace(View_id,fragment);
    }
}
