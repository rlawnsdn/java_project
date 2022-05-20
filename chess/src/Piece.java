
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
	
	void checkMovables()	{ // Console에서 이동 가능한 칸을 미리 체크해보기 (디버깅용)
		for(int i=7; i>=0; i--)
		{
			for(int j=0; j<8; j++)
				System.out.print(Moveable[i][j] ? 'O':'X');
			System.out.println();
		}

	}
}