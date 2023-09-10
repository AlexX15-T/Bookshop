package com.PSproject.dataSave;

import com.PSproject.model.Book;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

@Getter
@Setter
public class DataSaveObservable extends Observable {
    private String type;
    private List <Book> books;
    private List<Observer> observers;

    public DataSaveObservable(String type) {
        this.type = type;
        observers = new ArrayList<>();
        books = new ArrayList<>();
    }

    public void setType(String type) {
        this.type = type;
        setChanged();
        notifyObservers();
    }

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void notifyObservers() {
        for(Observer o : observers) {
            o.update(this, null);
        }
    }
}
