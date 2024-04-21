## JAVA 8 NEW Features (URL to access all API's http://localhost:9090/swagger-ui/index.html#/)

1. **Lambda Expression (LE)**
   1. LE is used to provide the implementation of **Functional Interface** e.g. Runnable (only run() Method), Comparable (only compareTo() method) etc.
   2. LE saves the lot of code i.e. no need to write another class and then provide implementation of method again, Here we can simply provide the implementation.
   3. Java lambda expression is treated as a function, so compiler does not create .class file for them.
   4. LE syntax -
   ```
   (argumentList) -> {Body}
   ``` 
   * **argumentList :** It can be empty or non-empty as well. in case of one argument brackets (()) are optional.
   * **Arrow-Token :** It is used to link arguments-list and body of expression.
   * **Body :** Body contains implementation of the lambda expression. For single line implementation in body both parenthesis ({}) and 'Return' statement is optional.
   * e.g. 
   ```
   (key1, key2,............. we can ave as many keys as we want) -> {
      // Body
   }
   ```
   5. For more info refer -- https://www.javatpoint.com/java-lambda-expressions

2. **Method Reference (::) (MR)**
   1. The **double colon (::) operator** is known as **Method Reference Operator** in JAVA.
   2. These are used to call a method by referring to it with the help of it's class name, and it's syntax is -
   ```
   <Class name (for static Methods) / Class Object (for Non-static Methods)>::<method name>
   ```
   3. It is compact and easy form of lambda expression, They behave exactly as lambda expression.
   4. Each time when we are using LE to just refer a method, we can replace LE with method reference e.g.
   ```
   List<String> s = existingList.stream().map(oldString -> oldString.toUpperCase()).collect(Collectors.toList());
                                          OR
   List<String> s = existingList.stream().map(String::toUpperCase).toList();
   ```
   5. Whenever we define a Functional Interface using lambda but that definition is already written somewhere inside a function then we can directly refer that method using method reference e.g.
   ```
   interface Inter{
      void show();
   }
   
   class Main{
      public static void Naruto(){
         System.out.println("My Favourite");
      }
   
      pulic static void main(String []args){
         Inter i = () -> System.out.println("My Favourite");
                         OR
         Iner i = Main::Naruto;
      }
   }
   ```
   6. There are three tyes of method references -
   * Reference to a Static method
     * above example is the reference of static method.
   * Reference to a Non-Static method
     * in the above example if the method is non-static then we call it like new Main()::Naruto. another e.g.
     ```
     stream.forEach(System.out::println);
     ```
   * Reference to a Constructor
     * it's syntax is -
     ```
     ClassName::new
     ```
     * for e.g. see the code.
   7. Method reference is used to replace the lambda expression but can we use them outside the scope of the lambda? let's see
   ```
   // without lambda
   String s = oldString.toUpperCase();   // this is fine but can we use method refernce here
   String s = String::toUpperCase;     // this is wrong bcz now on which element 'toUpperCase' func need to apply
                                       // we don't have that information here.
   ```
   8. we can also use method reference operator with **'this'** keyword. Suppose if a method is present in the same class and it's a non-static method and the object of the current class is already created then with the help of this we can call current class method no need to create a new object e.g. -
   ```
   this::METHOD_NAME
   ```
   9. For more nfo refer below links -
   * GFG - https://www.geeksforgeeks.org/double-colon-operator-in-java/
   * JAVATPOINT - https://www.javatpoint.com/java-8-method-reference


3. **Functional Interface (FI)**
   1. A FI is an interface which contains only **one abstract** method. They can have only one functionality to exhibit and FI is present in JAVA.lang package.
   2. FI is also known as **Single Abstract Method Interfaces (SAM Interfaces)**.
   3. A FI can have any no. of **default and static** methods.
   4. In FI we can **declare** any no. of methods of object class.
   5. **@FunctionalInterface** annotation is used to mark an interface as an FI, but it is optional but it'll be better to use annotation so that if in future anyone will see this annotation then he will not disturb the interface.
   6. If an interface is marked with @FunctionalInterface annotation and if we will try to write more than one abstract method in that then compiler will throw an error.
   ```
   @FunctionalInterface  
   interface sayable{  
      void say(String msg);   // abstract method  
      // It can contain any number of Object class methods.  
      int hashCode();  
      String toString();  
      boolean equals(Object obj);  
   }
   ```
   7. **Lambda expression can be used to represent the instance of a FI.**
   ```
   // creating a thread using Thread class w/o Runnable (A FI)
   Thread t = new Thread(either pass the object of class implementing Runnable interface 
                           or Lambda expression);
   t.start();
   Object = new ClassImplementingRunnable();
   Lambda expression be like -- () -> {// body of thread}
   ```
   8. **A FI can extends another interface only when it does not have any abstract method i.e. if inter1 is a FI and inter2 is an interface then inter1 entends inter2 is allowed only when inter2 don't have any abstract methods.**
   9. Mainly there are 4 types of FI present  and all following FIes are present in JAVA.util package -
      1. Consumer Interface (CI)
         * CI is a FI which accepts only one argument and does not return anything likewise we have Bi-Consumer that consumes two argument but does not return anything.
         * Following abstract method present in the Consumer interface -
         ```
         void accept(T t);
         ```
         * forEach loop accept Consumer Interface.
      2. Predicate Interface (PI)
         * PI is a FI which accepts only one argument and return a boolean value likewise we have bi-predicate which accepts two values and return boolean value.
         * Following abstract method present in the Consumer interface -
         ```
         boolean test(T t);
         ```
         * filter function in stream uses / accepts Predicate interface in it's implementation.
      3. Function Interface
         * A Function interface is a FI which accepts one argument and returns a value after the required processing likewise we have Bi-Function interface.
         * Following abstract method present in the Consumer interface -
         ```
         R apply(T t);
         ```
         * Map function in stream uses / accepts Function interface in it's implementation.
         * There are two other FI present **Unary Operator and Binary Operator**. They both extend the Function and Bi-Function respectively.
         * Reduce in stream uses Binary operator.
      4. Supplier interface (SI)
         * A SI is a FI that does not take any input and yet returns a single output.
         * This kind of interfaces are used for defining the logic for generation of any sequences like fibonacci.
         * Following abstract method present in the Supplier interface -
         ```
         T.get();
         ```
         * e.g. 1->
         ```
         Supplier<Double> randomValue = () -> Math.random();
         System.out.println(randomValue.get());
         ```
         2-> Generate function in stream use supplier function
         ```
         Stream.generate(new Random()::nextInt) 
         .limit(5).forEach(System.out::println);
         
         // o/p
            697197501
            50139200
            321540264
            1042847655
            -770409472
         ```
      10. for references 
          * JAVATPOINT -- https://www.javatpoint.com/java-8-functional-interfaces
          * GeeksForGeeks -- https://www.geeksforgeeks.org/functional-interfaces-java/
4. Stream API
   1. 
   