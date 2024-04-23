package com.team21.phishingfence.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class StatisticalTrendViewModel extends ViewModel {
    private MutableLiveData<Integer> choosenOption;
    public final static int PIECHART1 = 1;
    public final static int LINECHART2 = 2;
    public final static int BARCHART3 = 3;
    public final static int BARCHART4 = 4;

    public MutableLiveData<Integer> getChoosenOption() {
        if (this.choosenOption == null) {
            this.choosenOption = new MutableLiveData<>();
            this.choosenOption.setValue(StatisticalTrendViewModel.PIECHART1);
        }
        return choosenOption;
    }

    public void setChoosenOption(int option) {
        this.choosenOption.setValue(option);
    }

    public void nextChart() {//下一张
        switch (this.choosenOption.getValue()) {
            case PIECHART1 -> this.choosenOption.setValue(LINECHART2);
            case LINECHART2 -> this.choosenOption.setValue(BARCHART3);
            case BARCHART3 -> this.choosenOption.setValue(BARCHART4);
            case BARCHART4 -> this.choosenOption.setValue(PIECHART1);
        }
    }
}
