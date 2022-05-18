
public abstract class Piece {

	int pos_x, pos_y;
	char color, type;
	boolean Moveable[][] = new boolean[8][8];

	//	pos_x, pos_y:	Position of the Piece. (0~7)
	//					x for row 1~8,	y for column a~h
	//	color:	'b': black,	'w': white
	//  type:	'K': King,	'Q': Queen, 'B': Bishop,
	//			'P': Pawn,	'R': Rook,	'N': Knight
	
	abstract void findMovables(Square[][] board, boolean bqc, boolean bkc, boolean wqc, boolean wkc);
}