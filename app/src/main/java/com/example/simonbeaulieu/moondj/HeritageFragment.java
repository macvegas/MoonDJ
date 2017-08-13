package com.example.simonbeaulieu.moondj;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

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

    public static void hideFragment(Fragment fragment, FragmentTransaction ft){
        if (fragment.isVisible()){
            ft.hide(fragment);
            ft.commit();
        }
    }

    public static void showFragment(Fragment fragment, FragmentTransaction ft){
        if (fragment.isHidden()){
            ft.show(fragment);
            ft.commit();
        }
    }
}
