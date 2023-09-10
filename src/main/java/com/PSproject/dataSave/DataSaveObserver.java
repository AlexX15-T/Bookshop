package com.PSproject.dataSave;

import com.PSproject.helpers.Utility;
import lombok.Getter;
import lombok.Setter;

import java.util.Observable;
import java.util.Observer;

@Getter
@Setter
public class DataSaveObserver implements Observer {
    DataSaveObservable dataSaveObservable;

    public DataSaveObserver(DataSaveObservable dataSaveObservable) {
        this.dataSaveObservable = dataSaveObservable;
        dataSaveObservable.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o == dataSaveObservable) {
            Utility.saveAs(dataSaveObservable.getBooks(),dataSaveObservable.getType());
            System.out.println("DataSave changed to: " + dataSaveObservable.getType());
        }
    }
}
