package uk.co.rossbeazley.avp.android.ui.screenStack;

import android.app.Fragment;
import uk.co.rossbeazley.avp.android.ui.Screen;
import uk.co.rossbeazley.avp.android.ui.ScreenStack;

final class ScreenFragmentStack implements ScreenStack {

    private final FragmentTransaction fragmentTransaction;
    private FragmentFromScreen fragmentFromScreen;

    ScreenFragmentStack(FragmentFromScreen fragmentFromScreen, FragmentTransaction fragmentTransaction) {
        this.fragmentTransaction = fragmentTransaction;
        this.fragmentFromScreen = fragmentFromScreen;
    }

    @Override
    public void pushScreen(Class<? extends Screen> screenClass) {
        Class<? extends Fragment> fragmentClass = this.fragmentFromScreen.fragmentClass(screenClass);
        pushFragment(fragmentClass);
    }


    private void pushFragment(Class<? extends Fragment> fragmentClass) {
        try {
            attemptToPushFragment(fragmentClass);
        } catch (InstantiationException e) {
            processException(e);
        } catch (IllegalAccessException e) {
            processException(e);
        }
    }

    private void attemptToPushFragment(Class<? extends Fragment> fragmentClass) throws InstantiationException, IllegalAccessException {
        Fragment fragment = fragmentClass.newInstance();
        fragmentTransaction.addFragmentToBackStack(fragment);
    }


    private void processException(Exception e) {
        e.printStackTrace();
    }
}
