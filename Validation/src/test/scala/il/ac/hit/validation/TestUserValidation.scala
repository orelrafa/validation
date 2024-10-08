package il.ac.hit.validation

import org.scalatest.funsuite._

class TestUserValidation extends AnyFunSuite {

  test("emailEndsWithIL_WORKS") {
    // "emailEndsWithIL" should "be valid if email ends with 'il'
    val user:User = new User("user1", "test@domain.il", "password123", 25)
    val result:ValidationResult = UserValidation.emailEndsWithIL.apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(result.isValid)
  }
  test("emailEndsWithIL_FAILS") {
    // "emailEndsWithIL" should be invalid if email does not end with 'il'
    val user:User = new User("user1", "test@domain.com", "password123", 25)
    val result:ValidationResult = UserValidation.emailEndsWithIL.apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(!result.isValid)
  }
  test("emailLengthBiggerThan10_WORKS") {
    // "emailLengthBiggerThan10" should be valid if email length is greater than 10
    val user:User = new User("user1", "longemail@domain.co.il", "password123", 25)
    val result:ValidationResult = UserValidation.emailLengthBiggerThan10.apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(result.isValid)
  }
  test("emailLengthBiggerThan10_FAILS") {
    // "emailLengthBiggerThan10" should be invalid if email length is less than or equal to 10
    val user:User = new User("user1", "short@il", "password123", 25)
    val result:ValidationResult = UserValidation.emailLengthBiggerThan10.apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(!result.isValid)
  }
  test("passwordLengthBiggerThan8_WORKS") {
    // "passwordLengthBiggerThan8" should be valid if password length is greater than 8
    val user:User = new User("user1", "test@domain.co.il", "password123", 25)
    val result:ValidationResult = UserValidation.passwordLengthBiggerThan8.apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(result.isValid)
  }
  test("passwordLengthBiggerThan8_FAILS") {
    // "passwordLengthBiggerThan8" should be invalid if password length is less or equal to 8
    val user:User = new User("user1", "test@domain.co.il", "short", 25)
    val result:ValidationResult = UserValidation.passwordLengthBiggerThan8.apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(!result.isValid)
  }
  test("passwordIncludesLettersNumbersOnly_WORKS") {
    // "passwordIncludesLettersNumbersOnly" should be valid if password includes letters and numbers only
    val user:User = new User("user1", "test@domain.co.il", "password123", 25)
    val result:ValidationResult = UserValidation.passwordIncludesLettersNumbersOnly.apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(result.isValid)
  }
  test("passwordIncludesLettersNumbersOnly_FAILS") {
    // "passwordIncludesLettersNumbersOnly" should be invalid if password does not include letters and numbers only
    val user:User = new User("user1", "test@domain.co.il", "pass$", 25)
    val result:ValidationResult = UserValidation.passwordIncludesLettersNumbersOnly.apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(!result.isValid)
  }
  test("passwordIncludesDollarSign_WORKS") {
    // "passwordIncludesDollarSign" should be valid if password includes "$"
    val user:User = new User("user1", "test@domain.co.il", "pass$", 25)
    val result:ValidationResult = UserValidation.passwordIncludesDollarSign.apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(result.isValid)
  }
  test("passwordIncludesDollarSign_FAILS") {
    // "passwordIncludesDollarSign" should be invalid if password does not include "$"
    val user:User = new User("user1", "test@domain.co.il", "password123", 25)
    val result:ValidationResult = UserValidation.passwordIncludesDollarSign.apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(!result.isValid)
  }
  test("passwordIsDifferentFromUsername_WORKS") {
    // "passwordIsDifferentFromUsername" should be valid if password is different from the username
    val user:User = new User("user1", "test@domain.co.il", "password123", 25)
    val result:ValidationResult = UserValidation.passwordIsDifferentFromUsername.apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(result.isValid)
  }
  test("passwordIsDifferentFromUsername_FAILS") {
    // "passwordIsDifferentFromUsername" should be invalid if password is the same as username
    val user:User = new User("user1", "test@domain.co.il", "user1", 25)
    val result:ValidationResult = UserValidation.passwordIsDifferentFromUsername.apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(!result.isValid)
  }
  test("ageBiggerThan18_WORKS") {
    // "ageBiggerThan18" should be valid if age is greater than 18
    val user:User = new User("user1", "test@domain.co.il", "password123", 25)
    val result:ValidationResult = UserValidation.ageBiggerThan18.apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(result.isValid)
  }
  test("ageBiggerThan18_FAILS") {
    // "ageBiggerThan18" should be invalid if age is less than or equal 18
    val user:User = new User("user1", "test@domain.co.il", "password123", 18)
    val result:ValidationResult = UserValidation.ageBiggerThan18.apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(!result.isValid)
  }
  test("usernameLengthBiggerThan8_WORKS") {
    // "usernameLengthBiggerThan8" should be valid if username length is bigger than 8
    val user:User = new User("username1", "test@domain.co.il", "password123", 25)
    val result:ValidationResult = UserValidation.usernameLengthBiggerThan8.apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(result.isValid)
  }
  test("usernameLengthBiggerThan8_FAILS") {
    // "usernameLengthBiggerThan8" should be invalid if username length is less or equal to 8
    val user:User = new User("user1", "test@domain.co.il", "password123", 25)
    val result:ValidationResult = UserValidation.usernameLengthBiggerThan8.apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(!result.isValid)
  }
  test("Given_2_valid_When_all_Then_valid") {
    //"all" should "return Valid if all provided validations pass
    val user:User = new User("user1", "test@domain.co.il", "password123", 25)
    val con_A = UserValidation.emailEndsWithIL
    val con_B = UserValidation.passwordLengthBiggerThan8
    val result:ValidationResult = UserValidation.all(con_A, con_B).apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(con_A.apply(user).isValid)
    assert(con_B.apply(user).isValid)
    assert(result.isValid)
  }
  test("Given_2_mixed_validity_When_all_Then_invalid") {
    //"all" should return invalid if any provided validations fails
    val user:User = new User("user1", "test@domain.com", "password123", 25)
    val con_A = UserValidation.emailEndsWithIL
    val con_B = UserValidation.passwordLengthBiggerThan8
    val result:ValidationResult = UserValidation.all(con_A, con_B).apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(!con_A.apply(user).isValid)
    assert(con_B.apply(user).isValid)
    assert(!result.isValid)
  }
  test("Given_2_invalids_When_all_Then_invalid") {
    //"all" should "return Valid if all provided validations pass
    val user:User = new User("user1", "test@domain.com", "pass", 25)
    val con_A = UserValidation.emailEndsWithIL
    val con_B = UserValidation.passwordLengthBiggerThan8
    val result:ValidationResult = UserValidation.all(con_A, con_B).apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(!con_A.apply(user).isValid)
    assert(!con_B.apply(user).isValid)
    assert(!result.isValid)
  }
  test("Given_2_invalids_When_none_Then_valid") {
    //"none" should return valid if none of the provided validations pass
    val user:User = new User("user1", "test@domain.com", "pass", 25)
    val con_A = UserValidation.emailEndsWithIL
    val con_B = UserValidation.passwordLengthBiggerThan8
    val result:ValidationResult = UserValidation.none(con_A, con_B).apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(!con_A.apply(user).isValid)
    assert(!con_B.apply(user).isValid)
    assert(result.isValid)
  }
  test("Given_mixed_validity_When_none_Then_not_valid") {
    //"none" should return valid if none of the provided validations pass
    val user:User = new User("user1", "test@domain.co.il", "pass", 25)
    val con_A = UserValidation.emailEndsWithIL
    val con_B = UserValidation.passwordLengthBiggerThan8
    val result:ValidationResult = UserValidation.none(con_A, con_B).apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(con_A.apply(user).isValid)
    assert(!con_B.apply(user).isValid)
    assert(!result.isValid)
  }
  test("Given_2_valid_When_none_Then_not_valid") {
    //"none" should return invalid if any of the provided validations pass
    val user:User = new User("user1", "test@domain.co.il", "passwordlong", 25)
    val con_A = UserValidation.emailEndsWithIL
    val con_B = UserValidation.passwordLengthBiggerThan8
    val result:ValidationResult = UserValidation.none(con_A, con_B).apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(con_A.apply(user).isValid)
    assert(con_B.apply(user).isValid)
    assert(!result.isValid)
  }

