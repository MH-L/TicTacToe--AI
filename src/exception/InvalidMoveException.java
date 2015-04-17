package exception;

public class InvalidMoveException extends Exception {
	public InvalidMoveException(){
		super();
	}

	public InvalidMoveException(String e){
		super(e);
	}

	public InvalidMoveException(Throwable e){
		super(e);
	}
}
