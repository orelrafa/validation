package il.ac.hit.validation

/**
 * Trait representing a validation logic for a user. It extends the `Function1` trait, allowing
 * the validation to be applied as a function, which takes a `User` and returns a `ValidationResult`.
 */
trait UserValidation extends Function1[User, ValidationResult] {

  /**
   * Combines this validation with another validation using logical AND.
   *
   * @param other The other `UserValidation` to combine with.
   * @return A new `UserValidation` that passes only if both validations are successful.
   *         If both validations fail, their failure reasons are combined.
   *         If one fails, its failure reason is returned.
   */
  def and(other: UserValidation): UserValidation = {
    (v1: User) => {
      // Apply both this validation and the other validation to the user.
      val thisRes = this.apply(v1)
      val otherRes = other.apply(v1)

      // Combine reasons from both results if necessary.
      lazy val combinedReasons = List(thisRes.getReason, otherRes.getReason).flatten.mkString("; ")

      // Helper function to get the reason from the result, or a default message if no reason is provided.
      def getReasonOrDefault(result: ValidationResult): String = {
        result.getReason.getOrElse("No reason provided")
      }

      // Check the result of both validations and return a new validation result.
      (thisRes.isValid, otherRes.isValid) match {
        case (true, true) => new Valid("Both conditions passed successfully") // Generic success message
        case (false, false) => new Invalid(combinedReasons)
        case (false, true) => new Invalid(getReasonOrDefault(thisRes))
        case (true, false) => new Invalid(getReasonOrDefault(otherRes))
      }
    }
  }

  /**
   * Combines this validation with another validation using logical OR.
   *
   * @param other The other `UserValidation` to combine with.
   * @return A new `UserValidation` that passes if at least one of the validations is successful.
   *         If both validations fail, their failure reasons are combined.
   */
  def or(other: UserValidation): UserValidation = {
    (v1: User) => {
      // Apply both this validation and the other validation to the user.
      val thisRes = this.apply(v1)
      val otherRes = other.apply(v1)

      // Combine reasons from both results if necessary.
      lazy val combinedReasons = List(thisRes.getReason, otherRes.getReason).flatten.mkString("; ")

      // Check if at least one of the validations passed and return the corresponding result.
      if (thisRes.isValid || otherRes.isValid) {
        new Valid("At least one condition passed successfully")
      } else {
        new Invalid(combinedReasons) // Both validations failed, combine reasons.
      }
    }
  }
}


object UserValidation {
  /**
   * Validates that all the provided validations succeed.
   *
   * @param validations A sequence of `UserValidation` instances to be validated.
   * @return A `UserValidation` that passes only if all validations are successful.
   *         Failure messages from all failed validations are combined.
   */
  private def validate(validations: Iterable[UserValidation], condition: Boolean)(user: User): ValidationResult = {
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

  /**
   * Validates that all the provided validations succeed.
   *
   * @param validations A sequence of `UserValidation` instances to be validated.
   * @return A `UserValidation` that passes only if all validations are successful.
   *         Failure messages from all failed validations are combined.
   */
  def all(validations: UserValidation*): UserValidation = {
    user: User => validate(validations, condition = true)(user)
  }

  /**
   * Validates that none of the provided validations succeed.
   *
   * @param validations A sequence of `UserValidation` instances to be validated.
   * @return A `UserValidation` that passes only if all validations fail.
   *         Success messages from all passed validations are combined if any succeed.
   */
  def none(validations: UserValidation*): UserValidation = {
    user: User => validate(validations, condition = false)(user)
  }

  // Various specific validation methods below...

  // Helper function for creating a validation
  private def validateCondition(condition: Boolean, successMsg: String, failureMsg: String): ValidationResult = {
    if (condition) new Valid(successMsg) else new Invalid(failureMsg)
  }

  /**
   * Validates that the user's email ends with ".il".
   *
   * @return A `UserValidation` that checks if the user's email ends with ".il".
   */
  def emailEndsWithIL: UserValidation = {
    (user: User) => validateCondition(user.email.endsWith("il"), "Email ends with 'il'", "Email must end with 'il'")
  }

  /**
   * Validates that the length of the user's email is greater than 10 characters.
   *
   * @return A `UserValidation` that checks if the email length is greater than 10.
   */
  def emailLengthBiggerThan10: UserValidation = {
    (user: User) => validateCondition(user.email.length > 10, "Email length is greater than 10", "Email length must be greater than 10")
  }

  /**
   * Validates that the user's password length is greater than 8 characters.
   *
   * @return A `UserValidation` that checks if the password length is greater than 8.
   */
  def passwordLengthBiggerThan8: UserValidation = {
    (user: User) => validateCondition(user.password.length > 8, "Password length is bigger than 8", "Password length must be greater than 8")
  }

  /**
   * Validates that the user's password contains only letters and numbers.
   *
   * @return A `UserValidation` that checks if the password contains only alphanumeric characters.
   */
  def passwordIncludesLettersNumbersOnly: UserValidation = {
    (user: User) => validateCondition(user.password.matches("[a-zA-Z0-9]+"), "Password includes letters and numbers only", "Password must include letters and numbers only")
  }

  /**
   * Validates that the user's password contains the '$' character.
   *
   * @return A `UserValidation` that checks if the password contains a '$' sign.
   */
  def passwordIncludesDollarSign: UserValidation = {
    (user: User) => validateCondition(user.password.contains('$'), "Password includes a '$' sign", "Password must include a '$' character")
  }

  /**
   * Validates that the user's password is different from their username.
   *
   * @return A `UserValidation` that checks if the password is different from the username.
   */
  def passwordIsDifferentFromUsername: UserValidation = {
    (user: User) => validateCondition(!user.password.equals(user.username), "Password is different from username", "Password must be different from username")
  }

  /**
   * Validates that the user's age is greater than 18.
   *
   * @return A `UserValidation` that checks if the user is older than 18.
   */
  def ageBiggerThan18: UserValidation = {
    (user: User) => validateCondition(user.age > 18, "Age is bigger than 18", "Age must be greater than 18")
  }

  /**
   * Validates that the user's username length is greater than 8 characters.
   *
   * @return A `UserValidation` that checks if the username length is greater than 8.
   */
  def usernameLengthBiggerThan8: UserValidation = {
    (user: User) => validateCondition(user.username.length > 8, "Username length is greater than 8", "Username length must be greater than 8")
  }
}
