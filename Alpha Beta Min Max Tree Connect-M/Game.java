import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game {

	static int N = 0; // number of cols and rows
	static int M = 0; // no of disks to be connected continguously
	static int H = 0; // 0 if human starts, 1 if computer starts

	static List<String> shape = new ArrayList<String>();
	static String computerShape = "";
	static String humanShape = "";

	public static void main(String[] args) {
		InputValidation(args);
		//
		// //choose shape for human and computer
		PickShape();
		Gameplay game = new Gameplay(5, 3, humanShape, computerShape, 0);
		game.play();

	}

	public static void InputValidation(String[] args) {

		// check command line args and input validation
		if (args.length > 0) {

			int arg0 = Integer.parseInt(args[0]);
			int arg1 = Integer.parseInt(args[1]);
			int arg2 = Integer.parseInt(args[2]);

			if (arg0 >= 3 && arg0 <= 10) {
				N = arg0;
			} else {
				System.out.println("N must be minimum 3 and maximum 10");
				System.exit(0);
			}

			if (arg1 > 1 && arg1 <= N) {
				M = arg1;
			} else {
				System.out.println("M must be greater than 1 and maximum N");
				System.exit(0);
			}

			if (arg2 == 0 || arg2 == 1) {
				H = arg2;
			} else {
				System.out.println("H must be 0 or 1");
				System.exit(0);
			}
		}

		System.out.println("N = " + N); // debug
		System.out.println("M = " + M); // debug
		System.out.println("H = " + H); // debug
	}

	// chooses shape for human and computer
	public static void PickShape() {
		shape.add("x");
		shape.add("o");

		Random rand = new Random();

		// picks from x or o in shape list and assigns to computerShape
		computerShape = shape.get(rand.nextInt(shape.size()));

		// assigns the other shape to humanShape
		for (String random : shape) {
			if (random != computerShape) {
				humanShape = random;
			}
		}

		System.out.println("The computer will play with the shape " + computerShape);
		System.out.println("The human will play with the shape " + humanShape);
	}
}