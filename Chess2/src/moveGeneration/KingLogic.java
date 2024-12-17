package moveGeneration;

import board.Position;
import system.BBO;

public class KingLogic {
	
	public static long[] moveBoards;

//Public Methods
	public void initializeAll() {
		KingLogic.moveBoards = generateMoveBoards();
	}
	
	public long getCaptures(int square, Position position) {
		long capturablePieces = position.whiteToPlay ? position.blackPieces : position.whitePieces;
		return moveBoards[square] & capturablePieces;
	}
	
	public long getQuietMoves(int square, Position position) {
		return moveBoards[square] & ~position.occupancy;
	}
	
	public long getKingAttacks(int square) {
		return moveBoards[square];
	}
	
	public long generateCastles(int square, Position position) {
		return position.whiteToPlay ? generateWhiteCastles(square, position) : generateBlackCastles(square, position);
	}

//Private Helper Methods
	private long generateWhiteCastles(int square, Position position) {
		long result = 0L;
		//queenside
		if (((position.castleRights & (1 << 3)) != 0) &&
				!BBO.squareHasPiece(position.occupancy, 1) &&
				!BBO.squareHasPiece(position.occupancy, 2) &&
				!BBO.squareHasPiece(position.occupancy, 3) &&
				!BBO.squareHasPiece(position.blackAttackMap, 2) &&
				!BBO.squareHasPiece(position.blackAttackMap, 3) &&
				!BBO.squareHasPiece(position.blackAttackMap, 4))
			result |= (1L << 2);
		//kingside
		if (((position.castleRights & (1 << 2)) != 0) &&
				!BBO.squareHasPiece(position.occupancy, 5) &&
				!BBO.squareHasPiece(position.occupancy, 6) &&
				!BBO.squareHasPiece(position.blackAttackMap, 4) &&
				!BBO.squareHasPiece(position.blackAttackMap, 5) &&
				!BBO.squareHasPiece(position.blackAttackMap, 6))
			result |= (1L << 6);
		return result;
	}
	
	private long generateBlackCastles(int square, Position position) {
		long result = 0L;
		//queenside
		if (((position.castleRights & (1 << 1)) != 0) &&
				!BBO.squareHasPiece(position.occupancy, 57) &&
				!BBO.squareHasPiece(position.occupancy, 58) &&
				!BBO.squareHasPiece(position.occupancy, 59) &&
				!BBO.squareHasPiece(position.blackAttackMap, 58) &&
				!BBO.squareHasPiece(position.blackAttackMap, 59) &&
				!BBO.squareHasPiece(position.blackAttackMap, 60))
			result |= (1L << 58);
		//kingside
		if (((position.castleRights & (1 << 0)) != 0) &&
				!BBO.squareHasPiece(position.occupancy, 61) &&
				!BBO.squareHasPiece(position.occupancy, 62) &&
				!BBO.squareHasPiece(position.blackAttackMap, 60) &&
				!BBO.squareHasPiece(position.blackAttackMap, 61) &&
				!BBO.squareHasPiece(position.blackAttackMap, 62))
			result |= (1L << 62);
		return result;
	}
	
	private long[] generateMoveBoards() {
		long[] moveBoards = new long[64];
		for (int i = 0; i < 64; i++) {
			moveBoards[i] = generateMoveBoard(i);
		}
		return moveBoards;
	}
	
	
	/**
	 * Returns a bitboard of squares that the king attacks at a given position
	 * @Param square an integer between 0 and 63
	 * @Return a bitboard of squares the king attacks
	 */
	private long generateMoveBoard(int square) {
		assert square < 64;
		assert square > 0;
		int rankLoc = square / 8;
		int fileLoc = square % 8;
		
		long moveBoard = 0;
		
		int testRank = rankLoc + 1;
		int testFile = fileLoc;
		if (testRank < 8)
			moveBoard |= 1L << (8 * testRank + testFile);
		
		testRank = rankLoc + 1;
		testFile = fileLoc + 1;
		if (testRank < 8 && testFile < 8)
			moveBoard |= 1L << (8 * testRank + testFile);
		
		testRank = rankLoc;
		testFile = fileLoc + 1;
		if (testFile < 8)
			moveBoard |= 1L << (8 * testRank + testFile);
		
		testRank = rankLoc - 1;
		testFile = fileLoc + 1;
		if (testRank >= 0 && testFile < 8)
			moveBoard |= 1L << (8 * testRank + testFile);
			
		testRank = rankLoc - 1;
		testFile = fileLoc;
		if (testRank >= 0)
			moveBoard |= 1L << (8 * testRank + testFile);

		testRank = rankLoc - 1;
		testFile = fileLoc - 1;
		if (testRank >= 0 && testFile >= 0)
			moveBoard |= 1L << (8 * testRank + testFile);
		
		testRank = rankLoc;
		testFile = fileLoc - 1;
		if (testFile >= 0)
			moveBoard |= 1L << (8 * testRank + testFile);
		
		testRank = rankLoc + 1;
		testFile = fileLoc - 1;
		if (testRank < 8 && testFile >= 0)
			moveBoard |= 1L << (8 * testRank + testFile);
		
		return moveBoard;
	}
	
}

/*LEGACY CODE
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * 
 * */