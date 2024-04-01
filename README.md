### JAVA 8 NEW Features (URL to access all API's http://localhost:9090/swagger-ui/index.html#/)

1. **Lambda Expression (LE)**
   1. LE is used to provide the implementation of **Functional Interface** e.g. Runnable.
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
   2. These are used to call a method by referring to it with the help of it's class name and it's syntax is -
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
   8. For more nfo refer below links -
   * GFG - https://www.geeksforgeeks.org/double-colon-operator-in-java/
   * JAVATPOINT - https://www.javatpoint.com/java-8-method-reference