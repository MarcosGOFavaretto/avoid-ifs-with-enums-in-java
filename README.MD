# Replacing If-Else Statements with Enums in Java
That project shows a simple way of how `if-else` statements can be changed with enums in Java. On that tutorial, we will use a simple application which was written solely using `if-else` statements, and remove all of them by creating an enum with constructor.

## The example program.
The Java program we are going to use for this tutorial is a simple command-line application that returns a HTTP status code after the user provides a required message.

For example, if we execute it with the argument "SUCCESS", then its return is "200".

## The old code.
Our application is composed of several `if-else` statements. Therefore, basing on user's message, it is tested on four conditions to define the corresponding HTTP status code and return it to the user

```java
public class Application {

	public static void main(String[] args) {

		if (args.length < 1) {
			System.out.println("No args provided!");
			System.exit(0);
		}

		String providedMessage = args[0];
		int returnCode = 0;

		if ("SUCCESS".equalsIgnoreCase(providedMessage)) {
			returnCode = 200;
		} else if ("CREATED".equalsIgnoreCase(providedMessage)) {
			returnCode = 201;
		} else if ("MOVED".equalsIgnoreCase(providedMessage)) {
			returnCode = 301;
		} else if ("UNAUTHORIZED".equalsIgnoreCase(providedMessage)) {
			returnCode = 401;
		}

		System.out.println("Your HTTP code is: " + returnCode);
	}
}
```

Running that Java program:
```
marcos@Desktop: javac Application.java
marcos@Desktop: java Application success
Your HTTP code is: 200.
```

## The refactored code.
To make that possible, we can use a property of java ENUMS: the constructors. Using it, for each value provided for our ENUM, we can define a default return value.

### Writing our ENUM.
The first change to be done is define our enum class.

```java
enum HttpCode {

	SUCCESS,
	CREATED,
	MOVED,
	UNAUTHORIZED;

}
```

As much as the class is declared, we are not allowe to use it for replace our `if-else` statements, since Java will not know the code that needs to be returned after the instantiation of our enum `HttpCode`.

This can be ajusted very simply, by defining a constructor for `HttpCode`.

```java
enum HttpCode {

	SUCCESS(200),
	CREATED(201),
	MOVED(301),
	UNAUTHORIZED(401);

	private int code;

	HttpCode(int code) {
		this.code = code;
	}

	public int getCode(){
		return this.code;
	}
}
```

Notice that a field `code` was created inside our enum class. Since each options of `HttpCode` has a default status defined for the contructor, after a instantiation using the method `HttpCode.valueOf(String message)`, one of the default values will be stored at the field `code`, which can be obtained by the method `HttpCode.getCode()`.

### Adjusting the main method.

Now that we have an enum class, we just need to change our original `main` code to use it.

```java
public static void main(String[] args) {

	if (args.length < 1) {
		System.out.println("No args provided!");
		System.exit(0);
	}

	String providedMessage = args[0];
	HttpCode httpStatusCode = HttpCode.valueOf(providedMessage.toUpperCase());

	System.out.println("Your HTTP code is: " + httpStatusCode.getCode());
}
```

Done! Now, our application can return for user a HTTP status code for a provided message from user. The following code shows our entire class, already refactored.

```java
public class Application {

	public static void main(String[] args) {

		if (args.length < 1) {
			System.out.println("No args provided!");
			System.exit(0);
		}

		String providedMessage = args[0];
		HttpCode httpStatusCode = HttpCode.valueOf(providedMessage.toUpperCase());

		System.out.println("Your HTTP code is: " + httpStatusCode.getCode());
	}
}

enum HttpCode {

	SUCCESS(200),
	CREATED(201),
	MOVED(301),
	UNAUTHORIZED(401);

	private int code;

	HttpCode(int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}
}
```

Running that Java program:
```
marcos@Desktop: javac Application.java
marcos@Desktop: java Application UNAUTHORIZED
Your HTTP code is: 401.
```

The first thing we can think on that case is: "okay, I removed my statements, but I also improved the quantity of lines of my code!". In the truth, it seems that our application is much bigger, but on real cases, that can decrease the quantity of lines, since the ENUM can be reused on another parts of our code. Also, makes the code more readable, since the if-else structures, with several validations, ceases to exist, and Java starts to do this verification for us.

Another good thing is that we can add more options at enums's class without the need of change at all occurrences of status code validations, giving it only exists in the enum that was created.

## Conclusion
This is a simple, and not that much applicable, example. However, it can demonstrate a better way to use enums. It's also important to note: do not change every `if-else` statement you see without think on consequences of it. This is an idea of how to do things on other way, every scenario is different between them, agreed ;D?

That's all!
Please, leave your suggestions, corrections and tests on comments. Thank you for reading :D.