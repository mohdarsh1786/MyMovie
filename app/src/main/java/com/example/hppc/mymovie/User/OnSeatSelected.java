package com.example.hppc.mymovie.User;
import java.util.List;


public interface OnSeatSelected {
    void onSeatSelected(int count);
    List<Integer> getSelectedSeat();
}
