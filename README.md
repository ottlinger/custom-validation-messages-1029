# custom-validation-messages-1029
Example repo to show a Thymeleaf SpringBoot problem reported as
https://github.com/thymeleaf/thymeleaf/issues/1029 and a StackOverflow-[question](https://stackoverflow.com/questions/79386749/custom-fielderror-error-message-is-not-translated-and-i18n-key-is-shown-instead)

## Build and Run

$ ./mvnw spring-boot:run

Go to http://localhost:8080

### Howto

* i18n works fine:
  * http://localhost:8080/?lang=en
  * http://localhost:8080/?lang=de

#### But ....

* If you fail to select CAR as captcha value you are supposed to see a custom validation error message.
* If your input has less than 3 characters you see a hibernate validation error that is properly translated.
