import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Subject group1 = new Subject("Группа 1");
        Subject group2 = new Subject("Группа 2");

        User user1 = new User("Пользователь 1");
        User user2 = new User("Пользователь 2");

        user1.subscribeTo(group1);
        user1.subscribeTo(group2);


        user2.subscribeTo(group1);
        user2.subscribeTo(group2);

        group1.postNotification("Что-то произошло в группе 1");

    }
}

interface SubjAction {
    void addObserver(Observer obs);
    void removeObserver(Observer obs);
    void notifyObservers();
}

class Subject implements SubjAction {
    public List<Observer> observers = new ArrayList<>();
    public String name;
    public String notification;

    public Subject(String name) {
        this.name = name;
    }

    public void postNotification(String text) {
        this.notification = text;
        System.out.println("Группа '" + name + "' отправила: " + text);
        notifyObservers();
    }

    @Override
    public void addObserver(Observer obs) {
        observers.add(obs);
        System.out.println("Пользователь " + ((User)obs).name + " подписался на " + name);
    }

    @Override
    public void removeObserver(Observer obs) {
        observers.remove(obs);
        System.out.println("Пользователь " + ((User)obs).name + " отписался от " + name);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}

interface Observer {
    void update(Subject subject);
}

class User implements Observer {
    public String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public void update(Subject subject) {
        System.out.println(name + ": Уведомление из '" + subject.name + "' - " + subject.notification);
    }

    public void subscribeTo(Subject group) {
        group.addObserver(this);
    }

    public void unsubscribeFrom(Subject group) {
        group.removeObserver(this);
    }
}