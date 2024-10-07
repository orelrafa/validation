package il.ac.hit.validation

class Valid(reason: String) extends ValidationResult {
  override def isValid: Boolean = true
  override def getReason: Option[String] = Some(reason)
}
