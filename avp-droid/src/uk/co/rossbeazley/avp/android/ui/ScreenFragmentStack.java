package uk.co.rossbeazley.avp.android.ui;

import android.R;
import android.app.Fragment;
import android.app.FragmentManager;

public class ScreenFragmentStack implements ScreenStack {

    private final FragmentManager fm;
    private FragmentFromScreen fragmentFromScreen;

    public ScreenFragmentStack(FragmentManager fm, FragmentFromScreen fragmentFromScreen) {
        this.fm = fm;
        this.fragmentFromScreen = fragmentFromScreen;
    }

    @Override
    public void pushScreen(Class<? extends Screen> screenClass) {
        Class<? extends Fragment> fragmentClass = this.fragmentFromScreen.fragmentClass(screenClass);
        pushFragment(fragmentClass);
    }


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
        Fragment fragment = createFragmentFromClass(fragmentClass);
        addFragmentToBackStack(fragment);
    }

    private Fragment createFragmentFromClass(Class<? extends Fragment> fragmentClass) throws InstantiationException, IllegalAccessException {
        return fragmentClass.newInstance();
    }

    private void addFragmentToBackStack(Fragment fragment) {
        fm.beginTransaction()
                .replace(R.id.content, fragment)
                .addToBackStack(fragment.getClass().getSimpleName())
                .commit();
    }


    private void processException(Exception e) {
        e.printStackTrace();
    }
}