  test("emailEndsWithIL_AND_emailLengthBiggerThan10_WORKS (T & T)") {
    // This test should succeed because the email ends with il and is greater than 10
    val user: User = new User("user1", "test@domain.il", "password123", 25)
    val result: ValidationResult = UserValidation.emailEndsWithIL
      .and(UserValidation.emailLengthBiggerThan10)
      .apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(result.isValid)
  }
  test("emailEndsWithIL_AND_emailLengthBiggerThan10_FAILS (F & T)") {
    // This test should fail because the email does not end with 'il'
    val user: User = new User("user1", "test@domain.com", "password123", 25)
    val result: ValidationResult = UserValidation.emailEndsWithIL
      .and(UserValidation.emailLengthBiggerThan10)
      .apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(!result.isValid)
  }
  test("emailEndsWithIL_AND_emailLengthBiggerThan10_FAILS (T & F)") {
    // This test should fail because the email is not greater than 10
    val user: User = new User("user1", "test@.il", "password123", 25)
    val result: ValidationResult = UserValidation.emailEndsWithIL
      .and(UserValidation.emailLengthBiggerThan10)
      .apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(!result.isValid)
  }
  test("emailEndsWithIL_AND_emailLengthBiggerThan10_FAILS (F & F)") {
    // This test should fail because the email does not end with 'il' and is not greater than 10
    val user: User = new User("user1", "test@.com", "password123", 25)
    val result: ValidationResult = UserValidation.emailEndsWithIL
      .and(UserValidation.emailLengthBiggerThan10)
      .apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(!result.isValid)
  }

