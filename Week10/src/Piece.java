
public abstract class Piece {

	int pos_x, pos_y;
	char color, type;
	boolean Moveable[][] = new boolean[8][8];

	//	pos_x, pos_y:	Position of the Piece. (0~7)
	//					x for row 1~8,	y for column a~h
	//	color:	'b': black,	'w': white
	//  type:	'K': King,	'Q': Queen, 'B': Bishop,
	//			'P': Pawn,	'R': Rook,	'N': Knight
	
	void move(int x, int y) {
		this.pos_x = x;
		this.pos_y = y;
	}
	
	abstract void findMovables(Square[][] board);
}