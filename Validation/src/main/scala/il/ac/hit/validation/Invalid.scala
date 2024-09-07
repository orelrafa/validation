package il.ac.hit.validation

class Invalid extends ValidationResult {
  override def isValid(): Boolean = false
}
