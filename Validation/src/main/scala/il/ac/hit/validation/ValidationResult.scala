package il.ac.hit.validation

/**
 * A trait representing the result of a validation check.
 *
 * This trait is designed to be implemented by classes that represent either a valid or invalid validation result.
 * It provides methods to check whether the validation was successful and to retrieve the reason for the validation result.
 */
trait ValidationResult {

  /**
   * Indicates whether the validation was successful or not.
   *
   * @return `true` if the validation passed, `false` otherwise.
   */
  def isValid: Boolean

  /**
   * Retrieves the reason for the validation result.
   *
   * @return An `Option` containing the reason for the validation. This will be `None` if no reason is provided.
   */
  def getReason: Option[String]
}
