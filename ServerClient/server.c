#define _POSIX_SOURCE
#include "common.h"
#include <stdlib.h>
#include <stdbool.h>
#include <unistd.h>
#include <stdio.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <mqueue.h>
#include <errno.h>
#include <string.h>
#include <signal.h>

//unity ID: rsalexan

//To determine how many digits for printing
#define DIGITS 10

// Print out an error message and exit.
static void fail( char const *message ) {
  fprintf( stderr, "%s\n", message );
  exit( 1 );
}

// Instance of Board for the current state of the gmae.
Board brd = {{{1,2,3,4},
              {5,6,7,8},
              {9,10,11,12},
              {13,14,15,0}},
              BOARD_COLS - 1,
              BOARD_ROWS - 1};

//Holds the string form of the board for printing
char board[ MESSAGE_LIMIT ];

//Prints the current board
void printBoard() {
  //Empties the board
  for ( int i = 0; i < MESSAGE_LIMIT; i++ )
    board[ i ] = '\0';
  //Will be used to hold each value
  int num;
  //Used to attach the values to the string
  char numChar[ 2 ];
  //Used to conver ints to chars
  char convert;
  //Loops through the board's rows
  for ( int i = 0; i < BOARD_ROWS; i++ ) {
    //Loops through the board's columns
    for ( int j = 0; j < BOARD_COLS; j++ ) {
      //Sets num to the board's current value
      num = brd.grid[ i ][ j ];
      //Adds a space between columns
      if ( j > 0 )
        strcat( board, " " );
      //If it's the blank, mark it
      if ( num == 0 ) {
        strcat( board, " -" );
      //If it's less than 10 add another space
      //and concatenate to the string
      } else if ( num < DIGITS ) {
        numChar[ 0 ] = ' ';
        convert = num + '0';
        numChar[ 1 ] = convert;
        strcat( board, numChar );
      //If it's greater than 10 concatenate
      //each digit
      } else {
        numChar[ 0 ] = '1';
        num = num - DIGITS;
        convert = num + '0';
        numChar[ 1 ] = convert;
        strcat( board, numChar );
      }
    }
    //Add a newline at the end of each row
    strcat( board, "\n" );
  }
  //strcat( board, "\0" );
  //return board;
}

//Used to print the board at the end of the program
void sigHandler( int sig ) {
  printBoard();
  printf( "\n%s", board );
  exit( 0 );
}

int main( int argc, char *argv[] ) {
  // Remove both queues, in case, last time, this program terminated
  // abnormally with some queued messages still queued.
  mq_unlink( SERVER_QUEUE );
  mq_unlink( CLIENT_QUEUE );

  // Prepare structure indicating maximum queue and message sizes.
  struct mq_attr attr;
  attr.mq_flags = 0;
  attr.mq_maxmsg = 1;
  attr.mq_msgsize = MESSAGE_LIMIT;

  // Make both the server and client message queues.
  mqd_t serverQueue = mq_open( SERVER_QUEUE, O_RDONLY | O_CREAT, 0600, &attr );
  mqd_t clientQueue = mq_open( CLIENT_QUEUE, O_WRONLY | O_CREAT, 0600, &attr );
  if ( serverQueue == -1 || clientQueue == -1 )
    fail( "Can't create the needed message queues" );

  //sig struct
  //(Pulled from signalExample.c)
  struct sigaction act;
  act.sa_handler = sigHandler;
  sigemptyset( &( act.sa_mask ) );
  act.sa_flags = 0;

  // Repeatedly read and process client messages.
  while ( true ) {
    //Used to accept the command
    char buffer[ MESSAGE_LIMIT ];
    //Clears the buffer
    for ( int i = 0; i < MESSAGE_LIMIT; i++ )
      buffer[ i ] = '\0';
    //Used to send the response
    char response[ MESSAGE_LIMIT ];
    //Clears the response
    for ( int i = 0; i < MESSAGE_LIMIT; i++ )
      response[ i ] = '\0';
    //The four values are used for swapping board positions
    int x = brd.xblank;
    int y = brd.yblank;
    int x2 = brd.xblank;
    int y2 = brd.yblank;
    //Called if the user presses ctrl + C
    sigaction( SIGINT, &act, 0 );
    //Receives the command from the client
    mq_receive( serverQueue, buffer, sizeof( buffer ), NULL );
    //Decision structure for commands
    //If it is up and valid, swap
    if ( strcmp( buffer, "up" ) == 0 && brd.yblank < (BOARD_ROWS - 1) ) {
      y2++;
      brd.grid[ y ][ x ] = brd.grid[ y2 ][ x ];
      brd.grid[ y2 ][ x ] = 0;
      brd.yblank = y2;
      strcpy( response, "success\n" );
    //If it is up and invalid, print invalid command
    } else if ( strcmp( buffer, "up" ) == 0 ) {
      strcpy( response, "invalid command\n" );
    //If it is down and valid, swap
    } else if ( strcmp( buffer, "down" ) == 0 && brd.yblank > 0 ) {
      y2--;
      //printf( "%d %d\n", x, y );
      //printf( "%d %d\n", brd.grid[ y ][ x ], brd.grid[ y2 ][ x ] );
      brd.grid[ y ][ x ] = brd.grid[ y2 ][ x ];
      //printf( "%d %d\n", brd.grid[ y ][ x ], brd.grid[ y2 ][ x ] );
      //printf( "%d %d\n", brd.grid[ brd.xblank ][ brd.yblank ], brd.grid[ brd.xblank ][ brd.yblank - 1 ] );
      brd.grid[ y2 ][ x ] = 0;
      brd.yblank = y2;
      //printf( "%d\n", brd.yblank );
      strcpy( response, "success\n" );
    //If it is down and invalid, print invalid command
    } else if ( strcmp( buffer, "down" ) == 0 ) {
      strcpy( response, "invalid command\n" );
    //If it is right and valid, swap
    } else if ( strcmp( buffer, "right" ) == 0 && brd.xblank > 0 ) {
      x2--;
      brd.grid[ y ][ x ] = brd.grid[ y ][ x2 ];
      brd.grid[ y ][ x2 ] = 0;
      brd.xblank = x2;
      strcpy( response, "success\n" );
    //If it is right and invalid, print invalid command
    } else if ( strcmp( buffer, "right" ) == 0 ) {
      strcpy( response, "invalid command\n" );
    //If it is left and valid, swap
    } else if ( strcmp( buffer, "left" ) == 0 && brd.xblank < (BOARD_COLS - 1) ) {
      x2++;
      brd.grid[ y ][ x ] = brd.grid[ y ][ x2 ];
      brd.grid[ y ][ x2 ] = 0;
      brd.xblank = x2;
      strcpy( response, "success\n" );
    //If it is left and invalid, print invalid command
    } else if ( strcmp( buffer, "left" ) == 0 ) {
      strcpy( response, "invalid command\n" );
    //If it is board, print the board
    } else if ( strcmp( buffer, "board" ) == 0 ) {  
      //strcpy( response, "functional" );
      //printf( "%s", board );
      printBoard();
      strcpy( response, board );
    }
    //Sends the response to the client
    mq_send( clientQueue, response, strlen( response ), 0 );

  }

  // The following code isn't reachable right now, but it will be useful
  // once you add support for SIGINT.

  // Close our two message queues (and delete them).
  mq_close( clientQueue );
  mq_close( serverQueue );

  // Delete the message queues, this is for normal server termination.
  mq_unlink( SERVER_QUEUE );
  mq_unlink( CLIENT_QUEUE );

  return 0;
}
