package il.ac.hit.validation

trait UserValidation extends Function1[User, ValidationResult] {
  def and(other: UserValidation) : UserValidation = {}
//  This method will be used to check whether two different conditions are fulfilled.

  def or(other: UserValidation) : UserValidation = {}
//  This method will be used to check whether at least one of two different conditions is fulfilled.
}

object UserValidation {
  def all() : UserValidation = {}
//  This method will be used to check whether all supplied conditions are fulfilled.

  def none() : UserValidation = {}
//  This method will be used to check whether none of the supplied conditions is fulfilled.


  def emailEndsWithIL() : UserValidation = {}
//  This method will be used to check whether the email address ends with “il”

  def emailLengthBiggerThan10() : UserValidation = {}
//  This method will be used to verify the length of the email address is bigger than 10

  def passwordLengthBiggerThan8() : UserValidation = {}
//  This method will be used to verify the length of the password is bigger than 8

  def passwordIncludesLettersNumbersOnly() : UserValidation = {}
//  This method will be used to verify the password includes letters and numbers only

  def passwordIncludesDollarSign() : UserValidation = {}
//  This method will be used to verify the password includes the $ character

  def passwordIsDifferentFromUsername() : UserValidation = {}
//  This method will be used to verify the password is different from the username

  def ageBiggerThan18() : UserValidation = {}
//  This method will be used to verify the age is bigger than 18

  def usernameLengthBiggerThan8() : UserValidation = {}
//  This method will be used to verify the length of the username is bigger than 8
}
