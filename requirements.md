# Final Project in Functional Programming  
## Validation Library in Scala

> **Note:** Changes to this document may be introduced during meetings until the end of the semester. Ensure your project complies with the final version.

> **Questions:** If you have questions about this document, please post them in the course forum. Follow the forum for updates to ensure accurate interpretations.

### Introduction

Your final project involves developing a Scala library (Scala 2.12), named `validation`. The library should implement the Combinator design pattern and be packaged as `validation.jar`. It will help developers validate new users in software applications.

The project must support **English only** and must include the following components:

- **Package:** `il.ac.hit.validation`
- **Classes, Traits, and Objects:** Must include specific ones described below, but you can add more if needed.
- **Test Code:** Your project will be tested with the sample code provided or something similar.

---

### Project Structure

#### Package: `il.ac.hit.validation`

All components should be part of this package.

##### User Class

The `User` class must define the following properties:

- `username`
- `email`
- `password`
- `age`

Include a primary constructor with these properties in order.

##### ValidationResult Trait

This trait must define two abstract methods:

- **isValid:** Returns `Boolean`.
- **getReason:** Returns `Option[String]`.

##### Valid Class

A concrete class that extends `ValidationResult`.

##### Invalid Class

A concrete class that extends `ValidationResult`.

##### UserValidation Object and Trait

- The `UserValidation` trait extends `Function1[User, ValidationResult]`.
- It must include two public methods to combine validation tests:
  - **and:** Ensures two conditions are met.
  - **or:** Ensures at least one condition is met.

The `UserValidation` object must provide these methods:

- **all:** Ensures all conditions are met (with repeated parameters).
- **none:** Ensures none of the conditions are met (with repeated parameters).

The following methods should also be included, all returning `UserValidation`:

- **emailEndsWithIL:** Checks if the email ends with `.il`.
- **emailLengthBiggerThan10:** Verifies if the email is longer than 10 characters.
- **passwordLengthBiggerThan8:** Verifies if the password is longer than 8 characters.
- **passwordIncludesLettersNumbersOnly:** Ensures the password includes only letters and numbers.
- **passwordIncludesDollarSign:** Verifies the password includes the `$` character.
- **passwordIsDifferentFromUsername:** Ensures the password is not the same as the username.
- **ageBiggerThan18:** Ensures the user is older than 18.
- **usernameLengthBiggerThan8:** Verifies the username is longer than 8 characters.

---

### Code Style

Follow the style guide found [here](https://tinyurl.com/javapoints), with the following adjustments:

1. Use ScalaDoc instead of JavaDoc for comments.
2. For identifiers, replace "Class/Enum/Exception/Interface" with "Class/Object/Exception/Trait."
3. Ignore sections such as Exceptions Handling, Primitive Types, IOStreams, Enum, and Threads.

---

### Submission Guidelines

1. Develop the project using **IntelliJ IDE** (either version) with **JDK 20** and **Scala 2.12**.
2. Create a short (max 60s) video demonstrating your project and explaining the Combinator design pattern. Upload this video to YouTube as an unlisted video.
3. Package your project into a ZIP file (IntelliJ → File → Export → Project to Zip file) and submit it along with the JAR file and a single PDF file (containing all your code).
4. The PDF must include:
   - Team manager’s name, and all team members' details (ID, mobile, email).
   - A clickable link to the YouTube video.
5. The team manager should submit the files to the Moodle assignment box. **Late submissions will not be accepted**.

---

### Testing Example

You can verify your solution with this sample program:

```scala
object Demo {
  def main(args: Array[String]): Unit = {
    val user1: User = new User("haim", "haim.michael@gmail.il", "abc123", 33)

    val result1: ValidationResult = UserValidation.emailEndsWithIL
      .and(UserValidation.emailLengthBiggerThan10).apply(user1)

    println(result1.isValid)

    val result2: ValidationResult = UserValidation.emailEndsWithIL
      .or(UserValidation.emailLengthBiggerThan10).apply(user1)

    println(result2.isValid)
  }
}
```

---

Make sure you follow all the guidelines and instructions provided for the successful completion of the project.
```
