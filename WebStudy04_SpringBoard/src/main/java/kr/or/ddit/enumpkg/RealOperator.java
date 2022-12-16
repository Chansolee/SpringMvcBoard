package kr.or.ddit.enumpkg;

// 인터페이스 하나에 하나의 함수만이 존재한다면 -> FuncitonalInterface
// RealOperator가 곧 realOperate이다. -> 람다식 함수표현 가능.
@FunctionalInterface
public interface RealOperator {
	
	// 실제 연산을 수행하는 메소드
	// 계산기의 종류가 달라져도 사용자는 계산기를 이용할 수 있다.
	// 이 realOperate는 계산기에 해당하고 각 계산기의 실제 구현방식은 이 메소드를 구현하는 구현부에서 작성한다.
	public int realOperate(int leftOp, int rightOp);
	
}


/***
 *	$("selector").on('click', function(){
 *
 *	});
 ***/
