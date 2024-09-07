import il.ac.hit.validation._

object HaimMain {
  def main(args: Array[String]):Unit = {
    val user1:User = new User("haim","haim.michael@gmail.ils","abc123",33)
    // This user has longer email than 10 chars, but is not ending with il
    // so result1 (and) should be false, and result2 (or) should be true
    val result1:ValidationResult = UserValidation.emailEndsWithIL.
      and(UserValidation.emailLengthBiggerThan10).apply(user1)
    println(s"MAIN: ${result1.isValid()}")
    val result2:ValidationResult = UserValidation.emailEndsWithIL.
      or(UserValidation.emailLengthBiggerThan10).apply(user1)
    println(s"MAIN: ${result2.isValid()}")
  }
}
