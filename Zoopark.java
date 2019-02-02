import java.util.Random;

//*1. Создать классы Собака и Кот с наследованием от класса Животное.
//2. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие. В качестве параметра каждому методу передается величина, означающая или длину препятствия (для бега и плавания), или высоту (для прыжков).
//3. У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.; прыжок: кот 2 м., собака 0.5 м.; плавание: кот не умеет плавать, собака 10 м.).
//4. При попытке животного выполнить одно из этих действий, оно должно сообщить результат в консоль. (Например, dog1.run(150); -> результат: run: true)
//5. * Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой 600 м. *//
class Zoopark {
    public static void main(String[] args) {
        Animal[] animals = {new Cat(), new DogSmall(), new DogBig(), new DogRandom()};

        for (Animal animal : animals) {
            System.out.printf("%s run: %s\n",
                    animal.getClass().getSimpleName(),
                    animal.run(201));

            System.out.printf("%s jump: %s\n",
                    animal.getClass().getSimpleName(),
                    animal.jump(0.5));

            System.out.printf("%s swim: %s\n\n",
                    animal.getClass().getSimpleName(),
                    animal.swim(8));
        }

        Animal cat = new Cat();
        Animal dogSmall = new DogSmall();
        Animal dogBig = new DogBig();

        assert !cat.run(201);
        assert cat.run(200);

        assert !dogSmall.run(0);
        assert !dogSmall.run(351);
        assert dogSmall.run(350);

        assert dogBig.run(351) == true;
        assert dogBig.run(560) == true;
        assert dogBig.run(561) == false;
    }
}
abstract class Animal {
    public abstract boolean run(double run);

    public abstract boolean jump(double jump);

    public abstract boolean swim(double swim);
}

class Cat extends Animal {
    @Override
    public boolean run(double run) {
        return (run > 0 && run <= 200);
    }

    @Override
    public boolean jump(double jump) {
        return (jump > 0 && jump <= 2);
    }

    @Override
    public boolean swim(double swim) {
        return false;
    }
}
abstract class Dog extends Animal {

    @Override
    public boolean jump(double jump) {
        return (jump > 0 && jump <= 0.5);
    }
}
class DogBig extends Dog {

    @Override
    public boolean run(double run) {
        return (run > 0 && run <= 560);
    }

    @Override
    public boolean swim(double swim) {
        return (swim > 0 && swim <= 10);
    }
}
class DogRandom extends Dog {

    private final double MAX_RUN;
    private final double MAX_SWIM;

    public DogRandom() {
        Random random = new Random();
        MAX_RUN = random.nextDouble() * (600.01 - 1.0) + 1.0; // 1 - 400
        MAX_SWIM = random.nextDouble() * (10.01 - 1.0) + 1.0; // 1 - 10
    }

    @Override
    public boolean run(double run) {
        return (run > 0 && run <= MAX_RUN);
    }

    @Override
    public boolean swim(double swim) {
        return (swim > 0 && swim <= MAX_SWIM);
    }
}
class DogSmall extends Dog {

    @Override
    public boolean run(double run) {
        return (run > 0 && run <= 350);
    }

    @Override
    public boolean swim(double swim) {
        return (swim > 0 && swim <= 3);
    }
}

