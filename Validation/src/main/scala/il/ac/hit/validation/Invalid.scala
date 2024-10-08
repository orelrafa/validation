package il.ac.hit.validation

/**
 * Represents a failed validation result.
 *
 * @param reason The reason for the validation failure.
 *
 * This class extends [[ValidationResult]] and overrides `isValid` to return `false`.
 * It also provides a reason for the failure through the `getReason` method.
 */
class Invalid(reason: String) extends ValidationResult {

  /**
   * Indicates that the validation has failed.
   *
   * @return `false`, indicating the validation failed.
   */
  override def isValid: Boolean = false

  /**
   * Provides the reason for the failed validation.
   *
   * @return An `Option` containing the reason for failure.
   */
  override def getReason: Option[String] = Some(reason)
}
