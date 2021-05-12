package com.rysecamp.utils;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import android.util.Log;
import android.widget.FrameLayout;

import com.rysecamp.controller.fragments.BaseFragment;

import java.util.List;

/**
 * Created by ankush on 4/10/17.
 */
public class CustomWizard implements Parcelable {

    public static final Creator<CustomWizard> CREATOR = new Creator<CustomWizard>() {
        @Override
        public CustomWizard createFromParcel(Parcel source) {
            return new CustomWizard(source);
        }

        @Override
        public CustomWizard[] newArray(int size) {
            return new CustomWizard[size];
        }
    };
    private List<BaseFragment> fragmentList;
    private FragmentActivity context;
    private FrameLayout container;
    private int containerId;
    private int enterAnimation = 0;
    private int exitAnimation = 0;
    private int popEnterAnimation = 0;
    private int popExitAnimation = 0;
    private BaseFragment currentFragment;
    private int currentFragmentIndex = 0;

    public CustomWizard(FragmentActivity context, List<BaseFragment> fragmentList, FrameLayout containerId) {
        this.context = context;
        this.fragmentList = fragmentList;
        this.container = containerId;
        this.containerId = containerId.getId();
    }

    public CustomWizard(FragmentActivity context, FrameLayout containerId) {
        this.context = context;
        container = containerId;
        this.containerId = containerId.getId();
    }

    public CustomWizard(FragmentActivity context, FrameLayout containerId, int enterAnimation,
                        int exitAnimation, int popEnterAnimation, int popExitAnimation) {
        this.context = context;
        this.container = containerId;
        this.containerId = containerId.getId();
        this.enterAnimation = enterAnimation;
        this.exitAnimation = exitAnimation;
        this.popEnterAnimation = popEnterAnimation;
        this.popExitAnimation = popExitAnimation;
    }

    public CustomWizard(FragmentActivity context, List<BaseFragment> fragmentList, FrameLayout containerId, int enterAnimation,
                        int exitAnimation, int popEnterAnimation, int popExitAnimation) {
        this.context = context;
        this.fragmentList = fragmentList;
        this.container = containerId;
        this.containerId = containerId.getId();
        this.enterAnimation = enterAnimation;
        this.exitAnimation = exitAnimation;
        this.popEnterAnimation = popEnterAnimation;
        this.popExitAnimation = popExitAnimation;
    }

    protected CustomWizard(Parcel in) {
        this.containerId = in.readInt();
        this.enterAnimation = in.readInt();
        this.exitAnimation = in.readInt();
        this.popEnterAnimation = in.readInt();
        this.popExitAnimation = in.readInt();
        this.currentFragmentIndex = in.readInt();
    }

    public void addFragment(BaseFragment fragment) {
        fragmentList.add(fragment);
    }

    public void addFragment(BaseFragment fragment, int position) {
        fragmentList.add(position, fragment);
    }

    public void removeFragment(int position) {
        fragmentList.remove(position);
    }

    public void removeFragment(Fragment fragmentObject) {
        fragmentList.remove(fragmentObject);
    }

    public void removeFragment(List<Fragment> collections) {
        fragmentList.removeAll(collections);
    }

    public List<BaseFragment> getFragmentList() {
        return fragmentList;
    }

    public Boolean navigateToNextFragment(FragmentActivity context, String addToBackStack) {
        this.context = context;
        if (fragmentList != null && fragmentList.size() > currentFragmentIndex + 1) {
            currentFragmentIndex++;
            currentFragment = fragmentList.get(currentFragmentIndex);
            if (enterAnimation == 0 && exitAnimation == 0 && popEnterAnimation == 0 && popExitAnimation == 0) {
                context.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(containerId, currentFragment, currentFragment.getClass().getSimpleName())
                        .addToBackStack(addToBackStack)
                        .commit();
            } else {
                context.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(containerId, currentFragment, currentFragment.getClass().getSimpleName())
                        .setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
                        .addToBackStack(addToBackStack)
                        .commit();
            }
            return true;
        } else {
            Log.d(getClass().getSimpleName(), "This is last fragment in fragments list");
            return false;
        }
    }

    public Boolean navigateToNextFragment(FragmentActivity context) {
        this.context = context;
        if (fragmentList != null && fragmentList.size() > currentFragmentIndex + 1) {
            currentFragmentIndex++;
            currentFragment = fragmentList.get(currentFragmentIndex);
            if (enterAnimation == 0 && exitAnimation == 0 && popEnterAnimation == 0 && popExitAnimation == 0) {
                context.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(containerId, currentFragment, currentFragment.getClass().getSimpleName())
                        .commit();
            } else {
                context.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(containerId, currentFragment, currentFragment.getClass().getSimpleName())
                        .setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
                        .commit();
            }
            return true;
        } else {
            Log.d(getClass().getSimpleName(), "This is last fragment in fragments list");
            return false;
        }
    }

