package uk.co.rossbeazley.avp.android.ui;

import android.R;
import android.app.Fragment;
import android.app.FragmentManager;

public class FragmentManagerFragmentStack implements FragmentStack {

    private final FragmentManager fm;

    public FragmentManagerFragmentStack(FragmentManager fm) {
        this.fm = fm;
    }

    @Override
    public void pushFragment(Class<? extends Fragment> fragmentClass) {

        try {

            attemptToPushFragment(fragmentClass);

        } catch (InstantiationException e) {
            processException(e);
        } catch (IllegalAccessException e) {
            processException(e);
        }

    }

    private void attemptToPushFragment(Class<? extends Fragment> fragmentClass) throws InstantiationException, IllegalAccessException {
        Fragment fragment = createFragment(fragmentClass);
        addFragmentToBackStack(fragment);
    }

    private Fragment createFragment(Class<? extends Fragment> fragmentClass) throws InstantiationException, IllegalAccessException {
        return fragmentClass.newInstance();
    }

    private void addFragmentToBackStack(Fragment fragment) {
        fm.beginTransaction()
                .add(R.id.content, fragment)
                .commit();
    }


    private void processException(Exception e) {
        e.printStackTrace();
    }
}
