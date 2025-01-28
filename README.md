### Chess engine and GUI WIP
## Features:
 - Bitboard position representation
 - GUI with promotion piece selection, timers, color choice, choice to play against the engine
 - Transposition tables with zobrist hashing
 - Three-fold repetition detection
 - Negamax with alpha beta
 - Iterative deepening (with thread interruption and position restoration based on a timer)
 - Quiessence search
 - Move ordering (Principle Variation -> checks -> capture with MVVLVA -> other)
 - Piece square table position heuristic with tapered eval and game stage detection

## Issues:
 - Known issue with perft transposition tables: Non-destructive for normal gameplay

## Status:
 - Somewhat retired due to seeking to transition to using the Universal Chess Interface (UCI) protocol (in another repo)
