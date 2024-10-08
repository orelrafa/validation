package il.ac.hit.validation

/**
 * Represents a successful validation result.
 *
 * @param reason The reason for the successful validation.
 *
 * This class extends [[ValidationResult]] and overrides `isValid` to return `true`.
 * It also provides an optional reason for the validation success through the `getReason` method.
 */
class Valid(reason: String) extends ValidationResult {

  /**
   * Indicates that the validation was successful.
   *
   * @return `true`, indicating the validation passed.
   */
  override def isValid: Boolean = true

  /**
   * Provides the reason for the successful validation.
   *
   * @return An `Option` containing the success reason.
   */
  override def getReason: Option[String] = Some(reason)
}
