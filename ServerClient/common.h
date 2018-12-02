#ifndef _COMMON_H_
#define _COMMON_H_

//unity ID: rsalexan

// Name for the queue of messages going to the server.
#define SERVER_QUEUE "/rsalexan-server-queue"

// Name for the queue of messages going to the current client.
#define CLIENT_QUEUE "/rsalexan-client-queue"

// Maximum length for a message in the queue
// (Long enough to return a copy of the whole board)
#define MESSAGE_LIMIT 1024

// Height of the board.
#define BOARD_ROWS 4

// Width of the board.
#define BOARD_COLS 4

// Representation for the board and votes for each candidate.
typedef struct {
  // Define your own representation.
  //Holds the values for the board
  int grid[ BOARD_ROWS ][ BOARD_COLS ]; 
  //x and y coordinates for where the blank is      
  int xblank;
  int yblank;
} Board;

#endif
