import il.ac.hit.validation._

object HaimMain {
  def main(args: Array[String]):Unit = {
    val user1:User = new User("haim","haim.michael@gmail.ils","abc123",33)
    // This user has longer email than 10 chars, but is not ending with il
    // so result1 (and) should be false, and result2 (or) should be true
    val result1:ValidationResult = UserValidation.emailEndsWithIL.
      and(UserValidation.emailLengthBiggerThan10).apply(user1)
    println(s"MAIN: ${result1.isValid}")
    val result2:ValidationResult = UserValidation.emailEndsWithIL.
      or(UserValidation.emailLengthBiggerThan10).apply(user1)
    println(s"MAIN: ${result2.isValid}")


    //orel tests
    {
      // "emailEndsWithIL" should "be valid if email ends with 'il'
      println("---Test 1---")
      val user:User = new User("user1", "test@domain.il", "password123", 25)
      val result:ValidationResult = UserValidation.emailEndsWithIL.apply(user)
      println(s"Expected: true | Result: ${result.isValid}")
    }
    {
      // "emailEndsWithIL" should be invalid if email does not end with 'il'
      println("---Test 2---")
      val user:User = new User("user1", "test@domain.com", "password123", 25)
      val result:ValidationResult = UserValidation.emailEndsWithIL.apply(user)
      println(s"Expected: false | Result: ${result.isValid}")
    }
    {
      // "emailLengthBiggerThan10" should be valid if email length is greater than 10
      println("---Test 3---")
      val user:User = new User("user1", "longemail@domain.co.il", "password123", 25)
      val result:ValidationResult = UserValidation.emailLengthBiggerThan10.apply(user)
      println(s"Expected: true | Result: ${result.isValid}")
    }
    {
      // "emailLengthBiggerThan10" should be invalid if email length is less than or equal to 10
      println("---Test 4---")
      val user:User = new User("user1", "short@il", "password123", 25)
      val result:ValidationResult = UserValidation.emailLengthBiggerThan10.apply(user)
      println(s"Expected: false | Result: ${result.isValid}")
    }
    {
      // "passwordLengthBiggerThan8" should be valid if password length is greater than 8
      println("---Test 5---")
      val user:User = new User("user1", "test@domain.co.il", "password123", 25)
      val result:ValidationResult = UserValidation.passwordLengthBiggerThan8.apply(user)
      println(s"Expected: true | Result: ${result.isValid}")
    }
    {
      // "passwordLengthBiggerThan8" should be invalid if password length is less or equal to 8
      println("---Test 6---")
      val user:User = new User("user1", "test@domain.co.il", "short", 25)
      val result:ValidationResult = UserValidation.passwordLengthBiggerThan8.apply(user)
      println(s"Expected: false | Result: ${result.isValid}")
    }
    {
    // "passwordIncludesLettersNumbersOnly" should be valid if password includes letters and numbers only
    println("---Test 7---")
    val user:User = new User("user1", "test@domain.co.il", "password123", 25)
    val result:ValidationResult = UserValidation.passwordIncludesLettersNumbersOnly.apply(user)
    println(s"Expected: true | Result: ${result.isValid}")
    }
    {
      // "passwordIncludesLettersNumbersOnly" should be invalid if password does not include letters and numbers only
      println("---Test 8---")
      val user:User = new User("user1", "test@domain.co.il", "pass$", 25)
      val result:ValidationResult = UserValidation.passwordIncludesLettersNumbersOnly.apply(user)
      println(s"Expected: false | Result: ${result.isValid}")
    }
    {
      // "passwordIncludesDollarSign" should be valid if password includes "$"
      println("---Test 9---")
      val user:User = new User("user1", "test@domain.co.il", "pass$", 25)
      val result:ValidationResult = UserValidation.passwordIncludesDollarSign.apply(user)
      println(s"Expected: true | Result: ${result.isValid}")
    }
    {
      // "passwordIncludesDollarSign" should be invalid if password does not include "$"
      println("---Test 10---")
      val user:User = new User("user1", "test@domain.co.il", "password123", 25)
      val result:ValidationResult = UserValidation.passwordIncludesDollarSign.apply(user)
      println(s"Expected: false | Result: ${result.isValid}")
    }
    {
      // "passwordIsDifferentFromUsername" should be valid if password is different from the username
      println("---Test 11---")
      val user:User = new User("user1", "test@domain.co.il", "password123", 25)
      val result:ValidationResult = UserValidation.passwordIsDifferentFromUsername.apply(user)
      println(s"Expected: true | Result: ${result.isValid}")
    }
    {
      // "passwordIsDifferentFromUsername" should be invalid if password is the same as username
      println("---Test 12---")
      val user:User = new User("user1", "test@domain.co.il", "user1", 25)
      val result:ValidationResult = UserValidation.passwordIsDifferentFromUsername.apply(user)
      println(s"Expected: false | Result: ${result.isValid}")
    }
    {
      // "ageBiggerThan18" should be valid if age is greater than 18
      println("---Test 13---")
      val user:User = new User("user1", "test@domain.co.il", "password123", 25)
      val result:ValidationResult = UserValidation.ageBiggerThan18.apply(user)
      println(s"Expected: true | Result: ${result.isValid}")
    }
    {
      // "ageBiggerThan18" should be invalid if age is less than or equal 18
      println("---Test 13---")
      val user:User = new User("user1", "test@domain.co.il", "password123", 18)
      val result:ValidationResult = UserValidation.ageBiggerThan18.apply(user)
      println(s"Expected: false | Result: ${result.isValid}")
    }
    {
      // "usernameLengthBiggerThan8" should be valid if username length is bigger than 8
      println("---Test 15---")
      val user:User = new User("username1", "test@domain.co.il", "password123", 25)
      val result:ValidationResult = UserValidation.usernameLengthBiggerThan8.apply(user)
      println(s"Expected: true | Result: ${result.isValid}")
    }
    {
      // "usernameLengthBiggerThan8" should be invalid if username length is less or equal to 8
      println("---Test 16---")
      val user:User = new User("user1", "test@domain.co.il", "password123", 25)
      val result:ValidationResult = UserValidation.usernameLengthBiggerThan8.apply(user)
      println(s"Expected: false | Result: ${result.isValid}")
    }
    {
      //"all" should "return Valid if all provided validations pass
      println("---Test 17---")
      val user:User = new User("user1", "test@domain.co.il", "password123", 25)
      val result:ValidationResult = UserValidation.all(UserValidation.emailEndsWithIL, UserValidation.passwordLengthBiggerThan8).apply(user)
      println(s"Expected: true | Result: ${result.isValid}")
    }
    {
      //"all" should return invalid if any provided validations fails
      println("---Test 18---")
      val user:User = new User("user1", "test@domain.com", "password123", 25)
      val result:ValidationResult = UserValidation.all(UserValidation.emailEndsWithIL, UserValidation.passwordLengthBiggerThan8).apply(user)
      println(s"Expected: false | Result: ${result.isValid}")
    }
    {
      //"none" should return valid if none of the provided validations pass
      println("---Test 19---")
      val user:User = new User("user1", "test@domain.com", "pass", 25)
      val result:ValidationResult = UserValidation.none(UserValidation.emailEndsWithIL, UserValidation.passwordLengthBiggerThan8).apply(user)
      println(s"Expected: true | Result: ${result.isValid}")
    }
    {
      //"none" should return invalid if any of the provided validations pass
      println("---Test 20---")
      val user:User = new User("user1", "test@domain.co.il", "pass", 25)
      val result:ValidationResult = UserValidation.none(UserValidation.emailEndsWithIL, UserValidation.passwordLengthBiggerThan8).apply(user)
      println(s"Expected: false | Result: ${result.isValid}")
    }
  }
}
