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

//unity ID: rsalexan

// Print out an error message and exit.
static void fail( char const *message ) {
  fprintf( stderr, "%s\n", message );
  exit( 1 );
}

int main( int argc, char *argv[] ) {

  // Prepare structure indicating maximum queue and message sizes.
  //(Pulled from server.c)
  struct mq_attr attr;
  attr.mq_flags = 0;
  attr.mq_maxmsg = 1;
  attr.mq_msgsize = MESSAGE_LIMIT;

  //Creates the two queues for communication
  mqd_t serverQueue = mq_open( SERVER_QUEUE, O_WRONLY | O_CREAT, 0600, &attr );
  mqd_t clientQueue = mq_open( CLIENT_QUEUE, O_RDONLY | O_CREAT, 0600, &attr );
  //Creates the buffer for command line instructions
  char buffer[ MESSAGE_LIMIT + 1 ];
  //Will hold the ouput
  char output[ MESSAGE_LIMIT + 1 ];
  //fgets( buffer, MESSAGE_LIMIT, argv[ 1 ] );
  //Copies the command line argument into the buffer
  strcpy( buffer, argv[ 1 ] );

  //Checks if there is a command and a valid one
  if ( argc != 2 || (strcmp( buffer, "up" ) != 0 && strcmp( buffer, "down" ) != 0
     && strcmp( buffer, "right" ) != 0 && strcmp( buffer, "left" ) != 0 
     && strcmp( buffer, "board" ) != 0 )) {
    printf( "invalid command\n" );
  }
  //Sends the command to the server
  mq_send( serverQueue, buffer, strlen( buffer ), 0 );
  //Receives the response from the server
  int len = mq_receive( clientQueue, output, sizeof( output ), NULL );
  //Prints the output to the terminal
  //(Pulled from mqReceiver.c)
  if ( len >= 0 ) {
    for ( int i = 0; i < len; i++ )
      printf( "%c", output[ i ] );
  } else
    fail( "Unable to receive message." );

  //Closes the queues
  mq_close( serverQueue );
  mq_close( clientQueue );

  return 0;
}