    public Boolean navigateToFirstFragment(FragmentActivity context) {
        this.context = context;
        if (fragmentList != null && fragmentList.size() > 0) {
            currentFragmentIndex = 0;
            currentFragment = fragmentList.get(currentFragmentIndex);
            if (enterAnimation == 0 && exitAnimation == 0 && popEnterAnimation == 0 && popExitAnimation == 0) {
                context.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(containerId, currentFragment, currentFragment.getClass().getSimpleName())
                        .commit();
            } else {
                context.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(containerId, currentFragment, currentFragment.getClass().getSimpleName())
                        .setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
                        .commit();
            }
            return true;
        } else {
            Log.d(getClass().getSimpleName(), "This is last fragment in fragments list");
            return false;
        }
    }

    public Boolean navigateToNextFragment(FragmentActivity context, String addToBackStack, int position) {
        this.context = context;
        if (fragmentList != null && fragmentList.size() > position) {
            currentFragmentIndex = position;
            currentFragment = fragmentList.get(currentFragmentIndex);
            if (enterAnimation == 0 && exitAnimation == 0 && popEnterAnimation == 0 && popExitAnimation == 0) {
                context.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(containerId, currentFragment, currentFragment.getClass().getSimpleName())
                        .addToBackStack(addToBackStack)
                        .commit();
            } else {
                context.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(containerId, currentFragment, currentFragment.getClass().getSimpleName())
                        .setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
                        .addToBackStack(addToBackStack)
                        .commit();
            }
            return true;
        } else {
            Log.d(getClass().getSimpleName(), "This is last fragment in fragments list");
            return false;
        }
    }

    public void reInitFragmentsList(List<BaseFragment> fragmentList) {
        this.fragmentList = fragmentList;
    }

    public void navigateToNextFragmentAfterRestore(FragmentActivity context, String addToBackStack, int position) {
        this.context = context;
        FragmentManager fragmentManager = context.getSupportFragmentManager();
        while (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStackImmediate();
        }
        for (int i = 0; i < position + 1; i++) {
            if (fragmentList != null && fragmentList.size() > i) {
                currentFragmentIndex = i;
                currentFragment = fragmentList.get(i);
                if (currentFragmentIndex != 0) {
                    if (enterAnimation == 0 && exitAnimation == 0 && popEnterAnimation == 0 && popExitAnimation == 0) {
                        context.getSupportFragmentManager()
                                .beginTransaction()
                                .replace(containerId, currentFragment, currentFragment.getClass().getSimpleName())
                                .addToBackStack(addToBackStack)
                                .commit();
                    } else {
                        context.getSupportFragmentManager()
                                .beginTransaction()
                                .replace(containerId, currentFragment, currentFragment.getClass().getSimpleName())
                                .setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
                                .addToBackStack(addToBackStack)
                                .commit();
                    }
                } else {
                    if (enterAnimation == 0 && exitAnimation == 0 && popEnterAnimation == 0 && popExitAnimation == 0) {
                        context.getSupportFragmentManager()
                                .beginTransaction()
                                .replace(containerId, currentFragment, currentFragment.getClass().getSimpleName())
                                .commit();
                    } else {
                        context.getSupportFragmentManager()
                                .beginTransaction()
                                .replace(containerId, currentFragment, currentFragment.getClass().getSimpleName())
                                .setCustomAnimations(enterAnimation, exitAnimation, popEnterAnimation, popExitAnimation)
                                .commit();
                    }
                }
            } else {
                Log.d(getClass().getSimpleName(), "This is last fragment in fragments list");
                break;
            }
        }
    }

    public int getCurrentFragmentIndex() {
        return currentFragmentIndex;
    }

    public Fragment getCurrentFragment() {
        return currentFragment;
    }

    public String getCurrentFragmentTag() {
        return currentFragment.getClass().getSimpleName();
    }

    public String getFragmentTag(int position) {
        if (fragmentList.size() > position)
            return fragmentList.get(position).getClass().getSimpleName();
        else
            return null;
    }

    public Boolean moveToBackStack(FragmentActivity context) {
        this.context = context;
        if (currentFragmentIndex > 0) {
            currentFragmentIndex--;
            FragmentManager fm = context.getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                try {
                    fm.popBackStack();
                } catch (Exception e) {
                    e.printStackTrace();
                    try {
                        fm.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.containerId);
        dest.writeInt(this.enterAnimation);
        dest.writeInt(this.exitAnimation);
        dest.writeInt(this.popEnterAnimation);
        dest.writeInt(this.popExitAnimation);
        dest.writeInt(this.currentFragmentIndex);
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentFragmentIndex = currentIndex;
    }
}
