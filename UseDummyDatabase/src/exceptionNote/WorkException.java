package exceptionNote;
import java.util.Random;
class WorkException extends Exception{
	
	public static void main(String[] args) {
		AkiraAtwork akiraAtWork = new AkiraAtwork();
		akiraAtWork.working();
	}
	
	void handle(int t) {
		System.out.println("The leader asked weijing to finish the rest of the task instead of Akira.");
		System.out.println("weijing started doing the task.");
		for(int i=0; i<t; i++) {
			System.out.println("weijing's doing the job...ZZZ...");
		}
		System.out.println("weijing has done all the rest of the tasks!");
		System.out.println("The task of the team has been finished!");
	}

	void canNotHandle() {
		System.out.println("Akira: I tried my best, but I guess I can't finish the task today.");
		System.out.println("Leader:Oh! I don't know what to do! I can't handle this.");
	}
}

class PlayException extends WorkException{
	void handle(int t) {
		System.out.println("Leader: Oh shit, Akira's spent the whole day on playing java.");
		System.out.println("The leader fired Akira");
		super.handle(t);
	}
}

class PainException extends WorkException{
	void handle(int t) {
		System.out.println("Akira started having a stomachache");
		super.handle(t);
	}

	void canNotHandle() {
		System.out.println("Akira: What a pain...");
		super.canNotHandle();
	}
}

class SnackException extends WorkException{
	void handle(int t) {
		System.out.println("Akira has been snacking for the whole moring.");
		System.out.println("The leader fired Akira");
		super.handle(t);
	}

	void canNotHandle() {
		System.out.println("Akira: What a pain...");
		super.canNotHandle();
	}
}

class PhoneException extends WorkException{
	void handle(int t) {
		System.out.println("Akira has been checking her cellphone for the whole day;");
		System.out.println("The leader fired Akira");
		super.handle(t);
	}
}
class NapException extends WorkException{
	void handle(int t) {
		System.out.println("Akira has been taking a nap the whole afternoon;");
		System.out.println("The leader fired Akira");
		super.handle(t);
	}
}

class AkiraAtwork {
	public void working() {
		Random myRandom = new Random();
		System.out.println("Akira started doing the task.");
		System.out.println("Akira's doing the job...ZZZ...");
		for(int i=0; i<9; ) {
			i++;
			int number1= myRandom.nextInt(20)+1;
			int number2 =myRandom.nextInt(2)+1;
			switch(number1) {
			case 1:
				try {
					throw new PlayException();
				}
				catch(PlayException p) {
					if(number2==1) {
						p.handle(10-i);
						return;
					}else {
						p.canNotHandle();
						p.printStackTrace();
						return;
					}
				}

			case 2:
				try {
					throw new PainException();
				}
				catch(PainException p) {
					if(number2==1) {
						p.handle(10-i);
						return;
					}else {
						p.canNotHandle();
						p.printStackTrace();
						return;
					}
				}
			case 3:
				try {
					throw new SnackException();
				}
				catch(SnackException s) {
					if(number2==1) {
						s.handle(10-i);
						return;
					}else {
						s.canNotHandle();
						s.printStackTrace();
						return;
					}
				}

			case 4:
				try {
					throw new PhoneException();
				}
				catch(PhoneException p) {
					if(number2==1) {
						p.handle(10-i);
						return;
					}else {
						p.canNotHandle();
						p.printStackTrace();
						return;
					}

				}

			case 5:
				try {
					throw new  NapException();
				}
				catch(NapException n) {
					if(number2==1) {
						n.handle(10-i);
						return;
					}else {
						n.canNotHandle();
						n.printStackTrace();
						return;
					}

				}
			default:
				System.out.println("Akira's doing the job...ZZZ...");
				if(i==9) {
					System.out.println("Akira has finished all the task today.\nThis must be the first time.\nWhat a surprise! ");
				}
			}
		}

	}
	
}


//
//public class ExceptionTest1 {
//}