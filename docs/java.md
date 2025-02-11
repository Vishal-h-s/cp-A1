# day 1 and 2
Only one public class per Java file compiler to get multiple class files each class in Java file can have a main method call A class file and main method will execute what is the purpose of main method in non public class 

Public static main in a class is called by JVM when a class is executed without static it such chicken and egg problem however if the class is not supposed to be executed then the main class is free to Be anything 

All wrapper classes in Java are immutable when an object is to be updated new object is created and previous object if it has no difference is eligible for garbage collection 

However s1.kankat returns in your object if not assigned will have no reference 

Packages

# 10-2-25
```java
String.format("%x",num);
			  "%X"
			  "%h"
			  "%H"
	  .toHexString(num);
								
```

what's a constructor?
- simply a method with same name as the class

what if no constructor?
or 
why constructor? (in a language like c++)
- in simple words - without it you cant create an object

member without an access specifier 
java has an access modifier named "default"
c++ is "private" by default

### getter and setter
- encapsulation and data hiding
#### bank account - a standard OOP example
can you edit your balance? no
only internal methods can modify it like deposit withdraw
getter is public in order to view the balance

" ASK NOT WHAT THE SYSTEM DOES,
ASK WHAT IT DOES IT ***TO*** " 
	 - Mr. Tapadia

###
```java
Circle c1=new Circle();
```
Circle class dosent have a constructor 
default constructor will be called i.e. without arguments
	it initiates primitives to default values
	and wrapper class types to null (reference)

once you define your own construction
the default dosent exist anymore
##### you can overload the constructor just like any other method

constructor return an object of type ***this*** (same as the class)
no need to mention the return type

a constructor with a return type is not a constructor but just another method 

### initializer block
order
1. Class initiation blocks (in order of definition) only once - when the class is loaded
2. Instance initialization blocks
3. constructor

```java
class Init{
	static int value;
	{value=20;}
	
	psvm(sa){
		k
	}
}



```

# 11-02-25
---
### Calling a Method on a Null Object (NullPointerException)**
#### **Mistake in the code:**
```java
javaobj[0].m(); // Causes NullPointerException
```
- `obj[0]` is `null` because we only created an array but didn't initialize the objects.
#### **Why Does This Cause an Exception?**
- Since `obj[0]` is `null`, calling `m()` results in:
```java
cppException in thread "main" java.lang.NullPointerException
```
- Java does not automatically instantiate objects inside an object array.
#### **Correct Approach:**
```java
javaobj[0] = new Three(); // Initialize obj[0]
obj[0].m(); // Now this works without error
```
---