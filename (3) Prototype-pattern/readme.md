## Problem

Lets say we have a object of a class and we want to creatae copy of this object

# Screnario wehre this problem can come up

- Lets say we have a chess board
- We have piece class and we might need to create multiple copies of the same piece ex: Pawns
- Instead of creating instances of pawn we can create copies of the one pawn
- maybe the constructor call is expensive thats why

## Example of expensive constructor calls

- example : DB connection calls , File IO and Email object(Email instance) etc theese are constructor calls and these are expensive construtor calls
- Say we have a email class and we have an that creates a new object every time for senfding email for that the constructor does File IO to load the template file and than does sting manipulation for updating the template for each user here File IO is expensive because the body will be different , the recipients will be different

## Solutions

- Lets say we only know basic of programming and SOLID

# Brute force (Nice solution)

- Lets say we have a email class
  and this has attributes like sender , subject,body etc
- SO will create a new email like
  Email e=new Email()
- to rceate a copy lets put all teh properties one by one to a new object

- So we instead call a default constructor
- Email copy=new Email()
  copy.setSender(e.getSender())
  copy.setSubject(e.getSubject())
  copy.setBody(e.getBody.replace(---,"..."))
- The regex will be loaded by the base email object

- now the copy object doesnt have to load the body again it will replace the regex portion thats it using replace
- The base class will be in the driver class
- The question is are there any problems
  is there any issues in this approach ?
-

1. This approach violates the DRY principle (If we have to create DRY principle for resolving this will create a function to resolve it) still it violates the OCP
2. OCP why ?(if the attributes changes tomorrow than than the copies will also be have to be changed) and SRP as well

-- And there is also a tight coupling between the function and the class

# Improvement 1

- Problems :

1. We decided to put the function which will create the copies but it sits outside but what if we put the new copy function inside the Class itself ? that can technically help us
2. So the cloning of the Object will be handled by the Class only

# Improvement 2

1. Solution : Copy Constructor in C++ such that we write a class
   class Email {
   private String sender
   private String body
   private String subject
   Email(Email e){
   Email copy=new Email()
   copy.setSender(e.getSender)
   return copy
   }
   }
