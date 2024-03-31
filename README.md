### JAVA 8 NEW Features

1. Lambda Expression (LE)
   1. LE is used to provide the implementation of **Functional Interface**.
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
   