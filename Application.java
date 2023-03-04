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