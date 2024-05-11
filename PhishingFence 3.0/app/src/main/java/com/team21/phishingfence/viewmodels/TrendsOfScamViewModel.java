package com.team21.phishingfence.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class TrendsOfScamViewModel extends ViewModel {
    private MutableLiveData<Integer> chooseOption;

    public final static int BARCHART1 = 1;
    public final static int LINECHART2 = 2;
    public final static int BARCHART3 = 3;

    public MutableLiveData<Integer> getChooseOption() {
        if (this.chooseOption == null) {
            this.chooseOption = new MutableLiveData<>();
            this.chooseOption.setValue(TrendsOfScamViewModel.BARCHART1);
        }
        return chooseOption;
    }

    public void setChooseOption(int option) {
        this.chooseOption.setValue(option);
    }

    public void nextChart() {//下一张
        switch (this.chooseOption.getValue()) {
            case BARCHART1 -> this.chooseOption.setValue(LINECHART2);
            case LINECHART2 -> this.chooseOption.setValue(BARCHART3);
            case BARCHART3 -> this.chooseOption.setValue(BARCHART1);
        }
    }


}
