package kr.or.ddit.enumpkg;

public enum OperatorType {
	
	// enum 클래스
	// 1. 열거형으로 선언된 순서에 따라 0부터 index 값을 가진다.(순차적으로 증가)
	// 2. enum 열거형으로 지정된 상수들은 모두 대문자로 선언한다.
	// 3. 열거형 변수들을 선언한 후 마지막에 세미콜론(;)을 찍지 않는다.
	// 4. 상수와 특정 값을 연결시킬경우 마지막에 세미콜론(;)을 붙여줘야한다.
	
	// 각 선언한 상수의 속성에 +, 익명객체를 담았다.
	// 하나의 기능만 수행하는 인터페이스인데 각 연산을 단발성으로 사용하기 때문에 
	// +,-,*,/ 를 수행하는 객체를 4개나 만들어주면 불편하다.
	// 따라서 단발성으로 해당 역할을 수행하는 메소드를 재정의해서 사용
	PLUS('+', new RealOperator() {
		@Override
		public int realOperate(int leftOp, int rightOp) {
			return leftOp + rightOp;
		}
	}) ,
	MINUS('-', new RealOperator() {
		public int realOperate(int leftOp, int rightOp) {
			return leftOp - rightOp;
		}
	}) ,
	// 하나의 인터페이스에 하나의 메소드만 정의되어있으면 @FunctionalInterface 라고 정의함
	// 이런 인터페이스는 람다식으로써 사용할 수 있다.
	// 람다형식 : (파라미터 블록) -> {함수 연산 수행 블록} 바로 return같은 한줄연산이라면 return 과 {} 를 생략할 수 있다.
	MULTIPLY('*', (leftOp, rightOp)->{ return leftOp * rightOp; }) ,
	// return같은 한줄연산이라면 return 과 {} 를 생략할 수 있다.
	DIVIDE('/', (leftOp, rightOp)->leftOp / rightOp),
	
	REMAINDER('%',(leftOp, rightOp) -> leftOp % rightOp);
	;
	
	// enum 클래스의 생성자 -> jvm이 만들어준다.
	private OperatorType(char sign, RealOperator operator) {
		this.sign = sign;
		this.operator = operator;
	}

	// 선언된 상수의 속성을 담고 출력하기 위한 변수 선언
	// sign에는 +,-,*,/ 가 담긴다.
	// operator에는 익명 객체로 수행한 메소드 결과가 담긴다. -> return leftOp + rightOp
	private char sign;
	private RealOperator operator;
	
	// +,-,*,/ 를 외부에서 받아오기 위한 getter
	public char getSign() {
		return sign;
	}
	
	// 인터페이스를 바로 사용해서 연산을 수행 (이 코드에서는 실행되지 않음)
	public int operate(int leftOp, int rightOp) {
		return operator.realOperate(leftOp, rightOp);
	}
	
	// 문자열 포맷을 위한 String 타입 변수, 
	private static final String PATTERN = "%d %s %d = %d";
	
	public String getExpression(int leftOp, int rightOp) {
		return String.format(PATTERN, leftOp, sign, rightOp, operate(leftOp, rightOp));
	}
}
