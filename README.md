# custom-validation-messages-1029
Example repo to show a Thymeleaf SpringBoot problem

## Build and Run

$ ./mvnw spring-boot:run

Go to http://localhost:8080

### Howto

* If you fail to select CAR as captcha value you are supposed to see a validation error message.
* If your input has less than 3 characters you see a hibernate validation error that is properly translated.
