package il.ac.hit.validation

import scala.util.matching.Regex

trait UserValidation extends Function1[User, ValidationResult] {
  // This method will be used to check whether two different conditions are fulfilled.
  def and(other: UserValidation): UserValidation = {
    (v1: User) => {
      val thisRes: ValidationResult = this.apply(v1)
      val otherRes: ValidationResult = other.apply(v1)

      if (thisRes.isValid && otherRes.isValid) new Valid()
      else new Invalid()
    }
  }

  // This method will be used to check whether at least one of two different conditions is fulfilled.
  def or(other: UserValidation): UserValidation = {
    (v1: User) => {
      val thisRes: ValidationResult = this.apply(v1)
      val otherRes: ValidationResult = other.apply(v1)

      if (thisRes.isValid || otherRes.isValid) new Valid()
      else new Invalid()
    }
  }
}

object UserValidation {
  // This method will be used to check whether all supplied conditions are fulfilled.
//  def all() : UserValidation = {}

  // This method will be used to check whether none of the supplied conditions is fulfilled.
//  def none() : UserValidation = {}

  // This method will be used to check whether the email address ends with “il”
  def emailEndsWithIL : UserValidation = {
    (v1: User) => {
      if (v1.email.endsWith("il")) {
        new Valid()
      }
      else {
        new Invalid()
      }
    }
  }

  // This method will be used to verify the length of the email address is bigger than 10
  def emailLengthBiggerThan10 : UserValidation = {
    (v1: User) => {
        if (v1.email.length > 10) {
          new Valid()
        }
        else {
          new Invalid()
        }
    }
  }

  // This method will be used to verify the length of the password is bigger than 8
  def passwordLengthBiggerThan8 : UserValidation = {
    (v1: User) => {
      if (v1.password.length > 8) new Valid()
      else new Invalid()
    }
  }

  // This method will be used to verify the password includes letters and numbers only
  def passwordIncludesLettersNumbersOnly : UserValidation = {
    (v1: User) => {
      val nonAlphanumericRegex = new Regex("^[^a-zA-Z0-9]+$")

      if (nonAlphanumericRegex.findFirstIn(v1.password).isEmpty) new Valid
      else new Valid
    }
  }

  // This method will be used to verify the password includes the $ character
  def passwordIncludesDollarSign : UserValidation = {
    (v1: User) => {
      if (v1.password.contains('$')) new Valid
      else new Invalid
    }
  }

  // This method will be used to verify the password is different from the username
  def passwordIsDifferentFromUsername : UserValidation = {
    (v1: User) => {
      if (!v1.password.equals(v1.username)) new Valid
      else new Invalid
    }
  }

  // This method will be used to verify the age is bigger than 18
  def ageBiggerThan18 : UserValidation = {
    (v1: User) => {
      if (v1.age > 18) new Valid
      else new Invalid
    }
  }

  // This method will be used to verify the length of the username is bigger than 8
  def usernameLengthBiggerThan8 : UserValidation = {
    (v1: User) => {
      if (v1.username.length > 8) new Valid
      else new Invalid
    }
  }
}
