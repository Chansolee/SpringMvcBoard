package kr.or.ddit.board.vo;

import java.io.Serializable;

import kr.or.ddit.enumpkg.OperatorType;

public class CalculateVO implements Serializable{
	private int leftOp;
	private int rightOp;
	private OperatorType operator;
	
	public String getExpression() {
		return operator.getExpression(leftOp, rightOp);
	}
	
	public int getLeftOp() {
		return leftOp;
	}
	public void setLeftOp(int leftOp) {
		this.leftOp = leftOp;
	}
	public int getRightOp() {
		return rightOp;
	}
	public void setRightOp(int rightOp) {
		this.rightOp = rightOp;
	}
	public OperatorType getOperator() {
		return operator;
	}
	public void setOperator(OperatorType operator) {
		this.operator = operator;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + leftOp;
		result = prime * result + ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + rightOp;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CalculateVO other = (CalculateVO) obj;
		if (leftOp != other.leftOp)
			return false;
		if (operator != other.operator)
			return false;
		if (rightOp != other.rightOp)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "CalculateVO [leftOp=" + leftOp + ", rightOp=" + rightOp + ", operator=" + operator + "]";
	}
	
	
}
