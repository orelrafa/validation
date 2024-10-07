package il.ac.hit.validation

trait UserValidation extends Function1[User, ValidationResult] {
  // This method will be used to check whether two different conditions are fulfilled.
  def and(other: UserValidation): UserValidation = {
    (v1: User) => {
      val thisRes = this (v1)
      val otherRes = other(v1)
      // combined reasons of failure, computed only when needed.
      lazy val combinedReasons = List(thisRes.getReason, otherRes.getReason).flatten.mkString("; ")

      // Helper function to avoid repetition for getting reasons
      def getReasonOrDefault(result: ValidationResult): String = {
        result.getReason.getOrElse("No reason provided")
      }

      (thisRes.isValid, otherRes.isValid) match {
        case (true, true) =>
          new Valid(combinedReasons) // Both are valid
        case (false, false) =>
          new Invalid(combinedReasons) // Both are invalid
        case (false, true) =>
          new Invalid(getReasonOrDefault(thisRes)) // Only this is invalid
        case (true, false) =>
          new Invalid(getReasonOrDefault(otherRes)) // Only other is invalid
      }
    }
  }

  // This method will be used to check whether at least one of two different conditions is fulfilled.
  def or(other: UserValidation): UserValidation = {
    (v1: User) => {
      val thisRes = this (v1)
      val otherRes = other(v1)
      lazy val combinedReasons = List(thisRes.getReason, otherRes.getReason).flatten.mkString("; ")

      if (thisRes.isValid || otherRes.isValid) {
        new Valid(combinedReasons)
      } else {
        new Invalid(combinedReasons)
      }
    }
  }
}

object UserValidation {
  // This method will be used to check whether all supplied conditions are fulfilled.
  private def validate(validations: Seq[UserValidation], condition: Boolean)(user: User): ValidationResult = {
    val results = validations.map(v => v(user))

    // Separate the successful and failed validations
    val successes = results.filter(_.isValid)
    val failures = results.filterNot(_.isValid)

    (condition, successes.isEmpty, failures.isEmpty) match {
      //using All, if there are no failures then all checks succeeded
      case (true, _, true) => new Valid("All validations succeeded as expected")
      //using None, if there are no successes then all checks failed.
      case (false, true, _) => new Valid("All validations failed as expected")
      //using All, if there are some failures return invalid with what failed
      case (true, _, false) => new Invalid(failures.flatMap(_.getReason).mkString("; "))
      //Using None, if there are some success print what succeeded
      case (false, false, _) => new Invalid(successes.flatMap(_.getReason).mkString("; "))
    }
  }

  def all(validations: UserValidation*): UserValidation = {
    user: User => validate(validations, condition = true)(user)
  }

  def none(validations: UserValidation*): UserValidation = {
    user: User => validate(validations, condition = false)(user)
  }

  // Helper function for creating a validation
  private def validateCondition(condition: Boolean, successMsg: String, failureMsg: String): ValidationResult = {
    if (condition) new Valid(successMsg) else new Invalid(failureMsg)
  }

  // This method will be used to check whether the email address ends with “il”
  def emailEndsWithIL: UserValidation = {
    (user: User) => validateCondition(user.email.endsWith("il"), "Email ends with 'il'", "Email must end with 'il'")
  }

  // This method will be used to verify the length of the email address is bigger than 10
  def emailLengthBiggerThan10: UserValidation = {
    (user: User) => validateCondition(user.email.length > 10, "Email length is greater than 10", "Email length must be greater than 10")
  }

  // This method will be used to verify the length of the password is bigger than 8
  def passwordLengthBiggerThan8: UserValidation = {
    (user: User) => validateCondition(user.password.length > 8, "Password length is bigger than 8", "Password length must be greater than 8")
  }

  // This method will be used to verify the password includes letters and numbers only
  def passwordIncludesLettersNumbersOnly: UserValidation = {
    (user: User) => validateCondition(user.password.matches("[a-zA-Z0-9]+"), "Password includes letters and numbers only", "Password must include letters and numbers only")
  }

  // This method will be used to verify the password includes the $ character
  def passwordIncludesDollarSign: UserValidation = {
    (user: User) => validateCondition(user.password.contains('$'), "Password includes a '$' sign", "Password must include a '$' character")
  }

  // This method will be used to verify the password is different from the username
  def passwordIsDifferentFromUsername: UserValidation = {
    (user: User) => validateCondition(!user.password.equals(user.username), "Password is different from username", "Password must be different from username")
  }

  // This method will be used to verify the age is bigger than 18
  def ageBiggerThan18: UserValidation = {
    (user: User) => validateCondition(user.age > 18, "Age is bigger than 18", "Age must be greater than 18")
  }

  // This method will be used to verify the length of the username is bigger than 8
  def usernameLengthBiggerThan8: UserValidation = {
    (user: User) => validateCondition(user.username.length > 8, "Username length is greater than 8", "Username length must be greater than 8")
  }
}
