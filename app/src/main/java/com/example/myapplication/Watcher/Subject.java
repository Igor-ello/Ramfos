package com.example.myapplication.Watcher;

public interface Subject {
    public void registerObserver(Observer o); //регистрируемый или исключаемый наблюдатель
    public void removeObserver(Observer o); //регистрируемый или исключаемый наблюдатель
    public void notifyObservers(); //Этот метод вызывается для оповещения наблюдателей об изменении состояния субъекта
}
