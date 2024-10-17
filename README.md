# Design Patterns in Java

Design patterns are typical solutions to commonly occurring problems in software design. They represent best practices and provide a way to structure code, making it more flexible, reusable, and maintainable. In this repository, we demonstrate three popular design patterns in Java: Singleton, Decorator, and Command patterns.

---

## Singleton Pattern

The **Singleton Pattern** ensures that a class has only one instance and provides a global point of access to it. This pattern is useful when exactly one object is needed to coordinate actions across the system.

### Singleton Pattern Demo Code

```java
// Singleton Pattern

package Sigletone_Patterns;
import java.util.Properties;

public class ConfigurationManager {

    private static ConfigurationManager instance;
    private Properties properties;

    private ConfigurationManager() {
        properties = new Properties();
        properties.setProperty("url", "http://example.com");
        properties.setProperty("username", "admin");
        properties.setProperty("password", "password");
    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setProperty(String key, String value) {
        properties.setProperty(key, value);
    }
}

class SingletonPatternDemo {
    public static void main(String[] args) {
        ConfigurationManager config1 = ConfigurationManager.getInstance();
        ConfigurationManager config2 = ConfigurationManager.getInstance();

        System.out.println("URL: " + config1.getProperty("url"));
        System.out.println("Username: " + config1.getProperty("username"));

        config1.setProperty("username", "newAdmin");

        System.out.println("Modified Username: " + config2.getProperty("username"));

        if (config1 == config2) {
            System.out.println("Both configuration manager instances are the same.");
        }
    }
}
```


## Decorator Pattern

The **Decorator Pattern** allows you to dynamically add behavior or functionality to an object without modifying its original structure. This pattern is ideal for enhancing the capabilities of individual objects without affecting other objects of the same class.

### Decorator Pattern Demo Code

```java
Copy code
// Decorator Pattern
package Decorator_Pattern;

interface Shape {
    void draw();
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }
}

// Decorator Class
abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape) {
        this.decoratedShape = decoratedShape;
    }

    public void draw() {
        decoratedShape.draw();
    }
}

// Concrete Decorator
class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape) {
        System.out.println("Border Color: Red");
    }
}

// Client Code
class DecoratorPatternDemo {
    public static void main(String[] args) {
        Shape circle = new Circle();
        Shape redCircle = new RedShapeDecorator(new Circle());

        System.out.println("Circle with normal border");
        circle.draw();

        System.out.println("\nCircle with red border");
        redCircle.draw();
    }
}
```


## Command Pattern

The **Command Pattern** is a behavioral design pattern that turns a request into a stand-alone object that contains all information about the request. It allows for parameterizing objects with different requests, delaying the execution of a request, or supporting undoable operations.

### Command Pattern Demo Code

```java
Copy code
// Command Pattern
package Command_Pattern;

interface Command {
    void execute();
}

class Light {
    public void turnOn() {
        System.out.println("Light is on");
    }

    public void turnOff() {
        System.out.println("Light is off");
    }
}

class TurnOnLightCommand implements Command {
    private Light light;

    public TurnOnLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }
}

class TurnOffLightCommand implements Command {
    private Light light;

    public TurnOffLightCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOff();
    }
}

class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

class CommandPatternDemo {
    public static void main(String[] args) {
        Light livingRoomLight = new Light();

        Command turnOnLight = new TurnOnLightCommand(livingRoomLight);
        Command turnOffLight = new TurnOffLightCommand(livingRoomLight);

        RemoteControl remote = new RemoteControl();

        remote.setCommand(turnOnLight);
        remote.pressButton();

        remote.setCommand(turnOffLight);
        remote.pressButton();
    }
}
```