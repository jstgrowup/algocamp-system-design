# Question

- We want to create a User class with multiple attributes like lastname , age ,email passowrd etc . We also neeed to add a bunch of validations as well that might include a combination of 2 or more than 2 attributes
- Class User{
  Stirng firstName;
  String lastName
  String email
  String password
  int age

}

all the properties have the visibility public
when its actually public means it can be changed as well from outside
means
u=new User(

)
u.firstName=:
this is now possible but may be we dont want an empty firstName thats our validation

- if we keep things public we cant enforce validations
- Now this time lets assume all of these properties are private
- now no one will be able to modify the properties outside of the class
- People wont be able to even access the property outside the class
- we can introduce getter and setter functions
- void setFirstName(String x){
  if(x=null or x.length()==0){
  throw
  }
  this.firstName=x
  }
  But hte issue is the object is already created because the object was created using default constructor till the time we dont manually call all the relevant setters this object is invalid as it doesnt go through any validations

## Solution 2

- What if we put all the validations checks in the constructor call that means we make a custom constructor where we have all the valiadtio in place apart from getter setter so taht if any validation fails we will not allow objct creation
- class User {
  User (String firstName, Stirng lastname, int age, String email){
  if(firstName==null ){
  ...throw
  }
  }
  }
- This solution brings more solutions

1.  So many attributes that will passed to the constructor and we have to remember the parameters order and adding a new param is also a headache and the client code will be less readable

2.  LEts say we add a parameter called company and its not a mendatory parameter but because of that we have to add a validation and every user is not associated to a company but if the user is associated to a company than it should be associated to a valid company only now this company it will create un managable code
3.  we have to manually pass null for those params which are not required always during obj creation

Now i dont want to do validations in the setters rather than constructors
but what if its an immutable object so setters wont help

## Solution 3

- we can make multiple constructors
- Not every language supports multple constructors it will be constructor overloading
- If there are N number of constructor versions and orders for taking the params
- Now the problem is we want a validation but we dont want constructor bloating

## Solution 4

- WHat if rather than passing all teh params in one go and getting confused for the order of the params what if we wrap the params inside a data structure
  so we club or group the values together so that we dont have any constraint on order (hashmap, onordered_map,dict,object)
- What if we take the constructor i pass a key order based data structure
  User (mapper){if(
  mapper.firstName==null or mapper.firstName.length()==0
  )
  {
  throw
  }
  }

- IF somehow someone creates this key value pair based DS object for us we can leverage it and make a very clean class
- Now who can create an object ? we need to define a class for it
- User (builder){

}

- class Builder {

}

- this will contain all the properties of our main User class
- it will create a temporary object before we create our final User
- This builder is not going to use a bloated constructor for Obj Creation instead we are going to use setter based validation in the builder class
- Now because our builder setter have the validations User constructor doesnt need to have any validation
  User(builder){
  this.firstName=builder.firstName
  this.age=builder.age
  <!-- This is much cleaner -->

  }

- class Builder{
  private firstName;
  private age
  private email
  Builder (){

  }
  void setAge(x){
  if(this.x<0) return
  this.age=x;
  }

}
b=new Builder()
b.setFirstName("Sanket)
b.setLastName ("Singh)
b.age(27)
u=new User(b)

## Solution 5 (Slightly fancy)

- class Builder{
  Builder(){}
  User build(){
  return new User()
  }
  }
  now b=new Builder()
  b.setFirstName("Sanket)
  u=b.build()

## Solution 6 (SLightly better)

- instead of having the setters void what if we set the types as Buider age(x){
  if(this.x<0) return
  this.age=x
  return this
  }
  Builder lastname(y){
  return this
  }
- now the object construction will look like
  b=new Builder()
  b.age(10).firstName("Sanket).lastName("Singh).build()

## SOlution 7

- The user constructor is public so someone can call it with a null
- If we want to technically ensure this we cna make it private but the issue is the builder will also be not able to call that as well
- We can make
