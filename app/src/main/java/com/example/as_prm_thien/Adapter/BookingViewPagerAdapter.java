package com.example.as_prm_thien.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.as_prm_thien.Booking.CanceledFragment;
import com.example.as_prm_thien.Booking.CompletedFragment;
import com.example.as_prm_thien.Booking.ScheduledFragment;

public class BookingViewPagerAdapter extends FragmentStateAdapter {
    private static final int NUM_TABS = 3;

    public BookingViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new CompletedFragment();
            case 2:
                return new CanceledFragment();
            default:
                return new ScheduledFragment();
        }
    }

    @Override
    public int getItemCount() {
        return NUM_TABS;
    }
}