  test("emailEndsWithIL_OR_emailLengthBiggerThan10_WORKS (T & T)") {
    // This test should pass because the email length is greater than 10, and it ends with 'il'
    val user: User = new User("user1", "test@domain.il", "password123", 25)
    val result: ValidationResult = UserValidation.emailEndsWithIL
      .or(UserValidation.emailLengthBiggerThan10)
      .apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(result.isValid)
  }
  test("emailEndsWithIL_OR_emailLengthBiggerThan10_WORKS (F & T)") {
    // This test should pass because the email length is greater than 10, even though it doesn't end with 'il'
    val user: User = new User("user1", "test@domain.com", "password123", 25)
    val result: ValidationResult = UserValidation.emailEndsWithIL
      .or(UserValidation.emailLengthBiggerThan10)
      .apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(result.isValid)
  }
  test("emailEndsWithIL_OR_emailLengthBiggerThan10_WORKS (T & F)") {
    // This test should pass because it ends with 'il' even though it's shorter than 10
    val user: User = new User("user1", "test@il", "password123", 25)
    val result: ValidationResult = UserValidation.emailEndsWithIL
      .or(UserValidation.emailLengthBiggerThan10)
      .apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(result.isValid)
  }
  test("emailEndsWithIL_OR_emailLengthBiggerThan10_FAILS (F & F)") {
    // This test should fail because the email does not end with 'il' and is shorter than 10 characters
    val user: User = new User("user1", "short@com", "password123", 25)
    val result: ValidationResult = UserValidation.emailEndsWithIL
      .or(UserValidation.emailLengthBiggerThan10)
      .apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(!result.isValid)
  }
  test("empty_user_emailEndsWithIL_OR_emailLengthBiggerThan10_FAILS") {
    // This test should fail because the email does not end with 'il' and is shorter than 10 characters
    val user: User = new User("", "", "", 0)
    val result: ValidationResult = UserValidation.emailEndsWithIL
      .or(UserValidation.emailLengthBiggerThan10)
      .apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(!result.isValid)
  }
  test("empty_user_emailEndsWithIL_AND_emailLengthBiggerThan10_FAILS (F & F)") {
    // This test should fail because the email does not end with 'il' and is not greater than 10
    val user: User = new User("", "", "", 0)
    val result: ValidationResult = UserValidation.emailEndsWithIL
      .and(UserValidation.emailLengthBiggerThan10)
      .apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(!result.isValid)
  }
  test("empty_user_Given_2_invalids_When_all_Then_invalid") {
    //"all" should return Invalid if any of provided validations fail
    val user:User = new User("", "", "", 25)
    val con_A = UserValidation.emailEndsWithIL
    val con_B = UserValidation.passwordLengthBiggerThan8
    val result:ValidationResult = UserValidation.all(con_A, con_B).apply(user)

    if (!result.isValid) {
      println(s"Validation failed: ${result.getReason.getOrElse("Unknown reason")}")
    } else {
      println(s"Validation succeeded: ${result.getReason.getOrElse("Unknown reason")}")
    }

    assert(!con_A.apply(user).isValid)
    assert(!con_B.apply(user).isValid)
    assert(!result.isValid)
  }
}
