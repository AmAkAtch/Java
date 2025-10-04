package day13_polymorphism;

abstract class Animal {
    public abstract void makeSound();
}

class Dog extends Animal {

    @Override
    public void makeSound() {
        System.err.println("Woof");
    }

    public void fetch() {
        System.out.println("Fetching...");
    }
}

class Cat extends Animal {

    @Override
    public void makeSound() {
        System.out.println("Meow");
    }

    public void scratch() {
        System.out.println("Scratching...");
    }
}

public class InstaceOf {

    public static void main(String[] args) {
        Animal[] animals = new Animal[10];
        animals[0] = new Dog();
        animals[1] = new Cat();
        animals[2] = new Dog();

        for (Animal animal : animals) {
            animal.makeSound();

            if (animal instanceof Dog dog) {
                dog.fetch();
            } else if (animal instanceof Cat) {
                Cat cat = (Cat) animal;
                cat.scratch();
            }
        }
    }
}
